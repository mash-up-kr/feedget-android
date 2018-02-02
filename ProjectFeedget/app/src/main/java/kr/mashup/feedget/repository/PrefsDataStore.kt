package kr.mashup.feedget.repository

import android.content.Context
import kr.mashup.feedget.domain.repository.PrefsRepository
import javax.inject.Inject

class PrefsDataStore @Inject constructor(private val context: Context) : PrefsRepository {

    override fun setToken(token: String) {
        context.getSharedPreferences("common", Context.MODE_PRIVATE).edit()
            .putString("token", token)
            .commit()
    }
}
