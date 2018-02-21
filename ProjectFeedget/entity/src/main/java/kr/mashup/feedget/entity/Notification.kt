package kr.mashup.feedget.entity

data class Notification(
        val notificationId: String,
        val description: String,
        val actionType: String,
        val creationId: String,
        val isRead: Boolean //임시값, 서버에서 확정 안됨
) : Entity
