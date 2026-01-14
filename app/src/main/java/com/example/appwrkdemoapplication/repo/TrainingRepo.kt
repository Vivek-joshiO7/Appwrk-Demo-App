package com.example.appwrkdemoapplication.repo

import androidx.lifecycle.LiveData
import com.example.appwrkdemoapplication.data.local.entity.TrainingModule


interface TrainingRepo {

    fun getAllModules(): LiveData<List<TrainingModule>>

    fun getModuleById(id: Int): LiveData<TrainingModule>

    suspend fun updateModule(module: TrainingModule)

    fun getCompletedModules(): LiveData<List<TrainingModule>>

    fun getPendingModules(): LiveData<List<TrainingModule>>

    suspend fun insertIfEmpty(modules: List<TrainingModule>)
}
