package com.example.pushnotification.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.pushnotification.R
import com.example.pushnotification.helpers.CHANNEL_ID
import com.example.pushnotification.helpers.PreferencesHelpers
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

private const val TAG = "MyFirebaseMessService"

class MyFirebaseMessagingService: FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        Log.i(TAG, "remoteMessage: ${remoteMessage.data}")
        Log.i(TAG, "From: ${remoteMessage.from}")

        if (remoteMessage.data.isNotEmpty()) {
            Log.i(TAG, "Message data payload: ${remoteMessage.data}")
        }

        remoteMessage.notification?.let {
            Log.i(TAG, "Message Notification Body: ${it.body}")
            showNotification(it.body)
        }
    }

    override fun onNewToken(token: String) {
        Log.w(TAG, "Refreshed token: $token")
        sendRegistrationToServer(token)
    }

    override fun onSendError(error: String, exception: Exception) {
        super.onSendError(error, exception)
        exception.printStackTrace()
        Log.e(TAG, "Error $error")
    }

    private fun sendRegistrationToServer(token: String) {
        PreferencesHelpers(applicationContext).setToken(token)
    }

    private fun showNotification(message: String?) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel =
                NotificationChannel(CHANNEL_ID, CHANNEL_ID, NotificationManager.IMPORTANCE_DEFAULT)
            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(notificationChannel)
        }

        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle("Notification Test")
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(1, builder.build())
    }
}