package kr.mashup.feedget.ui.splash

import kr.mashup.feedget.ui.base.Presenter
import kr.mashup.feedget.ui.base.View
import java.io.File

interface SplashView : View<SplashPresenter> {
    override var presenter: SplashPresenter
    fun startIntro()
}

interface SplashPresenter : Presenter<SplashView> {
    fun test(file: File)
}
