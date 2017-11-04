package kr.mashup.projectnoname.ui.intro

import android.content.Intent
import com.facebook.login.LoginResult
import kr.mashup.projectnoname.ui.base.Presenter
import kr.mashup.projectnoname.ui.base.View

interface IntroView : View<IntroPresenter> {
    fun startMain()
}

interface IntroPresenter : Presenter<IntroView> {

    fun initialize()

    fun onFacebookCallbackSuccess(loginResult: LoginResult)

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
}
