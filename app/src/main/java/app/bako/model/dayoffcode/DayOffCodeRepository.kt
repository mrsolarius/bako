package app.bako.model.dayoffcode

import androidx.lifecycle.LiveData

/**
 * DayOffCodeRepository
 * Il permet d'encapsuler la DAO dans des fonction suspendu
 *
 * (une fonction suspendu peut être mise en pause et repris plus tard)
 */
class DayOffCodeRepository(private val dayOffCodeDao: DayOffCodeDao) {

    val readAllData: LiveData<List<DayOffCode>> = dayOffCodeDao.readAllData()

    suspend fun addDayOffCode(dayOffCode: DayOffCode){
        dayOffCodeDao.addDayOffCode(dayOffCode)
    }

    suspend fun updateDayOffCode(dayOffCode: DayOffCode){
        dayOffCodeDao.updateDayOffCode(dayOffCode)
    }

    suspend fun deleteDayOffCode(dayOffCode: DayOffCode){
        dayOffCodeDao.deleteDayOffCode(dayOffCode)
    }

    suspend fun deleteAllDayOffCode(){
        dayOffCodeDao.deleteAllDayOffCode()
    }
}