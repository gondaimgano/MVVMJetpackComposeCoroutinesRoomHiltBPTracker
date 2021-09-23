package gondai.tutorial.mvvmcomposebptracker.repositories.history

import gondai.tutorial.mvvmcomposebptracker.database.BpDatabase
import gondai.tutorial.mvvmcomposebptracker.database.models.Reading
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class HistoryRepositoryImpl
@Inject
    constructor(
    private val database: BpDatabase,
    private  val dispatcherContext:CoroutineDispatcher
       ): HistoryRepository {
    override fun fetchAllHistory(): Flow<List<Reading>>
       =  database.readingDao().getAllReadings().flowOn(dispatcherContext)

}