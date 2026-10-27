package alvaro.exposito.mykotlincleanexample.presentation.navigation

import alvaro.exposito.mykotlincleanexample.MainActivity
import alvaro.exposito.mykotlincleanexample.presentation.sportlist.SportListTestTags
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToIndex
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/** End-to-end through the real Hilt graph; data is local, so results are deterministic. */
@HiltAndroidTest
class AppNavigationTest {
    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp() = hiltRule.inject()

    @Test
    fun ac5_tappingATileOpensItsDetail() {
        composeRule.onNodeWithContentDescription("Cycling").performClick()

        composeRule.onNodeWithText("Cycling").assertIsDisplayed()
    }

    @Test
    fun ac8_backReturnsToListAtSameScrollPosition() {
        composeRule.onNodeWithTag(SportListTestTags.GRID).performScrollToIndex(7)
        composeRule.onNodeWithContentDescription("Athletics").performClick()
        composeRule.onNodeWithText("Athletics").assertIsDisplayed()

        composeRule.runOnUiThread { composeRule.activity.onBackPressedDispatcher.onBackPressed() }

        composeRule.onNodeWithTag(SportListTestTags.GRID).assertIsDisplayed()
        composeRule.onNodeWithContentDescription("Athletics").assertIsDisplayed()
    }
}
