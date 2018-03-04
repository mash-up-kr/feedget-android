package kr.mashup.feedget.remote

import io.reactivex.Completable
import io.reactivex.Single
import kr.mashup.feedget.data.repository.UserRemote
import kr.mashup.feedget.entity.SignIn
import javax.inject.Inject

class UserRemoteImpl @Inject constructor(private val api : FeedGetService): UserRemote {

    override fun register(realName: String, nickName: String, email: String, oAuthToken: String, oAuthType: String): Single<SignIn> =
        api.register(FeedGetService.RequestRegister(realName,nickName,email,oAuthToken,oAuthType)).map { it.item }

    override fun registerFCM(cloudMsgRegToken: String): Completable =
        api.registerFCM(FeedGetService.RequestRegisterFCM(cloudMsgRegToken))
}
