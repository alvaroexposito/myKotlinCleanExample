package alvaro.exposito.mykotlincleanexample.presentation.sportdetail

import alvaro.exposito.mykotlincleanexample.domain.usecase.GetSportDetailUseCase
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SportDetailViewModel
    @Inject
    constructor(
        savedStateHandle: SavedStateHandle,
        private val getSportDetail: GetSportDetailUseCase,
    ) : ViewModel() {
        private val id: Int = checkNotNull(savedStateHandle[ARG_ID])

        private val _uiState = MutableStateFlow<SportDetailUiState>(SportDetailUiState.Loading)
        val uiState: StateFlow<SportDetailUiState> = _uiState.asStateFlow()

        init {
            viewModelScope.launch {
                _uiState.value =
                    getSportDetail(id).fold(
                        onSuccess = { sport -> sport?.let { SportDetailUiState.Found(it) } ?: SportDetailUiState.NotFound },
                        onFailure = { SportDetailUiState.Error(it.message) },
                    )
            }
        }

        companion object {
            /** Must match the property name in `SportDetailRoute`. */
            const val ARG_ID = "id"
        }
    }
