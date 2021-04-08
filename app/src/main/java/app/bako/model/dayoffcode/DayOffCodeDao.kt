package app.bako.model.dayoffcode

import androidx.lifecycle.LiveData
import androidx.room.*

/*
 * DayOffCodeDao Query
 * C'est l'interface qui détermine la composition des query
 */
@Dao
interface DayOffCodeDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun addDayOffCode(dayOffCode: DayOffCode)

    @Update
    suspend fun updateDayOffCode(dayOffCode: DayOffCode)

    @Delete
    suspend fun deleteDayOffCode(dayOffCode: DayOffCode)

    @Query("DELETE FROM dayoffcode")
    suspend fun deleteAllDayOffCode()

    @Query("SELECT * FROM dayoffcode ORDER BY code ASC")
    fun readAllData(): LiveData<List<DayOffCode>>
}