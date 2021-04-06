package app.bako.model.workcode

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import app.bako.model.DataBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WorkCodeViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<WorkCode>>
    val listWorkCode: LiveData<List<String>>
    private val repository: WorkCodeRepository

    init {
        val workCodeDao = DataBase.getDatabase(application).WorkCodeDao()
        repository = WorkCodeRepository(workCodeDao)
        readAllData = repository.readAllData
        listWorkCode = repository.listWorkCode
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

    fun getCodeList() {
        viewModelScope.launch(Dispatchers.IO){
            return@launch repository.getCodeList()
        }
    }
}