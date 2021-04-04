package app.bako.model.workingday

import androidx.lifecycle.LiveData
import java.util.*

class WorkingDayRepository(private val workingDayDao: WorkingDayDao) {

    val readAllData: LiveData<List<WorkingDay>> = workingDayDao.readAllData()

    suspend fun addWorkingDay(workingDay: WorkingDay){
        workingDayDao.addWorkingDay(workingDay)
    }

    suspend fun updateWorkingDay(workingDay: WorkingDay){
        workingDayDao.updateWorkingDay(workingDay)
    }

    suspend fun deleteWorkingDay(workingDay: WorkingDay){
        workingDayDao.deleteWorkingDay(workingDay)
    }

    suspend fun deleteAllWorkingDay(){
        workingDayDao.deleteAllWorkingDay()
    }

    suspend fun getByDate(year: Int){
        workingDayDao.getByDate(year)
    }

    suspend fun getBetween(before: Date, after: Date){
        workingDayDao.getBetween(before, after)
    }

    suspend fun getWorkCodeForWorkDay(date: Date){
        workingDayDao.getWorkCodeForWorkDay(date)
    }

}