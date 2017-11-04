package kr.mashup.projectnoname.ui.intro

import android.content.Intent
import com.facebook.AccessToken
import com.facebook.login.LoginResult

class IntroPresenterImpl(override val view: IntroView) : IntroPresenter {

    override fun initialize() {
        if (AccessToken.getCurrentAccessToken() != null)
            view.startMain()
    }

    override fun onFacebookCallbackSuccess(loginResult: LoginResult) {
        view.startMain()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    }
}
