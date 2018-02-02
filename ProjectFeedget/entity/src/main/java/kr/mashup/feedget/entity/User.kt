package kr.mashup.feedget.entity

data class User(
    val userId: String,
    val nickName: String,
    val grade: Grade
) : Entity
