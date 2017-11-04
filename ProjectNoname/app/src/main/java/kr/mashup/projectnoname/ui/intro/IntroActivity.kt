package kr.mashup.projectnoname.ui.intro

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import kotlinx.android.synthetic.main.activity_intro.*
import kr.mashup.projectnoname.R
import kr.mashup.projectnoname.extension.startActivity
import kr.mashup.projectnoname.ui.main.MainActivity

class IntroActivity : AppCompatActivity(), IntroView {
    override val presenter: IntroPresenter by lazy { IntroPresenterImpl(this) }
    private val facebookCallbackManager: CallbackManager by lazy {
        com.facebook.CallbackManager.Factory.create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)
        facebookLoginButton.registerCallback(facebookCallbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(result: LoginResult?) {
                result?.let {
                    presenter.onFacebookCallbackSuccess(it)
                } ?: Toast.makeText(this@IntroActivity, "Error", Toast.LENGTH_SHORT).show()

            }

            override fun onCancel() {
            }

            override fun onError(error: FacebookException?) {
                Toast.makeText(this@IntroActivity, error?.message, Toast.LENGTH_SHORT).show()
            }

        })

        presenter.initialize()
    }

    override fun startMain() {
        startActivity<MainActivity>()
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        facebookCallbackManager.onActivityResult(requestCode, resultCode, data)
    }

}


