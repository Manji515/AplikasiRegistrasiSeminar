package com.example.aplikasiregistrasiseminar

import android.content.Context

class SessionManager(context: Context) {
    private val prefs = context.getSharedPreferences("seminar_prefs", Context.MODE_PRIVATE)

    companion object {
        private const val KEY_NAME = "key_name"
        private const val KEY_EMAIL = "key_email"
        private const val KEY_PASSWORD = "key_password"
        private const val KEY_PHONE = "key_phone"
    }

    fun saveUser(name: String, email: String, password: String, phone: String) {
        prefs.edit()
            .putString(KEY_NAME, name)
            .putString(KEY_EMAIL, email)
            .putString(KEY_PASSWORD, password)
            .putString(KEY_PHONE, phone)
            .apply()
    }

    fun hasRegisteredUser(): Boolean {
        return !prefs.getString(KEY_NAME, "").isNullOrBlank()
    }

    fun getName(): String = prefs.getString(KEY_NAME, "") ?: ""
    fun getEmail(): String = prefs.getString(KEY_EMAIL, "") ?: ""
    fun getPassword(): String = prefs.getString(KEY_PASSWORD, "") ?: ""
    fun getPhone(): String = prefs.getString(KEY_PHONE, "") ?: ""
}