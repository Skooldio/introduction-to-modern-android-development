package com.skooldio.githubviewer.data

import com.skooldio.githubviewer.data.response.RepositoryResponse
import com.skooldio.githubviewer.data.response.UserResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubApi {
    // https://api.github.com/users/android
    // users/{user}
    @GET("users/{user}")
    suspend fun getUser(
        @Path("user") user: String
    ): UserResponse

    // https://api.github.com/users/android/repos
    // users/{user}/repos
    @GET("users/{user}/repos")
    suspend fun getRepositories(
        @Path("user") user: String
    ): List<RepositoryResponse>
}
