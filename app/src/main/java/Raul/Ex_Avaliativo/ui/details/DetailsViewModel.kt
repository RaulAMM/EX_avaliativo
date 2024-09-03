package Raul.Ex_Avaliativo.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import Raul.Ex_Avaliativo.data.model.Anotation
import Raul.Ex_Avaliativo.data.repository.AnotationRepository
import kotlinx.coroutines.launch
import java.time.LocalDate
class DetailsViewModel(private val repository: AnotationRepository): ViewModel() {

    private var anotationId: Long = -1
    private val _saved = MutableLiveData<Boolean>()
    val saved: LiveData<Boolean> = _saved

    private val _isUpdate = MutableLiveData<Boolean>()
    val isUpdate: LiveData<Boolean> = _isUpdate

    private val _title = MutableLiveData<String>()
    val title: LiveData<String> = _title

    private val _anotation = MutableLiveData<String>()
    val anotation: LiveData<String> = _anotation

    private val _description = MutableLiveData<String>()
    val description: LiveData<String> = _description

    private val _deadlineString = MutableLiveData<String>()
    val deadlineString: LiveData<String> = _deadlineString

    init {
        _isUpdate.value = false
    }
    fun saveAnotation(title: String, local: String, deadline: LocalDate){
        val anotation = Anotation(title = title, local = local, deadLineDate = deadline)
        if (_isUpdate.value == false) {
            viewModelScope.launch {
                _saved.value = repository.insert(anotation)
            }
        } else {
            anotation.id = anotationId
            viewModelScope.launch {
                _saved.value = repository.update(anotation)
                anotationId = -1
            }
        }
    }
    fun showEvent(id: Long) {
        viewModelScope.launch {
            val anotation = repository.findById(id)
            if (anotation != null){
                anotationId = anotation.id
                _isUpdate.value = true
                _description.value = anotation.local
                _title.value = anotation.title
                _deadlineString.value = anotation.deadline
            }
        }
    }
}

