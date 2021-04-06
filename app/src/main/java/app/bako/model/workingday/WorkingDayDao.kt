package app.bako.model.workingday

import androidx.lifecycle.LiveData
import androidx.room.*
import app.bako.model.relation.WorkingDayWithWorkCodes
import app.bako.model.workcode.WorkCode
import java.util.*

@Dao
interface WorkingDayDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun addWorkingDay(workingDay: WorkingDay)

    @Update
    suspend fun updateWorkingDay(workingDay: WorkingDay)

    @Delete
    suspend fun deleteWorkingDay(workingDay: WorkingDay)

    @Query("DELETE FROM workingday")
    suspend fun deleteAllWorkingDay()

    @Query("SELECT * FROM workingday ORDER BY date ASC")
    fun readAllData(): LiveData<List<WorkingDay>>

    @Query("SELECT * FROM workingday WHERE strftime('%Y', date) = :year")
    fun getByDate(year: Int): LiveData<List<WorkingDay>>

    @Query("SELECT * FROM workingday WHERE date BETWEEN :first and :last")
    fun getBetween(first: Date, last: Date): LiveData<List<WorkingDay>>

    @Transaction
    @Query("SELECT * FROM workingday WHERE date = :date")
    suspend fun getWorkCodeForWorkDay(date: Date): LiveData<List<WorkingDayWithWorkCodes>>

    @Transaction
    @Query("SELECT * FROM workingday")
    suspend fun getAllWorkingDayWithWorkCode(): LiveData<List<WorkingDayWithWorkCodes>>

    @Transaction
    @Query("SELECT * FROM workingday  WHERE date BETWEEN :first and :last")
    suspend fun getAllWorkingDayWithWorkCodeBetween(first: Date, last: Date): LiveData<List<WorkingDayWithWorkCodes>>

}