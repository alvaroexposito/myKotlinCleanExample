package alvaro.exposito.mykotlincleanexample.data.local

import alvaro.exposito.mykotlincleanexample.R
import alvaro.exposito.mykotlincleanexample.domain.model.Sport
import javax.inject.Inject

/** In-memory sports catalog with bundled placeholder images (spec 001, D-1 and D-2). */
class LocalSportDataSource
    @Inject
    constructor() {
        fun getSports(): List<Sport> = SPORTS

        private companion object {
            val SPORTS =
                listOf(
                    Sport(1, "Baseball", R.drawable.ic_sport_baseball),
                    Sport(2, "Windsurfing", R.drawable.ic_sport_windsurfing),
                    Sport(3, "Cycling", R.drawable.ic_sport_cycling),
                    Sport(5, "Motorcycle", R.drawable.ic_sport_motorcycle),
                    Sport(6, "Football", R.drawable.ic_sport_football),
                    Sport(7, "Swimming", R.drawable.ic_sport_swimming),
                    Sport(8, "Karate", R.drawable.ic_sport_karate),
                    Sport(9, "Athletics", R.drawable.ic_sport_athletics),
                )
        }
    }
