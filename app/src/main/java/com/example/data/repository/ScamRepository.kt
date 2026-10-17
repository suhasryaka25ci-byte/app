package com.example.data.repository

import com.example.data.model.DifficultyLevel
import com.example.data.model.ScamCategory
import com.example.data.model.ScamModule

object ScamRepository {

    val categories = listOf(
        ScamCategory(
            id = "cat_a",
            codeLetter = "A",
            name = "Banking, UPI & Payments",
            description = "UPI collect requests, QR payment traps, fake screenshots & OTP theft",
            iconName = "account_balance_wallet",
            moduleCount = 15
        ),
        ScamCategory(
            id = "cat_b",
            codeLetter = "B",
            name = "Phishing & Impersonation",
            description = "Electricity bill alerts, traffic challans, smishing & fake login portals",
            iconName = "mark_email_unread",
            moduleCount = 15
        ),
        ScamCategory(
            id = "cat_c",
            codeLetter = "C",
            name = "Social Media & Messaging",
            description = "WhatsApp friend impersonation, hacked accounts & fake giveaways",
            iconName = "forum",
            moduleCount = 12
        ),
        ScamCategory(
            id = "cat_d",
            codeLetter = "D",
            name = "Jobs, Internships & Education",
            description = "Work-from-home tasks, fake internships, registration fees & scholarship fraud",
            iconName = "school",
            moduleCount = 12
        ),
        ScamCategory(
            id = "cat_e",
            codeLetter = "E",
            name = "Shopping & Delivery",
            description = "Fake ecommerce stores, OLX advance payments & parcel rescheduling traps",
            iconName = "shopping_bag",
            moduleCount = 11
        ),
        ScamCategory(
            id = "cat_f",
            codeLetter = "F",
            name = "Investments & Financial Scams",
            description = "Telegram stock tips, guaranteed crypto returns & predatory loan apps",
            iconName = "trending_up",
            moduleCount = 14
        ),
        ScamCategory(
            id = "cat_g",
            codeLetter = "G",
            name = "Remote Access & Malicious APKs",
            description = "AnyDesk/TeamViewer caller fraud, screen-sharing traps & sideloaded APKs",
            iconName = "install_mobile",
            moduleCount = 11
        ),
        ScamCategory(
            id = "cat_h",
            codeLetter = "H",
            name = "Identity Theft & Digital Arrest",
            description = "Fake police/CBI Skype calls, SIM swap fraud & Aadhaar/PAN misuse",
            iconName = "badge",
            moduleCount = 12
        ),
        ScamCategory(
            id = "cat_i",
            codeLetter = "I",
            name = "Personal Safety & AI Deepfakes",
            description = "AI voice clone family distress calls, sextortion blackmail & romance scams",
            iconName = "security",
            moduleCount = 13
        ),
        ScamCategory(
            id = "cat_j",
            codeLetter = "J",
            name = "Public Wi-Fi & Privacy Footprint",
            description = "Insecure hotspots, oversharing on social media & password reuse",
            iconName = "wifi_lock",
            moduleCount = 11
        )
    )

    val modules = listOf(
        // Category A
        ScamModule(
            id = "mod_a_upi_collect",
            categoryId = "cat_a",
            categoryName = "Banking, UPI & Payments",
            title = "Fake UPI Collect Request & PIN Scam",
            definition = "A scam where fraudsters send a UPI payment request (collect request) or show a QR code claiming it will transfer money into your account, whereas entering your UPI PIN always deducts money from your account.",
            howItWorks = listOf(
                "The scammer poses as an online buyer on OLX/Marketplace or a lottery agent.",
                "They tell you: 'I am sending you ₹15,000. Accept the request on Google Pay/PhonePe and enter your PIN to credit the money.'",
                "When you open your payment app, a 'Collect Request' is pending asking you to enter your UPI PIN.",
                "The moment you enter your UPI PIN, the funds are instantly deducted from your bank balance and sent to the scammer."
            ),
            realisticExample = "'Hello Sir, I want to buy your second-hand sofa listed on OLX. I have initiated a ₹12,000 transfer on PhonePe. Please click Accept and enter your UPI PIN immediately to receive the funds.'",
            warningSigns = listOf(
                "Being asked to enter your UPI PIN to RECEIVE money",
                "Pop-up notification on GPay/PhonePe/Paytm with 'Pay' or 'Approve'",
                "Buyer is overly eager and refuses to meet in person or do cash on delivery",
                "Scammer demands you stay on the phone while completing the step"
            ),
            scammerTactics = "Scammers exploit people's excitement about receiving money and confusion regarding how UPI operates. They create false urgency to prevent the victim from reading the warning text on screen.",
            neverDoList = listOf(
                "NEVER enter your UPI PIN to receive money. UPI PIN is solely for paying.",
                "NEVER approve an unexpected Collect Request in your UPI app.",
                "NEVER share your UPI PIN or banking passwords with anyone over the phone."
            ),
            preventionMethods = listOf(
                "Remember the Golden Rule: 'PIN is ONLY for sending, NEVER for receiving.'",
                "Check your balance directly without authorizing collect requests.",
                "Decline any unfamiliar collect requests and report the VPA inside your UPI app."
            ),
            whatToDoIfVictim = listOf(
                "Call 1930 immediately (National Cyber Financial Fraud Helpline).",
                "Open your bank app and block UPI/cards instantly.",
                "File a formal complaint on https://cybercrime.gov.in within 2 hours (the golden hour for freezing fraudulent accounts)."
            ),
            quizQuestion = "A buyer online says: 'Enter your 4-digit UPI PIN on Google Pay so that ₹5,000 can be credited to your account.' What will happen?",
            quizOptions = listOf(
                "₹5,000 will be successfully credited to your bank account",
                "₹5,000 will be deducted from your bank account",
                "Your account will simply verify your identity without debit",
                "You will receive an official RBI receipt"
            ),
            correctOptionIndex = 1,
            quizExplanation = "UPI PIN authorizes outgoing debits. Entering your UPI PIN will transfer ₹5,000 from your account to the scammer! You never enter a PIN to receive money.",
            difficulty = DifficultyLevel.BEGINNER,
            targetAudience = "All Users & Seniors"
        ),
        ScamModule(
            id = "mod_a_qr_code",
            categoryId = "cat_a",
            categoryName = "Banking, UPI & Payments",
            title = "QR Code 'Scan to Receive' Scam",
            definition = "Scammers send a QR code via WhatsApp or email, claiming scanning it will deposit money into your account.",
            howItWorks = listOf(
                "Scammer contacts you claiming to offer a refund, prize, or advance payment.",
                "They send a picture of a QR code via WhatsApp with instructions to scan it.",
                "Scanning the QR code opens your payment app with a pre-filled amount to send.",
                "They insist: 'Scan and approve to complete the reverse debit.'"
            ),
            realisticExample = "Scammer sends a WhatsApp image: 'Axis Bank Refund Portal: Scan this QR code to claim your pending ₹4,850 electricity subsidy.'",
            warningSigns = listOf(
                "Instructions claiming 'Scan QR code to receive cashback/refund'",
                "Receiving QR code images over WhatsApp or Telegram",
                "Screen shows a payment authorization screen with amount payable"
            ),
            scammerTactics = "Technical jargon like 'reverse debit authorization' or 'security handshake' used to confuse non-technical users.",
            neverDoList = listOf(
                "NEVER scan a QR code to receive money.",
                "NEVER scan QR codes sent by unknown contacts via chat apps."
            ),
            preventionMethods = listOf(
                "To receive money via UPI, you only need to provide your mobile number or UPI ID (VPA). No scanning needed.",
                "Only scan QR codes at physical merchant checkouts when you are willingly paying for goods."
            ),
            whatToDoIfVictim = listOf(
                "Immediately contact your bank's fraud reporting desk.",
                "Dial 1930 and report transaction ID and recipient VPA.",
                "Save chat screenshots as evidence."
            ),
            quizQuestion = "When should you scan a QR code with your UPI app?",
            quizOptions = listOf(
                "To receive a government subsidy or cashback",
                "Only when YOU want to PAY someone or a store",
                "To verify your bank account KYC",
                "To receive payments from an online buyer"
            ),
            correctOptionIndex = 1,
            quizExplanation = "Scanning a QR code is strictly for making an outbound payment, never for receiving money or receiving subsidies.",
            difficulty = DifficultyLevel.BEGINNER,
            targetAudience = "Senior Citizens & General Users"
        ),
        ScamModule(
            id = "mod_a_otp_theft",
            categoryId = "cat_a",
            categoryName = "Banking, UPI & Payments",
            title = "OTP Theft & Social Engineering",
            definition = "Deceptive phone calls or messages coercing you into reading out the One Time Password (OTP) sent by your bank or service provider.",
            howItWorks = listOf(
                "Scammer initiates an unauthorized transfer, password reset, or SIM swap on your account.",
                "Your bank generates an authentication OTP and sends it via SMS to your registered phone.",
                "Scammer calls immediately: 'I am calling from SBI Fraud Prevention. Someone tried hacking your account! Read the 6-digit OTP to block the hacker!'",
                "As soon as you speak the digits, the scammer enters it and drains your balance."
            ),
            realisticExample = "'Dear Customer, your debit card ending in 4102 has been temporarily restricted. A security officer will call. Please disclose the OTP for security verification.'",
            warningSigns = listOf(
                "Caller claiming to be from your bank asking for OTP",
                "SMS stating 'DO NOT SHARE WITH ANYONE' followed by a caller asking for it",
                "Threats that your card or account will be permanently blocked in 10 minutes"
            ),
            scammerTactics = "Fabricating extreme urgency and fear of account loss to make victims bypass clear SMS warnings.",
            neverDoList = listOf(
                "NEVER share your OTP with anyone, including bank managers, police, or tech support.",
                "NEVER forward SMS containing OTPs to other numbers."
            ),
            preventionMethods = listOf(
                "Read the full SMS: Notice that the SMS explicitly specifies what the OTP is authorizing (e.g., 'OTP for txn of Rs 25,000').",
                "Hang up and call the official bank number printed on your physical debit card."
            ),
            whatToDoIfVictim = listOf(
                "Call your bank customer care to immediately freeze your NetBanking and debit card.",
                "Call 1930 to log a cyber financial fraud complaint.",
                "Change login passwords immediately from a secure device."
            ),
            quizQuestion = "A person claiming to be your Bank Branch Manager calls and asks for your 6-digit OTP to reverse a fraudulent charge. What should you do?",
            quizOptions = listOf(
                "Quickly read out the OTP so the fraud is stopped",
                "Ask him for his employee ID and then give the OTP",
                "Refuse and disconnect; banks NEVER ask for OTP over the phone",
                "Send the OTP via SMS instead of speaking it"
            ),
            correctOptionIndex = 2,
            quizExplanation = "Legitimate bank officials and automated systems will NEVER ask for your OTP. Anyone asking for an OTP is an impostor trying to authorize a transaction.",
            difficulty = DifficultyLevel.BEGINNER,
            targetAudience = "All Users"
        ),
        // Category B
        ScamModule(
            id = "mod_b_electricity_bill",
            categoryId = "cat_b",
            categoryName = "Phishing & Impersonation",
            title = "Fake Electricity Bill Disconnection Alert",
            definition = "Urgent SMS claiming your electricity power supply will be cut off tonight unless you call an unknown mobile number or download an app.",
            howItWorks = listOf(
                "Victim receives an SMS: 'Dear consumer, your electricity power will be disconnected tonight at 9:30 PM because previous month bill was not updated. Please call electricity officer on 98XXXXXXXX.'",
                "When you call, the scammer claims your bill of ₹10 is pending or an update is needed.",
                "They ask you to download a 'quick bill viewer' APK or remote app (AnyDesk).",
                "They prompt you to pay ₹10 via card or NetBanking, stealing your credentials during the transaction."
            ),
            realisticExample = "'Urgent Alert: Your BESCOM power connection #948201 will be disconnected at 10 PM tonight due to pending bill update. Immediately contact power engineer at 9876543210.'",
            warningSigns = listOf(
                "SMS sent from an ordinary 10-digit mobile number instead of an official governmental sender ID like TD-BESCOM or DZ-UPPCL",
                "Threats of immediate power cut within hours",
                "Instruction to contact a personal mobile phone number",
                "Request to pay a trivial sum like ₹10 or ₹11 through an unknown link"
            ),
            scammerTactics = "Playing on the fear of family or home being left in total darkness overnight, triggering panicked compliance.",
            neverDoList = listOf(
                "NEVER call the phone number mentioned inside such SMS alerts.",
                "NEVER install any APK file or app recommended by the caller.",
                "NEVER make payments via third-party links received on SMS."
            ),
            preventionMethods = listOf(
                "Check your actual bill status on your official electricity board portal or authorized consumer app.",
                "Inspect the sender ID: Official government entities use standardized 6-character sender headers (e.g., VM-BESCOM), not 10-digit phone numbers."
            ),
            whatToDoIfVictim = listOf(
                "Immediately notify your bank if you made a payment or shared card details.",
                "Uninstall any application installed during the call immediately.",
                "Report the malicious phone number on the national Chakshu portal (sancharsaathi.gov.in)."
            ),
            quizQuestion = "You receive an SMS from a 10-digit mobile number stating your electricity will be cut off at 9:30 PM unless you call them. What is the safest step?",
            quizOptions = listOf(
                "Immediately call the number to avoid losing power",
                "Send ₹10 to the number mentioned to verify your bill",
                "Ignore the SMS number, open your official electricity board app/website to check status",
                "Forward the SMS to all family members in panic"
            ),
            correctOptionIndex = 2,
            quizExplanation = "Official utility boards never send cut-off notices from personal 10-digit phone numbers. Always verify bills directly via official consumer portals.",
            difficulty = DifficultyLevel.BEGINNER,
            targetAudience = "All Users & Senior Citizens"
        ),
        // Category D
        ScamModule(
            id = "mod_d_wfh_job",
            categoryId = "cat_d",
            categoryName = "Jobs, Internships & Education",
            title = "Work-From-Home / Part-Time Task Scam",
            definition = "Deceptive job offers promising high daily earnings for simple online tasks like liking YouTube videos or rating Google Maps hotels, leading to massive financial extortion.",
            howItWorks = listOf(
                "You receive an unsolicited Telegram or WhatsApp message offering ₹3,000 - ₹8,000 per day for 'part-time remote rating'.",
                "Phase 1: They assign simple tasks (like 3 YouTube videos) and actually transfer ₹150 - ₹500 to gain your trust.",
                "Phase 2: They invite you to a VIP Telegram group and introduce 'Prepaid Investment Tasks' or 'Crypto Merchant Tasks' where you must deposit ₹5,000 to earn ₹7,000.",
                "Phase 3: When you want to withdraw your money, they claim 'System freeze: you made a mistake! Deposit ₹50,000 more to unlock all funds.' The money is never returned."
            ),
            realisticExample = "'Hello! HR recruiter from Amazon Global. We need reviewers. Work 30 mins from home on your phone. Daily wage ₹2,500 - ₹5,000 credited instantly. Reply YES to start.'",
            warningSigns = listOf(
                "Unsolicited job offer via WhatsApp, Telegram, or SMS",
                "High promised income for effortless actions (liking videos, clicking links)",
                "Requirement to pay or 'recharge' your balance to receive higher paying tasks",
                "Communication exclusively through Telegram channels with anonymous admins"
            ),
            scammerTactics = "Sunk cost fallacy: victims invest small amounts, see fake profit dashboards, and keep paying larger sums hoping to recover previous deposits.",
            neverDoList = listOf(
                "NEVER pay money to secure a job or receive task earnings.",
                "NEVER join speculative Telegram investment groups claiming corporate partnerships."
            ),
            preventionMethods = listOf(
                "Legitimate employers pay you for work; they never require you to deposit money to work.",
                "Verify job listings on company career portals directly."
            ),
            whatToDoIfVictim = listOf(
                "Stop depositing any further money regardless of threats or promises.",
                "Call 1930 immediately with transaction UTR numbers.",
                "Report the Telegram/WhatsApp accounts and log evidence on cybercrime.gov.in."
            ),
            quizQuestion = "A company offers you ₹4,000 daily for liking YouTube videos, but asks you to deposit ₹2,000 as a refundable 'VIP security deposit'. This is:",
            quizOptions = listOf(
                "A genuine corporate freelancer initiative",
                "A classic Task/Prepaid employment scam",
                "A government approved startup scheme",
                "Safe as long as they pay the first ₹200 bonus"
            ),
            correctOptionIndex = 1,
            quizExplanation = "Any job requiring upfront 'deposits', 'recharges', or 'investment tasks' is a scam. Genuine employers never charge employees to work.",
            difficulty = DifficultyLevel.BEGINNER,
            targetAudience = "Students & College Graduates"
        ),
        // Category G
        ScamModule(
            id = "mod_g_remote_app",
            categoryId = "cat_g",
            categoryName = "Remote Access & Malicious APKs",
            title = "Remote Desktop App (AnyDesk/TeamViewer) Fraud",
            definition = "Scammers trick victims into downloading screen-sharing applications (AnyDesk, TeamViewer QuickSupport, RustDesk) to view OTPs and take over banking apps.",
            howItWorks = listOf(
                "Scammer poses as a bank executive, telecom representative, or courier agent.",
                "They instruct: 'To resolve your KYC or deliver your courier, please install our verification tool from Play Store.'",
                "They direct you to install a legitimate remote desktop tool (e.g., AnyDesk).",
                "They ask you to read out the 9-digit remote access code, giving them remote control or screen visibility.",
                "They ask you to open your banking app and pay ₹1, observing your PIN and OTP in real time."
            ),
            realisticExample = "'Sir, this is Airtel 5G SIM upgrade division. To prevent your SIM card deactivation, please install QuickSupport app and share the connection code.'",
            warningSigns = listOf(
                "Stranger asking you to install AnyDesk, TeamViewer, RustDesk, or Screen Share",
                "Asking you to read out a 9-digit code or grant accessibility permissions",
                "Asking you to open banking apps while a remote session is active"
            ),
            scammerTactics = "Exploiting trust by guiding users to official Google Play Store apps (which are real tools misused by scammers) to seem legitimate.",
            neverDoList = listOf(
                "NEVER install screen-sharing apps on the instruction of an unsolicited caller.",
                "NEVER share remote desktop access codes with strangers."
            ),
            preventionMethods = listOf(
                "Understand that no genuine bank or government agency asks for remote screen access.",
                "If someone asks you to install AnyDesk/TeamViewer, terminate the call immediately."
            ),
            whatToDoIfVictim = listOf(
                "Immediately disconnect Wi-Fi / Mobile Data (turn on Airplane Mode).",
                "Uninstall the remote application.",
                "Call your bank on a separate device to freeze your accounts."
            ),
            quizQuestion = "A customer care agent asks you to install 'AnyDesk' or 'QuickSupport' on your phone to fix your cashback issue. You should:",
            quizOptions = listOf(
                "Install it and give the 9-digit code so they can fix the phone",
                "Refuse and disconnect immediately; it allows them to see your screen and OTPs",
                "Install it but only hide your password",
                "Ask your friend to install it instead"
            ),
            correctOptionIndex = 1,
            quizExplanation = "Screen sharing tools allow scammers to view your screen in real time, exposing OTPs, passwords, and personal messages. Banks NEVER request remote access.",
            difficulty = DifficultyLevel.BEGINNER,
            targetAudience = "Senior Citizens & General Users"
        ),
        // Category H
        ScamModule(
            id = "mod_h_digital_arrest",
            categoryId = "cat_h",
            categoryName = "Identity Theft & Digital Arrest",
            title = "Digital Arrest & Fake Police Video Call Scam",
            definition = "An aggressive extortion scheme where scammers pose as CBI, Police, or Customs officers over Skype/WhatsApp video calls with fake police backgrounds, falsely accusing victims and demanding immediate money transfers under the guise of 'verification' or 'bail'.",
            howItWorks = listOf(
                "Victim receives an IVR automated call: 'A courier containing drugs and fake passports in your name has been seized by Customs.'",
                "Call is transferred to an alleged 'CBI/Cyber Crime Inspector' in uniform.",
                "Scammers initiate a Skype/WhatsApp video call featuring realistic police station backdrops, logos, and forged arrest warrants.",
                "They claim you are placed under 'Digital Arrest' and cannot disconnect the call or tell anyone without violating national secrets acts.",
                "They demand you transfer all your savings into 'RBI safe verification escrow accounts' which will allegedly be returned after 30 minutes of audit."
            ),
            realisticExample = "'This is Cyber Crime Branch New Delhi. An arrest warrant #CR-8012 has been issued against your Aadhaar card for laundering 2.5 Crore. Stay on Skype camera or a police team will raid your house.'",
            warningSigns = listOf(
                "Claims of 'Digital Arrest' — NO SUCH LEGAL CONCEPT EXISTS in Indian Law!",
                "Police or judges conducting official interrogations over Skype or WhatsApp video",
                "Demand to transfer money into 'government verification accounts' to prove innocence",
                "Strict orders not to tell family members or disconnect the video call"
            ),
            scammerTactics = "Intense psychological coercion, isolation, forged legal documents, and simulating authoritarian intimidation.",
            neverDoList = listOf(
                "NEVER believe in 'Digital Arrest'. Genuine police do not detain citizens over video calls.",
                "NEVER transfer money to any 'security account' under legal threats.",
                "NEVER keep legal threats secret from your trusted family or local police."
            ),
            preventionMethods = listOf(
                "Remember: Indian law enforcement NEVER serves warrants via WhatsApp or conducts trials via Skype.",
                "Police officers never demand financial transfers to establish innocence.",
                "Hang up immediately and contact your local police station or dial 1930."
            ),
            whatToDoIfVictim = listOf(
                "Disconnect the call immediately. You are in NO danger of immediate arrest.",
                "If money was transferred, call 1930 within minutes to block beneficiary bank accounts.",
                "Visit your nearest local police station and file a formal FIR."
            ),
            quizQuestion = "A caller dressed in police uniform on a Skype video call tells you that your Aadhaar was found in a money laundering case and you are under 'Digital Arrest'. What is the truth?",
            quizOptions = listOf(
                "Digital Arrest is an official expedited court procedure in India",
                "There is NO such thing as 'Digital Arrest' in Indian law; this is 100% fraud",
                "You must immediately wire all funds to prove your innocence",
                "You must stay on the video call without telling family"
            ),
            correctOptionIndex = 1,
            quizExplanation = "Both the Supreme Court and Prime Minister's Office of India have clarified that 'Digital Arrest' does NOT exist in Indian law. Genuine police do not arrest or interrogate via Skype.",
            difficulty = DifficultyLevel.BEGINNER,
            targetAudience = "Senior Citizens, Parents & General Users"
        ),
        // Category I
        ScamModule(
            id = "mod_i_voice_cloning",
            categoryId = "cat_i",
            categoryName = "Personal Safety & AI Deepfakes",
            title = "AI Voice Cloning / Family Emergency Scam",
            definition = "Scammers use AI audio synthesis tools to clone the voice of your child, grandchild, or relative using short audio samples from social media, then call demanding emergency funds for an accident or arrest.",
            howItWorks = listOf(
                "Scammers harvest 5-10 seconds of your child's or friend's voice from Instagram Reels, TikTok, or YouTube.",
                "They use an AI voice cloning model to generate a speech clip crying: 'Papa, please help me! I met with a terrible accident!'",
                "Another person grabs the phone posing as a doctor or police officer demanding ₹50,000 immediately via UPI for emergency treatment or bail.",
                "The distress and familiar voice trick parents into transferring funds before checking."
            ),
            realisticExample = "Caller with your son's exact crying voice: 'Mom, my car hit someone! Police have seized my phone. Please send ₹40,000 to this UPI ID right now or they will lock me up!'",
            warningSigns = listOf(
                "Call from an unknown number with the voice of a family member in acute distress",
                "Call quality sounds muffled, crying, or background siren noises",
                "Aggressive insistence on immediate UPI or cash transfer",
                "Caller refuses to let you talk to the person for more than a few seconds"
            ),
            scammerTactics = "Hijacking maternal/paternal instincts and panic so critical reasoning is completely disabled.",
            neverDoList = listOf(
                "NEVER send emergency money without independently verifying through another phone number.",
                "NEVER panic and keep the call secret."
            ),
            preventionMethods = listOf(
                "Establish a secret 'Family Safety Code Word' known only to close family members to verify authentic emergencies.",
                "Hang up and dial your family member's known regular phone number directly.",
                "Ask a specific personal question only the real person would know (e.g., 'What is our pet dog's name?')."
            ),
            whatToDoIfVictim = listOf(
                "Confirm safety of your real family member first.",
                "If money was sent, report immediately to 1930 and bank fraud helpline.",
                "Report the calling number on Sanchar Saathi Chakshu facility."
            ),
            quizQuestion = "You get a call from an unknown number. A voice sounding just like your daughter cries that she had an accident and urgently needs ₹30,000 via UPI. What is your first step?",
            quizOptions = listOf(
                "Immediately send the UPI payment to avoid medical delay",
                "Hang up and call your daughter's known phone number or ask your family secret code word",
                "Transfer half the money just in case",
                "Forward the UPI ID to all friends"
            ),
            correctOptionIndex = 1,
            quizExplanation = "AI voice cloning can replicate anyone's voice in seconds. Always hang up and call the real person directly on their known number or verify with a family secret question.",
            difficulty = DifficultyLevel.INTERMEDIATE,
            targetAudience = "Parents & Senior Citizens"
        )
    )

    fun getCategoryById(categoryId: String): ScamCategory? =
        categories.find { it.id == categoryId }

    fun getModulesByCategory(categoryId: String): List<ScamModule> =
        modules.filter { it.categoryId == categoryId }

    fun searchModules(query: String): List<ScamModule> {
        if (query.isBlank()) return modules
        val q = query.trim().lowercase()
        return modules.filter {
            it.title.lowercase().contains(q) ||
            it.definition.lowercase().contains(q) ||
            it.categoryName.lowercase().contains(q) ||
            it.warningSigns.any { sign -> sign.lowercase().contains(q) }
        }
    }
}
