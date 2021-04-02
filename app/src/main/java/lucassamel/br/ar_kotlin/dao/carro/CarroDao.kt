package lucassamel.br.ar_kotlin.dao.carro

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import lucassamel.br.ar_kotlin.model.Carro

interface CarroDao {

    fun insert(carro: Carro): Task<Void>

    fun delete(carro: Carro) : Task<Void>

    fun get(modelo: String): Task<DocumentSnapshot>

    fun selectByMarca(marca: String): Task<QuerySnapshot>

    fun all(): CollectionReference
}