package kr.mashup.feedget.remote

import io.reactivex.Completable
import io.reactivex.Single
import kr.mashup.feedget.data.repository.FeedbackRemote
import kr.mashup.feedget.data.repository.NotificationRemote
import kr.mashup.feedget.entity.Feedback
import kr.mashup.feedget.entity.Notification
import javax.inject.Inject

class NotificationRemoteImpl @Inject constructor(private val api: FeedGetService) : NotificationRemote {
    override fun getNotifications(page: String): Single<Pair<Long, List<Notification>>> =
            api.getNotifications(page).map { it.nextPage to it.list }

}