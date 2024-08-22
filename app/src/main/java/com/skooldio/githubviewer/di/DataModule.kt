package com.skooldio.githubviewer.di

import com.skooldio.githubviewer.data.DefaultGitHubRepository
import com.skooldio.githubviewer.data.GitHubApi
import com.skooldio.githubviewer.data.GitHubRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object DataModule {
    @Provides
    fun provideGitHubRepository(gitHubApi: GitHubApi): GitHubRepository {
        return DefaultGitHubRepository(gitHubApi)
    }
}
