package com.criticalay.h20reminder.utils

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.criticalay.h20reminder.model.Drink
import com.criticalay.h20reminder.model.Notification
import com.criticalay.h20reminder.model.User


private const val DATABASE_NAME = "SQLITE_DATABASE.db"
private const val DATABASE_VERSION = 1
@Database(
    entities = [User::class, Drink::class, Notification::class],
    version = DATABASE_VERSION,
    exportSchema = true
)

abstract class AppDatabase : RoomDatabase() {


    abstract fun dao(): Dao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context?): AppDatabase {

            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context?.applicationContext!!,
                    AppDatabase::class.java, DATABASE_NAME
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}