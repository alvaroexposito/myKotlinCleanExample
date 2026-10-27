package alvaro.exposito.mykotlincleanexample.presentation.sportdetail

import alvaro.exposito.mykotlincleanexample.domain.model.Sport
import alvaro.exposito.mykotlincleanexample.domain.usecase.FakeSportRepository
import alvaro.exposito.mykotlincleanexample.domain.usecase.GetSportDetailUseCase
import alvaro.exposito.mykotlincleanexample.testutil.MainDispatcherExtension
import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension

class SportDetailViewModelTest {
    @JvmField
    @RegisterExtension
    val mainDispatcher = MainDispatcherExtension()

    private val athletics = Sport(9, "Athletics", 90)
    private val repository = FakeSportRepository(sports = listOf(athletics))

    private fun viewModel(id: Int) =
        SportDetailViewModel(
            SavedStateHandle(mapOf(SportDetailViewModel.ARG_ID to id)),
            GetSportDetailUseCase(repository),
        )

    @Test
    fun `AC-6 shows the sport for a known id`() =
        runTest {
            viewModel(9).uiState.test {
                assertEquals(SportDetailUiState.Loading, awaitItem())
                assertEquals(SportDetailUiState.Found(athletics), awaitItem())
            }
        }

    @Test
    fun `AC-7 shows NotFound for an unknown id`() =
        runTest {
            viewModel(4).uiState.test {
                assertEquals(SportDetailUiState.Loading, awaitItem())
                assertEquals(SportDetailUiState.NotFound, awaitItem())
            }
        }
}
