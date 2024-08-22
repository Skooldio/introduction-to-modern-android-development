package com.skooldio.githubviewer.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
        Header(
            title = stringResource(R.string.home_title),
            leadingIcon = painterResource(R.drawable.ic_github)
        )
        Column(modifier = Modifier.padding(32.dp)) {
            Button(
                modifier = Modifier.height(40.dp),
                onClick = onAndroidButtonClicked,
            ) {
                Text(text = "Android")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                modifier = Modifier.height(40.dp),
                onClick = onSkooldioButtonClicked,
            ) {
                Text(text = "Skooldio")
            }
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    GitHubViewerTheme {
        HomeScreen(
            onAndroidButtonClicked = {},
            onSkooldioButtonClicked = {},
        )
    }
}
