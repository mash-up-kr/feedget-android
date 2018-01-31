package kr.mashup.feedget.domain.interactor.usecases

import io.reactivex.Single
import kr.mashup.feedget.domain.executor.PostExecutionThread
import kr.mashup.feedget.domain.executor.ThreadExecutor
import kr.mashup.feedget.domain.interactor.SingleUseCase
import kr.mashup.feedget.domain.repository.CreationRepository
import kr.mashup.feedget.entity.Creation
import javax.inject.Inject

class GetCreations @Inject constructor(
    private val creationRepository: CreationRepository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : SingleUseCase<Pair<Long, List<Creation>>, GetCreations.Params>(
    threadExecutor,
    postExecutionThread
) {

    override fun buildUseCaseObservable(params: Params?): Single<Pair<Long, List<Creation>>> =
        params?.let {
            creationRepository.getCreations(
                it.page,
                it.cursor,
                it.category
            )
        } ?: Single.error(RuntimeException())

    data class Params(
        val page: String,
        val cursor: String?,
        val category: String
    )
}
