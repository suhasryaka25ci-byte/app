package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Elderly
import androidx.compose.material.icons.filled.Emergency
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.Quiz
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.ScamShieldViewModel
import com.example.ui.ScreenRoute
import com.example.ui.components.ScamShieldTopBar
import com.example.ui.screens.AdminScreen
import com.example.ui.screens.AssistantScreen
import com.example.ui.screens.ChecklistScreen
import com.example.ui.screens.EmergencyScreen
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.LibraryScreen
import com.example.ui.screens.LiveVoiceScreen
import com.example.ui.screens.QuizScreen
import com.example.ui.screens.ResourcesScreen
import com.example.ui.screens.ScamDetailScreen
import com.example.ui.screens.SeniorCenterScreen
import com.example.ui.screens.SettingsScreen
import com.example.ui.screens.SimulatorScreen
import com.example.ui.screens.StudentCenterScreen
import com.example.ui.theme.RiskHighRed
import com.example.ui.theme.ScamShieldTheme

class MainActivity : ComponentActivity() {

    private val viewModel: ScamShieldViewModel by viewModels()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val isDarkMode by viewModel.prefs.isDarkMode.collectAsState()
            val isHighContrast by viewModel.prefs.isHighContrast.collectAsState()
            val fontScale by viewModel.prefs.fontScale.collectAsState()
            val currentScreen by viewModel.currentScreen.collectAsState()
            val selectedModule by viewModel.selectedModule.collectAsState()

            val currentDensity = LocalDensity.current
            val customDensity = Density(
                density = currentDensity.density,
                fontScale = currentDensity.fontScale * fontScale
            )

            CompositionLocalProvider(LocalDensity provides customDensity) {
                ScamShieldTheme(
                    darkTheme = isDarkMode,
                    highContrast = isHighContrast
                ) {
                    var showMoreSheet by remember { mutableStateOf(false) }
                    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        topBar = {
                            ScamShieldTopBar(
                                viewModel = viewModel,
                                onEmergencyClick = { viewModel.navigateTo(ScreenRoute.EMERGENCY) }
                            )
                        },
                        bottomBar = {
                            NavigationBar(
                                modifier = Modifier
                                    .windowInsetsPadding(WindowInsets.navigationBars)
                                    .testTag("bottom_nav_bar")
                            ) {
                                NavigationBarItem(
                                    selected = currentScreen == ScreenRoute.HOME,
                                    onClick = { viewModel.navigateTo(ScreenRoute.HOME) },
                                    icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                                    label = { Text("Home") },
                                    modifier = Modifier.testTag("nav_home")
                                )
                                NavigationBarItem(
                                    selected = currentScreen == ScreenRoute.LIBRARY || currentScreen == ScreenRoute.DETAIL,
                                    onClick = { viewModel.navigateTo(ScreenRoute.LIBRARY) },
                                    icon = { Icon(Icons.Default.MenuBook, contentDescription = "Library") },
                                    label = { Text("Library") },
                                    modifier = Modifier.testTag("nav_library")
                                )
                                NavigationBarItem(
                                    selected = currentScreen == ScreenRoute.ASSISTANT || currentScreen == ScreenRoute.LIVE_VOICE,
                                    onClick = { viewModel.navigateTo(ScreenRoute.ASSISTANT) },
                                    icon = { Icon(Icons.Default.AutoAwesome, contentDescription = "AI Assistant") },
                                    label = { Text("AI Scam") },
                                    modifier = Modifier.testTag("nav_assistant")
                                )
                                NavigationBarItem(
                                    selected = currentScreen == ScreenRoute.SIMULATOR,
                                    onClick = { viewModel.navigateTo(ScreenRoute.SIMULATOR) },
                                    icon = { Icon(Icons.Default.Security, contentDescription = "Simulator") },
                                    label = { Text("Simulator") },
                                    modifier = Modifier.testTag("nav_simulator")
                                )
                                NavigationBarItem(
                                    selected = currentScreen in listOf(
                                        ScreenRoute.CHECKLIST,
                                        ScreenRoute.QUIZ,
                                        ScreenRoute.STUDENT_CENTER,
                                        ScreenRoute.SENIOR_CENTER,
                                        ScreenRoute.RESOURCES,
                                        ScreenRoute.SETTINGS,
                                        ScreenRoute.ADMIN,
                                        ScreenRoute.EMERGENCY
                                    ),
                                    onClick = { showMoreSheet = true },
                                    icon = { Icon(Icons.Default.MoreHoriz, contentDescription = "More Tools") },
                                    label = { Text("Tools") },
                                    modifier = Modifier.testTag("nav_more")
                                )
                            }
                        }
                    ) { innerPadding ->
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(innerPadding)
                        ) {
                            when (currentScreen) {
                                ScreenRoute.HOME -> HomeScreen(viewModel = viewModel)
                                ScreenRoute.LIBRARY -> LibraryScreen(viewModel = viewModel)
                                ScreenRoute.DETAIL -> {
                                    if (selectedModule != null) {
                                        ScamDetailScreen(module = selectedModule!!, viewModel = viewModel)
                                    } else {
                                        LibraryScreen(viewModel = viewModel)
                                    }
                                }
                                ScreenRoute.SIMULATOR -> SimulatorScreen(viewModel = viewModel)
                                ScreenRoute.QUIZ -> QuizScreen(viewModel = viewModel)
                                ScreenRoute.ASSISTANT -> AssistantScreen(viewModel = viewModel)
                                ScreenRoute.LIVE_VOICE -> LiveVoiceScreen(viewModel = viewModel)
                                ScreenRoute.STUDENT_CENTER -> StudentCenterScreen(viewModel = viewModel)
                                ScreenRoute.SENIOR_CENTER -> SeniorCenterScreen(viewModel = viewModel)
                                ScreenRoute.CHECKLIST -> ChecklistScreen(viewModel = viewModel)
                                ScreenRoute.EMERGENCY -> EmergencyScreen(viewModel = viewModel)
                                ScreenRoute.RESOURCES -> ResourcesScreen(viewModel = viewModel)
                                ScreenRoute.SETTINGS -> SettingsScreen(viewModel = viewModel)
                                ScreenRoute.ADMIN -> AdminScreen(viewModel = viewModel)
                            }
                        }

                        // More Tools Bottom Sheet
                        if (showMoreSheet) {
                            ModalBottomSheet(
                                onDismissRequest = { showMoreSheet = false },
                                sheetState = sheetState
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 20.dp, vertical = 10.dp)
                                ) {
                                    Text(
                                        text = "ScamShield Safety Hub",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 18.sp,
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                    Spacer(modifier = Modifier.height(14.dp))

                                    // Tools List
                                    MoreMenuItem(
                                        icon = Icons.Default.Quiz,
                                        title = "Safety Quizzes & Challenges",
                                        subtitle = "Test your fraud awareness & earn badges",
                                        onClick = {
                                            showMoreSheet = false
                                            viewModel.navigateTo(ScreenRoute.QUIZ)
                                        }
                                    )
                                    MoreMenuItem(
                                        icon = Icons.Default.CheckCircle,
                                        title = "Safe Digital Checklist",
                                        subtitle = "Audit mobile, banking & password security",
                                        onClick = {
                                            showMoreSheet = false
                                            viewModel.navigateTo(ScreenRoute.CHECKLIST)
                                        }
                                    )
                                    MoreMenuItem(
                                        icon = Icons.Default.School,
                                        title = "Student Learning Center",
                                        subtitle = "Internships, gaming, scholarships & campus safety",
                                        onClick = {
                                            showMoreSheet = false
                                            viewModel.navigateTo(ScreenRoute.STUDENT_CENTER)
                                        }
                                    )
                                    MoreMenuItem(
                                        icon = Icons.Default.Elderly,
                                        title = "Senior Citizen Learning Center",
                                        subtitle = "Large print, audio voice guides & fraud call defense",
                                        onClick = {
                                            showMoreSheet = false
                                            viewModel.navigateTo(ScreenRoute.SENIOR_CENTER)
                                        }
                                    )
                                    MoreMenuItem(
                                        icon = Icons.Default.Emergency,
                                        title = "Emergency Reporting & 1930",
                                        subtitle = "Step-by-step actions if you lost money",
                                        tint = RiskHighRed,
                                        onClick = {
                                            showMoreSheet = false
                                            viewModel.navigateTo(ScreenRoute.EMERGENCY)
                                        }
                                    )
                                    MoreMenuItem(
                                        icon = Icons.Default.VerifiedUser,
                                        title = "Official Verified Resources",
                                        subtitle = "Cybercrime portal, RBI, Sanchar Saathi & CERT-In",
                                        onClick = {
                                            showMoreSheet = false
                                            viewModel.navigateTo(ScreenRoute.RESOURCES)
                                        }
                                    )
                                    MoreMenuItem(
                                        icon = Icons.Default.Settings,
                                        title = "Settings & Accessibility",
                                        subtitle = "Languages (Kannada/Hindi), themes & text scale",
                                        onClick = {
                                            showMoreSheet = false
                                            viewModel.navigateTo(ScreenRoute.SETTINGS)
                                        }
                                    )

                                    Spacer(modifier = Modifier.height(30.dp))
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MoreMenuItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    subtitle: String,
    tint: androidx.compose.ui.graphics.Color = MaterialTheme.colorScheme.primary,
    onClick: () -> Unit
) {
    Surface(
        color = MaterialTheme.colorScheme.surface,
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 4.dp)
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(38.dp)
                    .background(tint.copy(alpha = 0.12f), RoundedCornerShape(10.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(icon, contentDescription = null, tint = tint, modifier = Modifier.size(20.dp))
            }
            Spacer(modifier = Modifier.width(14.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = subtitle,
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}
