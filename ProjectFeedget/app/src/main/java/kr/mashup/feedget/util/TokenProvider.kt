package kr.mashup.feedget.util

import android.annotation.SuppressLint
import android.content.Context
import io.reactivex.Completable
import io.reactivex.Single
import kr.mashup.feedget.domain.repository.TokenRepository
import javax.inject.Inject

class TokenProvider @Inject constructor(private val context: Context) : TokenRepository {
    override var token: String?
        get() = context.getSharedPreferences("common", Context.MODE_PRIVATE).getString("token", "")
        @SuppressLint("ApplySharedPref")
        set(value) {
            context.getSharedPreferences("common", Context.MODE_PRIVATE)
                .edit()
                .putString("token", value)
                .commit()
        }
    override fun getToken(): Single<String> =
        Single.fromCallable {
            token
        }

    @SuppressLint("ApplySharedPref")
    override fun setToken(token: String): Completable =
        Completable.fromCallable {
            context.getSharedPreferences("common", Context.MODE_PRIVATE)
                .edit()
                .putString("token", token)
                .commit()
        }
}
