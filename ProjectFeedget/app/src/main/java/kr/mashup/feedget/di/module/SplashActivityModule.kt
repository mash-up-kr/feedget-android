package kr.mashup.feedget.di.module

import dagger.Module
import dagger.Provides
import kr.mashup.feedget.domain.PostContentsUseCase
import kr.mashup.feedget.ui.splash.SplashActivity
import kr.mashup.feedget.ui.splash.SplashPresenter
import kr.mashup.feedget.ui.splash.SplashPresenterImpl
import kr.mashup.feedget.ui.splash.SplashView
import org.buffer.android.boilerplate.ui.injection.scopes.PerActivity

@Module
open class SplashActivityModule {

    @PerActivity
    @Provides
    internal fun provideView(view: SplashActivity): SplashView = view

    @PerActivity
    @Provides
    internal fun provideBrowsePresenter(view: SplashView, postContentsUseCase: PostContentsUseCase): SplashPresenter =
        SplashPresenterImpl(view, postContentsUseCase)
}
