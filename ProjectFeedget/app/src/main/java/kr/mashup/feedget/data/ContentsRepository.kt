package kr.mashup.feedget.data

import io.reactivex.Completable
import kr.mashup.feedget.api.ApiService
import okhttp3.MultipartBody

class ContentsRepository(private val apiService: ApiService) {

    fun postContents(id:Long,type: String = "IMAGE",files: List<MultipartBody.Part>): Completable =
        apiService.postContents(id,type,files = files)
}
