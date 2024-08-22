package com.skooldio.githubviewer.data

import com.skooldio.githubviewer.data.response.RepositoryResponse
import com.skooldio.githubviewer.data.response.UserResponse
import javax.inject.Inject

interface GitHubRepository {
    suspend fun getUser(id: String): UserResponse

    suspend fun getRepositories(id: String): List<RepositoryResponse>
}

class DefaultGitHubRepository @Inject constructor(
    private val gitHubApi: GitHubApi,
) : GitHubRepository {
    override suspend fun getUser(id: String): UserResponse = gitHubApi.getUser(id)

    override suspend fun getRepositories(id: String): List<RepositoryResponse> = gitHubApi.getRepositories(id)
}
