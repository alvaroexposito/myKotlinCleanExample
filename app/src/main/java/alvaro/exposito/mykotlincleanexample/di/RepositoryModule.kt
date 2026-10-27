package alvaro.exposito.mykotlincleanexample.di

import alvaro.exposito.mykotlincleanexample.data.repository.SportRepositoryImpl
import alvaro.exposito.mykotlincleanexample.domain.repository.SportRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindSportRepository(impl: SportRepositoryImpl): SportRepository
}
