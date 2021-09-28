package gondai.tutorial.mvvmcomposebptracker.screens

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import gondai.tutorial.mvvmcomposebptracker.R
import gondai.tutorial.mvvmcomposebptracker.screens.history.HistoryScreen
import gondai.tutorial.mvvmcomposebptracker.screens.new_entry.NewEntryScreen

sealed class Index(val route: String, @StringRes val resourceId: Int,val label:String,val screen:@Composable (NavController)->Unit) {
    object History : Index("history", R.string.history,"History",{
        HistoryScreen(navController = it)

    })
    object NewEntry : Index("newentry", R.string.new_entry,"Add Reading",{
        NewEntryScreen(navController = it)
    })
}