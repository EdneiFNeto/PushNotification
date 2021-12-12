package com.example.pushnotification.data.api.service

import com.example.pushnotification.BuildConfig
import com.example.pushnotification.data.api.request.NotificationRequest
import com.example.pushnotification.data.api.response.NotificationResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface NotificationService {
    @POST("send")

    fun send(
        @Header("Content-Type") type: String = "application/json",
        @Header("project_id") project_id: String = BuildConfig.PROJECT_ID,
        @Header("Authorization") auto1: String = "key=${BuildConfig.KEY_API}",
        @Body request: NotificationRequest?
    ): Call<NotificationResponse>
}
