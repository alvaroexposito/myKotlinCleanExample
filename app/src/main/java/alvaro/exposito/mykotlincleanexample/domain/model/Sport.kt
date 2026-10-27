package alvaro.exposito.mykotlincleanexample.domain.model

/**
 * A sport shown in the list and detail screens.
 *
 * @property imageRes Android drawable resource id. Kept as a plain [Int] so the domain stays free of Android imports.
 */
data class Sport(
    val id: Int,
    val name: String,
    val imageRes: Int,
)
