package alvaro.exposito.mykotlincleanexample.domain.usecase

import alvaro.exposito.mykotlincleanexample.domain.model.Sport
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class GetSportsUseCaseTest {
    private val sports =
        listOf(
            Sport(1, "Baseball", 10, "https://example.test/tile/1", "https://example.test/detail/1"),
            Sport(2, "Windsurfing", 20, "https://example.test/tile/2", "https://example.test/detail/2"),
        )

    @Test
    fun `AC-1 returns the sports from the repository`() =
        runTest {
            val useCase = GetSportsUseCase(FakeSportRepository(sports = sports))

            assertEquals(Result.success(sports), useCase())
        }

    @Test
    fun `AC-3 propagates repository failure`() =
        runTest {
            val useCase = GetSportsUseCase(FakeSportRepository(failure = IllegalStateException("boom")))

            assertTrue(useCase().isFailure)
        }
}
