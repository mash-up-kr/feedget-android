package kr.mashup.projectnoname.api

import android.text.method.SingleLineTransformationMethod
import io.reactivex.Single
import retrofit2.http.GET

interface ApiService {

    @GET("/hello")
    fun getFeedbacks(): Single<String>
}
