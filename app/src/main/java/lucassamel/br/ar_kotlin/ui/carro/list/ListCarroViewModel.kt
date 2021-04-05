package lucassamel.br.ar_kotlin.ui.carro.list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import lucassamel.br.ar_kotlin.dao.carro.CarroDao
import lucassamel.br.ar_kotlin.model.Modelo
import lucassamel.br.ar_kotlin.model.ProjectMarca
import lucassamel.br.ar_kotlin.model.ProjectModelo
import lucassamel.br.ar_kotlin.retrofit.ApiClient
import java.lang.Exception

class ListCarroViewModel(private val carroDao: CarroDao) : ViewModel() {


    private val _carros = MutableLiveData<List<Modelo>>()
    val carros: LiveData<List<Modelo>> = _carros

    fun atualizarQuantidade(){
        carroDao.all()
            .addSnapshotListener { snapshot, error ->
                if (error != null)
                    Log.i("LstCarroVMSnapshotError", "${error.message}")
                else
                    if (snapshot != null && !snapshot.isEmpty)
                        _carros.value = snapshot.toObjects(Modelo::class.java)
            }
    }


}
