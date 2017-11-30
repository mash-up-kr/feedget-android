package kr.mashup.projectnoname.di.component

import dagger.Subcomponent
import dagger.android.AndroidInjector
import kr.mashup.projectnoname.ui.splash.SplashActivity

@Subcomponent
interface SplashActivitySubComponent :AndroidInjector<SplashActivity>{
    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<SplashActivity>()
}
