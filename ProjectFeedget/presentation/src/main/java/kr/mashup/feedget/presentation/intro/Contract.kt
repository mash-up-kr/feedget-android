package kr.mashup.feedget.presentation.intro

import kr.mashup.feedget.presentation.Presenter
import kr.mashup.feedget.presentation.View

interface IntroView : View<IntroPresenter> {
    fun startMain()
    fun requestLoginFB()
}

interface IntroPresenter : Presenter<IntroView> {

    fun initialize(isLogin: Boolean)
    fun onFacebookCallbackSuccess()
    fun onRequestLoginFBSuccess(name: String, email: String, token: String)
}
