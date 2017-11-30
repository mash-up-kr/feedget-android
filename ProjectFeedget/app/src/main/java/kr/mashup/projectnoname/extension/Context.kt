package kr.mashup.projectnoname.extension

import android.app.Activity
import android.content.Context
import android.content.Intent

inline fun <reified T> Context.startActivity() {
    startActivity(Intent(this, T::class.java))
}

inline fun <reified T> Activity.startActivityWithFinish() {
    startActivity<T>()
    finish()
}
