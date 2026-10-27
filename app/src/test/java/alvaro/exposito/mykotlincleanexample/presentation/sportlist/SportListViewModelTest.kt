package alvaro.exposito.mykotlincleanexample.presentation.sportlist

import alvaro.exposito.mykotlincleanexample.domain.model.Sport
import alvaro.exposito.mykotlincleanexample.domain.usecase.FakeSportRepository
import alvaro.exposito.mykotlincleanexample.domain.usecase.GetSportsUseCase
import alvaro.exposito.mykotlincleanexample.testutil.MainDispatcherExtension
import app.cash.turbine.test
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension

class SportListViewModelTest {
    @JvmField
    @RegisterExtension
    val mainDispatcher = MainDispatcherExtension()

    private val sports = listOf(Sport(1, "Baseball", 10), Sport(2, "Windsurfing", 20))
    private val repository = FakeSportRepository(sports = sports)

    private fun viewModel() = SportListViewModel(GetSportsUseCase(repository))

    @Test
    fun `AC-2 starts in Loading, then AC-1 shows the sports`() =
        runTest {
            viewModel().uiState.test {
                assertEquals(SportListUiState.Loading, awaitItem())
                assertEquals(SportListUiState.Success(sports), awaitItem())
            }
        }

    @Test
    fun `AC-3 shows Error when the repository fails, and retry reloads the list`() =
        runTest {
            repository.failure = IllegalStateException("boom")
            val viewModel = viewModel()

            viewModel.uiState.test {
                assertEquals(SportListUiState.Loading, awaitItem())
                assertTrue(awaitItem() is SportListUiState.Error)

                repository.failure = null
                viewModel.retry()

                assertEquals(SportListUiState.Loading, awaitItem())
                assertEquals(SportListUiState.Success(sports), awaitItem())
            }
        }
}
