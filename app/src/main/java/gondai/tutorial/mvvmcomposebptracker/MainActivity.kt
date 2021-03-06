package gondai.tutorial.mvvmcomposebptracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import gondai.tutorial.mvvmcomposebptracker.screens.Index
import gondai.tutorial.mvvmcomposebptracker.ui.theme.MVVMComposeBPTrackerTheme


@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            MVVMComposeBPTrackerTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                   BPTrackerApp()
                }
            }
        }
    }



}




@Composable
fun BPTrackerApp(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Index.History.route) {
        composable(Index.History.route) { Index.History.screen(navController) }
        composable(Index.NewEntry.route) { Index.NewEntry.screen(navController) }

    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MVVMComposeBPTrackerTheme {
     BPTrackerApp()
    }
}