package kr.mashup.feedget.ui.intro

import android.content.Intent
import com.facebook.login.LoginResult
import kr.mashup.feedget.ui.base.Presenter
import kr.mashup.feedget.ui.base.View

interface IntroView : View<IntroPresenter> {
    fun startMain()
}

interface IntroPresenter : Presenter<IntroView> {

    fun initialize()

    fun onFacebookCallbackSuccess(loginResult: LoginResult)

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
}
