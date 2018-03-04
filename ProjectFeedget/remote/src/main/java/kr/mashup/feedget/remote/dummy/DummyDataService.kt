package kr.mashup.feedget.remote.dummy

import io.reactivex.Completable
import io.reactivex.Single
import kr.mashup.feedget.entity.*
import kr.mashup.feedget.remote.FeedGetService
import okhttp3.MultipartBody
import java.util.*

class DummyDataService : FeedGetService {
    override fun getCategories(): Single<FeedGetService.GetCategoriesResponse> = Single.just(
        FeedGetService.GetCategoriesResponse(
            listOf(
                Category(1L, "1번"),
                Category(2L, "2번"),
                Category(3L, "3번"),
                Category(4L, "4번"),
                Category(5L, "5번")
            )
        )
    )

    override fun getCreations(page: String, cursor: String?, categories: String): Single<FeedGetService.GetCreationsResponse> =
        Single.just(
            FeedGetService.GetCreationsResponse(
                1,
                listOf(
                    Creation(
                        "1",
                        "이것은 타이틀이 맞습니다",
                        "이것은 세부 사항",
                        Date().time,
                        null,
                        10.0,
                        CreationStatus.DEADLINE,
                        true,
                        10,
                        false,
                        User(
                            "1",
                            "니그네임",
                            Grade.BRONZE
                        ),
                        listOf()
                    )
                )
            )
        )


    override fun getCreation(creationId: String): Single<FeedGetService.GetCreationResponse> =
        Single.just(
            FeedGetService.GetCreationResponse(
                Creation(
                    "1",
                    "이것은 타이틀",
                    "이것은 세부 사항",
                    Date().time,
                    null,
                    10.0,
                    CreationStatus.DEADLINE,
                    true,
                    10,
                    false,
                    User(
                        "1",
                        "니그네임",
                        Grade.BRONZE
                    ),
                    listOf()
                )
            )
        )

    override fun postCreations(body: FeedGetService.RequestPostCreations): Single<FeedGetService.PostCreationResponse> =
        Single.just(
            FeedGetService.PostCreationResponse(
                Creation(
                    "1",
                    "이것은 타이틀",
                    "이것은 세부 사항",
                    Date().time,
                    null,
                    10.0,
                    CreationStatus.DEADLINE,
                    true,
                    10,
                    false,
                    User(
                        "1",
                        "니그네임",
                        Grade.BRONZE
                    ),
                    listOf()
                )
            )
        )

    override fun updateCreation(creationId: String, body: FeedGetService.RequestUpdateCreation): Single<FeedGetService.UpdateCreationResponse> =
        Single.just(
            FeedGetService.UpdateCreationResponse(
                Creation(
                    "1",
                    "이것은 타이틀",
                    "이것은 세부 사항",
                    Date().time,
                    null,
                    10.0,
                    CreationStatus.DEADLINE,
                    true,
                    10,
                    false,
                    User(
                        "1",
                        "니그네임",
                        Grade.BRONZE
                    ),
                    listOf()
                )
            )
        )

    override fun deleteCreation(creationId: String): Completable =
        Completable.complete()

    override fun postContent(creationId: String, type: String, files: List<MultipartBody.Part>): Completable =
        Completable.complete()

    override fun deleteContent(creationId: String, body: FeedGetService.RequestDeleteContent): Completable =
        Completable.complete()

    override fun getFeedback(creationId: String): Single<FeedGetService.GetFeedbackResponse> =
        Single.just(
            FeedGetService.GetFeedbackResponse(
                listOf(
                    Feedback(
                        1,
                        "설명",
                        User(
                            "1",
                            "니그네임",
                            Grade.BRONZE
                        ),
                        false,
                        true,
                        listOf()
                    )
                )
            )
        )

    override fun postFeedback(creationId: String, body: FeedGetService.RequestPostFeedabck): Completable =
        Completable.complete()


    override fun deleteFeedback(creationId: String, feedbackId: String): Completable =
        Completable.complete()

    override fun register(body: FeedGetService.RequestRegister): Single<FeedGetService.RegisterResponse> =
        Single.just(
            FeedGetService.RegisterResponse(
                SignIn(
                    "access",
                    "refresh"
                )
            )
        )

    override fun registerFCM(body: FeedGetService.RequestRegisterFCM): Completable =
        Completable.complete()

}
