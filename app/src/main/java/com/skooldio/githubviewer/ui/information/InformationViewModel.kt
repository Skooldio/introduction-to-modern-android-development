package com.skooldio.githubviewer.ui.information

import androidx.lifecycle.ViewModel
import com.skooldio.githubviewer.domain.GetGitHubUseCase
import com.skooldio.githubviewer.domain.PublicUserInformation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

data class InformationUiState(
    val publicUserInformation: PublicUserInformation?,
)

@HiltViewModel
class InformationViewModel @Inject constructor(
    private val getGitHubUseCase: GetGitHubUseCase,
) : ViewModel() {
    private val _uiState: MutableStateFlow<InformationUiState?> = MutableStateFlow(null)
    val uiState: StateFlow<InformationUiState?> = _uiState

    fun loadInformation(id: String) {
        // TODO 10: Get data from GetGitHubUseCase

        // TODO 13: Handle GetGitHubUseCase result

    }
}
