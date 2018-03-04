package kr.mashup.feedget.data.repository

import io.reactivex.Completable
import java.io.File

interface ContentRemote {

    fun deleteContent(creationId: String, contentIds: List<String>): Completable

    fun postContent(creationId: String, type: String, files: List<File>): Completable
}
