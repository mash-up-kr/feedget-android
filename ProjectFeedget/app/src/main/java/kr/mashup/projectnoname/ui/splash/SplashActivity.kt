package kr.mashup.projectnoname.ui.splash

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import dagger.android.AndroidInjection
import io.reactivex.Completable
import kotlinx.android.synthetic.main.activity_splash.*
import kr.mashup.projectnoname.R
import kr.mashup.projectnoname.extension.startActivityWithFinish
import kr.mashup.projectnoname.ui.intro.IntroActivity
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SplashActivity : AppCompatActivity(), SplashView {

    @Inject
    lateinit override var presenter: SplashPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        AndroidInjection.inject(this)
        Completable.timer(1000, TimeUnit.MILLISECONDS)
            .subscribe {
                startIntro()
            }

//        testButton.setOnClickListener { //Todo Remove
//            presenter.test(file(R.mipmap.ic_launcher))
//        }
    }

    override fun startIntro() {
        startActivityWithFinish<IntroActivity>()
    }

    fun file(id: Int): File { // Todo Remove
        val f = File(filesDir.path + "/aa.png")
        val inputStream = resources.openRawResource(id)
        val out = FileOutputStream(f)
        inputStream.use { input ->
            out.use { fileOut ->
                input.copyTo(fileOut)
            }
        }
        return f
    }
}
