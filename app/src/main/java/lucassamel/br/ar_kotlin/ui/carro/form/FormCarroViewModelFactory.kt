package lucassamel.br.ar_kotlin.ui.carro.form

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class FormCarroViewModelFactory(
    private val codigo: Long
): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FormCarroViewModel(codigo) as T
    }

}