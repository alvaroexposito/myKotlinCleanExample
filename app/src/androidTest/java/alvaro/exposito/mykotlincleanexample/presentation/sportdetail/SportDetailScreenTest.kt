package alvaro.exposito.mykotlincleanexample.presentation.sportdetail

import alvaro.exposito.mykotlincleanexample.domain.model.Sport
import alvaro.exposito.mykotlincleanexample.presentation.theme.SportsTheme
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class SportDetailScreenTest {
    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun ac6_showsImageAboveName() {
        composeRule.setContent {
            SportsTheme { SportDetailScreen(SportDetailUiState.Found(Sport(9, "Athletics", android.R.drawable.ic_menu_gallery))) }
        }

        composeRule.onNodeWithText("Athletics").assertIsDisplayed()
        val image = composeRule.onNodeWithContentDescription("Athletics").assertIsDisplayed().fetchSemanticsNode().boundsInRoot
        val name = composeRule.onNodeWithText("Athletics").fetchSemanticsNode().boundsInRoot
        assertTrue(image.bottom <= name.top)
    }

    @Test
    fun ac7_showsNoInfoForUnknownSport() {
        composeRule.setContent { SportsTheme { SportDetailScreen(SportDetailUiState.NotFound) } }

        composeRule.onNodeWithText("No info").assertIsDisplayed()
    }
}
