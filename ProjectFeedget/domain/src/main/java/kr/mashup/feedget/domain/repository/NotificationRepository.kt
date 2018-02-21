package kr.mashup.feedget.domain.repository

import io.reactivex.Completable
import io.reactivex.Single
import kr.mashup.feedget.entity.Creation
import kr.mashup.feedget.entity.Notification

interface NotificationRepository {
    fun getNotifications(
        page: String
    ): Single<Pair<Long, List<Notification>>>
}