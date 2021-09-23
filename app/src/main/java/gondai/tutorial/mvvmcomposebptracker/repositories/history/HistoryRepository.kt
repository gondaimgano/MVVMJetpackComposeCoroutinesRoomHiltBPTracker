package gondai.tutorial.mvvmcomposebptracker.repositories.history

import gondai.tutorial.mvvmcomposebptracker.database.models.Reading
import kotlinx.coroutines.flow.Flow

interface HistoryRepository {

    fun fetchAllHistory(): Flow<List<Reading>>
}