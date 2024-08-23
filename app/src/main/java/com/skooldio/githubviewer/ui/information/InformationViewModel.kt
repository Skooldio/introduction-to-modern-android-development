package com.skooldio.githubviewer.ui.information

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skooldio.githubviewer.domain.GetGitHubUseCase
import com.skooldio.githubviewer.domain.PublicUserInformation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class InformationUiState {
    data object Loading : InformationUiState()
    data object Error : InformationUiState()
    data class Success(val publicUserInformation: PublicUserInformation) : InformationUiState()
}

@HiltViewModel
class InformationViewModel @Inject constructor(
    private val getGitHubUseCase: GetGitHubUseCase,
) : ViewModel() {
    private val _uiState: MutableStateFlow<InformationUiState> = MutableStateFlow(InformationUiState.Loading)
    val uiState: StateFlow<InformationUiState> = _uiState

    fun loadInformation(id: String) {
        _uiState.update { InformationUiState.Loading }
        viewModelScope.launch {
            getGitHubUseCase.invoke(
                GetGitHubUseCase.Params(id = id)
            )
                .onRight { result ->
                    _uiState.update { InformationUiState.Success(publicUserInformation = result) }
                }
                .onLeft {
                    _uiState.update { InformationUiState.Error }
                }
        }
    }
}
