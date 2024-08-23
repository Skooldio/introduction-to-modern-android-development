package com.skooldio.githubviewer.domain

import com.skooldio.githubviewer.data.GitHubRepository
import javax.inject.Inject

class GetGitHubUseCase @Inject constructor(
    private val gitHubRepository: GitHubRepository,
) : UseCase<GetGitHubUseCase.Params, PublicUserInformation> {
    data class Params(
        val id: String,
    )

    override suspend fun execute(input: Params): PublicUserInformation {
        return PublicUserInformation(
            user = gitHubRepository.getUser(input.id).toUser(),
            repositories = gitHubRepository.getRepositories(input.id).map { it.toRepository() },
        )
    }
}
