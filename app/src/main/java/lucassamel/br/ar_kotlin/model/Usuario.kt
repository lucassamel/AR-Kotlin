package lucassamel.br.ar_kotlin.model

import com.google.firebase.firestore.DocumentId

class Usuario(var nome: String? = null,
              var username: String? = null,
              var dataNascimento: String? = null,
              @DocumentId
              val uid: String? = null) {
}