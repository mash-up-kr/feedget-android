package kr.mashup.feedget.ui.intro

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.facebook.*
import com.facebook.login.LoginResult
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_intro.*
import kr.mashup.feedget.R
import kr.mashup.feedget.extension.startActivityWithFinish
import kr.mashup.feedget.presentation.intro.IntroPresenter
import kr.mashup.feedget.presentation.intro.IntroView
import kr.mashup.feedget.ui.main.MainActivity
import javax.inject.Inject


class IntroActivity : AppCompatActivity(), IntroView {

    @Inject
    lateinit override var presenter: IntroPresenter

    private val facebookCallbackManager: CallbackManager by lazy {
        com.facebook.CallbackManager.Factory.create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        AndroidInjection.inject(this)

        facebookLoginButton.setReadPermissions(listOf("public_profile", "email"))
        facebookLoginButton.registerCallback(facebookCallbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(result: LoginResult?) {
                result?.let {
                    presenter.onFacebookCallbackSuccess()
                } ?: Toast.makeText(this@IntroActivity, "Error", Toast.LENGTH_SHORT).show()

            }

            override fun onCancel() {
                Toast.makeText(this@IntroActivity, "onCancel", Toast.LENGTH_SHORT).show()
            }

            override fun onError(error: FacebookException?) {
                Toast.makeText(this@IntroActivity, error?.message, Toast.LENGTH_SHORT).show()
            }
        })
        presenter.initialize()
    }

    override fun requestLoginFB() {
        val request = GraphRequest.newMeRequest(
            AccessToken.getCurrentAccessToken(),
            { jsonObject, response ->
                Log.e("jsonObject", jsonObject.toString())
                val name = (jsonObject.get("name") as? String)
                    ?: return@newMeRequest Toast.makeText(this@IntroActivity, "Error", Toast.LENGTH_SHORT).show()
                val email = (jsonObject.get("email") as? String)
                    ?: return@newMeRequest Toast.makeText(this@IntroActivity, "Error", Toast.LENGTH_SHORT).show()
                presenter.onRequestLoginFBSuccess(name, email, AccessToken.getCurrentAccessToken().token)
            })
        val parameters = Bundle()
        parameters.putString("fields", "id,name,email")
        request.parameters = parameters
        request.executeAsync()
    }

    override fun startMain() {
        startActivityWithFinish<MainActivity>()
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        facebookCallbackManager.onActivityResult(requestCode, resultCode, data)
    }

}


