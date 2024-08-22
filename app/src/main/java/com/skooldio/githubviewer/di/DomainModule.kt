package com.skooldio.githubviewer.di

import com.skooldio.githubviewer.data.GitHubRepository
import com.skooldio.githubviewer.domain.GetGitHubUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object DomainModule {
    @Provides
    fun provideGetGitHubUseCase(repository: GitHubRepository): GetGitHubUseCase {
        return GetGitHubUseCase(repository)
    }
}
