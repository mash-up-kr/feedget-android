package kr.mashup.feedget.domain.interactor.usecases

import io.reactivex.Completable
import kr.mashup.feedget.domain.executor.PostExecutionThread
import kr.mashup.feedget.domain.executor.ThreadExecutor
import kr.mashup.feedget.domain.interactor.CompletableUseCase
import kr.mashup.feedget.domain.repository.ContentRepository
import kr.mashup.feedget.domain.repository.CreationRepository
import java.io.File
import javax.inject.Inject

class PostCreation @Inject constructor(
    private val creationRepository: CreationRepository,
    private val contentRepository: ContentRepository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : CompletableUseCase<PostCreation.Params>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseObservable(params: Params): Completable =
        creationRepository.postCreation(
            params.title,
            params.description,
            params.category,
            params.anonymity,
            params.rewardPoint
        ).flatMapCompletable {
            if (params.files.isNotEmpty())
                contentRepository.postContents(
                    it.id,
                    params.type,
                    params.files
                )
            else
                Completable.complete()
        }

    data class Params(
        val title: String,
        val description: String,
        val category: String,
        val anonymity: Boolean,
        val rewardPoint: Double,
        val type: String,
        val files: List<File>
    )
}
