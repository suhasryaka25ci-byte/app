package com.example.data.repository

import com.example.data.model.OfficialResource

object OfficialResourcesRepository {

    val resources = listOf(
        OfficialResource(
            id = "res_1930",
            title = "1930 National Cyber Financial Fraud Helpline",
            organization = "Ministry of Home Affairs / I4C",
            url = "https://cybercrime.gov.in/",
            helpline = "1930",
            purpose = "Dedicated national toll-free helpline for rapid reporting of cyber financial fraud, unauthorized bank transfers, and UPI fraud. Calling within the 'golden hour' enables law enforcement to freeze funds in the beneficiary accounts."
        ),
        OfficialResource(
            id = "res_portal",
            title = "National Cyber Crime Reporting Portal",
            organization = "Government of India",
            url = "https://cybercrime.gov.in/",
            helpline = "1930",
            purpose = "The central citizen portal to lodge official complaints for financial frauds, cyber stalking, social media harassment, sextortion, and identity theft."
        ),
        OfficialResource(
            id = "res_sanchar",
            title = "Sanchar Saathi (Chakshu & CEIR)",
            organization = "Department of Telecommunications (DoT)",
            url = "https://sancharsaathi.gov.in/",
            helpline = "14422",
            purpose = "Report suspected fraudulent calls, SMS, WhatsApp messages on the Chakshu facility, trace or block stolen mobile phones via CEIR, and check SIM connections registered in your name (TAFCOP)."
        ),
        OfficialResource(
            id = "res_rbi",
            title = "RBI Kehta Hai & Banking Ombudsman",
            organization = "Reserve Bank of India",
            url = "https://www.rbi.org.in/",
            helpline = "14440",
            purpose = "Official RBI consumer awareness and redressal platform. If your bank fails to resolve an unauthorized fraudulent transaction within 30 days, file an appeal through the RBI Integrated Ombudsman scheme."
        ),
        OfficialResource(
            id = "res_certin",
            title = "CERT-In (Indian Computer Emergency Response Team)",
            organization = "Ministry of Electronics & IT",
            url = "https://www.cert-in.org.in/",
            helpline = "1800-11-4949",
            purpose = "National nodal agency for responding to cybersecurity incidents, malware outbreaks, data breaches, and vulnerability advisories."
        ),
        OfficialResource(
            id = "res_uidai",
            title = "UIDAI Resident Portal (Aadhaar Lock)",
            organization = "Unique Identification Authority of India",
            url = "https://myaadhaar.uidai.gov.in/",
            helpline = "1947",
            purpose = "Lock your Aadhaar biometrics online to prevent unauthorized authentication misuse, verify Aadhaar validity, and monitor authentication history."
        )
    )
}
