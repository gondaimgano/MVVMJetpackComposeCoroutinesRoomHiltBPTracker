package gondai.tutorial.mvvmcomposebptracker.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import gondai.tutorial.mvvmcomposebptracker.database.converters.DateConverters
import gondai.tutorial.mvvmcomposebptracker.database.dao.ReadingDao
import gondai.tutorial.mvvmcomposebptracker.database.models.Reading

@Database(
    version = 2,
    entities = [Reading::class],
    //exportSchema = false
)
@TypeConverters(DateConverters::class)
abstract class BpDatabase:RoomDatabase() {
    abstract fun readingDao():ReadingDao
}
