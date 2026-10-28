package alvaro.exposito.mykotlincleanexample.presentation.sportlist

import alvaro.exposito.mykotlincleanexample.R
import alvaro.exposito.mykotlincleanexample.domain.model.Sport
import alvaro.exposito.mykotlincleanexample.presentation.common.SportImage
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun SportListRoute(
    onSportClick: (Int) -> Unit,
    viewModel: SportListViewModel = hiltViewModel(),
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    SportListScreen(state = state, onRetry = viewModel::retry, onSportClick = onSportClick)
}

@Composable
fun SportListScreen(
    state: SportListUiState,
    onRetry: () -> Unit,
    onSportClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(modifier = modifier) { padding ->
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            when (state) {
                SportListUiState.Loading -> CircularProgressIndicator(Modifier.testTag(SportListTestTags.LOADING))
                is SportListUiState.Error -> ErrorContent(onRetry)
                is SportListUiState.Success -> SportGrid(state.sports, onSportClick, padding)
            }
        }
    }
}

@Composable
private fun SportGrid(
    sports: List<Sport>,
    onSportClick: (Int) -> Unit,
    contentPadding: PaddingValues,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        state = rememberLazyGridState(),
        contentPadding = contentPadding,
        horizontalArrangement = Arrangement.spacedBy(1.dp),
        verticalArrangement = Arrangement.spacedBy(1.dp),
        modifier = Modifier.fillMaxSize().testTag(SportListTestTags.GRID),
    ) {
        items(sports, key = { it.id }) { sport ->
            SportImage(
                url = sport.tileImageUrl,
                placeholderRes = sport.imageRes,
                contentDescription = sport.name,
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .aspectRatio(2f)
                        .clickable { onSportClick(sport.id) }
                        .testTag(SportListTestTags.TILE),
            )
        }
    }
}

@Composable
private fun ErrorContent(onRetry: () -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Text(stringResource(R.string.sport_list_error))
        Button(onClick = onRetry) { Text(stringResource(R.string.retry)) }
    }
}
