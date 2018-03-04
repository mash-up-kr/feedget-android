package kr.mashup.feedget.remote

import io.reactivex.Completable
import io.reactivex.Single
import kr.mashup.feedget.data.repository.FeedbackRemote
import kr.mashup.feedget.entity.Feedback
import javax.inject.Inject

class FeedbackRemoteImpl @Inject constructor(private val api: FeedGetService) : FeedbackRemote {

    override fun getFeedback(creationId: String): Single<List<Feedback>> =
        api.getFeedback(creationId).map { it.list }

    override fun postFeedback(creationId: String, description: String, anonymity: Boolean): Completable =
        api.postFeedback(creationId, FeedGetService.RequestPostFeedabck(description, anonymity))

    override fun deleteFeedback(creationId: String, feedbackId: String): Completable =
        api.deleteFeedback(creationId, feedbackId)

}
