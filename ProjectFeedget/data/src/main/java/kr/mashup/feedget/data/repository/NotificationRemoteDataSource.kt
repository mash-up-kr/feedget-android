package kr.mashup.feedget.data.repository

import io.reactivex.Completable
import io.reactivex.Single
import kr.mashup.feedget.domain.repository.CreationRepository
import kr.mashup.feedget.domain.repository.NotificationRepository
import kr.mashup.feedget.entity.Creation
import kr.mashup.feedget.entity.Notification
import kr.mashup.feedget.remote.FeedGetService

class NotificationRemoteDataSource(private val api: FeedGetService) : NotificationRepository {
    override fun getNotifications(page: String): Single<Pair<Long, List<Notification>>> =
            api.getNotifications(page)
                    .map { it.nextPage to it.list }

}