package gondai.tutorial.mvvmcomposebptracker.screens

import androidx.annotation.StringRes
import gondai.tutorial.mvvmcomposebptracker.R

sealed class Index(val route: String, @StringRes val resourceId: Int,val label:String) {
    object History : Index("history", R.string.history,"History")
    object NewEntry : Index("newentry", R.string.new_entry,"Add Reading")
}