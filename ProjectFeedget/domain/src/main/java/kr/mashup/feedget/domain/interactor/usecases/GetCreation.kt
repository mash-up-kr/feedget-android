package kr.mashup.feedget.domain.interactor.usecases

import io.reactivex.Single
import kr.mashup.feedget.domain.executor.PostExecutionThread
import kr.mashup.feedget.domain.executor.ThreadExecutor
import kr.mashup.feedget.domain.interactor.SingleUseCase
import kr.mashup.feedget.domain.repository.CreationRepository
import kr.mashup.feedget.entity.Creation
import javax.inject.Inject

class GetCreation @Inject constructor(
    private val creationRepository: CreationRepository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : SingleUseCase<Creation, GetCreation.Params>(
    threadExecutor,
    postExecutionThread
) {

    override fun buildUseCaseObservable(params: Params?): Single<Creation> =
        params?.let {
            creationRepository.getCreation(it.creationId)
        } ?: Single.error(RuntimeException())

    data class Params(
        val creationId: String
    )
}
