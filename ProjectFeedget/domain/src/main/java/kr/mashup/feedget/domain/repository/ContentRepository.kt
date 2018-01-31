package kr.mashup.feedget.domain.repository

import io.reactivex.Completable
import io.reactivex.Single
import java.io.File

interface ContentRepository {

    fun postContents(
        creationId: String,
        type: String = "IMAGE",
        files: List<File>
    ): Completable

    fun deleteContent(
        creationId: String,
        contentIds: List<String>
    ): Completable
}
