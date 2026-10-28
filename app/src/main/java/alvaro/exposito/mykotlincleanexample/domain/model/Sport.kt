package alvaro.exposito.mykotlincleanexample.domain.model

/**
 * A sport shown in the list and detail screens.
 *
 * @property imageRes Android drawable resource id of the bundled icon, used as the placeholder while
 * [tileImageUrl] or [detailImageUrl] load and as the fallback when they fail (spec 002, D-2 and D-3).
 * Kept as a plain [Int] so the domain stays free of Android imports.
 * @property tileImageUrl Remote photo sized for a grid tile.
 * @property detailImageUrl The same remote photo, sized for the detail screen.
 */
data class Sport(
    val id: Int,
    val name: String,
    val imageRes: Int,
    val tileImageUrl: String,
    val detailImageUrl: String,
)
