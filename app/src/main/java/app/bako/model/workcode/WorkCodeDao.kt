package app.bako.model.workcode

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow

/*
 * Work Code SQL Query
 * C'est l'interface qui détermine la composition des query
 */
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

    @Query("SELECT code FROM workcode")
    fun getCodeList(): Flow<List<String>>
}