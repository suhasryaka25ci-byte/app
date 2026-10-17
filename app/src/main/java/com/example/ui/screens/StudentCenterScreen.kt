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
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Gamepad
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.filled.Wifi
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.repository.SimulatorRepository
import com.example.ui.ScreenRoute
import com.example.ui.ScamShieldViewModel
import com.example.ui.components.TtsSpeakerButton

data class StudentTopic(
    val title: String,
    val icon: String,
    val summary: String,
    val doRules: List<String>,
    val dangerAlert: String
)

@Composable
fun StudentCenterScreen(
    viewModel: ScamShieldViewModel,
    modifier: Modifier = Modifier
) {
    BackHandler {
        viewModel.navigateTo(ScreenRoute.HOME)
    }

    val topics = listOf(
        StudentTopic(
            title = "Fake Internships & Placement Offers",
            icon = "school",
            summary = "Unsolicited WhatsApp or Telegram messages offering work-from-home tasks, remote data entry, or MNC internships requiring a 'security fee' or 'registration charge'.",
            doRules = listOf(
                "Verify postings on authentic company career portals directly.",
                "Remember: Real companies PAY interns; they never demand money to work.",
                "Never share your college ID card or Aadhaar with anonymous recruiters."
            ),
            dangerAlert = "Red Flag: Any recruiter asking for registration fees, training fees, or laptop insurance deposits."
        ),
        StudentTopic(
            title = "Gaming Account Theft & Free In-Game Currency",
            icon = "gamepad",
            summary = "Links promising free BGMI Unknown Cash (UC), Free Fire diamonds, Steam wallet codes, or Discord Nitro that ask you to log in with your social/gaming account.",
            doRules = listOf(
                "Only purchase in-game currency through official in-app store mechanisms.",
                "Enable 2FA (Two-Factor Authentication) on your Steam, Discord, Google, and Epic accounts.",
                "Never enter login credentials on third-party reward spin websites."
            ),
            dangerAlert = "Red Flag: Websites asking you to 'Authorize App' or log into Google to claim exclusive free skins."
        ),
        StudentTopic(
            title = "Deepfakes, AI Voice Clones & Misinformation",
            icon = "auto_awesome",
            summary = "AI-generated synthetic photos, morphed videos, or cloned voices used for blackmail, bullying, fake campus notices, or impersonating friends in distress.",
            doRules = listOf(
                "Never share sensitive personal photographs or video clips online.",
                "Set social media profiles to private to prevent audio/photo harvesting.",
                "If someone threatens you with morphed photos, immediately report to cybercrime.gov.in and tell a parent, teacher, or counselor. Do NOT pay blackmailers."
            ),
            dangerAlert = "Important: Blackmailers never stop after the first payment. Never pay; preserve screenshots and report immediately."
        ),
        StudentTopic(
            title = "Campus Public Wi-Fi & Shared Library Computers",
            icon = "wifi",
            summary = "Insecure open Wi-Fi networks in cafes, colleges, and libraries where unencrypted network traffic can be monitored or hijacked.",
            doRules = listOf(
                "Always check that websites use HTTPS before entering passwords.",
                "Never conduct NetBanking or UPI transactions on open public Wi-Fi.",
                "Always log out and use Incognito/Private mode when using public college library terminals."
            ),
            dangerAlert = "Red Flag: 'Free Public Wi-Fi' networks that don't require any password or portal verification."
        ),
        StudentTopic(
            title = "Protecting College Email & Learning Platforms",
            icon = "lock",
            summary = "Targeted phishing emails sent to your .edu or .ac.in student inbox claiming your university portal has changed or tuition refund is pending.",
            doRules = listOf(
                "Inspect the sender email domain carefully before clicking links.",
                "Never reuse your college email password for external websites or gaming accounts.",
                "Check authentic university announcements on your official university noticeboard."
            ),
            dangerAlert = "Red Flag: Emails claiming your student portal will be deactivated in 24 hours unless you re-enter your password."
        )
    )

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .testTag("student_center_screen"),
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
                        text = "Student Learning Center",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = "Cyber defense for school & college digital life",
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }

        // Student Banner
        item {
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(46.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.primary),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Default.School, contentDescription = null, tint = MaterialTheme.colorScheme.onPrimary)
                    }
                    Spacer(modifier = Modifier.width(14.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Smart Student Guidelines",
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                        Text(
                            text = "Learn to protect your career, gaming profiles, passwords, and digital identity.",
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.9f)
                        )
                    }
                }
            }
        }

        // Campus Scenarios
        item {
            Text(
                text = "Key Campus Safety Focus Areas",
                fontWeight = FontWeight.Bold,
                fontSize = 17.sp,
                color = MaterialTheme.colorScheme.onSurface
            )
        }

        items(topics) { topic ->
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = topic.title,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            color = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.weight(1f)
                        )
                        TtsSpeakerButton(
                            textToRead = "${topic.title}. ${topic.summary}. ${topic.dangerAlert}",
                            viewModel = viewModel
                        )
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = topic.summary,
                        fontSize = 13.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        lineHeight = 18.sp
                    )

                    Spacer(modifier = Modifier.height(10.dp))
                    Surface(
                        color = Color(0xFFFEF2F2),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(modifier = Modifier.padding(10.dp)) {
                            Icon(Icons.Default.Warning, contentDescription = null, tint = Color(0xFFEF4444), modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = topic.dangerAlert,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color(0xFFB91C1C),
                                lineHeight = 16.sp
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "Safety Rules:",
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.primary
                    )
                    topic.doRules.forEach { rule ->
                        Row(modifier = Modifier.padding(vertical = 2.dp)) {
                            Text("✓", color = MaterialTheme.colorScheme.primary, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = rule,
                                fontSize = 12.sp,
                                color = MaterialTheme.colorScheme.onSurface,
                                lineHeight = 16.sp
                            )
                        }
                    }
                }
            }
        }

        // Test Yourself on Student Scenarios
        item {
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer),
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { viewModel.navigateTo(ScreenRoute.SIMULATOR) }
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Default.Shield, contentDescription = null, tint = MaterialTheme.colorScheme.secondary)
                    Spacer(modifier = Modifier.width(12.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Practice on Real Simulator Scenarios",
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            color = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                        Text(
                            text = "Try identifying fake Google internships and free BGMI skin traps.",
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
                        )
                    }
                }
            }
        }
    }
}
