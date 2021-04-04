package lucassamel.br.ar_kotlin.model

import com.google.firebase.firestore.DocumentId

class Carro(
    @DocumentId
    var modelo: String? = null,
    var marca : String? = null,
) {
}