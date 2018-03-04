package kr.mashup.feedget.remote

import io.reactivex.Completable
import kr.mashup.feedget.data.repository.ContentRemote
import java.io.File
import javax.inject.Inject

class ContentRemoteImpl @Inject constructor(private val api: FeedGetService) : ContentRemote {

    override fun deleteContent(creationId: String, contentIds: List<String>): Completable =
        api.deleteContent(creationId, FeedGetService.RequestDeleteContent(contentIds))

    override fun postContent(creationId: String, type: String, files: List<File>): Completable =
        api.postContent(creationId, type, FeedGetService.MultiPartBodyPart(files).multiparts())
}
