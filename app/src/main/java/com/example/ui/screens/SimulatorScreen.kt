package com.example.ui.screens

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
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Sms
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
import com.example.data.repository.SimulatorRepository
import com.example.ui.ScamShieldViewModel
import com.example.ui.components.TtsSpeakerButton
import com.example.ui.theme.RiskHighRed
import com.example.ui.theme.SafeGreen

@Composable
fun SimulatorScreen(
    viewModel: ScamShieldViewModel,
    modifier: Modifier = Modifier
) {
    val currentIndex by viewModel.activeScenarioIndex.collectAsState()
    val userChoice by viewModel.simulatorUserChoice.collectAsState()
    val feedbackGiven by viewModel.simulatorFeedbackGiven.collectAsState()
    val simScore by viewModel.prefs.simulatorScore.collectAsState()
    val solvedScenarios by viewModel.prefs.solvedScenarios.collectAsState()

    val totalScenarios = SimulatorRepository.scenarios.size
    val scenario = SimulatorRepository.scenarios[currentIndex]

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .testTag("simulator_screen"),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Header
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Interactive Scam Simulator",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = "Practice spotting scams safely • No real links or calls",
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                Surface(
                    color = MaterialTheme.colorScheme.primaryContainer,
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        text = "Score: $simScore XP",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                    )
                }
            }
        }

        // Scenario Navigation Progress
        item {
            Card(
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Scenario ${currentIndex + 1} of $totalScenarios",
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.primary
                    )

                    Row {
                        IconButton(
                            onClick = {
                                if (currentIndex > 0) {
                                    viewModel.openScenarioDetail(SimulatorRepository.scenarios[currentIndex - 1])
                                }
                            },
                            enabled = currentIndex > 0
                        ) {
                            Icon(Icons.Default.ArrowBack, contentDescription = "Previous")
                        }
                        IconButton(
                            onClick = {
                                if (currentIndex < totalScenarios - 1) {
                                    viewModel.openScenarioDetail(SimulatorRepository.scenarios[currentIndex + 1])
                                }
                            },
                            enabled = currentIndex < totalScenarios - 1
                        ) {
                            Icon(Icons.Default.ArrowForward, contentDescription = "Next")
                        }
                    }
                }
            }
        }

        // Mock App Message Card
        item {
            Card(
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFF8FAFC)),
                border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFE2E8F0)),
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("simulator_mock_message_card")
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    // Simulation Disclaimer Tag
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Surface(
                            color = Color(0xFFFEF3C7),
                            shape = RoundedCornerShape(6.dp)
                        ) {
                            Text(
                                text = "EDUCATIONAL SIMULATION",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF92400E),
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                            )
                        }
                        Text(
                            text = scenario.simulatedDate,
                            fontSize = 11.sp,
                            color = Color(0xFF64748B)
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    // Sender Header
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(38.dp)
                                .clip(CircleShape)
                                .background(Color(0xFF0F172A)),
                            contentAlignment = Alignment.Center
                        ) {
                            val icon = when {
                                scenario.simulatedAppType.contains("WhatsApp") -> Icons.Default.Chat
                                scenario.simulatedAppType.contains("SMS") -> Icons.Default.Sms
                                scenario.simulatedAppType.contains("Phone") -> Icons.Default.Phone
                                scenario.simulatedAppType.contains("Email") -> Icons.Default.Email
                                else -> Icons.Default.Security
                            }
                            Icon(icon, contentDescription = null, tint = Color.White, modifier = Modifier.size(20.dp))
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = scenario.senderName,
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp,
                                color = Color(0xFF0F172A)
                            )
                            Text(
                                text = "${scenario.simulatedAppType} • ${scenario.senderInfo}",
                                fontSize = 11.sp,
                                color = Color(0xFF64748B)
                            )
                        }
                        TtsSpeakerButton(textToRead = scenario.messageText, viewModel = viewModel)
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // Simulated Message Bubble
                    Surface(
                        color = Color.White,
                        shape = RoundedCornerShape(12.dp),
                        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFE2E8F0)),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = scenario.messageText,
                            fontSize = 14.sp,
                            color = Color(0xFF1E293B),
                            lineHeight = 20.sp,
                            modifier = Modifier.padding(14.dp)
                        )
                    }
                }
            }
        }

        // Decision Question
        item {
            Text(
                text = "What is the safest action to take?",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onSurface
            )
        }

        // Decision Options
        itemsIndexed(scenario.options) { idx, option ->
            val isSelected = userChoice == idx
            val isCorrect = idx == scenario.correctOptionIndex

            val cardColor = when {
                !feedbackGiven && isSelected -> MaterialTheme.colorScheme.primaryContainer
                feedbackGiven && isCorrect -> SafeGreen.copy(alpha = 0.15f)
                feedbackGiven && isSelected && !isCorrect -> RiskHighRed.copy(alpha = 0.15f)
                else -> MaterialTheme.colorScheme.surface
            }

            Card(
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = cardColor),
                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(enabled = !feedbackGiven) {
                        viewModel.submitSimulatorChoice(idx)
                    }
                    .testTag("sim_option_$idx")
            ) {
                Row(
                    modifier = Modifier.padding(14.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(28.dp)
                            .clip(CircleShape)
                            .background(
                                when {
                                    feedbackGiven && isCorrect -> SafeGreen
                                    feedbackGiven && isSelected && !isCorrect -> RiskHighRed
                                    else -> MaterialTheme.colorScheme.surfaceVariant
                                }
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        val icon = when {
                            feedbackGiven && isCorrect -> Icons.Default.Check
                            feedbackGiven && isSelected && !isCorrect -> Icons.Default.Close
                            else -> null
                        }
                        if (icon != null) {
                            Icon(icon, contentDescription = null, tint = Color.White, modifier = Modifier.size(16.dp))
                        } else {
                            Text(
                                text = "${('A' + idx)}",
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = option,
                        fontSize = 13.sp,
                        fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal,
                        color = MaterialTheme.colorScheme.onSurface,
                        lineHeight = 18.sp,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }

        // Feedback Card
        if (feedbackGiven) {
            val userWon = userChoice == scenario.correctOptionIndex
            item {
                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = if (userWon) SafeGreen.copy(alpha = 0.12f) else RiskHighRed.copy(alpha = 0.12f)
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("simulator_feedback_card")
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = if (userWon) "🎯 Excellent Decision! (+25 XP)" else "⚠️ Danger! You would have been scammed!",
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.sp,
                                color = if (userWon) SafeGreen else RiskHighRed
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = scenario.explanation,
                            fontSize = 13.sp,
                            color = MaterialTheme.colorScheme.onSurface,
                            lineHeight = 19.sp
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Surface(
                            color = MaterialTheme.colorScheme.surface,
                            shape = RoundedCornerShape(10.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(modifier = Modifier.padding(10.dp)) {
                                Text(
                                    text = "Safest Next Action:",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 12.sp,
                                    color = MaterialTheme.colorScheme.primary
                                )
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    text = scenario.safestNextAction,
                                    fontSize = 12.sp,
                                    color = MaterialTheme.colorScheme.onSurface,
                                    lineHeight = 17.sp
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(14.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            OutlinedButton(
                                onClick = { viewModel.retrySimulatorScenario() },
                                modifier = Modifier.weight(1f)
                            ) {
                                Icon(Icons.Default.Refresh, contentDescription = null, modifier = Modifier.size(16.dp))
                                Spacer(modifier = Modifier.width(4.dp))
                                Text("Retry")
                            }

                            Button(
                                onClick = { viewModel.nextSimulatorScenario() },
                                modifier = Modifier.weight(1f)
                            ) {
                                Text("Next Scenario")
                                Spacer(modifier = Modifier.width(4.dp))
                                Icon(Icons.Default.ArrowForward, contentDescription = null, modifier = Modifier.size(16.dp))
                            }
                        }
                    }
                }
            }
        }
    }
}
