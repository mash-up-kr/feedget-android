package kr.mashup.feedget.domain.interactor.usecases

import io.reactivex.Single
import kr.mashup.feedget.domain.executor.PostExecutionThread
import kr.mashup.feedget.domain.executor.ThreadExecutor
import kr.mashup.feedget.domain.interactor.SingleUseCase
import kr.mashup.feedget.domain.repository.CategoryRepositoy
import kr.mashup.feedget.entity.Category
import javax.inject.Inject

class GetCategories @Inject constructor(
    private val categoriesRepository: CategoryRepositoy,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : SingleUseCase<List<Category>, Nothing>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseObservable(params: Nothing?): Single<List<Category>> =
        categoriesRepository.getCategories()
}
