package com.example.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.OpenInBrowser
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.ScreenRoute
import com.example.ui.ScamShieldViewModel
import com.example.ui.components.TtsSpeakerButton
import com.example.ui.theme.RiskHighRed
import com.example.ui.theme.SafeGreen

data class EmergencyGuideStep(
    val stepNumber: Int,
    val title: String,
    val instruction: String,
    val actionDetail: String
)

@Composable
fun EmergencyScreen(
    viewModel: ScamShieldViewModel,
    modifier: Modifier = Modifier
) {
    BackHandler {
        viewModel.navigateTo(ScreenRoute.HOME)
    }

    val context = LocalContext.current

    val steps = listOf(
        EmergencyGuideStep(
            stepNumber = 1,
            title = "Stop Interacting Immediately",
            instruction = "Cut off all communication with the scammer.",
            actionDetail = "Do not send any additional money, do not argue, and do not try to negotiate. Scammers often demand 'clearance fees' to release frozen money, which is always another trick."
        ),
        EmergencyGuideStep(
            stepNumber = 2,
            title = "Contact Your Bank Immediately",
            instruction = "Call your bank's fraud reporting desk to freeze accounts.",
            actionDetail = "Call the customer support number on the back of your physical ATM card or inside your official banking app. Request an immediate temporary block on UPI, NetBanking, and debit cards."
        ),
        EmergencyGuideStep(
            stepNumber = 3,
            title = "Dial 1930 — National Cyber Helpline",
            instruction = "Call 1930 within the 'Golden Hour' to freeze funds.",
            actionDetail = "1930 connects directly to the Indian Cyber Crime Coordination Centre (I4C). If reported quickly, police can trigger emergency holds on beneficiary bank and UPI accounts before cash is withdrawn."
        ),
        EmergencyGuideStep(
            stepNumber = 4,
            title = "Preserve All Evidence",
            instruction = "Take screenshots of all chats, numbers, and transaction IDs.",
            actionDetail = "Keep transaction reference numbers (UTR, Txn ID), sender phone numbers, bank account numbers, SMS messages, and emails. Do not delete chats."
        ),
        EmergencyGuideStep(
            stepNumber = 5,
            title = "Change Passwords & Reset Credentials",
            instruction = "Change login credentials from a separate secure device.",
            actionDetail = "If NetBanking passwords or email credentials were typed on a suspicious page, update them immediately and choose 'Log out of all other devices'."
        ),
        EmergencyGuideStep(
            stepNumber = 6,
            title = "Remove Any Suspicious Remote App",
            instruction = "Turn on Airplane Mode, uninstall AnyDesk or unknown APKs.",
            actionDetail = "Revoke accessibility and administrator permissions in phone Settings, run a device security scan, and restart your device."
        ),
        EmergencyGuideStep(
            stepNumber = 7,
            title = "File Official Complaint Online",
            instruction = "Register an e-complaint on cybercrime.gov.in.",
            actionDetail = "Upload the preserved evidence and transaction acknowledgements. Save the generated Acknowledgement Number for tracking."
        ),
        EmergencyGuideStep(
            stepNumber = 8,
            title = "Confide in a Trusted Person",
            instruction = "You are not alone. Do not feel ashamed.",
            actionDetail = "Cyber scammers use military-grade psychological manipulation. Millions of people are targeted daily. Talk to a trusted family member, teacher, or friend."
        )
    )

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .testTag("emergency_screen"),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Header
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { viewModel.navigateTo(ScreenRoute.HOME) }) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                }
                Spacer(modifier = Modifier.width(4.dp))
                Column {
                    Text(
                        text = "What To Do If Scammed",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = RiskHighRed
                    )
                    Text(
                        text = "Official emergency steps for online fraud recovery",
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }

        // Direct SOS Action Card (Call 1930 & Portal)
        item {
            Card(
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(containerColor = RiskHighRed),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Text(
                        text = "Active Financial Fraud Emergency?",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "If money was transferred in the last few hours, immediate action can freeze the funds before the scammer withdraws them at an ATM.",
                        color = Color.White.copy(alpha = 0.95f),
                        fontSize = 13.sp,
                        lineHeight = 18.sp
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Button(
                            onClick = {
                                val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:1930"))
                                context.startActivity(intent)
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                            modifier = Modifier
                                .weight(1f)
                                .testTag("call_1930_emergency_button")
                        ) {
                            Icon(Icons.Default.Call, contentDescription = null, tint = RiskHighRed, modifier = Modifier.size(18.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("Call 1930", color = RiskHighRed, fontWeight = FontWeight.Bold)
                        }

                        OutlinedButton(
                            onClick = {
                                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://cybercrime.gov.in"))
                                context.startActivity(intent)
                            },
                            colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.White),
                            border = androidx.compose.foundation.BorderStroke(1.dp, Color.White),
                            modifier = Modifier.weight(1f)
                        ) {
                            Icon(Icons.Default.OpenInBrowser, contentDescription = null, tint = Color.White, modifier = Modifier.size(18.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("Official Portal", color = Color.White)
                        }
                    }
                }
            }
        }

        // 8 Step Recovery Roadmap
        item {
            Text(
                text = "8-Step Emergency Recovery Roadmap",
                fontWeight = FontWeight.Bold,
                fontSize = 17.sp,
                color = MaterialTheme.colorScheme.onSurface
            )
        }

        items(steps) { step ->
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.Top
                ) {
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .clip(CircleShape)
                            .background(
                                if (step.stepNumber <= 3) RiskHighRed else MaterialTheme.colorScheme.primary
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "${step.stepNumber}",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp
                        )
                    }
                    Spacer(modifier = Modifier.width(14.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = step.title,
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.sp,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            TtsSpeakerButton(
                                textToRead = "Step ${step.stepNumber}: ${step.title}. ${step.instruction}. ${step.actionDetail}",
                                viewModel = viewModel
                            )
                        }
                        Text(
                            text = step.instruction,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = if (step.stepNumber <= 3) RiskHighRed else MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = step.actionDetail,
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            lineHeight = 17.sp
                        )
                    }
                }
            }
        }
    }
}
