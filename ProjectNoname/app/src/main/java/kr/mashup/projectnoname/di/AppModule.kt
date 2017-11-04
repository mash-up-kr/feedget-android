package kr.mashup.projectnoname.di

import dagger.Module
import dagger.Provides
import kr.mashup.projectnoname.api.ApiService
import javax.inject.Singleton
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory


@Module
internal class AppModule {

    @Singleton
    @Provides
    fun provideApiService(): ApiService =
        Retrofit.Builder()
            .baseUrl("192.168.0.4:8060/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create<ApiService>(ApiService::class.java)

}
