package kr.mashup.feedget.data.repository

import io.reactivex.Completable
import io.reactivex.Single
import kr.mashup.feedget.entity.Feedback
import kr.mashup.feedget.entity.Notification

interface NotificationRemote {

    fun getNotifications(page: String): Single<Pair<Long, List<Notification>>>

}