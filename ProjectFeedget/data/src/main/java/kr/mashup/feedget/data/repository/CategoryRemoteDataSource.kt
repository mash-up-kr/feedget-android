package kr.mashup.feedget.data.repository

import io.reactivex.Single
import kr.mashup.feedget.domain.repository.CategoryRepositoy
import kr.mashup.feedget.entity.Category
import kr.mashup.feedget.remote.FeedGetService
import javax.inject.Inject

class CategoryRemoteDataSource(private val api: FeedGetService) : CategoryRepositoy {
    override fun getCategories(): Single<List<Category>> =
        api.getCategories()
            .map { it.categories }
}
