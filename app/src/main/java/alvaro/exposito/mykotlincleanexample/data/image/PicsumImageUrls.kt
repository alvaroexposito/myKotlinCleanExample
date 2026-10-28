package alvaro.exposito.mykotlincleanexample.data.image

/**
 * Builds picsum.photos URLs for a sport photo from a stable seed (spec 002, D-1).
 * Both sizes use the same seed, so the tile and the detail show the same photo.
 */
object PicsumImageUrls {
    private const val BASE = "https://picsum.photos/seed"

    // Grid tile: half the screen width at the tile's 2:1 aspect ratio.
    private const val TILE_WIDTH = 600
    private const val TILE_HEIGHT = 300

    // Detail: full width, top third of the screen.
    private const val DETAIL_WIDTH = 1200
    private const val DETAIL_HEIGHT = 800

    fun tile(seed: String): String = "$BASE/$seed/$TILE_WIDTH/$TILE_HEIGHT"

    fun detail(seed: String): String = "$BASE/$seed/$DETAIL_WIDTH/$DETAIL_HEIGHT"
}
