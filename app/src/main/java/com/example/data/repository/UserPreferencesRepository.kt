package com.example.data.repository

import android.content.Context
import android.content.SharedPreferences
import com.example.data.model.Language
import com.example.data.model.ProfileType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class UserPreferencesRepository(context: Context) {

    private val prefs: SharedPreferences =
        context.getSharedPreferences("scamshield_prefs", Context.MODE_PRIVATE)

    private val _language = MutableStateFlow(
        Language.values().find { it.code == prefs.getString("key_lang", "en") } ?: Language.ENGLISH
    )
    val language: StateFlow<Language> = _language.asStateFlow()

    private val _profileType = MutableStateFlow(
        ProfileType.values().find { it.name == prefs.getString("key_profile", ProfileType.GENERAL.name) } ?: ProfileType.GENERAL
    )
    val profileType: StateFlow<ProfileType> = _profileType.asStateFlow()

    private val _isDarkMode = MutableStateFlow(prefs.getBoolean("key_dark_mode", false))
    val isDarkMode: StateFlow<Boolean> = _isDarkMode.asStateFlow()

    private val _isHighContrast = MutableStateFlow(prefs.getBoolean("key_high_contrast", false))
    val isHighContrast: StateFlow<Boolean> = _isHighContrast.asStateFlow()

    private val _fontScale = MutableStateFlow(prefs.getFloat("key_font_scale", 1.0f))
    val fontScale: StateFlow<Float> = _fontScale.asStateFlow()

    private val _completedLessons = MutableStateFlow(
        prefs.getStringSet("key_completed_lessons", emptySet()) ?: emptySet()
    )
    val completedLessons: StateFlow<Set<String>> = _completedLessons.asStateFlow()

    private val _completedChecklist = MutableStateFlow(
        prefs.getStringSet("key_completed_checklist", emptySet()) ?: emptySet()
    )
    val completedChecklist: StateFlow<Set<String>> = _completedChecklist.asStateFlow()

    private val _simulatorScore = MutableStateFlow(prefs.getInt("key_sim_score", 0))
    val simulatorScore: StateFlow<Int> = _simulatorScore.asStateFlow()

    private val _solvedScenarios = MutableStateFlow(
        prefs.getStringSet("key_solved_scenarios", emptySet()) ?: emptySet()
    )
    val solvedScenarios: StateFlow<Set<String>> = _solvedScenarios.asStateFlow()

    private val _quizScore = MutableStateFlow(prefs.getInt("key_quiz_score", 0))
    val quizScore: StateFlow<Int> = _quizScore.asStateFlow()

    private val _unlockedBadges = MutableStateFlow(
        prefs.getStringSet("key_unlocked_badges", setOf("badge_spotter")) ?: setOf("badge_spotter")
    )
    val unlockedBadges: StateFlow<Set<String>> = _unlockedBadges.asStateFlow()

    fun setLanguage(lang: Language) {
        prefs.edit().putString("key_lang", lang.code).apply()
        _language.value = lang
    }

    fun setProfileType(profile: ProfileType) {
        prefs.edit().putString("key_profile", profile.name).apply()
        _profileType.value = profile
        if (profile == ProfileType.SENIOR) {
            setFontScale(1.25f)
        }
    }

    fun toggleDarkMode(enabled: Boolean) {
        prefs.edit().putBoolean("key_dark_mode", enabled).apply()
        _isDarkMode.value = enabled
    }

    fun toggleHighContrast(enabled: Boolean) {
        prefs.edit().putBoolean("key_high_contrast", enabled).apply()
        _isHighContrast.value = enabled
    }

    fun setFontScale(scale: Float) {
        prefs.edit().putFloat("key_font_scale", scale).apply()
        _fontScale.value = scale
    }

    fun markLessonCompleted(moduleId: String) {
        val updated = _completedLessons.value + moduleId
        prefs.edit().putStringSet("key_completed_lessons", updated).apply()
        _completedLessons.value = updated
        checkBadgeProgression()
    }

    fun toggleChecklistItem(itemId: String) {
        val current = _completedChecklist.value
        val updated = if (current.contains(itemId)) current - itemId else current + itemId
        prefs.edit().putStringSet("key_completed_checklist", updated).apply()
        _completedChecklist.value = updated
        checkBadgeProgression()
    }

    fun addSimulatorScore(scenarioId: String, points: Int) {
        val newScore = _simulatorScore.value + points
        val updatedScenarios = _solvedScenarios.value + scenarioId
        prefs.edit()
            .putInt("key_sim_score", newScore)
            .putStringSet("key_solved_scenarios", updatedScenarios)
            .apply()
        _simulatorScore.value = newScore
        _solvedScenarios.value = updatedScenarios
        checkBadgeProgression()
    }

    fun recordQuizResult(correctCount: Int, totalCount: Int) {
        val current = _quizScore.value
        val newScore = current + (correctCount * 10)
        prefs.edit().putInt("key_quiz_score", newScore).apply()
        _quizScore.value = newScore
        checkBadgeProgression()
    }

    private fun checkBadgeProgression() {
        val current = _unlockedBadges.value.toMutableSet()
        if (_solvedScenarios.value.size >= 3) {
            current.add("badge_spotter")
        }
        if (_completedChecklist.value.size >= 5) {
            current.add("badge_password")
        }
        if (_solvedScenarios.value.contains("sim_1_upi_collect") || _solvedScenarios.value.contains("sim_2_bank_kyc")) {
            current.add("badge_payment")
        }
        if (_completedChecklist.value.size >= 12) {
            current.add("badge_privacy")
        }
        if (_quizScore.value >= 40 && _solvedScenarios.value.size >= 8) {
            current.add("badge_champion")
        }
        prefs.edit().putStringSet("key_unlocked_badges", current).apply()
        _unlockedBadges.value = current
    }

    fun clearAllProgress() {
        prefs.edit().clear().apply()
        _completedLessons.value = emptySet()
        _completedChecklist.value = emptySet()
        _simulatorScore.value = 0
        _solvedScenarios.value = emptySet()
        _quizScore.value = 0
        _unlockedBadges.value = setOf("badge_spotter")
    }
}
