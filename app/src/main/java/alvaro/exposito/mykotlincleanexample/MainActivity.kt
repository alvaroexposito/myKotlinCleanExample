package alvaro.exposito.mykotlincleanexample

import alvaro.exposito.mykotlincleanexample.presentation.navigation.AppNavHost
import alvaro.exposito.mykotlincleanexample.presentation.theme.SportsTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SportsTheme { AppNavHost() }
        }
    }
}
