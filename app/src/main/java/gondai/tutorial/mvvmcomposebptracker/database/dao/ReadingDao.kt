package gondai.tutorial.mvvmcomposebptracker.database.dao

import androidx.room.*
import gondai.tutorial.mvvmcomposebptracker.database.models.Reading
import kotlinx.coroutines.flow.Flow

@Dao
interface ReadingDao {
    @Query("SELECT * FROM READING ORDER BY dateCreated DESC")
    fun getAllReadings(): Flow<List<Reading>>

    @Insert
    fun save(reading: Reading)


    @Delete
    fun delete(reading: Reading)


}