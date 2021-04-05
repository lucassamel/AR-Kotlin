package lucassamel.br.ar_kotlin.ui.carro.form

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import lucassamel.br.ar_kotlin.dao.carro.CarroDao
import lucassamel.br.ar_kotlin.model.Modelo
import lucassamel.br.ar_kotlin.model.ProjectModelo
import lucassamel.br.ar_kotlin.retrofit.ApiClient

class FormCarroViewModel(
    private val codigo: Long,
    private val carroDao: CarroDao
) : ViewModel() {

    private val _modelos = MutableLiveData<ProjectModelo>()
    val modelos: LiveData<ProjectModelo> = _modelos

    private val _msg = MutableLiveData<String>()
    val msg: LiveData<String> = _msg

    private val _status = MutableLiveData<Boolean>()
    val status: LiveData<Boolean> = _status

    private var modelo: String? = null

    private val _spinner = MutableLiveData<MutableList<String>>()
    private val spinner: LiveData<MutableList<String>> = _spinner

    init {
        viewModelScope.launch {
            _modelos.value = ApiClient.getProjectService().modelos(codigo)
//            modelos.value?.modelos?.forEach {
//                _spinner.value?.add(it.nome!!) ?: mutableListOf(it.nome)
//            }
        }

         _spinner.value = mutableListOf()

    }

    fun modeloSelecionado(modelo: String?) {
        this.modelo = modelo
    }

    fun insertCarro(modelo: String, marca: String){

        val carro = Modelo(modelo,marca,codigo)
        carroDao.insert(carro)
            .addOnSuccessListener {
                _status.value = true
                _msg.value = "Persistência realizada com sucesso."
            }
            .addOnFailureListener {
                _msg.value = "${it.message}"
            }

    }

}