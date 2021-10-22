package gondai.tutorial.mvvmcomposebptracker.screens.new_entry

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import gondai.tutorial.mvvmcomposebptracker.database.models.Reading
import gondai.tutorial.mvvmcomposebptracker.repositories.add_entry.AddEntryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class NewEntryViewModel
  @Inject constructor (
      private val repository:AddEntryRepository
           ): ViewModel(){

    // Backing property to avoid state updates from other classes
    private val _uiState = MutableStateFlow<NewEntryState>(NewEntryState.Initial)
    // The UI collects from this StateFlow to get its state updates
    val uiState: StateFlow<NewEntryState> = _uiState

   val reading= MutableStateFlow(Reading(uid = 0))


    fun confirm()=viewModelScope.launch {
        _uiState.value=NewEntryState.Confirm

    }
    fun cancel()=viewModelScope.launch {
        _uiState.value=NewEntryState.Initial
    }

    fun submit()=viewModelScope.launch {
        _uiState.value = NewEntryState.Loading


        try {
            delay(2000L) //call repository
       val result= withContext(coroutineContext+Dispatchers.IO){
                repository.add(reading = reading.value)
            }
            _uiState.value = NewEntryState.Success(message = " Saved reading on ${result?.dateCreated}")
            delay(1500L)
            _uiState.value = NewEntryState.Initial

        }
        catch (ex:Throwable){
            _uiState.value = NewEntryState.Failed(message = ex.message)
        }

    }

    fun onSys(sys: String) {
        tryToExecute { reading.value=reading.value.copy(sys = sys.trim().toInt()) }
    }
    fun onDia(sys: String) {
        tryToExecute {  reading.value = reading.value.copy(dia = sys.trim().toInt()) }
    }

    fun onPulse(sys: String) {
        tryToExecute { reading.value=reading.value.copy(pulse = sys.trim().toInt()) }
    }
    fun onWeight(sys: String) {
        tryToExecute {  reading.value = reading.value.copy(weight = sys.trim().toInt()) }
    }
    fun onSpO2(sys: String) {
        tryToExecute {  reading.value= reading.value.copy(spO2 = sys.trim().toInt()) }
    }

    private fun tryToExecute(callback:()->Unit){
        try {
            callback()
        }
        catch (ex:Throwable){

        }
    }

}