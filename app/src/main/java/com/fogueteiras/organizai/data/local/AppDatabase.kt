// O que faz: Define o banco de dados Room do Organizaí (tabelas Task e Subject).
// Por que existe: Gerencia a persistência local offline dos dados do app.
// O que quebra se sumir: O acesso ao banco de dados Room falha e os dados não são salvos.

package com.fogueteiras.organizai.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.fogueteiras.organizai.data.Subject
import com.fogueteiras.organizai.data.Task

@Database(entities = [Task::class, Subject::class], version = 2, exportSchema = false)
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
                )
                .fallbackToDestructiveMigration()
                .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
