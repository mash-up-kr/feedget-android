package kr.mashup.feedget.data.source

import io.reactivex.Completable
import io.reactivex.Single
import kr.mashup.feedget.data.repository.CreationRemote
import kr.mashup.feedget.domain.repository.CreationRepository
import kr.mashup.feedget.entity.Creation
import javax.inject.Inject

class CreationRemoteDataSource @Inject constructor(private val remote: CreationRemote) : CreationRepository {

    override fun getCreations(page: String, cursor: String?, categories: String): Single<Pair<Long, List<Creation>>> =
        remote.getCreations(page, cursor, categories)

    override fun getCreation(creationId: String): Single<Creation> =
        remote.getCreation(creationId)

    override fun postCreation(title: String, description: String, category: String, anonymity: Boolean, rewardPoint: Double): Single<Creation> =
        remote.postCreations(title, description, category, anonymity, rewardPoint)

    override fun patchCreation(creationId: String, title: String?, description: String?, category: String?, anonymity: Boolean?, rewardPoint: Double?): Single<Creation> =
        remote.patchCreation(creationId, title, description, category, anonymity, rewardPoint)

    override fun deleteCreation(creationId: String): Completable =
        remote.deleteCreation(creationId)

}
