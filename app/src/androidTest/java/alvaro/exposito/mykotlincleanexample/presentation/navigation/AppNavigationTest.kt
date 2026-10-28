package alvaro.exposito.mykotlincleanexample.presentation.navigation

import alvaro.exposito.mykotlincleanexample.MainActivity
import alvaro.exposito.mykotlincleanexample.presentation.common.SportImageState
import alvaro.exposito.mykotlincleanexample.presentation.sportlist.SportListTestTags
import alvaro.exposito.mykotlincleanexample.testutil.FakeImages
import alvaro.exposito.mykotlincleanexample.testutil.waitForSportImage
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
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
import org.junit.rules.TestWatcher
import org.junit.runner.Description

/** End-to-end through the real Hilt graph; data is local and images are faked offline, so results are deterministic. */
@HiltAndroidTest
class AppNavigationTest {
    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    /** Offline images for every test; installed before the activity starts loading them. */
    @get:Rule(order = 1)
    val offlineImages =
        object : TestWatcher() {
            override fun starting(description: Description) = FakeImages.failing()
        }

    @get:Rule(order = 2)
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

    @Test
    fun ac6_offlineListAndDetailStillWork() {
        composeRule.onAllNodesWithTag(SportListTestTags.TILE).assertCountEquals(8)
        composeRule.waitForSportImage("Cycling", SportImageState.Error)

        composeRule.onNodeWithContentDescription("Cycling").performClick()

        composeRule.onNodeWithText("Cycling").assertIsDisplayed()
    }
}
