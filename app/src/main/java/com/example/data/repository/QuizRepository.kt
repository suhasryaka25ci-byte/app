package com.example.data.repository

import com.example.data.model.DifficultyLevel
import com.example.data.model.QuizQuestion
import com.example.data.model.SafetyBadge

object QuizRepository {

    val badges = listOf(
        SafetyBadge("badge_spotter", "Scam Spotter", "Recognize at least 5 scam simulation attempts", "visibility"),
        SafetyBadge("badge_password", "Password Protector", "Master password hygiene and 2FA safety rules", "lock"),
        SafetyBadge("badge_payment", "Payment Safety Expert", "Ace the UPI, QR, and banking security quiz", "credit_card"),
        SafetyBadge("badge_privacy", "Privacy Guardian", "Complete social media & personal data protections", "shield"),
        SafetyBadge("badge_champion", "Digital Safety Champion", "Score 90% or higher on the Comprehensive Safety Assessment", "emoji_events")
    )

    val questions: List<QuizQuestion> = listOf(
        QuizQuestion(
            id = "q1",
            category = "UPI & Payments",
            question = "When receiving money through Google Pay, PhonePe, or Paytm, what action is required from you?",
            options = listOf(
                "Enter your 4 or 6 digit UPI PIN",
                "Scan the sender's QR code",
                "No action required; money credits directly without entering a PIN",
                "Provide your ATM card CVV"
            ),
            correctIndex = 2,
            explanation = "Money is received automatically into your linked bank account. Entering a UPI PIN or scanning a QR code is ONLY for sending money, never receiving.",
            targetAudience = "All Users & Seniors",
            difficulty = DifficultyLevel.BEGINNER
        ),
        QuizQuestion(
            id = "q2",
            category = "Identity & Police",
            question = "Does the legal concept of 'Digital Arrest' exist in Indian law or police procedure?",
            options = listOf(
                "Yes, CBI and Police use Skype to interrogate suspects",
                "No, 'Digital Arrest' does NOT exist in Indian law; it is 100% cyber fraud",
                "Yes, but only for financial crimes above ₹1 Crore",
                "Yes, authorized under IT Act 2024"
            ),
            correctIndex = 1,
            explanation = "Both the Supreme Court of India and Prime Minister have explicitly warned the public that 'Digital Arrest' has no legal basis in India. Genuine police never interrogate citizens on video calls.",
            targetAudience = "All Users",
            difficulty = DifficultyLevel.BEGINNER
        ),
        QuizQuestion(
            id = "q3",
            category = "Emergency Reporting",
            question = "What is the official National Cyber Financial Fraud helpline number in India to report money lost to online scams?",
            options = listOf(
                "100",
                "1930",
                "1800-11-2233",
                "112"
            ),
            correctIndex = 1,
            explanation = "Dial 1930 promptly! Reporting cyber financial fraud to 1930 immediately helps freeze the funds in the fraudulent beneficiary account before the scammer can withdraw it.",
            targetAudience = "All Users",
            difficulty = DifficultyLevel.BEGINNER
        ),
        QuizQuestion(
            id = "q4",
            category = "Banking & OTP",
            question = "Your bank sends an SMS: 'Your OTP is 482910 for txn of Rs 15,000'. A caller from your bank asks for this OTP to cancel the charge. You should:",
            options = listOf(
                "Immediately share the OTP so the transaction is cancelled",
                "Never share the OTP; banks never ask for OTPs over phone calls",
                "Give the OTP backwards for security",
                "Text the OTP to customer care"
            ),
            correctIndex = 1,
            explanation = "Banks never ask for OTPs to cancel transactions. Sharing the OTP will actually approve the ₹15,000 transfer to the scammer.",
            targetAudience = "Senior Citizens & General Users",
            difficulty = DifficultyLevel.BEGINNER
        ),
        QuizQuestion(
            id = "q5",
            category = "Jobs & Students",
            question = "A recruiter on Telegram offers a remote job paying ₹5,000 daily for rating Google Maps locations, but requires a ₹1,000 'VIP Task Deposit'. What is this?",
            options = listOf(
                "A legitimate high-paying freelance gig",
                "A classic Task/Prepaid Job Scam designed to steal deposits",
                "An internship approved by tech companies",
                "Safe if they pay you ₹200 on Day 1"
            ),
            correctIndex = 1,
            explanation = "Legitimate companies pay you for work; they never ask employees or contractors to deposit money to unlock tasks.",
            targetAudience = "Students & Youth",
            difficulty = DifficultyLevel.BEGINNER
        ),
        QuizQuestion(
            id = "q6",
            category = "AI & Deepfakes",
            question = "How can a family protect themselves against AI voice cloning emergency scams where an impostor cries for urgent money?",
            options = listOf(
                "Always transfer money immediately to prevent harm",
                "Agree on a secret 'Family Code Word' and independently call their known number",
                "Rely on caller ID without questioning",
                "Disable call forwarding only"
            ),
            correctIndex = 1,
            explanation = "Having a pre-arranged secret family code word and hanging up to call their trusted regular number immediately exposes voice cloning scams.",
            targetAudience = "Parents & Seniors",
            difficulty = DifficultyLevel.INTERMEDIATE
        ),
        QuizQuestion(
            id = "q7",
            category = "Device Security",
            question = "An unknown caller claiming to be telecom support asks you to install 'AnyDesk' or 'QuickSupport' on your smartphone. What will happen if you do?",
            options = listOf(
                "Your mobile signal speed will increase to 5G",
                "The caller can see your screen in real time, view bank OTPs, and control your phone",
                "Your antivirus database will update",
                "Nothing harmful as long as the screen is locked"
            ),
            correctIndex = 1,
            explanation = "Remote desktop applications grant screen visibility and control. Fraudsters watch OTPs and banking credentials as you type them.",
            targetAudience = "Seniors & General Users",
            difficulty = DifficultyLevel.BEGINNER
        ),
        QuizQuestion(
            id = "q8",
            category = "Phishing & Web Safety",
            question = "Which of the following website URLs is the genuine official National Cyber Crime Reporting Portal of India?",
            options = listOf(
                "http://cybercrime-portal-india.com",
                "https://cybercrime.gov.in",
                "https://national-cybercrime.org",
                "http://report-cyberfraud.nic.in.net"
            ),
            correctIndex = 1,
            explanation = "Official Indian Government portals end in .gov.in (or .nic.in). The authentic portal is https://cybercrime.gov.in.",
            targetAudience = "All Users",
            difficulty = DifficultyLevel.BEGINNER
        ),
        QuizQuestion(
            id = "q9",
            category = "Student & Campus",
            question = "When using public campus or railway Wi-Fi networks, which activity is safest to avoid?",
            options = listOf(
                "Reading Wikipedia articles",
                "Conducting NetBanking transactions and entering credit card details",
                "Checking public exam timetables",
                "Streaming an educational YouTube lecture"
            ),
            correctIndex = 1,
            explanation = "Unsecured public Wi-Fi networks can be intercepted by rogue actors using man-in-the-middle attacks. Avoid financial transactions on public Wi-Fi.",
            targetAudience = "Students & College Users",
            difficulty = DifficultyLevel.INTERMEDIATE
        ),
        QuizQuestion(
            id = "q10",
            category = "Shopping & Marketplace",
            question = "An online seller on Instagram offers a brand new iPhone 16 Pro for ₹18,000, but demands full payment via UPI before shipping. This is:",
            options = listOf(
                "A rare clearance warehouse discount",
                "An advance payment fraud scam; once paid, the seller blocks you",
                "Safe if they send a photo of their Aadhaar card",
                "Guaranteed by Instagram Buyer Protection"
            ),
            correctIndex = 1,
            explanation = "Aadhaar photos sent by scammers are stolen from previous victims. Unrealistic 80-90% discounts requiring advance UPI transfers are always scams.",
            targetAudience = "All Users",
            difficulty = DifficultyLevel.BEGINNER
        )
    )

    fun getQuestionsByAudience(target: String): List<QuizQuestion> {
        if (target == "All") return questions
        return questions.filter { it.targetAudience.contains(target, ignoreCase = true) || it.targetAudience.contains("All Users") }
    }
}
