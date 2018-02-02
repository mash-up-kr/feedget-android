package kr.mashup.feedget.data.repository

import io.reactivex.Completable
import io.reactivex.Single
import kr.mashup.feedget.domain.repository.UserRepository
import kr.mashup.feedget.entity.SignIn
import kr.mashup.feedget.remote.FeedGetService
import javax.inject.Inject

class UserRemoteDataSource @Inject constructor(private val api: FeedGetService) : UserRepository {

    override fun register(realName: String, nickName: String, email: String, oAuthToken: String, oAuthType: String): Single<SignIn> =
        api.register(FeedGetService.RequestRegister(
            realName,
            nickName,
            email,
            oAuthToken,
            oAuthType
        )).map { it.item }

    override fun registerFCM(cloudMsgRegToken: String): Completable =
        api.registerFCM(FeedGetService.RequestRegisterFCM(cloudMsgRegToken))
}
