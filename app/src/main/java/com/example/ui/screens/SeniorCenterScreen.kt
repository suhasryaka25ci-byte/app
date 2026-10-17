package com.example.ui.screens

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
import androidx.compose.material.icons.filled.Elderly
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.Language
import com.example.ui.ScreenRoute
import com.example.ui.ScamShieldViewModel
import com.example.ui.components.TtsSpeakerButton
import com.example.ui.theme.RiskHighRed
import com.example.ui.theme.SafeGreen

data class SeniorLesson(
    val title: String,
    val whatScammerSays: String,
    val whySuspicious: String,
    val whatToDo: String,
    val neverShare: String,
    val howToVerify: String
)

@Composable
fun SeniorCenterScreen(
    viewModel: ScamShieldViewModel,
    modifier: Modifier = Modifier
) {
    BackHandler {
        viewModel.navigateTo(ScreenRoute.HOME)
    }

    val currentLang by viewModel.prefs.language.collectAsState()

    val lessons = listOf(
        SeniorLesson(
            title = "1. UPI PIN is ONLY for Paying Money",
            whatScammerSays = "'Sir, enter your 4-digit UPI PIN on Google Pay so your pension/cashback can be credited.'",
            whySuspicious = "You NEVER enter a UPI PIN to receive money. Entering your PIN transfers your money to the caller!",
            whatToDo = "Refuse and hang up immediately. You do not need a PIN to receive any money.",
            neverShare = "Never enter your UPI PIN, ATM PIN, or OTP on anyone's instructions.",
            howToVerify = "Check your bank balance directly inside your bank app without approving any requests."
        ),
        SeniorLesson(
            title = "2. Fake Electricity Power Cut Warning",
            whatScammerSays = "'Your electricity connection will be cut off tonight at 9:30 PM due to unpaid bill. Call this mobile number.'",
            whySuspicious = "Official electricity boards never send notices from 10-digit mobile phone numbers or demand quick payment over phone.",
            whatToDo = "Do NOT call the number in the SMS. Your electricity will NOT be cut.",
            neverShare = "Never pay ₹10 or install any app to verify electricity bills.",
            howToVerify = "Check your monthly physical paper bill or visit your neighborhood electricity office."
        ),
        SeniorLesson(
            title = "3. Fake Police 'Digital Arrest' Threat",
            whatScammerSays = "'This is CBI officer on Skype video call. A parcel of drugs in your name was found. You are under Digital Arrest.'",
            whySuspicious = "There is NO SUCH THING as 'Digital Arrest' in India! Real police never interrogate citizens on Skype or demand money.",
            whatToDo = "Disconnect the video call right away. You are in no legal danger.",
            neverShare = "Never transfer your life savings to 'verification' or 'safe' accounts.",
            howToVerify = "Tell your family or visit your local neighborhood police station."
        ),
        SeniorLesson(
            title = "4. Remote Screen Apps (AnyDesk/QuickSupport)",
            whatScammerSays = "'Sir, install AnyDesk app from Play Store so our bank manager can fix your account from our office.'",
            whySuspicious = "AnyDesk allows the caller to see your phone screen, read your secret OTPs, and take your money.",
            whatToDo = "Never install AnyDesk, TeamViewer, or QuickSupport for any caller.",
            neverShare = "Never read out any 9-digit numbers that appear on your phone screen.",
            howToVerify = "If your bank card has issues, visit your bank branch in person."
        ),
        SeniorLesson(
            title = "5. Fake Son/Grandchild Accident Voice Call",
            whatScammerSays = "'Grandpa, I met with a car accident! Police have my phone. Send ₹30,000 to this doctor right now!'",
            whySuspicious = "Scammers can copy your family member's voice using computer AI voice tools to cause panic.",
            whatToDo = "Stay calm. Hang up, take a deep breath, and call your grandchild on their regular phone number.",
            neverShare = "Never send emergency money in haste without talking to parents or other relatives.",
            howToVerify = "Ask them a personal question only your real grandchild knows (like their childhood pet or favorite food)."
        )
    )

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .testTag("senior_center_screen"),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Top Bar
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { viewModel.navigateTo(ScreenRoute.HOME) }) {
                    Icon(
                        Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        modifier = Modifier.size(28.dp)
                    )
                }
                Spacer(modifier = Modifier.width(6.dp))
                Column {
                    Text(
                        text = "Senior Citizen Learning Center",
                        fontWeight = FontWeight.Bold,
                        fontSize = 22.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = "Simple, large-print cyber protection guide",
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }

        // Persistent Urgent Help Button
        item {
            Surface(
                color = RiskHighRed,
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { viewModel.navigateTo(ScreenRoute.EMERGENCY) }
                    .testTag("senior_help_sos_button")
            ) {
                Row(
                    modifier = Modifier.padding(18.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .clip(CircleShape)
                            .background(Color.White.copy(alpha = 0.2f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            Icons.Default.Phone,
                            contentDescription = "Emergency",
                            tint = Color.White,
                            modifier = Modifier.size(28.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text(
                            text = "I Need Help / Call 1930",
                            color = Color.White,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.ExtraBold
                        )
                        Text(
                            text = "Did someone take money from your bank? Tap here for emergency help.",
                            color = Color.White.copy(alpha = 0.9f),
                            fontSize = 13.sp,
                            lineHeight = 18.sp
                        )
                    }
                }
            }
        }

        // Senior Lessons
        items(lessons) { lesson ->
            Card(
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = lesson.title,
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 18.sp,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.weight(1f)
                        )
                        TtsSpeakerButton(
                            textToRead = "${lesson.title}. Scammer says: ${lesson.whatScammerSays}. Why suspicious: ${lesson.whySuspicious}. What to do: ${lesson.whatToDo}. Never share: ${lesson.neverShare}",
                            viewModel = viewModel
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // What Scammer Says
                    Surface(
                        color = Color(0xFFFEF2F2),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text(
                                text = "🗣️ What the Scammer Might Say:",
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp,
                                color = RiskHighRed
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = lesson.whatScammerSays,
                                fontSize = 15.sp,
                                color = Color(0xFF0F172A),
                                lineHeight = 21.sp
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    // Why Suspicious
                    Text(
                        text = "⚠️ Why this is a scam:",
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = lesson.whySuspicious,
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        lineHeight = 20.sp
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    // What To Do
                    Surface(
                        color = SafeGreen.copy(alpha = 0.1f),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text(
                                text = "✅ What You Should Do:",
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp,
                                color = SafeGreen
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = lesson.whatToDo,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = MaterialTheme.colorScheme.onSurface,
                                lineHeight = 21.sp
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    // What NOT to share
                    Text(
                        text = "❌ What you must NEVER share:",
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = RiskHighRed
                    )
                    Text(
                        text = lesson.neverShare,
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        lineHeight = 20.sp
                    )
                }
            }
        }
    }
}
