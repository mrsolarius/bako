package app.bako.model.year

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import app.bako.model.DataBase
import app.bako.model.workingday.WorkingDay
import app.bako.model.workingday.WorkingDayRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class YearViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<Year>>
    private val repository: YearRepository

    init {
        val yearDao = DataBase.getDatabase(application).YearDao()
        repository = YearRepository(yearDao)
        readAllData = repository.readAllData
    }


    fun addYear(year: Year){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addYear(year)
        }
    }

    fun updateWorkCode(year: Year){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateYear(year)
        }
    }

    fun deleteWorkCode(year: Year){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteYear(year)
        }
    }

    fun deleteAllWorkCode(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllYear()
        }
    }

    fun getSpecificYear(yearLabel: Int){
        viewModelScope.launch(Dispatchers.IO) {
            repository.getSpecificYear(yearLabel)
        }
    }

}