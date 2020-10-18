package com.example.botflow.activities

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.botflow.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.vk.api.sdk.VK
import com.vk.api.sdk.VKApiCallback
import com.vk.api.sdk.auth.VKAccessToken
import com.vk.api.sdk.auth.VKAuthCallback
import com.vk.api.sdk.auth.VKScope
import com.vk.api.sdk.exceptions.VKApiCodes
import com.vk.api.sdk.exceptions.VKApiExecutionException
import com.vk.api.sdk.requests.VKRequest
import org.json.JSONObject


class AuthActivity : AppCompatActivity() {
    private lateinit var mGoogleSignInClient: GoogleSignInClient
    companion object {
        private const val RC_SIGN_IN = 1
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
        val account = GoogleSignIn.getLastSignedInAccount(this)
        if (account != null) {
            authorisedWithGoogle(account)
        }
        setContentView(R.layout.auth_activity)
        val logo = findViewById<ImageView>(R.id.logo_image)
        val authButtons = findViewById<LinearLayout>(R.id.auth_buttons)
        val signInButton = findViewById<SignInButton>(R.id.sign_in_button)
        val vkAuthButton = findViewById<ImageButton>(R.id.vk_auth)
        ObjectAnimator.ofFloat(logo, "translationY", -500f).apply {
            duration = 2000
            start()
        }
        ObjectAnimator.ofFloat(authButtons, "alpha", 0f, 1f).apply {
            duration = 4000
            start()

        }
        signInButton.setOnClickListener {
            val signInIntent = mGoogleSignInClient.signInIntent;
            startActivityForResult(signInIntent, RC_SIGN_IN);
        }
        vkAuthButton.setOnClickListener {
            VK.login(this, arrayListOf(VKScope.EMAIL))
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
                Toast.makeText(this@AuthActivity, "VK auth error", Toast.LENGTH_LONG).show()
            }
        }
        if (data == null || !VK.onActivityResult(requestCode, resultCode, data, callback)) {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
    private fun authorisedWithGoogle(account: GoogleSignInAccount) {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("email", account.email.toString())
        startActivity(intent)
    }
    private fun authorisedWithVK(token: VKAccessToken) {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("email", token.email.toString())
        startActivity(intent)
    }
}