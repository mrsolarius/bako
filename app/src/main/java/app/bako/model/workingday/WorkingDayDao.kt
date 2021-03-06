package app.bako.model.workingday

import androidx.lifecycle.LiveData
import androidx.room.*
import app.bako.model.relation.WorkingDayWithWorkCodes
import app.bako.model.workcode.WorkCode
import kotlinx.coroutines.flow.Flow
import java.util.*

/**
 * SQL Query For WorkingDayDao
 */
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
    fun getWorkCodeForWorkDay(date: Date): Flow<List<WorkingDayWithWorkCodes>>

    @Transaction
    @Query("SELECT * FROM workingday")
    fun getAllWorkingDayWithWorkCode(): Flow<List<WorkingDayWithWorkCodes>>

    @Transaction
    @Query("SELECT * FROM workingday  WHERE date BETWEEN :first and :last")
    fun getAllWorkingDayWithWorkCodeBetween(first: Date, last: Date): Flow<List<WorkingDayWithWorkCodes>>

}