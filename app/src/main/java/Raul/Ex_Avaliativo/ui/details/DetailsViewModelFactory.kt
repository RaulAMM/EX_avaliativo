package Raul.Ex_Avaliativo.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import Raul.Ex_Avaliativo.data.repository.AnotationRepository

class DetailsViewModelFactory(private val repository: AnotationRepository):
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailsViewModel::class.java)){
            return DetailsViewModel(repository) as T
        }
        throw IllegalArgumentException("View model desconhecido")
    }
}
