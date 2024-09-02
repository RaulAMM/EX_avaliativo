package Raul.Ex_Avaliativo.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import Raul.Ex_Avaliativo.data.model.Anotation
import Raul.Ex_Avaliativo.data.repository.AnotationRepository
import kotlinx.coroutines.launch
class MainViewModel(private val repository: AnotationRepository) : ViewModel() {

    private val _anotation = MutableLiveData<List<Anotation>>()
    val anotation: LiveData<List<Anotation>> = _anotation
    init {
        checkDatabase()
    }
    fun checkDatabase(){
        viewModelScope.launch {
            val list = repository.findAll()
            _anotation.postValue(list)
        }
    }
    fun makeTaskDone(id: Long) {
        viewModelScope.launch {
            val anotation = repository.findById(id)
            if(anotation != null){
                repository.remove(anotation)
                checkDatabase()
            }
        }
    }
}
