package kr.mashup.projectnoname.ui.splash

import kr.mashup.projectnoname.ui.base.Presenter
import kr.mashup.projectnoname.ui.base.View
import java.io.File

interface SplashView : View<SplashPresenter> {
    override var presenter: SplashPresenter
    fun startIntro()
}

interface SplashPresenter : Presenter<SplashView> {
    fun test(file: File)
}
