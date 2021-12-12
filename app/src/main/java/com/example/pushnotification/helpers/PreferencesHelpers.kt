package com.example.pushnotification.helpers

import android.content.Context
import android.content.SharedPreferences
import com.example.pushnotification.R
import androidx.core.content.edit

class PreferencesHelpers(context: Context) {

    private var sharedPreferences: SharedPreferences? = null

    init {
        sharedPreferences = context.getSharedPreferences(
            context.resources.getString(R.string.app_name),
            Context.MODE_PRIVATE
        )
    }

    companion object {
        const val TOKEN_FIREBASE = "TOKEN_FIREBASE"
    }

    fun setToken(token: String?) {
        sharedPreferences?.edit { putString(TOKEN_FIREBASE, token)?.commit() }
    }

    fun getToken() = sharedPreferences?.getString(TOKEN_FIREBASE, null)

}