package alvaro.exposito.mykotlincleanexample.domain.repository

import alvaro.exposito.mykotlincleanexample.domain.model.Sport

/** Source of sports. Implementations report failures through [Result] instead of throwing. */
interface SportRepository {
    /** All sports, each exactly once, ordered by id. */
    suspend fun getSports(): Result<List<Sport>>

    /** The sport with [id], or `null` when no sport has that id. */
    suspend fun getSport(id: Int): Result<Sport?>
}
