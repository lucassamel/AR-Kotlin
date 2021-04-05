package lucassamel.br.ar_kotlin.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class LoginViewModel : ViewModel() {

    private val _status = MutableLiveData<Boolean>()
    val status: LiveData<Boolean> = _status

    private val _msg = MutableLiveData<String?>()
    val msg: MutableLiveData<String?> = _msg

    fun verificarLogin(email: String, senha: String) {
        val firebaseAuth = FirebaseAuth.getInstance()
        val task = firebaseAuth
            .signInWithEmailAndPassword(email,senha)

        task
            .addOnSuccessListener {
                _status.value = true
                _msg.value = "Usuário autenticado com sucesso!"
            }
            .addOnFailureListener{
                _msg.value = "${it.message}"
            }
    }

}
