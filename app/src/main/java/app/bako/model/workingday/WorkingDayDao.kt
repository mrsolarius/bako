package app.bako.model.workingday

import androidx.lifecycle.LiveData
import androidx.room.*
import java.util.*

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

    @Query("SELECT * FROM workingday WHERE YEAR(date) = :year")
    fun getByDate(year: Int): LiveData<List<WorkingDay>>

    @Query("SELECT * FROM workingday WHERE date BETWEEN :first and :last")
    fun getBetween(first: Date, last: Date): LiveData<List<WorkingDay>>
}