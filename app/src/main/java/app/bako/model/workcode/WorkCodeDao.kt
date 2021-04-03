package app.bako.model.workcode

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface WorkCodeDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun addWorkCode(workCode: WorkCode)

    @Update
    suspend fun updateWorkCode(workCode: WorkCode)

    @Delete
    suspend fun deleteWorkCode(workCode: WorkCode)

    @Query("DELETE FROM workcode")
    suspend fun deleteAllWorkCode()

    @Query("SELECT * FROM workcode ORDER BY code ASC")
    fun readAllData(): LiveData<List<WorkCode>>
}