package kr.mashup.feedget.data.repository

import io.reactivex.Completable
import io.reactivex.Single
import kr.mashup.feedget.entity.SignIn

interface UserRemote {

    fun register(realName: String, nickName: String, email: String, oAuthToken: String, oAuthType: String): Single<SignIn>

    fun registerFCM(cloudMsgRegToken: String): Completable
}
