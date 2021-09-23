package gondai.tutorial.mvvmcomposebptracker.screens.history

import gondai.tutorial.mvvmcomposebptracker.database.models.Reading

sealed class HistoryState{

    object Loading : HistoryState()
    class Success(val readings: List<Reading>) : HistoryState()
    class Failed(val message:String?) : HistoryState()
}