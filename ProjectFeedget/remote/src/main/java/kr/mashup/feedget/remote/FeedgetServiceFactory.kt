package kr.mashup.feedget.remote

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object FeedgetServiceFactory {

    fun makeFeedGetService(isDebug: Boolean, tokenProvider: TokenProvider): FeedGetService {
        val okHttpClient = makeOkHttpClient(
            makeLoggingInterceptor(isDebug),
            makeHeaderInterceptor(tokenProvider)
        )
        return makeFeedGetService(okHttpClient, makeGson())
    }

    private fun makeFeedGetService(okHttpClient: OkHttpClient, gson: Gson): FeedGetService {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://joe-birch-dsdb.squarespace.com/s/")
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
            .setLenient()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
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

    private fun makeHeaderInterceptor(tokenProvider: TokenProvider): Interceptor = Interceptor {
        val request = it.request()
        val requestBuilder = request.newBuilder()
            .method(request.method(), request.body())
            .header("Content-Type", "application/json")
            .addHeader("authorization", "Bearer ${tokenProvider.token}")
        it.proceed(requestBuilder.build())
    }

}
