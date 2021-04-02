package lucassamel.br.ar_kotlin.dao.carro

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import lucassamel.br.ar_kotlin.model.Carro

class CarroDaoFirestore: CarroDao {

    private val collection = FirebaseFirestore
        .getInstance()
        .collection("carros")

    override fun insert(carro: Carro): Task<Void> {
        return collection
            .document(carro.modelo!!)
            .set(carro)
    }
    override fun delete(carro: Carro): Task<Void> {
        return collection
            .document(carro.modelo!!)
            .delete()
    }
    override fun get(modelo: String): Task<DocumentSnapshot> {
        return collection
            .document(modelo)
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