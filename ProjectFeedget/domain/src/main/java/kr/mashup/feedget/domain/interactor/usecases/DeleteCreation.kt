package kr.mashup.feedget.domain.interactor.usecases

import io.reactivex.Completable
import kr.mashup.feedget.domain.executor.PostExecutionThread
import kr.mashup.feedget.domain.executor.ThreadExecutor
import kr.mashup.feedget.domain.interactor.CompletableUseCase
import kr.mashup.feedget.domain.repository.ContentRepository
import kr.mashup.feedget.domain.repository.CreationRepository
import javax.inject.Inject

class DeleteCreation @Inject constructor(
    private val creationRepository: CreationRepository,
    private val contentRepository: ContentRepository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : CompletableUseCase<DeleteCreation.Params>(
    threadExecutor,
    postExecutionThread
) {

    override fun buildUseCaseObservable(params: Params): Completable =
        if (params.contentIds.isNotEmpty())
            contentRepository.deleteContent(params.creationId, params.contentIds).andThen(
                creationRepository.deleteCreation(params.creationId)
            )
        else
            creationRepository.deleteCreation(params.creationId)

    class Params(
        val creationId: String,
        val contentIds: List<String>
    )
}
