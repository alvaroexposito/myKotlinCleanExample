package alvaro.exposito.mykotlincleanexample.presentation.sportlist

import alvaro.exposito.mykotlincleanexample.domain.model.Sport

sealed interface SportListUiState {
    data object Loading : SportListUiState

    data class Success(
        val sports: List<Sport>,
    ) : SportListUiState

    data class Error(
        val message: String?,
    ) : SportListUiState
}
