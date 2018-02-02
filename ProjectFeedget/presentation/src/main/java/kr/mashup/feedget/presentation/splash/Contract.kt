package kr.mashup.feedget.presentation.splash

import kr.mashup.feedget.presentation.Presenter
import kr.mashup.feedget.presentation.View
import java.io.File

interface SplashView : View<SplashPresenter> {
    override var presenter: SplashPresenter
    fun startIntro()
}

interface SplashPresenter : Presenter<SplashView> {
    fun test(file: File)
}
