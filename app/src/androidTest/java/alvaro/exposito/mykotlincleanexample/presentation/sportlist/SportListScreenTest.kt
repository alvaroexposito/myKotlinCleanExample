package alvaro.exposito.mykotlincleanexample.presentation.sportlist

import alvaro.exposito.mykotlincleanexample.domain.model.Sport
import alvaro.exposito.mykotlincleanexample.presentation.theme.SportsTheme
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class SportListScreenTest {
    @get:Rule
    val composeRule = createComposeRule()

    private val sports =
        listOf(
            Sport(1, "Baseball", android.R.drawable.ic_menu_gallery),
            Sport(2, "Windsurfing", android.R.drawable.ic_menu_gallery),
            Sport(3, "Cycling", android.R.drawable.ic_menu_gallery),
        )

    private fun setContent(
        state: SportListUiState,
        onRetry: () -> Unit = {},
        onSportClick: (Int) -> Unit = {},
    ) = composeRule.setContent {
        SportsTheme { SportListScreen(state, onRetry, onSportClick) }
    }

    @Test
    fun ac1_showsOneTilePerSportInTwoColumns() {
        setContent(SportListUiState.Success(sports))

        composeRule.onAllNodesWithTag(SportListTestTags.TILE).assertCountEquals(3)
        composeRule.onNodeWithContentDescription("Baseball").assertIsDisplayed()
        val first = composeRule.onNodeWithContentDescription("Baseball").fetchSemanticsNode().boundsInRoot
        val second = composeRule.onNodeWithContentDescription("Windsurfing").fetchSemanticsNode().boundsInRoot
        val third = composeRule.onNodeWithContentDescription("Cycling").fetchSemanticsNode().boundsInRoot
        assertEquals(first.top, second.top)
        assertTrue(third.top > first.top)
    }

    @Test
    fun ac2_showsLoadingIndicator() {
        setContent(SportListUiState.Loading)

        composeRule.onNodeWithTag(SportListTestTags.LOADING).assertIsDisplayed()
        composeRule.onAllNodesWithTag(SportListTestTags.TILE).assertCountEquals(0)
    }

    @Test
    fun ac3_showsErrorWithRetry() {
        var retried = false
        setContent(SportListUiState.Error("boom"), onRetry = { retried = true })

        composeRule.onNodeWithText("Couldn't load sports").assertIsDisplayed()
        composeRule.onNodeWithText("Retry").performClick()
        assertTrue(retried)
    }

    @Test
    fun ac5_tappingATileReportsItsId() {
        var clicked: Int? = null
        setContent(SportListUiState.Success(sports), onSportClick = { clicked = it })

        composeRule.onNodeWithContentDescription("Windsurfing").performClick()
        assertEquals(2, clicked)
    }
}
