package kr.mashup.feedget.remote

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kr.mashup.feedget.domain.repository.TokenRepository
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object FeedgetServiceFactory {

    fun makeFeedGetService(isDebug: Boolean, baseUrl: String, tokenProvider: TokenRepository): FeedGetService {
        val okHttpClient = makeOkHttpClient(
            makeLoggingInterceptor(isDebug),
            makeHeaderInterceptor(tokenProvider)
        )
        return makeFeedGetService(baseUrl, okHttpClient, makeGson())
    }

    private fun makeFeedGetService(baseUrl: String, okHttpClient: OkHttpClient, gson: Gson): FeedGetService {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        return retrofit.create(FeedGetService::class.java)
    }

    private fun makeOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor, headerInterceptor: Interceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(headerInterceptor)
            .build()
    }

    private fun makeGson(): Gson {
        return GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            .create()
    }

    private fun makeLoggingInterceptor(isDebug: Boolean): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = if (isDebug)
            HttpLoggingInterceptor.Level.BODY
        else
            HttpLoggingInterceptor.Level.NONE
        return logging
    }

    private fun makeHeaderInterceptor(tokenProvider: TokenRepository): Interceptor = Interceptor {
        val request = it.request()
        val requestBuilder = request.newBuilder()
            .method(request.method(), request.body())
            .header("Content-Type", "application/json")
            .addHeader("authorization", "Bearer ${tokenProvider.token}")
        it.proceed(requestBuilder.build())
    }

}
