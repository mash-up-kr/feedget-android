package kr.mashup.feedget.data.source

import io.reactivex.Completable
import io.reactivex.Single
import kr.mashup.feedget.data.repository.NotificationRemote
import kr.mashup.feedget.domain.repository.NotificationRepository
import kr.mashup.feedget.entity.Notification

class NotificationRemoteDataSource(private val remote: NotificationRemote) : NotificationRepository {
    override fun getNotifications(page: String): Single<Pair<Long, List<Notification>>> =
            remote.getNotifications(page)

}