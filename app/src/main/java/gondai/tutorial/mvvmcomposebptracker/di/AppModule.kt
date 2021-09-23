package gondai.tutorial.mvvmcomposebptracker.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import gondai.tutorial.mvvmcomposebptracker.database.BpDatabase
import gondai.tutorial.mvvmcomposebptracker.repositories.add_entry.AddEntryRepositoryImpl
import gondai.tutorial.mvvmcomposebptracker.repositories.add_entry.AddEntryRepository
import gondai.tutorial.mvvmcomposebptracker.repositories.history.HistoryRepositoryImpl
import gondai.tutorial.mvvmcomposebptracker.repositories.history.HistoryRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object  AppModule{

    @Singleton
    @Provides
    fun provideDatabaseService(
        @ApplicationContext
        applicationContext: Context
    ): BpDatabase {
        return Room.databaseBuilder(
            applicationContext,
            BpDatabase::class.java, "reading-db"
        ).fallbackToDestructiveMigration().build()
    }
    @Provides
    fun provideHistoryRepository(
        database: BpDatabase,
        dispatcher: CoroutineDispatcher,
    ): HistoryRepository {
        return HistoryRepositoryImpl(database,dispatcher)
    }
    @Provides
    fun provideAddEntryRepository(
        database: BpDatabase
    ): AddEntryRepository {
        return AddEntryRepositoryImpl(database)
    }

    @Provides
    fun provideCoroutineContext(
    ): CoroutineDispatcher {
        return Dispatchers.Default
    }
}
