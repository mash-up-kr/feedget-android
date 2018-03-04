package kr.mashup.feedget.remote

import io.reactivex.Completable
import io.reactivex.Single
import kr.mashup.feedget.entity.Category
import kr.mashup.feedget.entity.Creation
import kr.mashup.feedget.entity.Feedback
import kr.mashup.feedget.entity.SignIn
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*
import java.io.File

interface FeedGetService {

    //Categories

    @GET("/categories")
    fun getCategories(): Single<GetCategoriesResponse>

    data class GetCategoriesResponse(
        val categories: List<Category>
    )

    //Creations

    @GET("/creations")
    fun getCreations(
        @Query("page") page: String,
        @Query("cursor") cursor: String?,
        @Query("categories") categories: String
    ): Single<GetCreationsResponse>

    data class GetCreationsResponse(
        val nextPage: Long,
        val list: List<Creation>
    )

    @GET("/creations/{creationId}")
    fun getCreation(
        @Path("creationId") creationId: String
    ): Single<GetCreationResponse>

    data class GetCreationResponse(
        val item: Creation
    )

    @POST("/creations")
    fun postCreations(
        @Body body: RequestPostCreations
    ): Single<PostCreationResponse>

    data class RequestPostCreations(
        val title: String,
        val description: String,
        val category: String,
        val anonymity: Boolean,
        val rewardPoint: Double
    )

    data class PostCreationResponse(
        val item: Creation
    )

    @PUT("/creations/{creationId}")
    fun updateCreation(
        @Path("creationId") creationId: String,
        @Body body: RequestUpdateCreation
    ): Single<UpdateCreationResponse>

    data class RequestUpdateCreation(
        val title: String? = null,
        val description: String? = null,
        val category: String? = null,
        val anonymity: Boolean? = null,
        val rewardPoint: Double? = null
    )

    data class UpdateCreationResponse(
        val item: Creation
    )

    @DELETE("/creations/{creationId}")
    fun deleteCreation(
        @Path("creationId") creationId: String
    ): Completable

    //Contents

    @Multipart
    @POST("/creations/{creationId}/contents")
    fun postContent(
        @Path("creationId") creationId: String,
        @Part type: String,
        @Part files: List<MultipartBody.Part>
    ): Completable

    data class MultiPartBodyPart(
        val files: List<File>
    ) {
        fun multiparts() = files.map {
            MultipartBody.Part.createFormData("files", it.name, RequestBody.create(MediaType.parse("image/*"), it))
        }
    }

    @DELETE("/creations/{creationId}/contents")
    fun deleteContent(
        @Path("creationId") creationId: String,
        @Body body: RequestDeleteContent
    ): Completable

    data class RequestDeleteContent(
        val contentId: List<String>
    )

    //Feedback

    @GET("/creations/{creationId}/feedbacks")
    fun getFeedback(
        @Path("creationId") creationId: String
    ): Single<GetFeedbackResponse>

    data class GetFeedbackResponse(
        val list: List<Feedback>
    )

    @POST("/creations/{creationId}/feedbacks")
    fun postFeedback(
        @Path("creationId") creationId: String,
        @Body body: RequestPostFeedabck
    ): Completable

    data class RequestPostFeedabck(
        val description: String,
        val anonymity: Boolean
    )

    @DELETE("/creations/{creationId}/feedbacks/{feedbackId}")
    fun deleteFeedback(
        @Path("creationId") creationId: String,
        @Path("feedbackId") feedbackId: String
    ): Completable

    //User

    @POST("/sign-in")
    fun register(
        @Body body: RequestRegister
    ): Single<RegisterResponse>

    data class RequestRegister(
        val realName: String,
        val nickname: String,
        val email: String,
        val oauthToken: String,
        val oauthType: String
    )

    data class RegisterResponse(
        val item: SignIn
    )

    @PATCH("/devices/cloud-messaging")
    fun registerFCM(
        @Body body: RequestRegisterFCM
    ): Completable

    data class RequestRegisterFCM(
        val cloudMsgRegToken: String
    )
}
