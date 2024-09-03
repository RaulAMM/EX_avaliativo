package Raul.Ex_Avaliativo.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import Raul.Ex_Avaliativo.data.repository.AnotationRepository

class MainViewModelFactory(
    private val repository: AnotationRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(repository) as T
        }
        throw IllegalArgumentException("View Model desconhecido")
    }
}