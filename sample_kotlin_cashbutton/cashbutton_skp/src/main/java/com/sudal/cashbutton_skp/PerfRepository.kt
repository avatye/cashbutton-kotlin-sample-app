package com.sudal.cashbutton_skp

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

object PerfRepository {

    private const val preferenceName = "cash-button:app-channeling:"

    private var pref: SharedPreferences? = null

    fun init(context: Context) {
        if (pref == null) {
            pref = context.getSharedPreferences(preferenceName, Context.MODE_PRIVATE)
        }
    }

    private const val USERID = "userid"
    var userid: String
        get() {
            return pref?.getString(USERID, "") ?: ""
        }
        set(value) {
            pref?.edit { putString(USERID, value) }
        }
}