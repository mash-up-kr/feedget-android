package kr.mashup.feedget.injection.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import kr.mashup.feedget.ui.main.feed.FeedFragment
import kr.mashup.feedget.ui.main.tabs.news.NewsFragment

@Module
abstract class NewsFragmentProvider {

    @ContributesAndroidInjector()
    abstract fun provideNewsFragment(): NewsFragment
}