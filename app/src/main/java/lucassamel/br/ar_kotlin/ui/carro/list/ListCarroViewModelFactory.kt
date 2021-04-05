package lucassamel.br.ar_kotlin.ui.carro.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import lucassamel.br.ar_kotlin.dao.carro.CarroDao

class ListCarroViewModelFactory(private val carroDao: CarroDao) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListCarroViewModel::class.java))
            return ListCarroViewModel(carroDao) as T
        throw IllegalArgumentException("Classe ViewModel desconhecida.")
    }
}