package kr.mashup.projectnoname

import android.app.Application
import kr.mashup.projectnoname.di.AppInjector

class App : Application(){

    override fun onCreate() {
        super.onCreate()
        AppInjector.init(this)
    }
}
