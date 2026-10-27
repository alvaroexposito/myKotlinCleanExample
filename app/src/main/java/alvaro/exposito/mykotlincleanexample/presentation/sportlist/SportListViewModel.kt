package alvaro.exposito.mykotlincleanexample.presentation.sportlist

import alvaro.exposito.mykotlincleanexample.domain.usecase.GetSportsUseCase
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SportListViewModel
    @Inject
    constructor(
        private val getSports: GetSportsUseCase,
    ) : ViewModel() {
        private val _uiState = MutableStateFlow<SportListUiState>(SportListUiState.Loading)
        val uiState: StateFlow<SportListUiState> = _uiState.asStateFlow()

        init {
            load()
        }

        fun retry() = load()

        private fun load() {
            _uiState.value = SportListUiState.Loading
            viewModelScope.launch {
                _uiState.value =
                    getSports().fold(
                        onSuccess = { SportListUiState.Success(it) },
                        onFailure = { SportListUiState.Error(it.message) },
                    )
            }
        }
    }
