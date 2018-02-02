package kr.mashup.feedget.injection.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import kr.mashup.feedget.ui.main.feed.FeedFragment


@Module
abstract class FeedFragmentProvider {

    @ContributesAndroidInjector()
    abstract fun provideFeedFragment(): FeedFragment
}
