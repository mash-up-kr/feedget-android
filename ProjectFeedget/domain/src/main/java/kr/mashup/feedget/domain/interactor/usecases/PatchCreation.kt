package kr.mashup.feedget.domain.interactor.usecases

import io.reactivex.Completable
import io.reactivex.Single
import kr.mashup.feedget.domain.executor.PostExecutionThread
import kr.mashup.feedget.domain.executor.ThreadExecutor
import kr.mashup.feedget.domain.interactor.CompletableUseCase
import kr.mashup.feedget.domain.repository.ContentRepository
import kr.mashup.feedget.domain.repository.CreationRepository
import java.io.File
import javax.inject.Inject

class PatchCreation @Inject constructor(
    private val creationRepository: CreationRepository,
    private val contentRepository: ContentRepository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : CompletableUseCase<PatchCreation.Params>(
    threadExecutor,
    postExecutionThread
) {

    override fun buildUseCaseObservable(params: Params): Completable =
        creationRepository.patchCreation(
            params.creationId,
            params.title,
            params.description,
            params.category,
            params.anonymity,
            params.rewardPoint
        ).flatMapCompletable {
            if (params.newFiles?.isNotEmpty() == true)
                params.type?.let { type ->
                    contentRepository.postContents(params.creationId, type, params.newFiles!!)
                } ?: Completable.error(RuntimeException())
            else
                Completable.complete()
        }.andThen {
            Completable.fromCallable {
                if (params.deletedFiles?.isNotEmpty() == true)
                    contentRepository.deleteContent(params.creationId, params.deletedFiles!!).subscribe(it)
                else
                    it
            }
        }

    data class Params(
        val creationId: String,
        val title: String? = null,
        val description: String? = null,
        val category: String? = null,
        val anonymity: Boolean? = null,
        val rewardPoint: Double? = null,
        val type: String? = null,
        val newFiles: List<File>? = null,
        val deletedFiles: List<String>? = null
    )
}
