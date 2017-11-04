package kr.mashup.projectnoname.data

import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kr.mashup.projectnoname.api.ApiService
import javax.inject.Inject

class FeedbacksRepository(private val apiService: ApiService) {

    fun getFeedback(): Single<String> = apiService.getFeedbacks()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}
