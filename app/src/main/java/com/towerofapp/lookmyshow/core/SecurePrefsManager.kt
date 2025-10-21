package com.towerofapp.lookmyshow.core

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

class SecurePrefsManager private constructor(context: Context) {

    private val masterKey = MasterKey.Builder(context)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()

    private val encryptedPrefs = EncryptedSharedPreferences.create(
        context,
        "user_prefs",
        masterKey,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    fun saveEmail(email: String) {
        encryptedPrefs.edit().putString("user_email", email).apply()
    }

    fun getEmail(): String? {
        return encryptedPrefs.getString("user_email", null)
    }

    fun clear() {
        encryptedPrefs.edit().clear().apply()
    }

    companion object {
        @Volatile
        private var instance: SecurePrefsManager? = null

        fun getInstance(context: Context): SecurePrefsManager {
            return instance ?: synchronized(this) {
                instance ?: SecurePrefsManager(context.applicationContext).also { instance = it }
            }
        }
    }
}
