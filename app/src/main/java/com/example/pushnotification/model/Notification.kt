package com.example.pushnotification.model

class Notification(
    var title: String? = null,
    var body: String? = null,
    var mutable_content: Boolean? = false,
    var sound: String? = null,
) {
    init {
        title = "Push Notification"
        body = "Send notification using API"
        mutable_content = true
        sound = "Tri-tone"
    }
}
