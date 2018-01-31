package kr.mashup.feedget.injection.component

import dagger.Subcomponent
import dagger.android.AndroidInjector
import kr.mashup.feedget.ui.splash.SplashActivity

@Subcomponent
interface SplashActivitySubComponent :AndroidInjector<SplashActivity>{
    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<SplashActivity>()
}
