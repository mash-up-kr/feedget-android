package kr.mashup.feedget.data.repository

import io.reactivex.Completable
import kr.mashup.feedget.domain.repository.ContentRepository
import kr.mashup.feedget.remote.FeedGetService
import java.io.File
import javax.inject.Inject

class ContentRemoteDataSource (private val api: FeedGetService) : ContentRepository {

    override fun deleteContent(creationId: String, contentIds: List<String>): Completable =
        api.deleteContent(creationId, FeedGetService.RequestDeleteContent(contentIds))

    override fun postContents(creationId: String, type: String, files: List<File>): Completable =
        api.postContent(creationId, type, FeedGetService.MultiPartBodyPart(files).multiparts())
}
