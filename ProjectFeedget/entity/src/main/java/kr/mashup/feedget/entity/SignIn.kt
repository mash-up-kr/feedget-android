package kr.mashup.feedget.entity

data class SignIn(
    val accessToken: String,
    val refreshToken: String
) : Entity
