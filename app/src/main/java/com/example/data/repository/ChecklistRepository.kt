package com.example.data.repository

import com.example.data.model.ChecklistItem

object ChecklistRepository {

    val categories = listOf(
        "cat_pass" to "Password & Account Security",
        "cat_device" to "Mobile & Computer Security",
        "cat_browse" to "Browsing & Messaging",
        "cat_bank" to "Banking & Payments",
        "cat_privacy" to "Privacy & Social Media",
        "cat_family" to "Family & Community Safety"
    )

    val defaultItems = listOf(
        // Passwords & Account
        ChecklistItem("chk_1", "cat_pass", "Create strong, unique passwords", "Use 12+ characters combining letters, numbers, and symbols. Never reuse the same password across multiple accounts."),
        ChecklistItem("chk_2", "cat_pass", "Enable Two-Factor Authentication (2FA)", "Turn on 2FA (preferably Authenticator App rather than SMS) for email, banking, and social media accounts."),
        ChecklistItem("chk_3", "cat_pass", "Never share OTPs or recovery codes", "Understand that no legitimate bank, police officer, or customer support will ever ask for your OTP."),
        ChecklistItem("chk_4", "cat_pass", "Review active sessions regularly", "Check 'Logged-in Devices' in Google, WhatsApp, and social accounts; sign out of unfamiliar devices."),

        // Mobile & Device Security
        ChecklistItem("chk_5", "cat_device", "Install apps only from Google Play Store", "Avoid sideloading unknown APK files sent via WhatsApp, Telegram, or SMS."),
        ChecklistItem("chk_6", "cat_device", "Keep phone OS and apps updated", "Install security updates promptly to patch vulnerabilities exploited by malware."),
        ChecklistItem("chk_7", "cat_device", "Review app permissions", "Revoke microphone, camera, contacts, and SMS permissions from apps that do not genuinely need them."),
        ChecklistItem("chk_8", "cat_device", "Never install remote-access apps on caller request", "Refuse AnyDesk, TeamViewer QuickSupport, or RustDesk requests from unknown callers."),

        // Browsing & Messaging
        ChecklistItem("chk_9", "cat_browse", "Inspect URLs before tapping", "Verify the domain name carefully (e.g., sbi.co.in vs sbi-portal-login.com). Look out for typosquatting."),
        ChecklistItem("chk_10", "cat_browse", "Treat urgency, fear, and huge rewards as red flags", "Pause and think whenever a message threatens immediate account suspension, power cut, or promises prizes."),
        ChecklistItem("chk_11", "cat_browse", "Independently verify suspicious messages", "Never call the phone number inside an alert SMS. Check the official company app or website directly."),

        // Banking & Payments
        ChecklistItem("chk_12", "cat_bank", "Remember: UPI PIN is ONLY for paying", "You NEVER need to enter your UPI PIN to receive money, cashbacks, or refunds."),
        ChecklistItem("chk_13", "cat_bank", "Never scan a QR code to receive funds", "Scanning a QR code always sends money out of your account, never into it."),
        ChecklistItem("chk_14", "cat_bank", "Verify recipient name before hitting pay", "Read the registered merchant or personal name displayed on the payment confirmation screen."),
        ChecklistItem("chk_15", "cat_bank", "Enable instant SMS & email transaction alerts", "Ensure your bank notifies you immediately whenever any debit or credit occurs."),

        // Privacy & Social Media
        ChecklistItem("chk_16", "cat_privacy", "Keep social media profiles private", "Restrict who can see your personal photos, family members, location, and contact information."),
        ChecklistItem("chk_17", "cat_privacy", "Never post photos of IDs or travel tickets", "Avoid sharing photos of Aadhaar, PAN, passports, concert tickets, or flight boarding passes containing barcodes."),
        ChecklistItem("chk_18", "cat_privacy", "Turn off public location sharing", "Avoid checking in live to your current location when traveling or alone."),

        // Family & Community Safety
        ChecklistItem("chk_19", "cat_family", "Establish a secret Family Code Word", "Agree on a secret word with family to verify authentic distress calls vs AI voice clones."),
        ChecklistItem("chk_20", "cat_family", "Educate parents & seniors on phone scams", "Remind older family members never to entertain calls about Digital Arrest or electricity bills."),
        ChecklistItem("chk_21", "cat_family", "Know the emergency reporting hotline (1930)", "Save 1930 in your phone contacts and memorize https://cybercrime.gov.in for immediate response.")
    )
}
