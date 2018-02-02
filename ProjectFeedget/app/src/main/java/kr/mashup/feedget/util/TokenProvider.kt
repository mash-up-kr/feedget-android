package kr.mashup.feedget.util

import android.annotation.SuppressLint
import android.content.Context
import kr.mashup.feedget.remote.TokenProvider
import javax.inject.Inject

class TokenProvider @Inject constructor(private val context: Context) : TokenProvider {
    override var token: String
        get() = context.getSharedPreferences("common", Context.MODE_PRIVATE).getString("token", "")
        @SuppressLint("ApplySharedPref")
        set(value) {
            context.getSharedPreferences("common", Context.MODE_PRIVATE)
                .edit()
                .putString("token", value)
                .commit()
        }
}
