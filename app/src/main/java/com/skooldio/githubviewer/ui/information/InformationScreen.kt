package com.skooldio.githubviewer.ui.information

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import coil.compose.AsyncImage
import com.skooldio.githubviewer.R
import com.skooldio.githubviewer.domain.PublicUserInformation
import com.skooldio.githubviewer.domain.Repository
import com.skooldio.githubviewer.domain.User
import com.skooldio.githubviewer.ui.component.Header
import com.skooldio.githubviewer.ui.theme.GitHubViewerTheme
import com.skooldio.githubviewer.ui.theme.MaterialColors
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

const val INFORMATION_ROUTE = "information/{id}"

fun NavController.navigateToInformation(id: String) {
    navigate(INFORMATION_ROUTE.replace("{id}", id))
}

fun NavGraphBuilder.information(
    navController: NavController,
) {
    composable(
        INFORMATION_ROUTE,
        arguments = listOf(
            navArgument("id") { type = NavType.StringType }
        )
    ) { backStackEntry ->
        InformationRoute(
            navController = navController,
            id = backStackEntry.arguments?.getString("id") ?: "android"
        )
    }
}

@Composable
private fun InformationRoute(
    navController: NavController,
    id: String,
) {
    val viewModel: InformationViewModel = hiltViewModel()

    val uiState: InformationUiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadInformation(id)
    }

    InformationScreen(
        uiState = uiState,
        id = id,
        onBackButtonClick = {
            navController.popBackStack()
        },
    )
}

@Composable
private fun InformationScreen(
    uiState: InformationUiState,
    id: String,
    onBackButtonClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialColors.White)
    ) {
        when (uiState) {
            InformationUiState.Loading, null -> {
                LoadingContent()
            }

            InformationUiState.Error -> {
                ErrorContent()
            }

            is InformationUiState.Success -> {
                val info = uiState.publicUserInformation
                Header(
                    title = stringResource(R.string.information_title),
                    backIcon = true,
                    onButtonClick = onBackButtonClick,
                )
                UserProfile(user = info.user)
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(2.dp)
                        .background(color = MaterialColors.Gray200)
                )
                RepositoryList(repositories = info.repositories)
            }
        }
    }
}

@Composable
private fun UserProfile(user: User?) {
    user ?: return
    val since: String = user.createdAt
        .takeIf { it.isNotEmpty() }
        ?.let { createdAt ->
            Instant.parse(createdAt)
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime()
                .format(DateTimeFormatter.ofPattern("d MMMM yyyy"))
        } ?: ""

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row {
            AsyncImage(
                modifier = Modifier.size(80.dp),
                model = user.avatarUrl,
                contentDescription = "Avatar",
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = user.name ?: "",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = user.id,
                    fontSize = 18.sp,
                )
                Spacer(modifier = Modifier.height(8.dp))
                Box(
                    modifier = Modifier
                        .background(
                            shape = CircleShape,
                            color = MaterialColors.Blue500
                        )
                        .padding(horizontal = 8.dp, vertical = 1.dp)
                ) {
                    Text(
                        text = user.type,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        color = MaterialColors.White,
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(R.string.user_profile_since, since),
            fontSize = 16.sp,
        )
    }
}

@Composable
private fun RepositoryList(
    repositories: List<Repository>?
) {
    LazyColumn {
        items(items = repositories ?: listOf(), key = { it.name }) { repository ->
            RepositoryItem(repository)
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(2.dp)
                    .padding(horizontal = 16.dp)
                    .background(MaterialColors.Gray200)
            )
        }
    }
}

@Composable
private fun RepositoryItem(repository: Repository) {
    Column(Modifier.padding(16.dp)) {
        Text(
            text = repository.name,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
        )
        repository.description?.let { description ->
            Text(
                text = description,
                fontSize = 16.sp,
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row {
            repository.language?.let { language ->
                RepositoryBadge(
                    text = language,
                    color = MaterialColors.Green500,
                )
                Spacer(modifier = Modifier.width(8.dp))
            }
            repository.license?.let { license ->
                RepositoryBadge(
                    text = license,
                    color = MaterialColors.Green500,
                )
                Spacer(modifier = Modifier.width(8.dp))
            }
            if (repository.archived) {
                RepositoryBadge(
                    text = stringResource(R.string.repository_badge_archived),
                    color = MaterialColors.Yellow800,
                )
            }
        }
    }
}

@Composable
private fun RepositoryBadge(text: String, color: Color) {
    Box(
        modifier = Modifier
            .background(color = color, shape = CircleShape)
            .padding(horizontal = 8.dp, vertical = 2.dp)
    ) {
        Text(
            text = text,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            color = MaterialColors.White,
        )
    }
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
                User(
                    id = "android",
                    name = "Android",
                    avatarUrl = "https://avatars.githubusercontent.com/u/32689599?v=4",
                    type = "Organization",
                    createdAt = "2017-10-10T23:00:21Z",
                )
            )
        }
    }
}

@Preview
@Composable
private fun RepositoryItemPreview() {
    GitHubViewerTheme {
        Box(modifier = Modifier.background(MaterialColors.White)) {
            RepositoryItem(
                Repository(
                    name = "android-dev-challenge-compose",
                    description = "Template repository for the ADS22 Speed challenge",
                    language = "Kotlin",
                    license = "Apache License 2.0",
                    archived = true,
                )
            )
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
    val uiState = InformationUiState.Success(
        publicUserInformation = PublicUserInformation(
            user = User(
                id = "android",
                name = "Android",
                avatarUrl = "https://avatars.githubusercontent.com/u/32689599?v=4",
                type = "Organization",
                createdAt = "2017-10-10T23:00:21Z",
            ),
            repositories = listOf(
                Repository(
                    name = "Name",
                    description = "Description",
                    language = "Language",
                    license = "License",
                    archived = true,
                ),
                Repository(
                    name = "android-dev-challenge-compose",
                    description = "Template repository for the ADS22 Speed challenge",
                    language = "Kotlin",
                    license = "Apache License 2.0",
                    archived = true,
                ),
                Repository(
                    name = "android-ktx",
                    description = "A set of Kotlin extensions for Android app development.",
                    language = null,
                    license = null,
                    archived = true,
                ),
                Repository(
                    name = "architecture-templates",
                    description = null,
                    language = null,
                    license = "Apache License 2.0",
                    archived = false,
                ),
            ),
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
