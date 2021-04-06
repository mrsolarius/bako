package app.bako.model.workingday

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import app.bako.model.DataBase
import app.bako.model.relation.WorkingDayWithWorkCodes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class WorkingDayViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<WorkingDay>>
    private val repository: WorkingDayRepository

    init {
        val workingDayDao = DataBase.getDatabase(application).WorkingDayDao()
        repository = WorkingDayRepository(workingDayDao)
        readAllData = repository.readAllData
    }

    fun addWorkCode(workingDay: WorkingDay){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addWorkingDay(workingDay)
        }
    }

    fun updateWorkCode(workingDay: WorkingDay){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateWorkingDay(workingDay)
        }
    }

    fun deleteWorkCode(workingDay: WorkingDay){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteWorkingDay(workingDay)
        }
    }

    fun deleteAllWorkCode(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllWorkingDay()
        }
    }

    fun getByDate(year: Int){
        viewModelScope.launch(Dispatchers.IO) {
            repository.getByDate(year)
        }
    }

    fun getBetween(before: Date, after: Date){
        viewModelScope.launch(Dispatchers.IO) {
            repository.getBetween(before, after)
        }
    }

    fun getWorkCodeForWorkDay(date: Date): LiveData<List<WorkingDayWithWorkCodes>> {
        return repository.getWorkCodeForWorkDay(date).asLiveData()
    }

    fun getAllWorkingDayWithWorkCode(): LiveData<List<WorkingDayWithWorkCodes>> {
        return repository.getAllWorkingDayWithWorkCode().asLiveData()
    }

    fun getAllWorkingDayWithWorkCodeBetween(before: Date, after: Date): LiveData<List<WorkingDayWithWorkCodes>> {
        return repository.getAllWorkingDayWithWorkCodeBetween(before, after).asLiveData()
    }
}