package alvaro.exposito.mykotlincleanexample.domain.usecase

import alvaro.exposito.mykotlincleanexample.domain.model.Sport
import alvaro.exposito.mykotlincleanexample.domain.repository.SportRepository
import javax.inject.Inject

class GetSportsUseCase
    @Inject
    constructor(
        private val repository: SportRepository,
    ) {
        suspend operator fun invoke(): Result<List<Sport>> = repository.getSports()
    }
