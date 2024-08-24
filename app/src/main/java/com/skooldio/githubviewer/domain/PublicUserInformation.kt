package com.skooldio.githubviewer.domain

import com.skooldio.githubviewer.data.response.RepositoryResponse
import com.skooldio.githubviewer.data.response.UserResponse

data class User(
    val id: String,
    val name: String?,
    val avatarUrl: String,
    val type: String?,
    val createdAt: String,
)

data class Repository(
    val name: String,
    val description: String?,
    val language: String?,
    val license: String?,
    val archived: Boolean,
)

fun UserResponse.toUser() = User(
    id = this.login,
    name = this.name,
    avatarUrl = this.avatarUrl,
    type = this.type,
    createdAt = this.createdAt,
)

fun RepositoryResponse.toRepository() = Repository(
    name = this.name,
    description = this.description,
    language = this.language,
    license = this.license?.name,
    archived = this.archived,
)

data class PublicUserInformation(
    val user: User,
    val repositories: List<Repository>,
)
