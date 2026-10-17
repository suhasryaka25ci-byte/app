package com.example.data.repository

import com.example.data.model.RiskLevel
import com.example.data.model.SimulatorScenario

object SimulatorRepository {

    val scenarios: List<SimulatorScenario> = listOf(
        SimulatorScenario(
            id = "sim_1_upi_collect",
            category = "UPI & Payments",
            title = "OLX Buyer Payment Request",
            senderName = "Rajesh Verma (Buyer)",
            senderInfo = "+91 98450 11234 • PhonePe",
            messageText = "Hi, I have sent ₹14,500 for your iPhone on PhonePe. Please open your PhonePe app now. Click 'Pay/Approve' and enter your 6-digit UPI PIN to confirm receipt of the money into your account.",
            simulatedAppType = "PhonePe Collect",
            simulatedDate = "Today, 10:15 AM",
            options = listOf(
                "Accept the request and enter UPI PIN to receive ₹14,500",
                "Decline the request immediately; UPI PIN is only for sending money, not receiving!",
                "Ask him to send it via Google Pay instead and then enter PIN"
            ),
            correctOptionIndex = 1,
            explanation = "UPI PIN is NEVER required to receive money. Approving a collect request and entering your PIN will instantly DEBIT ₹14,500 from your account and send it to the scammer.",
            safestNextAction = "Decline the collect request on your UPI app and block the buyer on OLX.",
            riskLevel = RiskLevel.HIGH_RISK
        ),
        SimulatorScenario(
            id = "sim_2_bank_kyc",
            category = "Banking",
            title = "HDFC Bank KYC Expiry Notice",
            senderName = "VK-HDFCBK",
            senderInfo = "SMS Header",
            messageText = "Dear Customer, your HDFC bank account will be blocked within 24 hours due to non-submission of PAN & Aadhaar KYC. Update immediately at: http://hdfc-kyc-portal-update.in/login",
            simulatedAppType = "SMS Message",
            simulatedDate = "Yesterday, 4:20 PM",
            options = listOf(
                "Click the link and enter NetBanking credentials and PAN to prevent blockage",
                "Recognize the fake domain (hdfc-kyc-portal-update.in is not hdfcbank.com) and delete the message",
                "Reply to the SMS with your Aadhaar number"
            ),
            correctOptionIndex = 1,
            explanation = "Banks never ask customers to update KYC via third-party HTTP links sent in SMS. The official domain is hdfcbank.com. The link leads to a phishing credential harvester.",
            safestNextAction = "Do not click the link. Log into official HDFC mobile banking or visit your branch to verify KYC status.",
            riskLevel = RiskLevel.HIGH_RISK
        ),
        SimulatorScenario(
            id = "sim_3_electricity",
            category = "Utilities",
            title = "Electricity Disconnection Warning",
            senderName = "+91 87654 99120",
            senderInfo = "Personal Mobile Number",
            messageText = "Dear Consumer, your electricity power will be disconnected at 9:30 PM tonight due to pending bill update. Immediately contact power office superintendent Mr. Sharma on 8765499120.",
            simulatedAppType = "SMS Message",
            simulatedDate = "Today, 6:45 PM",
            options = listOf(
                "Call Mr. Sharma immediately and follow his instructions to pay ₹10",
                "Ignore the 10-digit sender and verify directly on your state electricity board official app",
                "Transfer ₹500 via Google Pay to the phone number"
            ),
            correctOptionIndex = 1,
            explanation = "Official electricity boards send notices with verified alpha headers (like VM-BESCOM or TD-TNEB), never from random 10-digit personal numbers.",
            safestNextAction = "Verify your account status on your official electricity consumer portal or monthly physical bill.",
            riskLevel = RiskLevel.HIGH_RISK
        ),
        SimulatorScenario(
            id = "sim_4_parcel_customs",
            category = "Courier & Delivery",
            title = "India Post / FedEx Parcel Rescheduling",
            senderName = "India Post Logistics",
            senderInfo = "+91 70123 45678",
            messageText = "Package delivery attempt failed due to incomplete address. Please pay ₹25 redelivery fee and update your address within 12 hours: https://indiapost-track-parcel.top/fee",
            simulatedAppType = "SMS Alert",
            simulatedDate = "Today, 11:10 AM",
            options = listOf(
                "Click the link and enter debit card details to pay ₹25",
                "Recognize the suspicious .top URL and check real consignment number on indiapost.gov.in",
                "Call the delivery driver and read out your card CVV"
            ),
            correctOptionIndex = 1,
            explanation = "The nominal ₹25 fee is bait to steal your debit/credit card number, CVV, and OTP on a cloned payment gateway.",
            safestNextAction = "Verify only through indiapost.gov.in using your consignment tracking ID.",
            riskLevel = RiskLevel.HIGH_RISK
        ),
        SimulatorScenario(
            id = "sim_5_wfh_internship",
            category = "Jobs & Internships",
            title = "Google Remote Freelance Internship",
            senderName = "HR Recruiter - Sarah",
            senderInfo = "WhatsApp • +62 812-3456-7890 (Indonesia)",
            messageText = "Congratulations! You have been selected for Google Data Reviewer Internship. Earn ₹3,500 daily. To activate your employee portal, pay ₹1,200 refundable registration fee on this UPI ID: hr.verify@okaxis.",
            simulatedAppType = "WhatsApp Chat",
            simulatedDate = "Today, 2:30 PM",
            options = listOf(
                "Pay ₹1,200 because it's a refundable registration fee for Google",
                "Recognize this as a scam: Google does not hire via WhatsApp with international numbers asking for upfront fees",
                "Ask for a discount on the registration fee"
            ),
            correctOptionIndex = 1,
            explanation = "Legitimate companies NEVER charge candidates any registration fees, security deposits, or software fees. Furthermore, the sender uses an Indonesian country code (+62).",
            safestNextAction = "Report and block the WhatsApp contact. Never pay money to get a job.",
            riskLevel = RiskLevel.HIGH_RISK
        ),
        SimulatorScenario(
            id = "sim_6_digital_arrest",
            category = "Police & Legal",
            title = "CBI Video Call / Digital Arrest Notice",
            senderName = "Cyber Crime Branch New Delhi",
            senderInfo = "Skype Call: cbi.investigation.officer",
            messageText = "A parcel containing narcotics and illegal SIM cards registered to your Aadhaar card has been seized at Mumbai airport. You are placed under 'Digital Arrest'. Join Skype video call immediately and transfer your savings to the RBI audit account or face police raid.",
            simulatedAppType = "Skype Video Request",
            simulatedDate = "Today, 1:00 PM",
            options = listOf(
                "Follow instructions and transfer your bank balance to the safety account to avoid arrest",
                "Understand that 'Digital Arrest' is a complete fiction not present in Indian law; hang up and dial 1930",
                "Beg the officer for forgiveness and offer cash"
            ),
            correctOptionIndex = 1,
            explanation = "No law enforcement agency in India conducts arrests, trials, or demands money transfers via Skype/WhatsApp. 'Digital Arrest' is entirely illegal fraud.",
            safestNextAction = "Disconnect immediately, alert family, and report the details to 1930 and your local police station.",
            riskLevel = RiskLevel.HIGH_RISK
        ),
        SimulatorScenario(
            id = "sim_7_remote_access",
            category = "Tech Support",
            title = "Airtel 5G SIM Upgrade Technical Support",
            senderName = "+91 93456 78123",
            senderInfo = "Incoming Voice Call",
            messageText = "Caller: 'Sir, your Airtel 4G SIM will be deactivated tomorrow. To migrate to 5G without visiting the store, please install the QuickSupport / AnyDesk app from Play Store and tell me the 9-digit code displayed on screen.'",
            simulatedAppType = "Phone Call",
            simulatedDate = "Today, 12:05 PM",
            options = listOf(
                "Install AnyDesk and read the 9-digit code so the technician can configure 5G",
                "Refuse and hang up; AnyDesk gives the caller full control over your phone screen and OTPs",
                "Install it but only hide your gallery photos"
            ),
            correctOptionIndex = 1,
            explanation = "AnyDesk and QuickSupport allow remote access to your device. Once granted, the caller can view incoming OTPs and execute unauthorized transactions.",
            safestNextAction = "Never install remote desktop apps on caller requests. Visit your official telecom store for physical SIM upgrades.",
            riskLevel = RiskLevel.HIGH_RISK
        ),
        SimulatorScenario(
            id = "sim_8_voice_clone",
            category = "AI Deepfake",
            title = "Son's Emergency Accident Voice Call",
            senderName = "Unknown Number (+91 91234 56780)",
            senderInfo = "Crying Voice Note",
            messageText = "Voice sounding exactly like your college son: 'Papa, please help me! My bike hit a pedestrian and police have detained me at the station! Send ₹35,000 to the sub-inspector's UPI ID right now or they will file a case!'",
            simulatedAppType = "Audio Message",
            simulatedDate = "Today, 8:40 PM",
            options = listOf(
                "Instantly transfer ₹35,000 via GPay because the voice matches your son",
                "Stay calm, hang up, call your son's actual known number or ask the family secret security question",
                "Forward the audio to relatives asking for financial contributions"
            ),
            correctOptionIndex = 1,
            explanation = "AI voice cloning can replicate human voices from short social media audio clips. Always verify through a secondary known channel or secret family passphrase.",
            safestNextAction = "Call your son's regular number directly or contact his roommate/college authority.",
            riskLevel = RiskLevel.HIGH_RISK
        ),
        SimulatorScenario(
            id = "sim_9_scholarship",
            category = "Education",
            title = "PM National Merit Scholarship 2026",
            senderName = "National Scholarship Portal",
            senderInfo = "email: awards@pm-scholarship-gov.net",
            messageText = "Dear Student, you have been awarded ₹75,000 under National Merit Grant 2026. To disburse funds to your bank account, please submit your NetBanking username and password on the portal.",
            simulatedAppType = "Email Notification",
            simulatedDate = "Today, 9:15 AM",
            options = listOf(
                "Submit your NetBanking password so the grant can be deposited",
                "Recognize the fraudulent domain (.net instead of official scholarships.gov.in) and never reveal passwords",
                "Forward the email to classmates to claim their rewards too"
            ),
            correctOptionIndex = 1,
            explanation = "Official government portals end in .gov.in or .nic.in, and NO scholarship ever asks for NetBanking passwords to deposit funds. Only account number and IFSC are needed.",
            safestNextAction = "Check authentic scholarships only on scholarships.gov.in.",
            riskLevel = RiskLevel.HIGH_RISK
        ),
        SimulatorScenario(
            id = "sim_10_crypto_investment",
            category = "Investments",
            title = "VIP Crypto Multiplier Telegram Group",
            senderName = "TradePro Crypto Signals",
            senderInfo = "Telegram Channel (42,000 members)",
            messageText = "🔥 Special 10X Opportunity! Invest ₹10,000 today and receive guaranteed ₹1,00,000 in 48 hours using our algorithmic AI arbitrage bot. 100% Risk Free! Limited to first 20 investors. Transfer to VIP USDT wallet.",
            simulatedAppType = "Telegram Announcement",
            simulatedDate = "Today, 3:00 PM",
            options = listOf(
                "Invest ₹10,000 since other members in the channel posted screenshots of profits",
                "Recognize this as a classic Ponzi/fraud scheme: guaranteed 1000% returns in 48 hours is impossible",
                "Start with ₹2,000 to test if it's real"
            ),
            correctOptionIndex = 1,
            explanation = "Guaranteed high returns with zero risk are the hallmark of investment scams. Telegram member screenshots are fabricated by bot accounts.",
            safestNextAction = "Exit and report the Telegram channel. Invest only through SEBI-registered intermediaries.",
            riskLevel = RiskLevel.HIGH_RISK
        ),
        SimulatorScenario(
            id = "sim_11_lottery_kbc",
            category = "Lottery & Prizes",
            title = "KBC Jio Lucky Draw 25 Lakhs",
            senderName = "KBC Head Office Mumbai",
            senderInfo = "+92 301 2345678 (Pakistan)",
            messageText = "Congratulations! Your mobile SIM card has won ₹25,00,000 in the KBC All India Lucky Draw. To receive the cheque, deposit ₹12,500 for government GST and clearance certificate.",
            simulatedAppType = "WhatsApp Media Card",
            simulatedDate = "Yesterday, 7:15 PM",
            options = listOf(
                "Pay ₹12,500 tax to claim the ₹25 Lakhs cheque",
                "Recognize the scam: You cannot win a lottery you never entered, and the sender is from a foreign country code (+92)",
                "Negotiate to deduct the tax from the 25 Lakh prize"
            ),
            correctOptionIndex = 1,
            explanation = "You cannot win a lottery you never bought a ticket for. Genuine lotteries deduct tax at source (TDS) rather than asking winners to wire money in advance.",
            safestNextAction = "Block the number and report on Sanchar Saathi Chakshu.",
            riskLevel = RiskLevel.HIGH_RISK
        ),
        SimulatorScenario(
            id = "sim_12_fake_ecommerce",
            category = "Shopping",
            title = "90% Off Sony PlayStation 5 & iPhone",
            senderName = "MegaBargainDeals.store",
            senderInfo = "Instagram Sponsored Ad",
            messageText = "FLASH SALE: Brand new PS5 Console for only ₹4,999 (Regular ₹54,990). Only 5 units left! Only Prepaid UPI orders accepted. No Cash on Delivery. Order now before stock runs out!",
            simulatedAppType = "Social Media Ad",
            simulatedDate = "Today, 5:30 PM",
            options = listOf(
                "Quickly pay ₹4,999 before the 5 units sell out",
                "Identify that 90% discount on electronics and no-COD policy is an advance payment trap",
                "Order two consoles to resell for profit"
            ),
            correctOptionIndex = 1,
            explanation = "Scam storefronts use massive unrealistic discounts and disable Cash on Delivery to collect advance payments without ever shipping goods.",
            safestNextAction = "Avoid purchasing from unverified sponsored ads. Purchase electronics from verified authorized retailers.",
            riskLevel = RiskLevel.HIGH_RISK
        ),
        SimulatorScenario(
            id = "sim_13_loan_app",
            category = "Loans",
            title = "Instant 2-Minute Personal Loan",
            senderName = "FastCash Instant Loan",
            senderInfo = "SMS • DM-LOAN",
            messageText = "Pre-approved loan of ₹2,00,000 ready in your account without salary slip or CIBIL check! Download app from: http://bit.ly/fastloan-apk and grant contact & gallery permissions to disburse.",
            simulatedAppType = "SMS Message",
            simulatedDate = "Today, 11:45 AM",
            options = listOf(
                "Download the APK and grant all phone permissions to get instant cash",
                "Reject the link; unregulated predatory loan apps harvest your contacts and photos for blackmail and extortion",
                "Download the app on a friend's phone"
            ),
            correctOptionIndex = 1,
            explanation = "Illegal loan apps require intrusive access to contacts and galleries. They disburse small amounts with 500% interest and harass friends and family using morphed images.",
            safestNextAction = "Borrow only from RBI-registered banks and NBFCs listed on rbi.org.in.",
            riskLevel = RiskLevel.HIGH_RISK
        ),
        SimulatorScenario(
            id = "sim_14_qr_scan_refund",
            category = "UPI & Payments",
            title = "Swiggy / Zomato Order Refund QR",
            senderName = "Customer Care Executive",
            senderInfo = "+91 97890 12345 • WhatsApp",
            messageText = "Sir, we have initiated your food order refund of ₹650. Please scan this QR code on Google Pay to credit the refund amount directly into your bank.",
            simulatedAppType = "WhatsApp Chat",
            simulatedDate = "Today, 2:15 PM",
            options = listOf(
                "Scan the QR code on Google Pay to claim the ₹650 refund",
                "Refuse: QR codes are only for paying money, food delivery apps process refunds automatically back to the original payment source",
                "Ask him to send a bigger refund"
            ),
            correctOptionIndex = 1,
            explanation = "Refunds never require scanning a QR code or entering a PIN. Authentic platforms credit refunds automatically without customer action.",
            safestNextAction = "Track refunds exclusively within the official Swiggy/Zomato app support section.",
            riskLevel = RiskLevel.HIGH_RISK
        ),
        SimulatorScenario(
            id = "sim_15_traffic_challan",
            category = "Government & Police",
            title = "Pending Traffic Challan Payment Notice",
            senderName = "E-Challan Transport Dept",
            senderInfo = "+91 96123 45678",
            messageText = "Challan #DL-2026-9041 issued against vehicle KA04ME1234 for overspeeding (₹1,000). Avoid court summons by paying online within 24 hours: http://echallan-parivahan-pay.site/pay.apk",
            simulatedAppType = "SMS Alert",
            simulatedDate = "Today, 4:10 PM",
            options = listOf(
                "Download the pay.apk file to pay the fine quickly",
                "Notice the .site domain and .apk file download; genuine challans are paid on echallan.parivahan.gov.in without downloading APKs",
                "Forward the SMS to the traffic police"
            ),
            correctOptionIndex = 1,
            explanation = "Traffic police portals do NOT distribute APK files via SMS. The APK contains malware designed to read your banking SMS and OTPs.",
            safestNextAction = "Check real challan status on the official Ministry of Road Transport portal: echallan.parivahan.gov.in.",
            riskLevel = RiskLevel.HIGH_RISK
        ),
        SimulatorScenario(
            id = "sim_16_instagram_verification",
            category = "Social Media",
            title = "Instagram Blue Badge Verification Offer",
            senderName = "Meta Support Official",
            senderInfo = "Direct Message • @meta_badge_help24",
            messageText = "Your profile is eligible for verified Meta blue checkmark! To activate the badge, confirm your account by logging in through this secure link within 24 hours or lose eligibility: https://instagram-meta-verify.co/auth",
            simulatedAppType = "Instagram DM",
            simulatedDate = "Yesterday, 10:00 PM",
            options = listOf(
                "Click the link and input your Instagram password to get the blue tick",
                "Recognize account phishing: Meta never contacts users via DM with third-party verification links",
                "Share your two-factor code with the support account"
            ),
            correctOptionIndex = 1,
            explanation = "Social media platforms never send direct messages to offer verification badges. The link is a credential phishing page designed to steal your account.",
            safestNextAction = "Report the profile for impersonation and enable two-factor authentication inside your app settings.",
            riskLevel = RiskLevel.HIGH_RISK
        ),
        SimulatorScenario(
            id = "sim_17_gaming_account",
            category = "Gaming & Youth",
            title = "Free BGMI Unknown Cash (UC) & Royale Pass",
            senderName = "BGMI Rewards Team",
            senderInfo = "Discord Message",
            messageText = "Season 32 Giveaway: Claim 600 Free UC + Glacier M416 skin! Login with your Google or Facebook gaming credentials here: https://bgmi-free-uc-spin.xyz to claim immediately.",
            simulatedAppType = "Discord Chat",
            simulatedDate = "Today, 7:00 PM",
            options = listOf(
                "Login with your gaming credentials to claim the free skins",
                "Identify this as gaming account theft designed to hijack your game profile and inventory",
                "Change your password to 123456"
            ),
            correctOptionIndex = 1,
            explanation = "Phishing sites targeting gamers steal game profiles, sell off inventories, and use linked accounts to scam friends.",
            safestNextAction = "Never enter gaming login credentials on external websites outside the official game client.",
            riskLevel = RiskLevel.HIGH_RISK
        ),
        SimulatorScenario(
            id = "sim_18_aadhaar_pan",
            category = "Identity Theft",
            title = "Aadhaar Card Deactivation Warning",
            senderName = "UIDAI Aadhaar Support",
            senderInfo = "SMS Header: BZ-AADHAR",
            messageText = "Your Aadhaar number XXXXXXXX4012 will be permanently suspended due to non-linking of biometric data. Download the Aadhaar Biometric Update APK to link: http://uidai-portal-biometric.club/app.apk",
            simulatedAppType = "SMS Alert",
            simulatedDate = "Today, 3:45 PM",
            options = listOf(
                "Download the APK and upload your PAN and fingerprint",
                "Recognize that UIDAI never asks citizens to download third-party APKs to update biometric data",
                "Send your Aadhaar PDF copy to the sender"
            ),
            correctOptionIndex = 1,
            explanation = "Biometric updates can only be completed in person at authorized Aadhaar Enrolment Centers using government biometric scanners. APK downloads are spyware.",
            safestNextAction = "Check your authentic Aadhaar status on resident.uidai.gov.in.",
            riskLevel = RiskLevel.HIGH_RISK
        ),
        SimulatorScenario(
            id = "sim_19_whatsapp_friend_money",
            category = "Social Media",
            title = "Urgent Money Request from Friend",
            senderName = "Vikram College Friend",
            senderInfo = "+91 99887 76655 (Hacked Account)",
            messageText = "Hey bro, I am stranded at an emergency hospital clinic in Bangalore and my Google Pay limit is exceeded. Please transfer ₹10,000 immediately to this doctor's UPI ID: clinic99@upi. I will return it by evening!",
            simulatedAppType = "WhatsApp Chat",
            simulatedDate = "Today, 1:20 PM",
            options = listOf(
                "Quickly send ₹10,000 without questioning because it's a close college friend",
                "Call Vikram directly over regular cellular voice or speak to mutual friends to verify if his account is hacked",
                "Send ₹5,000 instead"
            ),
            correctOptionIndex = 1,
            explanation = "Hacked WhatsApp accounts frequently message contacts with urgent emotional stories to divert money to scammer UPI IDs.",
            safestNextAction = "Always verify urgent financial requests via a standard phone call before sending funds.",
            riskLevel = RiskLevel.HIGH_RISK
        ),
        SimulatorScenario(
            id = "sim_20_customer_care_google",
            category = "Customer Care",
            title = "Google Search Result for Bank Support",
            senderName = "Google Search Listing",
            senderInfo = "Toll Free Helpline: +91 82999 12345",
            messageText = "You searched 'SBI Customer Care Helpline Bangalore' on Google and called the first phone number displayed on a sponsored ad. The person answers: 'State Bank Support, please share your ATM card number and CVV to cancel your pending charges.'",
            simulatedAppType = "Phone Call",
            simulatedDate = "Today, 10:00 AM",
            options = listOf(
                "Provide the card number and CVV since it was found on Google Search",
                "Recognize that fraudsters place fake phone numbers in Google Maps and Ads; genuine banks never ask for CVV",
                "Give only the CVV and not the card number"
            ),
            correctOptionIndex = 1,
            explanation = "Fraudsters manipulate search engine listings and Google Maps pins to display fake customer care numbers. Never share CVV, expiry dates, or PINs with any caller.",
            safestNextAction = "Find customer care numbers ONLY from the back of your physical bank card or official bank passbook.",
            riskLevel = RiskLevel.HIGH_RISK
        )
    )

    fun getScenarioById(id: String): SimulatorScenario? =
        scenarios.find { it.id == id }
}
