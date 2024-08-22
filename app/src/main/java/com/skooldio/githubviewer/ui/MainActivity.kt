package com.skooldio.githubviewer.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.skooldio.githubviewer.ui.home.HOME_ROUTE
import com.skooldio.githubviewer.ui.home.home
import com.skooldio.githubviewer.ui.information.information
import com.skooldio.githubviewer.ui.theme.GitHubViewerTheme
import com.skooldio.githubviewer.ui.theme.MaterialColors
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GitHubViewerTheme {
                DisposableEffect(Unit) {
                    enableEdgeToEdge(
                        statusBarStyle = SystemBarStyle.light(
                            MaterialColors.Orange700.toArgb(),
                            MaterialColors.Orange300.toArgb(),
                        ),
                        navigationBarStyle = SystemBarStyle.auto(
                            MaterialColors.White.copy(alpha = 0.75f).toArgb(),
                            0,
                        ) { false },
                    )
                    onDispose {}
                }
                GitHubViewerApp()
            }
        }

        // TODO 1: Fix app crashes by removing the below code
        throw NullPointerException("Welcome to Logcat 101")
    }
}

@Composable
fun GitHubViewerApp() {
    val navController = rememberNavController()
    Scaffold { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            NavHost(
                navController = navController,
                startDestination = HOME_ROUTE,
            ) {
                home(navController = navController)
                information(navController = navController)
            }
        }
    }
}
