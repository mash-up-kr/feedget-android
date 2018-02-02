package kr.mashup.feedget.domain.interactor.usecases

import io.reactivex.Single
import kr.mashup.feedget.domain.executor.PostExecutionThread
import kr.mashup.feedget.domain.executor.ThreadExecutor
import kr.mashup.feedget.domain.interactor.SingleUseCase
import kr.mashup.feedget.domain.repository.PrefsRepository
import kr.mashup.feedget.domain.repository.UserRepository
import kr.mashup.feedget.entity.SignIn
import javax.inject.Inject

class Register @Inject constructor(
    private val userRepository: UserRepository,
    private val pefsRepository: PrefsRepository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : SingleUseCase<SignIn, Register.Params>(
    threadExecutor,
    postExecutionThread
) {

    override fun buildUseCaseObservable(params: Register.Params?): Single<SignIn> =
        params?.let {
            userRepository.register(
                it.realName,
                it.nickName,
                it.email,
                it.oAuthToken,
                it.oAuthType
            ).doOnSuccess {
                pefsRepository.setToken(it.accessToken)
            }
        } ?: Single.error(RuntimeException())

    data class Params(
        val realName: String,
        val nickName: String,
        val email: String,
        val oAuthToken: String,
        val oAuthType: String = "FB"
    )
}
