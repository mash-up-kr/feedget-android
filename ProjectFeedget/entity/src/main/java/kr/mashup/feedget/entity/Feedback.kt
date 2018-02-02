package kr.mashup.feedget.entity

data class Feedback(
    val feedbackId: Long,
    val description: String,
    val user: User,
    val selection: Boolean,
    val anonymity: Boolean,
    val contents: List<Content>
) : Entity
