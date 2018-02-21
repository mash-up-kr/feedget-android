package kr.mashup.feedget.domain.interactor.usecases

import io.reactivex.Single
import kr.mashup.feedget.domain.executor.PostExecutionThread
import kr.mashup.feedget.domain.executor.ThreadExecutor
import kr.mashup.feedget.domain.interactor.SingleUseCase
import kr.mashup.feedget.domain.repository.CreationRepository
import kr.mashup.feedget.domain.repository.NotificationRepository
import kr.mashup.feedget.entity.Creation
import kr.mashup.feedget.entity.Notification
import javax.inject.Inject

class GetNotifications @Inject constructor(
        private val notificationRepository: NotificationRepository,
        threadExecutor: ThreadExecutor,
        postExecutionThread: PostExecutionThread
) : SingleUseCase<Pair<Long, List<Notification>>, GetNotifications.Params>(
    threadExecutor,
    postExecutionThread
) {

    override fun buildUseCaseObservable(params: Params?): Single<Pair<Long, List<Notification>>> =
        params?.let {
            notificationRepository.getNotifications(
                it.page
            )
        } ?: Single.error(RuntimeException())

    data class Params(
        val page: String
    )
}