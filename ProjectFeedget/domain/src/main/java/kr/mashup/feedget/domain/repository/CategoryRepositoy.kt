package kr.mashup.feedget.domain.repository

import io.reactivex.Single
import kr.mashup.feedget.entity.Category

interface CategoryRepositoy {
    fun getCategories(): Single<List<Category>>
}
