package alvaro.exposito.mykotlincleanexample.presentation.sportdetail

import alvaro.exposito.mykotlincleanexample.domain.model.Sport
import alvaro.exposito.mykotlincleanexample.presentation.common.SportImageState
import alvaro.exposito.mykotlincleanexample.presentation.theme.SportsTheme
import alvaro.exposito.mykotlincleanexample.testutil.FakeImages
import alvaro.exposito.mykotlincleanexample.testutil.waitForSportImage
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

    private val athletics =
        Sport(
            9,
            "Athletics",
            android.R.drawable.ic_menu_gallery,
            "https://example.test/tile/9",
            "https://example.test/detail/9",
        )

    private fun setFound() =
        composeRule.setContent {
            SportsTheme { SportDetailScreen(SportDetailUiState.Found(athletics)) }
        }

    @Test
    fun ac6_showsImageAboveName() {
        composeRule.setContent {
            SportsTheme {
                SportDetailScreen(
                    SportDetailUiState.Found(
                        Sport(
                            9,
                            "Athletics",
                            android.R.drawable.ic_menu_gallery,
                            "https://example.test/tile/9",
                            "https://example.test/detail/9",
                        ),
                    ),
                )
            }
        }

        composeRule.onNodeWithText("Athletics").assertIsDisplayed()
        val image =
            composeRule.onNodeWithContentDescription(
                "Athletics",
            ).assertIsDisplayed().fetchSemanticsNode().boundsInRoot
        val name = composeRule.onNodeWithText("Athletics").fetchSemanticsNode().boundsInRoot
        assertTrue(image.bottom <= name.top)
    }

    @Test
    fun ac7_showsNoInfoForUnknownSport() {
        composeRule.setContent { SportsTheme { SportDetailScreen(SportDetailUiState.NotFound) } }

        composeRule.onNodeWithText("No info").assertIsDisplayed()
    }

    @Test
    fun ac3_detailLoadsDetailUrl() {
        FakeImages.onlySucceeding(athletics.detailImageUrl)
        setFound()

        composeRule.waitForSportImage("Athletics", SportImageState.Success)
    }

    @Test
    fun ac4_showsIconWhileLoading() {
        FakeImages.neverCompleting()
        setFound()

        composeRule.waitForSportImage("Athletics", SportImageState.Loading)
        composeRule.onNodeWithContentDescription("Athletics").assertIsDisplayed()
    }

    @Test
    fun ac5_showsIconAndNameWhenLoadFails() {
        FakeImages.failing()
        setFound()

        composeRule.waitForSportImage("Athletics", SportImageState.Error)
        composeRule.onNodeWithContentDescription("Athletics").assertIsDisplayed()
        composeRule.onNodeWithText("Athletics").assertIsDisplayed()
    }
}
