package alvaro.exposito.mykotlincleanexample.data.local

import alvaro.exposito.mykotlincleanexample.R
import alvaro.exposito.mykotlincleanexample.data.image.PicsumImageUrls
import alvaro.exposito.mykotlincleanexample.domain.model.Sport
import javax.inject.Inject

/**
 * In-memory sports catalog (spec 001, D-1). Each sport has a picsum photo seeded by its name
 * (spec 002, D-1) and a bundled icon as placeholder and fallback (spec 002, D-2 and D-3).
 */
class LocalSportDataSource
    @Inject
    constructor() {
        fun getSports(): List<Sport> = SPORTS

        private companion object {
            val SPORTS =
                listOf(
                    sport(1, "Baseball", R.drawable.ic_sport_baseball),
                    sport(2, "Windsurfing", R.drawable.ic_sport_windsurfing),
                    sport(3, "Cycling", R.drawable.ic_sport_cycling),
                    sport(5, "Motorcycle", R.drawable.ic_sport_motorcycle),
                    sport(6, "Football", R.drawable.ic_sport_football),
                    sport(7, "Swimming", R.drawable.ic_sport_swimming),
                    sport(8, "Karate", R.drawable.ic_sport_karate),
                    sport(9, "Athletics", R.drawable.ic_sport_athletics),
                )

            fun sport(
                id: Int,
                name: String,
                imageRes: Int,
            ): Sport {
                val seed = name.lowercase()
                return Sport(id, name, imageRes, PicsumImageUrls.tile(seed), PicsumImageUrls.detail(seed))
            }
        }
    }
