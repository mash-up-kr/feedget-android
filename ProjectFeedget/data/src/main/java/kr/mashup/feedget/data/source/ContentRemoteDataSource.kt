package kr.mashup.feedget.data.source

import io.reactivex.Completable
import kr.mashup.feedget.data.repository.ContentRemote
import kr.mashup.feedget.domain.repository.ContentRepository
import java.io.File
import javax.inject.Inject

class ContentRemoteDataSource @Inject constructor(private val remote: ContentRemote) : ContentRepository {

    override fun deleteContent(creationId: String, contentIds: List<String>): Completable =
        remote.deleteContent(creationId, contentIds)

    override fun postContents(creationId: String, type: String, files: List<File>): Completable =
        remote.postContent(creationId, type, files)
}
