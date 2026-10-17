package com.example.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.model.AnalysisResult
import com.example.data.model.ChecklistItem
import com.example.data.model.Language
import com.example.data.model.ProfileType
import com.example.data.model.QuizQuestion
import com.example.data.model.ScamCategory
import com.example.data.model.ScamModule
import com.example.data.model.SimulatorScenario
import com.example.data.repository.ChecklistRepository
import com.example.data.repository.QuizRepository
import com.example.data.repository.ScamRepository
import com.example.data.repository.SimulatorRepository
import com.example.data.repository.UserPreferencesRepository
import com.example.service.GeminiService
import com.example.service.TtsManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

enum class ScreenRoute {
    HOME,
    LIBRARY,
    DETAIL,
    SIMULATOR,
    QUIZ,
    ASSISTANT,
    LIVE_VOICE,
    STUDENT_CENTER,
    SENIOR_CENTER,
    CHECKLIST,
    EMERGENCY,
    RESOURCES,
    SETTINGS,
    ADMIN
}

class ScamShieldViewModel(application: Application) : AndroidViewModel(application) {

    val prefs = UserPreferencesRepository(application)
    private val geminiService = GeminiService()
    val tts = TtsManager(application)

    // Navigation State
    private val _currentScreen = MutableStateFlow(ScreenRoute.HOME)
    val currentScreen: StateFlow<ScreenRoute> = _currentScreen.asStateFlow()

    private val _selectedModule = MutableStateFlow<ScamModule?>(null)
    val selectedModule: StateFlow<ScamModule?> = _selectedModule.asStateFlow()

    private val _selectedScenario = MutableStateFlow<SimulatorScenario?>(null)
    val selectedScenario: StateFlow<SimulatorScenario?> = _selectedScenario.asStateFlow()

    // Library State
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _selectedCategoryFilter = MutableStateFlow<String?>("All")
    val selectedCategoryFilter: StateFlow<String?> = _selectedCategoryFilter.asStateFlow()

    val filteredModules: StateFlow<List<ScamModule>> = combine(
        _searchQuery,
        _selectedCategoryFilter
    ) { query, categoryId ->
        val baseList = if (categoryId == "All" || categoryId == null) {
            ScamRepository.modules
        } else {
            ScamRepository.getModulesByCategory(categoryId)
        }

        if (query.isBlank()) {
            baseList
        } else {
            val q = query.lowercase().trim()
            baseList.filter {
                it.title.lowercase().contains(q) ||
                it.definition.lowercase().contains(q) ||
                it.categoryName.lowercase().contains(q) ||
                it.warningSigns.any { sign -> sign.lowercase().contains(q) }
            }
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), ScamRepository.modules)

    // AI Assistant State
    private val _analyzerInput = MutableStateFlow("")
    val analyzerInput: StateFlow<String> = _analyzerInput.asStateFlow()

    private val _isAnalyzing = MutableStateFlow(false)
    val isAnalyzing: StateFlow<Boolean> = _isAnalyzing.asStateFlow()

    private val _analysisResult = MutableStateFlow<AnalysisResult?>(null)
    val analysisResult: StateFlow<AnalysisResult?> = _analysisResult.asStateFlow()

    // Live Voice State
    private val _voiceConversation = MutableStateFlow<List<Pair<String, String>>>(emptyList())
    val voiceConversation: StateFlow<List<Pair<String, String>>> = _voiceConversation.asStateFlow()

    private val _isVoiceThinking = MutableStateFlow(false)
    val isVoiceThinking: StateFlow<Boolean> = _isVoiceThinking.asStateFlow()

    // Simulator State
    private val _activeScenarioIndex = MutableStateFlow(0)
    val activeScenarioIndex: StateFlow<Int> = _activeScenarioIndex.asStateFlow()

    private val _simulatorUserChoice = MutableStateFlow<Int?>(null)
    val simulatorUserChoice: StateFlow<Int?> = _simulatorUserChoice.asStateFlow()

    private val _simulatorFeedbackGiven = MutableStateFlow(false)
    val simulatorFeedbackGiven: StateFlow<Boolean> = _simulatorFeedbackGiven.asStateFlow()

    // Quiz State
    private val _activeQuestionIndex = MutableStateFlow(0)
    val activeQuestionIndex: StateFlow<Int> = _activeQuestionIndex.asStateFlow()

    private val _quizSelectedChoice = MutableStateFlow<Int?>(null)
    val quizSelectedChoice: StateFlow<Int?> = _quizSelectedChoice.asStateFlow()

    private val _quizSubmitted = MutableStateFlow(false)
    val quizSubmitted: StateFlow<Boolean> = _quizSubmitted.asStateFlow()

    private val _quizSessionCorrectCount = MutableStateFlow(0)
    val quizSessionCorrectCount: StateFlow<Int> = _quizSessionCorrectCount.asStateFlow()

    private val _quizFinished = MutableStateFlow(false)
    val quizFinished: StateFlow<Boolean> = _quizFinished.asStateFlow()

    // Detail Screen Mini Quiz State
    private val _detailQuizChoice = MutableStateFlow<Int?>(null)
    val detailQuizChoice: StateFlow<Int?> = _detailQuizChoice.asStateFlow()

    private val _detailQuizSubmitted = MutableStateFlow(false)
    val detailQuizSubmitted: StateFlow<Boolean> = _detailQuizSubmitted.asStateFlow()

    // Navigation Helpers
    fun navigateTo(screen: ScreenRoute) {
        _currentScreen.value = screen
    }

    fun openModuleDetail(module: ScamModule) {
        _selectedModule.value = module
        _detailQuizChoice.value = null
        _detailQuizSubmitted.value = false
        _currentScreen.value = ScreenRoute.DETAIL
    }

    fun openScenarioDetail(scenario: SimulatorScenario) {
        _selectedScenario.value = scenario
        val index = SimulatorRepository.scenarios.indexOf(scenario)
        if (index >= 0) {
            _activeScenarioIndex.value = index
        }
        _simulatorUserChoice.value = null
        _simulatorFeedbackGiven.value = false
        _currentScreen.value = ScreenRoute.SIMULATOR
    }

    // Search & Filter
    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun selectCategoryFilter(catId: String?) {
        _selectedCategoryFilter.value = catId ?: "All"
    }

    // AI Analysis
    fun setAnalyzerInput(text: String) {
        _analyzerInput.value = text
    }

    fun analyzeMessage(text: String? = null) {
        val content = text ?: _analyzerInput.value
        if (content.isBlank()) return

        _isAnalyzing.value = true
        _analysisResult.value = null

        viewModelScope.launch {
            val result = geminiService.analyzeSuspiciousMessage(
                content = content,
                targetLanguage = prefs.language.value,
                useSearchGrounding = true
            )
            _analysisResult.value = result
            _isAnalyzing.value = false
        }
    }

    // Live Voice
    fun sendVoiceMessage(userPrompt: String) {
        if (userPrompt.isBlank()) return
        val currentList = _voiceConversation.value
        _isVoiceThinking.value = true

        viewModelScope.launch {
            val aiResponse = geminiService.converseWithLiveVoice(userPrompt, currentList)
            _voiceConversation.value = currentList + (userPrompt to aiResponse)
            _isVoiceThinking.value = false
            // Read response aloud
            tts.speak(aiResponse, prefs.language.value)
        }
    }

    // Simulator Actions
    fun submitSimulatorChoice(choiceIndex: Int) {
        _simulatorUserChoice.value = choiceIndex
        _simulatorFeedbackGiven.value = true

        val scenario = SimulatorRepository.scenarios[_activeScenarioIndex.value]
        if (choiceIndex == scenario.correctOptionIndex) {
            prefs.addSimulatorScore(scenario.id, 25)
        }
    }

    fun nextSimulatorScenario() {
        val total = SimulatorRepository.scenarios.size
        _activeScenarioIndex.value = (_activeScenarioIndex.value + 1) % total
        _simulatorUserChoice.value = null
        _simulatorFeedbackGiven.value = false
    }

    fun retrySimulatorScenario() {
        _simulatorUserChoice.value = null
        _simulatorFeedbackGiven.value = false
    }

    // Quiz Actions
    fun submitQuizAnswer(choiceIndex: Int) {
        if (_quizSubmitted.value) return
        _quizSelectedChoice.value = choiceIndex
        _quizSubmitted.value = true

        val currentQ = QuizRepository.questions[_activeQuestionIndex.value]
        if (choiceIndex == currentQ.correctIndex) {
            _quizSessionCorrectCount.value += 1
        }
    }

    fun nextQuizQuestion() {
        val nextIdx = _activeQuestionIndex.value + 1
        if (nextIdx < QuizRepository.questions.size) {
            _activeQuestionIndex.value = nextIdx
            _quizSelectedChoice.value = null
            _quizSubmitted.value = false
        } else {
            _quizFinished.value = true
            prefs.recordQuizResult(_quizSessionCorrectCount.value, QuizRepository.questions.size)
        }
    }

    fun restartQuiz() {
        _activeQuestionIndex.value = 0
        _quizSelectedChoice.value = null
        _quizSubmitted.value = false
        _quizSessionCorrectCount.value = 0
        _quizFinished.value = false
    }

    // Detail Screen Mini Quiz
    fun submitDetailQuiz(choiceIndex: Int) {
        _detailQuizChoice.value = choiceIndex
        _detailQuizSubmitted.value = true
        val module = _selectedModule.value ?: return
        if (choiceIndex == module.correctOptionIndex) {
            prefs.markLessonCompleted(module.id)
        }
    }

    // Spoken Audio
    fun readAloud(text: String) {
        tts.speak(text, prefs.language.value)
    }

    fun stopAudio() {
        tts.stop()
    }

    override fun onCleared() {
        super.onCleared()
        tts.shutdown()
    }
}
