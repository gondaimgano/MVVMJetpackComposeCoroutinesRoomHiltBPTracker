package gondai.tutorial.mvvmcomposebptracker.screens.new_entry

sealed class NewEntryState{
    object Initial : NewEntryState()
    object Confirm:NewEntryState()
    object Loading : NewEntryState()
    class Success(val message: String?) : NewEntryState()
    class Failed(val message:String?) : NewEntryState()
}