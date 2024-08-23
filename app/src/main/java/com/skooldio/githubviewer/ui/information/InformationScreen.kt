package com.skooldio.githubviewer.ui.information

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.skooldio.githubviewer.R
import com.skooldio.githubviewer.domain.PublicUserInformation
import com.skooldio.githubviewer.domain.Repository
import com.skooldio.githubviewer.domain.User
import com.skooldio.githubviewer.ui.component.Header
import com.skooldio.githubviewer.ui.theme.GitHubViewerTheme
import com.skooldio.githubviewer.ui.theme.MaterialColors

const val INFORMATION_ROUTE = "information"

fun NavGraphBuilder.information(
    navController: NavController,
) {
    composable(INFORMATION_ROUTE) {
        InformationRoute(navController)
    }
}

@Composable
private fun InformationRoute(
    navController: NavController,
    id: String = "android",
) {
    val viewModel: InformationViewModel = hiltViewModel()

    // TODO 14: Collect UI state from InformationViewModel
    val uiState: InformationUiState? = null

    LaunchedEffect(Unit) {
        viewModel.loadInformation(id)
    }

    InformationScreen(
        uiState = uiState,
        id = id,
        onBackButtonClick = {
            // TODO 5: Back to previous screen

        },
    )
}

@Composable
private fun InformationScreen(
    uiState: InformationUiState?,
    id: String,
    onBackButtonClick: () -> Unit,
) {
    val info = uiState?.publicUserInformation
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialColors.White)
    ) {
        Header(
            title = stringResource(R.string.information_title),
            backIcon = true,
            onButtonClick = onBackButtonClick,
        )
        // TODO 16: Add loading and error UIs
        UserProfile(
            id = id,
            name = info?.user?.name ?: "",
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(2.dp)
                .background(color = MaterialColors.Gray200)
        )
        RepositoryList(repositories = info?.repositories)
    }
}

@Composable
private fun UserProfile(id: String, name: String) {
    // TODO 8: Build UI for user information

}

@Composable
private fun RepositoryList(
    repositories: List<Repository>?
) {
    // TODO 15: Replace Column with LazyColumn
    // TODO 9: Build UI for list of repositories

}

@Composable
private fun RepositoryItem(repository: Repository) {
    // TODO 9: Build UI for repository information

}

@Composable
private fun ErrorContent() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(text = stringResource(R.string.unknown_error_message))
    }
}

@Composable
private fun LoadingContent() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator()
    }
}

@Preview
@Composable
private fun UserProfilePreview() {
    GitHubViewerTheme {
        Box(modifier = Modifier.background(MaterialColors.White)) {
            UserProfile(
                id = "android",
                name = "Android",
            )
        }
    }
}

@Preview
@Composable
private fun RepositoryItemPreview() {
    GitHubViewerTheme {
        Box(modifier = Modifier.background(MaterialColors.White)) {
            RepositoryItem()
        }
    }
}

@Preview(widthDp = 200, heightDp = 200)
@Composable
private fun LoadingContentPreview() {
    GitHubViewerTheme {
        Box(modifier = Modifier.background(MaterialColors.White)) {
            LoadingContent()
        }
    }
}

@Preview(widthDp = 300, heightDp = 200)
@Composable
private fun ErrorContentPreview() {
    GitHubViewerTheme {
        Box(modifier = Modifier.background(MaterialColors.White)) {
            ErrorContent()
        }
    }
}

@Preview(heightDp = 400)
@Composable
private fun InformationScreenPreview() {
    val uiState = InformationUiState(
        publicUserInformation = PublicUserInformation(
            user = User(
                id = "android",
                name = "Android",
            ),
            repositories = listOf(),
        )
    )
    GitHubViewerTheme {
        InformationScreen(
            uiState = uiState,
            id = "android",
            onBackButtonClick = {},
        )
    }
}
