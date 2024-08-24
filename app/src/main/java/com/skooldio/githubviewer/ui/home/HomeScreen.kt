package com.skooldio.githubviewer.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.skooldio.githubviewer.R
import com.skooldio.githubviewer.ui.component.Header
import com.skooldio.githubviewer.ui.information.INFORMATION_ROUTE
import com.skooldio.githubviewer.ui.theme.GitHubViewerTheme
import com.skooldio.githubviewer.ui.theme.MaterialColors

const val HOME_ROUTE = "home"

fun NavGraphBuilder.home(
    navController: NavController,
) {
    composable(HOME_ROUTE) {
        HomeRoute(navController)
    }
}

@Composable
private fun HomeRoute(navController: NavController) {
    HomeScreen(
        onAndroidButtonClicked = {
            // TODO 17: Navigate and pass data to information screen
            // TODO 5: Navigate to information screen

        },
        onSkooldioButtonClicked = {
            // TODO 17: Navigate and pass data to information screen

        }
    )
}

@Composable
private fun HomeScreen(
    onAndroidButtonClicked: () -> Unit,
    onSkooldioButtonClicked: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialColors.White),
    ) {
        // TODO 3: Add image from drawable resource and title from string resource
        Header(title = "")

        // TODO 4: Add 2 buttons
        // https://www.figma.com/design/PZVok42lKzIwCHTwlp9Nfg/In-House%3A-Introduction-to-Modern-Android-Development
        
    }
}

// TODO 2: Add preview for HomeScreen
