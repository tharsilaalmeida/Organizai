package com.fogueteiras.organizai.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.fogueteiras.organizai.data.Subject
import com.fogueteiras.organizai.data.Task

@Database(entities = [Task::class, Subject::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun organizaiDao(): OrganizaiDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "organizai_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
