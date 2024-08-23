package com.skooldio.githubviewer.ui.information

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skooldio.githubviewer.domain.GetGitHubUseCase
import com.skooldio.githubviewer.domain.PublicUserInformation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

// TODO 16: Add loading and error states supports
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
        viewModelScope.launch {
            getGitHubUseCase.execute(
                GetGitHubUseCase.Params(id = id)
            )
        }
        // TODO 13: Handle GetGitHubUseCase result
        // TODO 16: Add loading and error states supports
    }
}
