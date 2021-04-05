package lucassamel.br.ar_kotlin.dao.carro

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import lucassamel.br.ar_kotlin.model.Carro
import lucassamel.br.ar_kotlin.model.Modelo

interface CarroDao {

    fun insert(carro: Modelo): Task<Void>

    fun delete(carro: Modelo) : Task<Void>

    fun get(nome: String): Task<DocumentSnapshot>

    fun selectByMarca(nome: String): Task<QuerySnapshot>

    fun all(): CollectionReference
}