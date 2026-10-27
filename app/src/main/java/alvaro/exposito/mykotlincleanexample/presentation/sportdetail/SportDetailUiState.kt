package alvaro.exposito.mykotlincleanexample.presentation.sportdetail

import alvaro.exposito.mykotlincleanexample.domain.model.Sport

sealed interface SportDetailUiState {
    data object Loading : SportDetailUiState

    data class Found(
        val sport: Sport,
    ) : SportDetailUiState

    /** No sport has the requested id; the screen shows "No info" (AC-7). */
    data object NotFound : SportDetailUiState

    data class Error(
        val message: String?,
    ) : SportDetailUiState
}
