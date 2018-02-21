package kr.mashup.feedget.injection.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import kr.mashup.feedget.injection.component.IntroActivitySubComponent
import kr.mashup.feedget.injection.component.SplashActivitySubComponent
import kr.mashup.feedget.ui.splash.SplashActivity
import kr.mashup.feedget.injection.scopes.PerActivity
import kr.mashup.feedget.ui.intro.IntroActivity
import kr.mashup.feedget.ui.main.MainActivity
import kr.mashup.feedget.ui.main.tabs.news.NewsFragment


@Module(subcomponents = arrayOf(IntroActivitySubComponent::class, SplashActivitySubComponent::class))
abstract class ActivityModule {

    @PerActivity
    @ContributesAndroidInjector(modules = arrayOf(SplashActivityModule::class))
    abstract fun bindSplashActivity(): SplashActivity

    @PerActivity
    @ContributesAndroidInjector(modules = arrayOf(IntroActivityModule::class))
    abstract fun bindIntroActivity(): IntroActivity

    @PerActivity
    @ContributesAndroidInjector(modules = arrayOf(FeedFragmentProvider::class))
    abstract fun bindMainActivity(): MainActivity

    @PerActivity
    @ContributesAndroidInjector(modules = arrayOf(NewsFragmentProvider::class))
    abstract fun bindNewsFragment(): NewsFragment
}
