package gondai.tutorial.mvvmcomposebptracker.repositories.add_entry

import gondai.tutorial.mvvmcomposebptracker.database.models.Reading

interface AddEntryRepository {
    fun add(reading: Reading):Reading?
}