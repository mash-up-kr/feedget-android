package kr.mashup.projectnoname.api

import io.reactivex.Completable
import io.reactivex.Single
import okhttp3.MultipartBody
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiService {

    @GET("/categories")
    fun getFeedbacks(): Single<String>

    @Multipart
    @POST("/contents")
    fun postContents(
        @Part("creationId") id: Long,
        @Part("type") type: String,
        @Part files: List<MultipartBody.Part>
    ): Completable
}
