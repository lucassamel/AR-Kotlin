package lucassamel.br.ar_kotlin.ui.marca

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import lucassamel.br.ar_kotlin.model.ProjectMarca
import lucassamel.br.ar_kotlin.retrofit.ApiClient
import java.lang.Exception

class MarcaViewModel : ViewModel() {

    private val _marcas = MutableLiveData<List<ProjectMarca>>()
    val marcas: LiveData<List<ProjectMarca>> = _marcas

    private val _msg = MutableLiveData<String>()
    val msg: LiveData<String> = _msg

    init {
        viewModelScope.launch {
            try {
                val projectService = ApiClient.getProjectService()
                val brands = projectService.all()
                _marcas.value = brands
            } catch (e: Exception){
                _msg.value = "${e.message}"
                Log.i("API","${e.message}")
            }
        }
    }

}