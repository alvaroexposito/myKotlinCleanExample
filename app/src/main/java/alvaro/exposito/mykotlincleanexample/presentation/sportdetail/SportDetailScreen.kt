package alvaro.exposito.mykotlincleanexample.presentation.sportdetail

import alvaro.exposito.mykotlincleanexample.R
import alvaro.exposito.mykotlincleanexample.presentation.common.SportImage
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun SportDetailRoute(viewModel: SportDetailViewModel = hiltViewModel()) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    SportDetailScreen(state)
}

/** Image in the top third, name centered in the bottom two thirds (baseline B-3). */
@Composable
fun SportDetailScreen(
    state: SportDetailUiState,
    modifier: Modifier = Modifier,
) {
    Scaffold(modifier = modifier) { padding ->
        when (state) {
            SportDetailUiState.Loading ->
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) { CircularProgressIndicator() }
            is SportDetailUiState.Found ->
                Column(Modifier.fillMaxSize().padding(padding)) {
                    SportImage(
                        url = state.sport.detailImageUrl,
                        placeholderRes = state.sport.imageRes,
                        contentDescription = state.sport.name,
                        modifier = Modifier.fillMaxWidth().weight(1f),
                    )
                    CenteredName(state.sport.name, Modifier.weight(2f))
                }
            SportDetailUiState.NotFound, is SportDetailUiState.Error ->
                Column(Modifier.fillMaxSize().padding(padding)) {
                    CenteredName(stringResource(R.string.sport_detail_no_info), Modifier.weight(1f))
                }
        }
    }
}

@Composable
private fun CenteredName(
    text: String,
    modifier: Modifier = Modifier,
) {
    Box(modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        Text(text, style = MaterialTheme.typography.displayMedium, textAlign = TextAlign.Center)
    }
}
