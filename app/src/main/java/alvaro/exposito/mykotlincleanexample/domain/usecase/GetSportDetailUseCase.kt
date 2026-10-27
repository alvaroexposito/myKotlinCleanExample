package alvaro.exposito.mykotlincleanexample.domain.usecase

import alvaro.exposito.mykotlincleanexample.domain.model.Sport
import alvaro.exposito.mykotlincleanexample.domain.repository.SportRepository
import javax.inject.Inject

/** Returns the sport with the given id, or `null` when it doesn't exist. */
class GetSportDetailUseCase
    @Inject
    constructor(
        private val repository: SportRepository,
    ) {
        suspend operator fun invoke(id: Int): Result<Sport?> = repository.getSport(id)
    }
