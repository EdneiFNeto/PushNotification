package com.example.pushnotification.data.api.response

import java.math.BigInteger

class NotificationResponse(
    val multicast_id: BigInteger,
    val success: Int,
    val failure: Int,
    val canonical_ids: Int,
    val results: List<ResultFirebase>,
)

class ResultFirebase( val message_id: String)
