package lucassamel.br.ar_kotlin.model

import com.google.firebase.firestore.DocumentId

class Modelo(
    @DocumentId
    var nome: String? = null,
    var marca: String? = null,
    var codigo: Long? = null
) {
}