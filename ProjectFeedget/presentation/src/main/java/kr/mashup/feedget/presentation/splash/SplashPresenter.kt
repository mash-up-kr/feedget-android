package kr.mashup.feedget.presentation.splash

import java.io.File
import javax.inject.Inject

class SplashPresenterImpl @Inject constructor(
    override val view: SplashView
) : SplashPresenter {

    override fun test(file: File) { //Todo Remove
    }


}
