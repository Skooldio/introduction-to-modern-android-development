package com.skooldio.githubviewer.domain

import com.skooldio.githubviewer.data.response.RepositoryResponse
import com.skooldio.githubviewer.data.response.UserResponse

data class User(
    val id: String,
    val name: String?,
    // TODO 8: Add missing parameters
)

data class Repository(
    val name: String?,
    // TODO 9: Add missing parameters
)

fun UserResponse.toUser() = User(
    id = this.login,
    name = this.name,
    // TODO 8: Map more parameters
)

fun RepositoryResponse.toRepository() = Repository(
    name = this.name,
    // TODO 9: Map more parameters
)

data class PublicUserInformation(
    val user: User,
    val repositories: List<Repository>,
)
