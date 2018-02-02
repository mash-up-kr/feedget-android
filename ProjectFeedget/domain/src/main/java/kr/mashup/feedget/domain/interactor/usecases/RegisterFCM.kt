package kr.mashup.feedget.domain.interactor.usecases

import io.reactivex.Completable
import io.reactivex.Single
import kr.mashup.feedget.domain.executor.PostExecutionThread
import kr.mashup.feedget.domain.executor.ThreadExecutor
import kr.mashup.feedget.domain.interactor.CompletableUseCase
import kr.mashup.feedget.domain.interactor.SingleUseCase
import kr.mashup.feedget.domain.repository.UserRepository
import javax.inject.Inject

class RegisterFCM @Inject constructor(
    private val userRepository: UserRepository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : CompletableUseCase<RegisterFCM.Params>(
    threadExecutor,
    postExecutionThread
) {

    override fun buildUseCaseObservable(params: RegisterFCM.Params): Completable =
        userRepository.registerFCM(
            params.cloudMsgRegToken
        )

    data class Params(
        val cloudMsgRegToken: String
    )
}
