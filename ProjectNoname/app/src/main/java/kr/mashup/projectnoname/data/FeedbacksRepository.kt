package kr.mashup.projectnoname.data

import io.reactivex.Single
import kr.mashup.projectnoname.api.ApiService
import javax.inject.Inject

class FeedbacksRepository(val apiService: ApiService) {

    fun getFeedback(): Single<String> = apiService.getFeedbacks()
}
