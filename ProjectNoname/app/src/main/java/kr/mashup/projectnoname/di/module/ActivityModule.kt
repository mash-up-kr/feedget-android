package kr.mashup.projectnoname.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import kr.mashup.projectnoname.di.component.SplashActivitySubComponent
import kr.mashup.projectnoname.ui.splash.SplashActivity
import org.buffer.android.boilerplate.ui.injection.scopes.PerActivity


@Module(subcomponents = arrayOf(SplashActivitySubComponent::class))
abstract class ActivityModule {

    @PerActivity
    @ContributesAndroidInjector(modules = arrayOf(SplashActivityModule::class))
    abstract fun bindSplashActivity(): SplashActivity
}
