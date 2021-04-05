package lucassamel.br.ar_kotlin.ui.carro.form

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import lucassamel.br.ar_kotlin.dao.carro.CarroDao

class FormCarroViewModelFactory(
    private val codigo: Long,
    private val carroDao: CarroDao
): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FormCarroViewModel::class.java))
            return FormCarroViewModel(codigo,carroDao) as T
        throw IllegalArgumentException("Classe ViewModel desconhecida.")
    }

}