package kr.mashup.feedget.domain.repository

import io.reactivex.Completable
import io.reactivex.Single
import kr.mashup.feedget.entity.Creation

interface CreationRepository {
    fun getCreations(
        page: String,
        cursor: String?,
        category: String
    ): Single<Pair<Long, List<Creation>>>

    fun getCreation(
        creationId: String
    ): Single<Creation>

    fun postCreation(
        title: String,
        description: String,
        category: String,
        anonymity: Boolean,
        rewardPoint: Double
    ): Single<Creation>

    fun patchCreation(
        creationId: String,
        title: String? = null,
        description: String? = null,
        category: String? = null,
        anonymity: Boolean? = null,
        rewardPoint: Double? = null
    ): Single<Creation>

    fun deleteCreation(
        creationId: String
    ): Completable
}
