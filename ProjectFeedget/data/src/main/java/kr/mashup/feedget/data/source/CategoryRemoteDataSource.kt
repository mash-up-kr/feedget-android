package kr.mashup.feedget.data.source

import io.reactivex.Single
import kr.mashup.feedget.data.repository.CategoryRemote
import kr.mashup.feedget.domain.repository.CategoryRepositoy
import kr.mashup.feedget.entity.Category
import javax.inject.Inject

class CategoryRemoteDataSource @Inject constructor(private val remote: CategoryRemote) : CategoryRepositoy {
    override fun getCategories(): Single<List<Category>> =
        remote.getCategories()
}
