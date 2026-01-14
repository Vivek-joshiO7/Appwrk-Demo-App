package com.example.appwrkdemoapplication.data.local

import androidx.lifecycle.LiveData
import com.example.appwrkdemoapplication.data.local.dao.TrainingDao
import com.example.appwrkdemoapplication.data.local.entity.TrainingModule
import com.example.appwrkdemoapplication.repo.TrainingRepo

class TrainingRepoImpl(
    private val dao: TrainingDao
) : TrainingRepo {

    override fun getAllModules(): LiveData<List<TrainingModule>> {
        return dao.getAllModules()
    }


    override suspend fun updateModule(module: TrainingModule) {
        dao.updateModule(module)
    }

    override fun getModuleById(id: Int): LiveData<TrainingModule> {
        return dao.getModuleById(id)
    }

    override fun getCompletedModules(): LiveData<List<TrainingModule>> {
        return dao.getCompletedModules()
    }

    override fun getPendingModules(): LiveData<List<TrainingModule>> {
        return dao.getPendingModules()
    }

    override suspend fun insertIfEmpty(modules: List<TrainingModule>) {
        if (dao.getCount() == 0) {
            dao.insertAll(modules)
        }
    }
}

