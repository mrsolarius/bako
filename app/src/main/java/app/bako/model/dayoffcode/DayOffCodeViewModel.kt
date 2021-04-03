package app.bako.model.dayoffcode

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import app.bako.model.DataBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DayOffCodeViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<DayOffCode>>
    private val repository: DayOffCodeRepository

    init {
        val DayOffCodeDao = DataBase.getDatabase(application).DayOffCodeDao()
        repository = DayOffCodeRepository(DayOffCodeDao)
        readAllData = repository.readAllData
    }

    fun addDayOffCode(dayOffCode: DayOffCode){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addDayOffCode(dayOffCode)
        }
    }

    fun updateDayOffCode(dayOffCode: DayOffCode){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateDayOffCode(dayOffCode)
        }
    }

    fun deleteDayOffCode(dayOffCode: DayOffCode){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteDayOffCode(dayOffCode)
        }
    }

    fun deleteAllDayOffCode(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllDayOffCode()
        }
    }
}