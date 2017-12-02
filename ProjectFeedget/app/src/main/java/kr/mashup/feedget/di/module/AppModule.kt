package kr.mashup.feedget.di.module

import dagger.Module
import dagger.Provides
import kr.mashup.feedget.api.ApiService
import kr.mashup.feedget.data.ContentsRepository
import kr.mashup.feedget.data.FeedbacksRepository
import kr.mashup.feedget.domain.PostContentsUseCase
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.buffer.android.boilerplate.ui.injection.scopes.PerApplication
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.util.concurrent.TimeUnit


@Module
internal class AppModule {

    @PerApplication
    @Provides
    fun provideApiService(): ApiService =
        Retrofit.Builder()
            .baseUrl("http://192.168.0.4:8060/")
            .client(OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .build())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create<ApiService>(ApiService::class.java)

    @PerApplication
    @Provides
    fun provideContentsRepository(apiService: ApiService) = ContentsRepository(apiService)

    @PerApplication
    @Provides
    fun provideFeedbackRepository(apiService: ApiService) = FeedbacksRepository(apiService)

    @PerApplication
    @Provides
    fun providePostContentUseCase(contentsRepository: ContentsRepository) = PostContentsUseCase(contentsRepository)

}
