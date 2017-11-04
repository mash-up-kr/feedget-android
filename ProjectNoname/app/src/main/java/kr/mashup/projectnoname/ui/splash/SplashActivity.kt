package kr.mashup.projectnoname.ui.splash

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.reactivex.Completable
import kr.mashup.projectnoname.R
import kr.mashup.projectnoname.extension.startActivityWithFinish
import kr.mashup.projectnoname.ui.intro.IntroActivity
import java.util.concurrent.TimeUnit

class SplashActivity : AppCompatActivity(), SplashView {

    override val presenter: SplashPresenter by lazy { SplashPresenterImpl(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Completable.timer(1000, TimeUnit.MILLISECONDS)
            .subscribe {
                startActivityWithFinish<IntroActivity>()
            }
    }

}
