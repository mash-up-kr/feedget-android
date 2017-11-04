package kr.mashup.projectnoname.domain

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kr.mashup.projectnoname.data.ContentsRepository
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class PostContentsUseCase(private val contentsRepository: ContentsRepository) {

    fun execute(id: Long, files: Array<File>) =
        contentsRepository.postContents(
             files.map {
                MultipartBody.Part.createFormData("files", it.name, RequestBody.create(MediaType.parse("image/*"), it))
            }
        ).observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())

}
