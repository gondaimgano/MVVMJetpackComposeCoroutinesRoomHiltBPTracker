package gondai.tutorial.mvvmcomposebptracker.screens.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import gondai.tutorial.mvvmcomposebptracker.repositories.history.HistoryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val repository: HistoryRepository
): ViewModel(){
    // Backing property to avoid state updates from other classes
    private val _uiState = MutableStateFlow<HistoryState>(HistoryState.Loading)
    // The UI collects from this StateFlow to get its state updates
    val uiState: StateFlow<HistoryState> = _uiState

//  private val repository: IHistoryRepository= HistoryRepository()
    init {
     update()

    }

    private fun update()= viewModelScope.launch {


        _uiState.value = HistoryState.Loading
        try {
            repository.fetchAllHistory().collect { result ->
                _uiState.value = HistoryState.Success(result)
            }


        }catch (ex:Throwable){
            _uiState.value=HistoryState.Failed(ex.message)
        }
    }

}