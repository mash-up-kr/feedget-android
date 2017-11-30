package kr.mashup.feedget.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import kr.mashup.feedget.di.component.SplashActivitySubComponent
import kr.mashup.feedget.ui.splash.SplashActivity
import org.buffer.android.boilerplate.ui.injection.scopes.PerActivity


@Module(subcomponents = arrayOf(SplashActivitySubComponent::class))
abstract class ActivityModule {

    @PerActivity
    @ContributesAndroidInjector(modules = arrayOf(SplashActivityModule::class))
    abstract fun bindSplashActivity(): SplashActivity
}
