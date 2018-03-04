package kr.mashup.feedget.data.repository

import io.reactivex.Single
import kr.mashup.feedget.entity.Category

interface CategoryRemote {
    fun getCategories(): Single<List<Category>>
}
