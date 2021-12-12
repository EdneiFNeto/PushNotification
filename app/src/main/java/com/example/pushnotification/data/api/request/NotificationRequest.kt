package com.example.pushnotification.data.api.request

import android.content.Context
import com.example.pushnotification.R
import com.example.pushnotification.helpers.PreferencesHelpers
import com.example.pushnotification.model.DataNotification
import com.example.pushnotification.model.Notification

class NotificationRequest(
    context: Context,
    var to: String? = null,
    var notification: Notification? = null,
    var data: DataNotification? = null
) {
    init {
        to = PreferencesHelpers(context).getToken().toString()
        notification = Notification()
        data = DataNotification()
    }
}
