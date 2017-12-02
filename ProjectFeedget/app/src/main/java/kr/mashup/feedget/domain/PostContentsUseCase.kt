package kr.mashup.feedget.domain

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kr.mashup.feedget.data.ContentsRepository
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class PostContentsUseCase(private val contentsRepository: ContentsRepository) {

    fun execute(id: Long, files: Array<File>) =
        contentsRepository.postContents(id,
            files = files.map {
                MultipartBody.Part.createFormData("files", it.name, RequestBody.create(MediaType.parse("image/*"), it))
            }
        ).observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())

}
