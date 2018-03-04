package kr.mashup.feedget.data.repository

import io.reactivex.Completable
import io.reactivex.Single
import kr.mashup.feedget.entity.Creation

interface CreationRemote {

    fun getCreations(page: String, cursor: String?, categories: String): Single<Pair<Long, List<Creation>>>

    fun getCreation(creationId: String): Single<Creation>

    fun postCreations(title: String, description: String, category: String, anonymity: Boolean, rewardPoint: Double): Single<Creation>

    fun patchCreation(creationId: String, title: String?, description: String?, category: String?, anonymity: Boolean?, rewardPoint: Double?): Single<Creation>

    fun deleteCreation(creationId: String): Completable
}
