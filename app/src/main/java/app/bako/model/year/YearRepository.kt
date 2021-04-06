package app.bako.model.year

import androidx.lifecycle.LiveData

class YearRepository(private val yearDao: YearDao) {

    val readAllData: LiveData<List<Year>> = yearDao.readAllData()

    suspend fun addYear(year: Year){
        yearDao.addYear(year)
    }

    suspend fun updateYear(year:Year){
        yearDao.updateYear(year)
    }

    suspend fun deleteYear(year: Year){
        yearDao.deleteYear(year)
    }

    suspend fun deleteAllYear(){
        yearDao.deleteAllYear()
    }

    suspend fun getSpecificYear(yearLabel: Int){
        yearDao.getSpecificYear(yearLabel)
    }

}