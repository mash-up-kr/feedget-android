package kr.mashup.feedget.entity

data class Creation(
    val id: String,
    val title: String,
    val description: String,
    val dueDate : Long,
    val writeDate : Long?,
    val rewardPoint: Double,
    val status: CreationStatus,
    val anonymity : Boolean,
    val feedbackCount: Long,
    val wroteFeedback: Boolean,
    val writer : User,
    val contents: List<Content>
) : Entity
