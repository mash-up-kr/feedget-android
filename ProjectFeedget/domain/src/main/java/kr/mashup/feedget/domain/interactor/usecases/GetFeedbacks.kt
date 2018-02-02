package kr.mashup.feedget.domain.interactor.usecases

import io.reactivex.Single
import kr.mashup.feedget.domain.executor.PostExecutionThread
import kr.mashup.feedget.domain.executor.ThreadExecutor
import kr.mashup.feedget.domain.interactor.SingleUseCase
import kr.mashup.feedget.domain.repository.FeedbackRepository
import kr.mashup.feedget.entity.Feedback
import javax.inject.Inject

class GetFeedbacks @Inject constructor(
    private val feedbackRepository: FeedbackRepository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : SingleUseCase<List<Feedback>, GetFeedbacks.Params>(
    threadExecutor,
    postExecutionThread
) {

    override fun buildUseCaseObservable(params: Params?): Single<List<Feedback>> =
        params?.let {
            feedbackRepository.getFeedback(it.creationId)
        } ?: Single.error(RuntimeException())

    data class Params(
        val creationId: String
    )
}
