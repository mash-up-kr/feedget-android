package kr.mashup.feedget.domain.interactor.usecases

import io.reactivex.Single
import kr.mashup.feedget.domain.executor.PostExecutionThread
import kr.mashup.feedget.domain.executor.ThreadExecutor
import kr.mashup.feedget.domain.interactor.SingleUseCase
import kr.mashup.feedget.domain.repository.TokenRepository
import javax.inject.Inject

class IsLogined @Inject constructor(
    private val repository: TokenRepository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : SingleUseCase<Boolean, IsLogined.Params>(
    threadExecutor,
    postExecutionThread
) {
    override fun buildUseCaseObservable(params: Params?): Single<Boolean> =
        repository.getToken()
            .map {
                it.isNotEmpty()
            }
            .onErrorResumeNext {
                Single.just(false)
            }

    class Params
}
