package kr.mashup.feedget.data

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kr.mashup.feedget.api.ApiService

class FeedbacksRepository(private val apiService: ApiService) {

    fun getFeedback(): Single<String> = apiService.getFeedbacks()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}
