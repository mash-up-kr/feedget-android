package kr.mashup.feedget.data.repository

import io.reactivex.Completable
import io.reactivex.Single
import kr.mashup.feedget.domain.repository.FeedbackRepository
import kr.mashup.feedget.entity.Feedback
import kr.mashup.feedget.remote.FeedGetService
import javax.inject.Inject

class FeedbackRemoteDataSource @Inject constructor(private val api: FeedGetService) : FeedbackRepository {

    override fun getFeedback(creationId: String): Single<List<Feedback>> =
        api.getFeedback(creationId)
            .map { it.list }

    override fun postFeedback(creationId: String, description: String, anonymity: Boolean): Completable =
        api.postFeedback(creationId, FeedGetService.RequestPostFeedabck(description, anonymity))

    override fun deleteFeedback(creationId: String, feedbackId: String): Completable =
        api.deleteFeedback(creationId, feedbackId)

}
