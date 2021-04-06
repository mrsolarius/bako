package app.bako.model.workcode

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import app.bako.model.DataBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WorkCodeViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<WorkCode>>
    private val repository: WorkCodeRepository

    init {
        val workCodeDao = DataBase.getDatabase(application).WorkCodeDao()
        repository = WorkCodeRepository(workCodeDao)
        readAllData = repository.readAllData
    }

    fun addWorkCode(workCode: WorkCode){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addWorkCode(workCode)
        }
    }

    fun updateWorkCode(workCode: WorkCode){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateWorkCode(workCode)
        }
    }

    fun deleteWorkCode(workCode: WorkCode){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteWorkCode(workCode)
        }
    }

    fun deleteAllWorkCode(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllWorkCode()
        }
    }

    fun getCodeList(): LiveData<List<String>> {
            return repository.getCodeList().asLiveData()
    }
}