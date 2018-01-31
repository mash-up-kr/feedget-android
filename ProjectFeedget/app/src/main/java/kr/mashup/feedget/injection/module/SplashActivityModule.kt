package kr.mashup.feedget.injection.module

import dagger.Module
import dagger.Provides
import kr.mashup.feedget.presentation.splash.SplashPresenter
import kr.mashup.feedget.presentation.splash.SplashPresenterImpl
import kr.mashup.feedget.presentation.splash.SplashView
import kr.mashup.feedget.ui.splash.SplashActivity
import kr.mashup.feedget.injection.scopes.PerActivity

@Module
open class SplashActivityModule {

    @PerActivity
    @Provides
    fun provideView(view: SplashActivity): SplashView = view

    @PerActivity
    @Provides
    fun provideBrowsePresenter(view: SplashView): SplashPresenter =
        SplashPresenterImpl(view)
}
