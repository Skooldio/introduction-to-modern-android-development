package com.skooldio.githubviewer.domain

import com.skooldio.githubviewer.data.response.RepositoryResponse
import com.skooldio.githubviewer.data.response.UserResponse

data class User(
    val id: String,
    val name: String?,
    val avatarUrl: String,
    val type: String,
    val bio: String?,
    val publicRepositories: Int,
    val publicGists: Int,
    val followers: Int,
    val following: Int,
    val createdAt: String,
)

data class Repository(
    val name: String?,
    val description: String?,
    val language: String?,
    val forks: Int,
    val watchers: Int,
    val archived: Boolean,
    val disabled: Boolean,
    val license: String?,
)

fun UserResponse.toUser() = User(
    id = this.login,
    name = this.name,
    avatarUrl = this.avatarUrl,
    type = this.type,
    bio = this.bio,
    publicRepositories = this.publicRepositories,
    publicGists = this.publicGists,
    followers = this.followers,
    following = this.following,
    createdAt = this.createdAt,
)

fun RepositoryResponse.toRepository() = Repository(
    name = this.name,
    description = this.description,
    language = this.language,
    forks = this.forks,
    watchers = this.watchers,
    archived = this.archived,
    disabled = this.disabled,
    license = this.license?.name,
)

data class PublicUserInformation(
    val user: User,
    val repositories: List<Repository>,
)
