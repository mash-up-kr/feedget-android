package kr.mashup.feedget.ui.splash

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import dagger.android.AndroidInjection
import io.reactivex.Completable
import kr.mashup.feedget.R
import kr.mashup.feedget.extension.startActivityWithFinish
import kr.mashup.feedget.presentation.splash.SplashPresenter
import kr.mashup.feedget.presentation.splash.SplashView
import kr.mashup.feedget.ui.intro.IntroActivity
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SplashActivity : AppCompatActivity(), SplashView {

    @Inject lateinit override var presenter: SplashPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        AndroidInjection.inject(this)
        Completable.timer(1000, TimeUnit.MILLISECONDS)
            .subscribe {
                startIntro()
            }
    }

    override fun startIntro() {
        startActivityWithFinish<IntroActivity>()
    }
}
