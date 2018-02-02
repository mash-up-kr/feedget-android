package kr.mashup.feedget

import android.app.Application
import dagger.android.HasActivityInjector
import android.app.Activity
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import kr.mashup.feedget.injection.component.DaggerAppComponent
import javax.inject.Inject





class App : Application() ,HasActivityInjector{
    @Inject
    lateinit var dispatchingActivityInjector: DispatchingAndroidInjector<Activity>
    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent
            .builder()
            .application(this)
            .build()
            .inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> = dispatchingActivityInjector
}
