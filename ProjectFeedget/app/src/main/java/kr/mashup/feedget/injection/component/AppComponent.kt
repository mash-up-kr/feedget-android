package kr.mashup.feedget.injection.component

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import kr.mashup.feedget.App
import kr.mashup.feedget.injection.module.ActivityModule
import kr.mashup.feedget.injection.module.AppModule
import kr.mashup.feedget.injection.scopes.PerApplication


@PerApplication
@Component(
    modules = arrayOf(
        ActivityModule::class,
        AppModule::class,
        AndroidSupportInjectionModule::class
    )
)
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance fun application(application: Application): Builder
        fun build(): AppComponent
    }

    fun inject(githubApp: App)
}
