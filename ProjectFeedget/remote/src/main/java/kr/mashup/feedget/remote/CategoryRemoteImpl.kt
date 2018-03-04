package kr.mashup.feedget.remote

import io.reactivex.Single
import kr.mashup.feedget.data.repository.CategoryRemote
import kr.mashup.feedget.entity.Category
import javax.inject.Inject

class CategoryRemoteImpl @Inject constructor(private val api: FeedGetService) : CategoryRemote {
    override fun getCategories(): Single<List<Category>> = api.getCategories().map { it.categories }
}
