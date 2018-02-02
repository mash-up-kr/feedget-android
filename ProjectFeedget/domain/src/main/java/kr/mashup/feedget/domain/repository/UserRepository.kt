package kr.mashup.feedget.domain.repository

import io.reactivex.Completable
import io.reactivex.Single
import kr.mashup.feedget.entity.SignIn

interface UserRepository {

    fun register(
        realName: String,
        nickName: String,
        email: String,
        oAuthToken: String,
        oAuthType: String = "FB"
    ): Single<SignIn>

    fun registerFCM(
        cloudMsgRegToken: String
    ): Completable
}
