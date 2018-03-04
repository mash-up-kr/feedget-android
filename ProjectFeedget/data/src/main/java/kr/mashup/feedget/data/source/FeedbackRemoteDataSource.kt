package kr.mashup.feedget.data.source

import io.reactivex.Completable
import io.reactivex.Single
import kr.mashup.feedget.data.repository.FeedbackRemote
import kr.mashup.feedget.domain.repository.FeedbackRepository
import kr.mashup.feedget.entity.Feedback
import javax.inject.Inject

class FeedbackRemoteDataSource @Inject constructor(private val remote: FeedbackRemote) : FeedbackRepository {

    override fun getFeedback(creationId: String): Single<List<Feedback>> =
        remote.getFeedback(creationId)

    override fun postFeedback(creationId: String, description: String, anonymity: Boolean): Completable =
        remote.postFeedback(creationId, description, anonymity)

    override fun deleteFeedback(creationId: String, feedbackId: String): Completable =
        remote.deleteFeedback(creationId, feedbackId)

}
