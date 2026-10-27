package alvaro.exposito.mykotlincleanexample.data.repository

import alvaro.exposito.mykotlincleanexample.data.local.LocalSportDataSource
import alvaro.exposito.mykotlincleanexample.domain.model.Sport
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class SportRepositoryImplTest {
    private val sports = listOf(Sport(1, "Baseball", 10), Sport(2, "Windsurfing", 20))
    private val dataSource = mockk<LocalSportDataSource> { every { getSports() } returns sports }
    private val repository = SportRepositoryImpl(dataSource)

    @Test
    fun `AC-1 getSports returns all sports`() =
        runTest {
            assertEquals(sports, repository.getSports().getOrThrow())
        }

    @Test
    fun `AC-6 getSport returns the matching sport`() =
        runTest {
            assertEquals(sports[1], repository.getSport(2).getOrThrow())
        }

    @Test
    fun `AC-7 getSport returns null for an unknown id`() =
        runTest {
            assertNull(repository.getSport(4).getOrThrow())
        }

    @Test
    fun `AC-3 failures are returned as Result failure instead of thrown`() =
        runTest {
            every { dataSource.getSports() } throws IllegalStateException("boom")

            assertTrue(repository.getSports().isFailure)
            assertTrue(repository.getSport(1).isFailure)
        }
}
