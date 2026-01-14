package com.example.appwrkdemoapplication.ui.common

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.appwrkdemoapplication.data.local.TrainingRepoImpl
import com.example.appwrkdemoapplication.data.local.db.AppDatabase
import com.example.appwrkdemoapplication.data.local.entity.TrainingModule
import kotlinx.coroutines.launch

class TrainingViewModel(application: Application) : AndroidViewModel(application) {

    private val dao = AppDatabase.getDatabase(application).trainingDao()
    private val repository = TrainingRepoImpl(dao)

    val modules: LiveData<List<TrainingModule>> = repository.getAllModules()

    fun updateStatus(module: TrainingModule) = viewModelScope.launch {
        repository.updateModule(module)
    }

    fun getModuleById(id: Int): LiveData<TrainingModule> {
        return repository.getModuleById(id)
    }

    val allModules: LiveData<List<TrainingModule>> =
        repository.getAllModules()

    fun getCompleted() = repository.getCompletedModules()

    fun getPending() = repository.getPendingModules()

    fun insertDefaultData(modules: List<TrainingModule>) {
        viewModelScope.launch {
            repository.insertIfEmpty(modules)
        }
    }
}
