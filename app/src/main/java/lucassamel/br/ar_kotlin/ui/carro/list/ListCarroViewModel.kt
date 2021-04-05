package lucassamel.br.ar_kotlin.ui.carro.list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import lucassamel.br.ar_kotlin.model.ProjectMarca
import lucassamel.br.ar_kotlin.model.ProjectModelo
import lucassamel.br.ar_kotlin.retrofit.ApiClient
import java.lang.Exception

class ListCarroViewModel : ViewModel() {


    private val _modelos = MutableLiveData<List<ProjectModelo>>()
    val modelos: LiveData<List<ProjectModelo>> = _modelos

    private val _msg = MutableLiveData<String>()
    val msg: LiveData<String> = _msg


}
