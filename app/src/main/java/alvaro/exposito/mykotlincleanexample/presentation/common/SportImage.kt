package alvaro.exposito.mykotlincleanexample.presentation.common

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.semantics
import coil3.compose.AsyncImage

/**
 * A sport's remote photo, with its bundled icon shown while loading and when loading fails
 * (spec 002, AC-4 and AC-5).
 */
@Composable
fun SportImage(
    url: String,
    @DrawableRes placeholderRes: Int,
    contentDescription: String,
    modifier: Modifier = Modifier,
) {
    var state by remember(url) { mutableStateOf(SportImageState.Loading) }
    val icon = painterResource(placeholderRes)
    AsyncImage(
        model = url,
        contentDescription = contentDescription,
        placeholder = icon,
        error = icon,
        contentScale = ContentScale.Crop,
        onLoading = { state = SportImageState.Loading },
        onSuccess = { state = SportImageState.Success },
        onError = { state = SportImageState.Error },
        modifier = modifier.semantics { sportImageState = state },
    )
}
