package alvaro.exposito.mykotlincleanexample.domain.usecase

import alvaro.exposito.mykotlincleanexample.domain.model.Sport
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test

class GetSportDetailUseCaseTest {
    private val repository =
        FakeSportRepository(
            sports =
                listOf(
                    Sport(1, "Baseball", 10, "https://example.test/tile/1", "https://example.test/detail/1"),
                    Sport(9, "Athletics", 90, "https://example.test/tile/9", "https://example.test/detail/9"),
                ),
        )
    private val useCase = GetSportDetailUseCase(repository)

    @Test
    fun `AC-6 returns the sport for a known id`() =
        runTest {
            assertEquals(
                Sport(9, "Athletics", 90, "https://example.test/tile/9", "https://example.test/detail/9"),
                useCase(9).getOrThrow(),
            )
        }

    @Test
    fun `AC-7 returns null for an unknown id`() =
        runTest {
            assertNull(useCase(4).getOrThrow())
        }
}
