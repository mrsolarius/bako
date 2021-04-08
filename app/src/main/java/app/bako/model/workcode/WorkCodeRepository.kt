package app.bako.model.workcode

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow

/**
 * WorkCodeRepository
 * Il permet d'encapsuler la DAO dans des fonction suspendu
 *
 * (une fonction suspendu peut être mise en pause et repris plus tard)
 */
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

    fun getCodeList(): Flow<List<String>> {
        return workCodeDao.getCodeList()
    }

}