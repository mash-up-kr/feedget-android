package kr.mashup.feedget.domain.interactor.usecases

import io.reactivex.Completable
import kr.mashup.feedget.domain.executor.PostExecutionThread
import kr.mashup.feedget.domain.executor.ThreadExecutor
import kr.mashup.feedget.domain.interactor.CompletableUseCase
import kr.mashup.feedget.domain.repository.FeedbackRepository
import javax.inject.Inject

class PostFeedback @Inject constructor(
    private val feedbackRepository: FeedbackRepository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : CompletableUseCase<PostFeedback.Params>(
    threadExecutor,
    postExecutionThread
) {

    override fun buildUseCaseObservable(params: Params): Completable =
        feedbackRepository.postFeedback(
            params.creationId,
            params.description,
            params.anonymity
        )

    data class Params(
        val creationId: String,
        val description: String,
        val anonymity: Boolean
    )
}
