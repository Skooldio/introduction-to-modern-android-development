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
        // TODO 12: Request data from GitHubRepository and return as PublicUserInformation
        return PublicUserInformation(
            user = User(
                id = "",
                name = "",
            ),
            repositories = listOf(),
        )
    }
}
