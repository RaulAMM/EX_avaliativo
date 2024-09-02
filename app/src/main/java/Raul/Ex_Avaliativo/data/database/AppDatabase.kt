package Raul.Ex_Avaliativo.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import Raul.Ex_Avaliativo.data.dao.Anotationdao
import Raul.Ex_Avaliativo.data.model.Anotation

@Database(entities = [Anotation::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        const val DATABASE_NAME = "app_diario.db"
        private lateinit var instance: AppDatabase
        fun getInstance(context: Context): AppDatabase {
            if (!::instance.isInitialized) {
                synchronized(AppDatabase::class) {
                    instance = Room
                        .databaseBuilder(
                            context,
                            AppDatabase::class.java,
                            DATABASE_NAME
                        )
                        .build()
                }
            }
            return instance
        }
    }
    abstract fun getAnotationdao(): Anotationdao
}
