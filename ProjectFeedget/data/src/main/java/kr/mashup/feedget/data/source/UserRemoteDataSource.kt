package kr.mashup.feedget.data.source

import io.reactivex.Completable
import io.reactivex.Single
import kr.mashup.feedget.data.repository.UserRemote
import kr.mashup.feedget.domain.repository.UserRepository
import kr.mashup.feedget.entity.SignIn
import javax.inject.Inject

class UserRemoteDataSource @Inject constructor(private val remote: UserRemote) : UserRepository {

    override fun register(realName: String, nickName: String, email: String, oAuthToken: String, oAuthType: String): Single<SignIn> =
        remote.register(
            realName,
            nickName,
            email,
            oAuthToken,
            oAuthType
        )

    override fun registerFCM(cloudMsgRegToken: String): Completable =
        remote.registerFCM(cloudMsgRegToken)
}
