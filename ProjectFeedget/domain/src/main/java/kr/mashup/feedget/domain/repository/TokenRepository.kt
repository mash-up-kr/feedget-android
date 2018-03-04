package kr.mashup.feedget.domain.repository

import io.reactivex.Completable
import io.reactivex.Single

interface TokenRepository {
    var token: String?
    fun getToken(): Single<String>
    fun setToken(token: String): Completable
}
