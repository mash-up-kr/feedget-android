package kr.mashup.feedget.remote

import io.reactivex.Completable
import io.reactivex.Single
import kr.mashup.feedget.data.repository.CreationRemote
import kr.mashup.feedget.entity.Creation
import javax.inject.Inject

class CreationRemoteImpl @Inject constructor(private val api: FeedGetService) : CreationRemote {

    override fun getCreations(page: String, cursor: String?, categories: String): Single<Pair<Long, List<Creation>>> =
        api.getCreations(page, cursor, categories)
            .map { it.nextPage to it.list }

    override fun getCreation(creationId: String): Single<Creation> =
        api.getCreation(creationId).map { it.item }

    override fun postCreations(title: String, description: String, category: String, anonymity: Boolean, rewardPoint: Double): Single<Creation> =
        api.postCreations(FeedGetService.RequestPostCreations(title, description, category, anonymity, rewardPoint)).map { it.item }

    override fun patchCreation(creationId: String, title: String?, description: String?, category: String?, anonymity: Boolean?, rewardPoint: Double?): Single<Creation> =
        api.updateCreation(creationId, FeedGetService.RequestUpdateCreation(title, description, category, anonymity, rewardPoint)).map { it.item }

    override fun deleteCreation(creationId: String): Completable =
        api.deleteCreation(creationId)
}
