package alvaro.exposito.mykotlincleanexample.data.image

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class PicsumImageUrlsTest {
    private val sizePattern = Regex("""^https://picsum\.photos/seed/([^/]+)/(\d+)/(\d+)$""")

    private fun parts(url: String): Triple<String, Int, Int> {
        val match = requireNotNull(sizePattern.matchEntire(url)) { "Not a picsum seed URL: $url" }
        val (seed, width, height) = match.destructured
        return Triple(seed, width.toInt(), height.toInt())
    }

    @Test
    fun `AC-1 tile URL is https picsum seed URL`() {
        val url = PicsumImageUrls.tile("football")

        assertTrue(url.startsWith("https://"), url)
        assertEquals("football", parts(url).first)
    }

    @Test
    fun `AC-3 tile and detail URLs share the same seed`() {
        assertEquals(parts(PicsumImageUrls.tile("karate")).first, parts(PicsumImageUrls.detail("karate")).first)
    }

    @Test
    fun `AC-3 detail URL is larger than tile URL`() {
        val tile = parts(PicsumImageUrls.tile("karate"))
        val detail = parts(PicsumImageUrls.detail("karate"))

        assertTrue(detail.second > tile.second && detail.third > tile.third)
    }
}
