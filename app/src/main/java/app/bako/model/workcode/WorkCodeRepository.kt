package app.bako.model.workcode

import androidx.lifecycle.LiveData

class WorkCodeRepository(private val workCodeDao: WorkCodeDao) {

    val readAllData: LiveData<List<WorkCode>> = workCodeDao.readAllData()

    suspend fun addWorkCode(workCode: WorkCode){
        workCodeDao.addWorkCode(workCode)
    }

    suspend fun updateWorkCode(workCode: WorkCode){
        workCodeDao.updateWorkCode(workCode)
    }

    suspend fun deleteWorkCode(workCode: WorkCode){
        workCodeDao.deleteWorkCode(workCode)
    }

    suspend fun deleteAllWorkCode(){
        workCodeDao.deleteAllWorkCode()
    }

}