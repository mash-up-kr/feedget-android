package kr.mashup.feedget.domain.interactor.usecases

import io.reactivex.Completable
import kr.mashup.feedget.domain.executor.PostExecutionThread
import kr.mashup.feedget.domain.executor.ThreadExecutor
import kr.mashup.feedget.domain.interactor.CompletableUseCase
import kr.mashup.feedget.domain.repository.FeedbackRepository
import javax.inject.Inject

class DeleteFeedback @Inject constructor(
    private val feedbackRepository: FeedbackRepository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : CompletableUseCase<DeleteFeedback.Params>(
    threadExecutor,
    postExecutionThread
) {

    override fun buildUseCaseObservable(params: Params): Completable =
        feedbackRepository.deleteFeedback(
            params.creationId,
            params.feedbackId
        )

    data class Params(
        val creationId: String,
        val feedbackId: String
    )
}
