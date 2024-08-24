package com.skooldio.githubviewer.data

import com.skooldio.githubviewer.data.response.RepositoryResponse
import com.skooldio.githubviewer.data.response.UserResponse

interface GitHubApi {
    // TODO 11: Add API path to get user information
    // https://api.github.com/users/android
    // users/{user}

    suspend fun getUser(
        // TODO 11: Refer the variable to API path
        user: String
    ): UserResponse

    // TODO 11: Add API path to get user repositories
    // https://api.github.com/users/android/repos
    // users/{user}/repos

    suspend fun getRepositories(
        // TODO 11: Refer the variable to API path
        user: String
    ): List<RepositoryResponse>
}
