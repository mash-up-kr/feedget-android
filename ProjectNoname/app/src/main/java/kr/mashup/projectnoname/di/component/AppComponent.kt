package kr.mashup.projectnoname.di.component

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import kr.mashup.projectnoname.App
import kr.mashup.projectnoname.di.module.ActivityModule
import kr.mashup.projectnoname.di.module.AppModule
import org.buffer.android.boilerplate.ui.injection.scopes.PerActivity
import org.buffer.android.boilerplate.ui.injection.scopes.PerApplication
import javax.inject.Singleton


@PerApplication
@Component(
    modules = arrayOf(
        AndroidInjectionModule::class,
        AppModule::class,
        ActivityModule::class,
        AndroidSupportInjectionModule::class
    )
)
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(githubApp: App)
}
