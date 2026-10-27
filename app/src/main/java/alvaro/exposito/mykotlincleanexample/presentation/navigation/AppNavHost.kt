package alvaro.exposito.mykotlincleanexample.presentation.navigation

import alvaro.exposito.mykotlincleanexample.presentation.sportdetail.SportDetailRoute
import alvaro.exposito.mykotlincleanexample.presentation.sportlist.SportListRoute
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable

@Serializable
data object SportListDestination

/** `id` must match `SportDetailViewModel.ARG_ID`. */
@Serializable
data class SportDetailDestination(
    val id: Int,
)

@Composable
fun AppNavHost(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = SportListDestination) {
        composable<SportListDestination> {
            SportListRoute(onSportClick = { id -> navController.navigate(SportDetailDestination(id)) })
        }
        composable<SportDetailDestination> {
            SportDetailRoute()
        }
    }
}
