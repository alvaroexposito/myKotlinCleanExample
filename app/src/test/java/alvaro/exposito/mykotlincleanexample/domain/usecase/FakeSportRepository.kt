package alvaro.exposito.mykotlincleanexample.domain.usecase

import alvaro.exposito.mykotlincleanexample.domain.model.Sport
import alvaro.exposito.mykotlincleanexample.domain.repository.SportRepository

class FakeSportRepository(
    var sports: List<Sport> = emptyList(),
    var failure: Throwable? = null,
) : SportRepository {
    override suspend fun getSports(): Result<List<Sport>> = failure?.let { Result.failure(it) } ?: Result.success(sports)

    override suspend fun getSport(id: Int): Result<Sport?> =
        failure?.let { Result.failure(it) } ?: Result.success(sports.firstOrNull { it.id == id })
}
