package com.example.pushnotification

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.pushnotification.data.RetrofitInstance
import com.example.pushnotification.data.api.request.NotificationRequest
import com.example.pushnotification.data.api.response.NotificationResponse
import com.example.pushnotification.helpers.PreferencesHelpers
import com.example.pushnotification.model.DataNotification
import com.example.pushnotification.model.Notification
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "MainActivityLig"

class MainActivity : AppCompatActivity() {

    private lateinit var buttonActionPushNotification: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        getTokenFirebase()
        buttonActionPushNotification = findViewById(R.id.button)
        buttonActionPushNotification.setOnClickListener { pushNotificationUsingAPI() }
    }

    private fun pushNotificationUsingAPI() {
        val request = NotificationRequest(context = this)
        RetrofitInstance().notificationService
            .send(request = request).enqueue(callbackNotification())
    }

    private fun callbackNotification(): Callback<NotificationResponse>? {
        return object : Callback<NotificationResponse> {
            override fun onResponse(
                call: Call<NotificationResponse>,
                response: Response<NotificationResponse>
            ) {
                if (response.isSuccessful) {
                    Log.i(TAG, "Success notification...")
                } else {
                    Log.e(TAG, "Failure notification user")
                }
            }

            override fun onFailure(call: Call<NotificationResponse>, t: Throwable) {
                t.printStackTrace()
                Log.e(TAG, "Failure ${t.localizedMessage}")
            }
        }
    }

    private fun getTokenFirebase() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w(TAG, "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }

            val token = task.result
            if (token != null) {
                PreferencesHelpers(this).setToken(token)
                Log.w(TAG, "Token ${PreferencesHelpers(this).getToken()}")
            }

            Log.w(TAG, "$token")
        })
    }
}