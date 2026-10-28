package alvaro.exposito.mykotlincleanexample.data.local

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class LocalSportDataSourceTest {
    private val sports = LocalSportDataSource().getSports()

    @Test
    fun `AC-1 contains each of the 8 sports exactly once, ordered by id`() {
        assertEquals(listOf(1, 2, 3, 5, 6, 7, 8, 9), sports.map { it.id })
    }

    @Test
    fun `AC-4 every sport has a bundled drawable`() {
        assertTrue(sports.all { it.imageRes != 0 })
        assertEquals(sports.size, sports.map { it.imageRes }.toSet().size)
    }

    @Test
    fun `AC-1 every sport has a picsum tile URL`() {
        assertTrue(sports.all { it.tileImageUrl.startsWith("https://picsum.photos/seed/") }, sports.toString())
        assertTrue(sports.all { it.detailImageUrl.startsWith("https://picsum.photos/seed/") }, sports.toString())
    }

    @Test
    fun `AC-2 image URLs are stable across calls`() {
        val again = LocalSportDataSource().getSports()

        assertEquals(
            sports.map { it.tileImageUrl to it.detailImageUrl },
            again.map { it.tileImageUrl to it.detailImageUrl },
        )
    }

    @Test
    fun `AC-2 each sport has a different seed`() {
        assertEquals(sports.size, sports.map { it.tileImageUrl }.toSet().size)
        assertEquals(sports.size, sports.map { it.detailImageUrl }.toSet().size)
    }

    @Test
    fun `AC-6 names follow baseline B-4 with Athletics fixed`() {
        assertEquals(
            listOf("Baseball", "Windsurfing", "Cycling", "Motorcycle", "Football", "Swimming", "Karate", "Athletics"),
            sports.map { it.name },
        )
    }
}
