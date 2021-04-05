package lucassamel.br.ar_kotlin.dao.carro

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import lucassamel.br.ar_kotlin.model.Carro
import lucassamel.br.ar_kotlin.model.Modelo

class CarroDaoFirestore: CarroDao {

    private val collection = FirebaseFirestore
        .getInstance()
        .collection("carros")

    override fun insert(modelo: Modelo): Task<Void> {
        return collection
            .document(modelo.nome!!)
            .set(modelo)
    }
    override fun delete(modelo: Modelo): Task<Void> {
        return collection
            .document(modelo.nome!!)
            .delete()
    }
    override fun get(nome: String): Task<DocumentSnapshot> {
        return collection
            .document(nome)
            .get()
    }

    override fun selectByMarca(marca: String): Task<QuerySnapshot> {
        return collection
            .whereEqualTo("carros", marca)
            .get()
    }

    override fun all(): CollectionReference {
        return collection
    }
}