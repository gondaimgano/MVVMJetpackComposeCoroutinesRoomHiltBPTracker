package gondai.tutorial.mvvmcomposebptracker.repositories.add_entry

import gondai.tutorial.mvvmcomposebptracker.database.BpDatabase
import gondai.tutorial.mvvmcomposebptracker.database.models.Reading
import javax.inject.Inject
class AddEntryRepositoryImpl
@Inject
constructor(
    private val database: BpDatabase
) : AddEntryRepository {
    override fun add(reading: Reading): Reading {
      database.readingDao().save(reading = reading)
        return reading
    }

}