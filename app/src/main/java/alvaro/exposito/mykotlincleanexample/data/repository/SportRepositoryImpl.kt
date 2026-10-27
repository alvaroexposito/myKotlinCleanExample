package alvaro.exposito.mykotlincleanexample.data.repository

import alvaro.exposito.mykotlincleanexample.data.local.LocalSportDataSource
import alvaro.exposito.mykotlincleanexample.domain.model.Sport
import alvaro.exposito.mykotlincleanexample.domain.repository.SportRepository
import javax.inject.Inject

class SportRepositoryImpl
    @Inject
    constructor(
        private val dataSource: LocalSportDataSource,
    ) : SportRepository {
        override suspend fun getSports(): Result<List<Sport>> = runCatching { dataSource.getSports() }

        override suspend fun getSport(id: Int): Result<Sport?> =
            runCatching {
                dataSource.getSports().firstOrNull { it.id == id }
            }
    }
