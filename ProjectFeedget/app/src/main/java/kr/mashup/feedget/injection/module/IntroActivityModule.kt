package kr.mashup.feedget.injection.module

import dagger.Module
import dagger.Provides
import kr.mashup.feedget.domain.interactor.usecases.Register
import kr.mashup.feedget.injection.scopes.PerActivity
import kr.mashup.feedget.presentation.intro.IntroPresenter
import kr.mashup.feedget.presentation.intro.IntroPresenterImpl
import kr.mashup.feedget.presentation.intro.IntroView
import kr.mashup.feedget.ui.intro.IntroActivity

@Module
open class IntroActivityModule {

    @PerActivity
    @Provides
    internal fun provideView(view: IntroActivity): IntroView = view

    @PerActivity
    @Provides
    internal fun provideBrowsePresenter(view: IntroView, register: Register): IntroPresenter =
        IntroPresenterImpl(view, register)
}
