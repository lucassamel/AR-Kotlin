package lucassamel.br.ar_kotlin.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
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
import lucassamel.br.ar_kotlin.R

class LoginFragment : Fragment() {

    private lateinit var callbackManager: CallbackManager
    private lateinit var viewModel: LoginViewModel

    private lateinit var loginButton: LoginButton

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        // Initialize Facebook Login button
        callbackManager = CallbackManager.Factory.create()

       // loginButton = findViewById(R.id.login_button)
        loginButton.setPermissions("email", "public_profile")
        loginButton.fragment = this
        loginButton.registerCallback(
                callbackManager,
                object : FacebookCallback<LoginResult>{
                    override fun onSuccess(result: LoginResult) {
                        handleFacebookAccessToken(result.accessToken)
                    }

                    override fun onCancel() {
                        Log.i("FacebookLogin","onCancel")
                    }

                    override fun onError(error: FacebookException?) {
                        Log.i("FacecebookLogin", "${error?.message}")
                    }

                }
        )

        auth = Firebase.auth

        return inflater.inflate(R.layout.login_fragment, container, false)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

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
                        Toast.makeText(requireContext(), "Authentication failed: ${task.exception?.message}",
                                Toast.LENGTH_SHORT).show()

                    }
                }
    }



}