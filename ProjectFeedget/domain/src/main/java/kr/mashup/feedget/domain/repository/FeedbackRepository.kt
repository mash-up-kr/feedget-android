package kr.mashup.feedget.domain.repository

import io.reactivex.Completable
import io.reactivex.Single
import kr.mashup.feedget.entity.Feedback

interface FeedbackRepository {

    fun getFeedback(
        creationId: String
    ): Single<List<Feedback>>

    fun postFeedback(
        creationId: String,
        description: String,
        anonymity: Boolean
    ): Completable

    fun deleteFeedback(
        creationId: String,
        feedbackId: String
    ): Completable
}
