package app.bako.model.dayoffcode

import androidx.lifecycle.LiveData

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