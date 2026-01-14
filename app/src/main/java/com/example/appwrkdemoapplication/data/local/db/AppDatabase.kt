package com.example.appwrkdemoapplication.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.appwrkdemoapplication.data.local.dao.TrainingDao
import com.example.appwrkdemoapplication.data.local.entity.TrainingModule

@Database(entities = [TrainingModule::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun trainingDao(): TrainingDao

    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "training_db"
                ).build().also { INSTANCE = it }
            }
    }
}
