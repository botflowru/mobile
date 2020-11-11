package com.example.botflow.ui

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.botflow.R
import com.example.botflow.activities.MainActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.vk.api.sdk.VK
import com.vk.api.sdk.auth.VKAccessToken
import com.vk.api.sdk.auth.VKAuthCallback
import com.vk.api.sdk.auth.VKScope

class AuthFragment : Fragment() {
    private lateinit var mGoogleSignInClient: GoogleSignInClient
    companion object {
        private const val RC_SIGN_IN = 1
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()
        mGoogleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)
        val account = GoogleSignIn.getLastSignedInAccount(context)
        if (account != null) {
            authorisedWithGoogle(account)
        }
        return inflater.inflate(R.layout.auth_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val logo = view.findViewById<ImageView>(R.id.logo_image)
        val authTextView = view.findViewById<TextView>(R.id.auth_textView)
        val authButtons = view.findViewById<LinearLayout>(R.id.auth_buttons)
        val googleAuthButton = view.findViewById<Button>(R.id.google_auth_button)
        val vkAuthButton = view.findViewById<Button>(R.id.vk_auth_button)
        ObjectAnimator.ofFloat(logo, "translationY", -500f).apply {
            duration = 2000
            start()
        }
        ObjectAnimator.ofFloat(authButtons, "alpha", 0f, 1f).apply {
            duration = 4000
            start()
        }
        ObjectAnimator.ofFloat(authTextView, "alpha", 0f, 1f).apply {
            duration = 4000
            start()
        }
        googleAuthButton.setOnClickListener {
            val signInIntent = mGoogleSignInClient.signInIntent
            startActivityForResult(signInIntent, RC_SIGN_IN)
        }
        vkAuthButton.setOnClickListener {
            VK.login(requireActivity(), arrayListOf(VKScope.EMAIL))
        }

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            val account = task.getResult(ApiException::class.java)
            authorisedWithGoogle(account!!)

        }
        val callback = object: VKAuthCallback {
            override fun onLogin(token: VKAccessToken) {
                authorisedWithVK(token)
            }

            override fun onLoginFailed(errorCode: Int) {
                Toast.makeText(context, "VK auth error", Toast.LENGTH_LONG).show()
            }
        }
        if (data == null || !VK.onActivityResult(requestCode, resultCode, data, callback)) {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
    private fun authorisedWithGoogle(account: GoogleSignInAccount) {
        val intent = Intent(activity, MainActivity::class.java)
        intent.putExtra("email", account.email.toString())
        startActivity(intent)
    }
    private fun authorisedWithVK(token: VKAccessToken) {
        val intent = Intent(activity, MainActivity::class.java)
        intent.putExtra("email", token.email.toString())
        startActivity(intent)
    }
}