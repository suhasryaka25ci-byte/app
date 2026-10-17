package com.example.data.model

enum class Language(val code: String, val displayName: String) {
    ENGLISH("en", "English"),
    KANNADA("kn", "ಕನ್ನಡ (Kannada)"),
    HINDI("hi", "हिंदी (Hindi)")
}

enum class ProfileType(val title: String, val description: String) {
    GENERAL("General User", "Everyday digital safety for smartphone users"),
    STUDENT("Student", "College & school scam safety: internships, gaming, social media"),
    SENIOR("Senior Citizen", "Simplified guides, big buttons, bank & call safety")
}

enum class RiskLevel(val label: String) {
    SAFE("Low Concern / Likely Safe"),
    SUSPICIOUS("Suspicious / Proceed With Caution"),
    HIGH_RISK("High Risk / Definite Threat")
}

enum class DifficultyLevel {
    BEGINNER,
    INTERMEDIATE,
    ADVANCED
}

data class ScamModule(
    val id: String,
    val categoryId: String,
    val categoryName: String,
    val title: String,
    val definition: String,
    val howItWorks: List<String>,
    val realisticExample: String,
    val warningSigns: List<String>,
    val scammerTactics: String,
    val neverDoList: List<String>,
    val preventionMethods: List<String>,
    val whatToDoIfVictim: List<String>,
    val quizQuestion: String,
    val quizOptions: List<String>,
    val correctOptionIndex: Int,
    val quizExplanation: String,
    val difficulty: DifficultyLevel = DifficultyLevel.BEGINNER,
    val targetAudience: String = "All Users"
)

data class ScamCategory(
    val id: String,
    val codeLetter: String,
    val name: String,
    val description: String,
    val iconName: String,
    val moduleCount: Int
)

data class SimulatorScenario(
    val id: String,
    val category: String,
    val title: String,
    val senderName: String,
    val senderInfo: String,
    val messageText: String,
    val simulatedAppType: String, // SMS, WhatsApp, Bank Alert, Phone Call, Email
    val simulatedDate: String,
    val options: List<String>,
    val correctOptionIndex: Int,
    val explanation: String,
    val safestNextAction: String,
    val riskLevel: RiskLevel
)

data class QuizQuestion(
    val id: String,
    val category: String,
    val question: String,
    val options: List<String>,
    val correctIndex: Int,
    val explanation: String,
    val targetAudience: String = "All Users",
    val difficulty: DifficultyLevel = DifficultyLevel.BEGINNER
)

data class ChecklistItem(
    val id: String,
    val categoryId: String,
    val title: String,
    val description: String,
    val isCompleted: Boolean = false
)

data class SafetyBadge(
    val id: String,
    val title: String,
    val description: String,
    val icon: String,
    val isUnlocked: Boolean = false
)

data class OfficialResource(
    val id: String,
    val title: String,
    val organization: String,
    val url: String,
    val helpline: String?,
    val purpose: String,
    val lastVerified: String = "October 2026"
)

data class AnalysisResult(
    val riskLevel: RiskLevel,
    val confidence: String,
    val warningSigns: List<String>,
    val explanation: String,
    val safeNextSteps: List<String>,
    val officialGuidance: String,
    val isGroundedWithSearch: Boolean = false
)
