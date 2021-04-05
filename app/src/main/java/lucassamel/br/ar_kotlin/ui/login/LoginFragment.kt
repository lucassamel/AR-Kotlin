package lucassamel.br.ar_kotlin.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.cadastro_fragment.*
import kotlinx.android.synthetic.main.login_fragment.*
import lucassamel.br.ar_kotlin.R

class LoginFragment : Fragment() {

    private lateinit var callbackManager: CallbackManager
    private lateinit var viewModel: LoginViewModel

    private lateinit var auth: FirebaseAuth

    private lateinit var loginButton: LoginButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.login_fragment, container, false)

        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        viewModel.status.observe(viewLifecycleOwner, Observer {
            if (it){
                findNavController().navigate(R.id.action_loginFragment_to_listCarroFragment)
            }
        })

        viewModel.msg.observe(viewLifecycleOwner, Observer {
            if (!it.isNullOrBlank())
                Toast
                    .makeText(
                        requireContext(),
                        it,
                        Toast.LENGTH_LONG
                    ).show()

        })



        // Initialize Facebook Login button
        callbackManager = CallbackManager.Factory.create()

        // loginButton = findViewById(R.id.login_button)
        loginButton = view.findViewById(R.id.login_button)
        loginButton.setPermissions("email", "public_profile")
        loginButton.fragment = this
        loginButton.registerCallback(
            callbackManager,
            object : FacebookCallback<LoginResult> {
                override fun onSuccess(result: LoginResult) {
                    handleFacebookAccessToken(result.accessToken)
                    findNavController().navigate(R.id.action_loginFragment_to_listCarroFragment)
                }

                override fun onCancel() {
                    Log.i("FacebookLogin", "onCancel")
                }

                override fun onError(error: FacebookException?) {
                    Log.i("FacecebookLogin", "${error?.message}")
                }

            }
        )

        auth = Firebase.auth

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnLogin.setOnClickListener {
            val email = editTextEmail.text.toString()
            val senha = editTextSenha.text.toString()
            viewModel.verificarLogin(email, senha)

        }

        btnCadastrar.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_cadastroFragment)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        btnLogin.setOnClickListener {
            val email = editTextEmail.text.toString()
            val senha = editTextSenha.text.toString()
            viewModel.verificarLogin(email, senha)

        }

        btnCadastrar.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_cadastroFragment)
        }

        // Pass the activity result back to the Facebook SDK
        callbackManager.onActivityResult(requestCode, resultCode, data)
    }

    private fun handleFacebookAccessToken(token: AccessToken) {

        val credential = FacebookAuthProvider.getCredential(token.token)
        auth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val user = auth.currentUser
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(
                        requireContext(), "Authentication failed: ${task.exception?.message}",
                        Toast.LENGTH_SHORT
                    ).show()

                }
            }
    }


}