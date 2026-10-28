package alvaro.exposito.mykotlincleanexample.testutil

import alvaro.exposito.mykotlincleanexample.presentation.common.SportImageState
import alvaro.exposito.mykotlincleanexample.presentation.common.SportImageStateKey
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.test.platform.app.InstrumentationRegistry
import coil3.ImageLoader
import coil3.SingletonImageLoader
import coil3.annotation.DelicateCoilApi
import coil3.request.ErrorResult
import coil3.test.FakeImage
import coil3.test.FakeImageLoaderEngine
import kotlinx.coroutines.awaitCancellation
import java.io.IOException

/** Installs a fake Coil engine so image tests never touch the network (plan 002, test strategy). */
object FakeImages {
    private fun failure() =
        FakeImageLoaderEngine.Builder()
            .default { chain -> ErrorResult(image = null, request = chain.request, throwable = IOException("offline")) }

    /** Every request succeeds. */
    fun succeeding() = install(FakeImageLoaderEngine.Builder().default(FakeImage()).build())

    /** Every request fails, as when offline. */
    fun failing() = install(failure().build())

    /** Every request stays in flight. */
    fun neverCompleting() = install(FakeImageLoaderEngine.Builder().default { awaitCancellation() }.build())

    /** Only [url] succeeds; anything else fails. */
    fun onlySucceeding(url: String) = install(failure().intercept(url, FakeImage()).build())

    @OptIn(DelicateCoilApi::class)
    private fun install(engine: FakeImageLoaderEngine) {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val loader =
            ImageLoader.Builder(context)
                .components { add(engine) }
                .memoryCache(null)
                .diskCache(null)
                .build()
        SingletonImageLoader.setUnsafe(loader)
    }
}

fun hasSportImageState(state: SportImageState) = SemanticsMatcher.expectValue(SportImageStateKey, state)

/** Waits until the image described by [name] reaches [state]. */
fun ComposeTestRule.waitForSportImage(
    name: String,
    state: SportImageState,
    timeoutMillis: Long = 5_000,
) = waitUntil(timeoutMillis) {
    onAllNodesWithContentDescription(name)
        .fetchSemanticsNodes()
        .any { hasSportImageState(state).matches(it) }
}
