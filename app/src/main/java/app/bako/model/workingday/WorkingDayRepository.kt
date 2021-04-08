package app.bako.model.workingday

import androidx.lifecycle.LiveData
import app.bako.model.relation.WorkingDayWithWorkCodes
import kotlinx.coroutines.flow.Flow
import java.util.*

/**
 * Useful Methode for working fay repository
 */
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

    fun getWorkCodeForWorkDay(date: Date): Flow<List<WorkingDayWithWorkCodes>> {
        return workingDayDao.getWorkCodeForWorkDay(date)
    }

    fun getAllWorkingDayWithWorkCode(): Flow<List<WorkingDayWithWorkCodes>> {
        return workingDayDao.getAllWorkingDayWithWorkCode()
    }

    fun getAllWorkingDayWithWorkCodeBetween(before: Date, after: Date): Flow<List<WorkingDayWithWorkCodes>> {
        return workingDayDao.getAllWorkingDayWithWorkCodeBetween(before, after)
    }

}