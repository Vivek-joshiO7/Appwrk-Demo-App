package com.example.appwrkdemoapplication.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.appwrkdemoapplication.data.local.entity.TrainingModule

@Dao
interface TrainingDao {

    @Query("SELECT * FROM training_modules")
    fun getAllModules(): LiveData<List<TrainingModule>>

    @Update
    suspend fun updateModule(module: TrainingModule)

    @Insert
    suspend fun insertAll(modules: List<TrainingModule>)

    @Query("SELECT * FROM training_modules WHERE id = :moduleId")
    fun getModuleById(moduleId: Int): LiveData<TrainingModule>


    @Query("SELECT * FROM training_modules WHERE isCompleted = 1")
    fun getCompletedModules(): LiveData<List<TrainingModule>>

    @Query("SELECT * FROM training_modules WHERE isCompleted = 0")
    fun getPendingModules(): LiveData<List<TrainingModule>>

    @Query("SELECT COUNT(*) FROM training_modules")
    suspend fun getCount(): Int


}
