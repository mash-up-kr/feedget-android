package kr.mashup.feedget.injection.component

import dagger.Subcomponent
import dagger.android.AndroidInjector
import kr.mashup.feedget.ui.intro.IntroActivity

@Subcomponent
interface IntroActivitySubComponent :AndroidInjector<IntroActivity>{
    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<IntroActivity>()
}
