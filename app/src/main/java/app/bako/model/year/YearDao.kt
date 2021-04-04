package app.bako.model.year

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface YearDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun addYear(year: Year)

    @Update
    suspend fun updateYear(year: Year)

    @Delete
    suspend fun deleteYear(year: Year)

    @Query("DELETE FROM year")
    suspend fun deleteAllYear()

    @Query("SELECT * FROM year ORDER BY yearLabel ASC")
    fun readAllData(): LiveData<List<Year>>

    @Query("SELECT * FROM year WHERE yearLabel = :yearLabel")
    fun getSpecificYear(yearLabel: Int): LiveData<List<Year>>

}