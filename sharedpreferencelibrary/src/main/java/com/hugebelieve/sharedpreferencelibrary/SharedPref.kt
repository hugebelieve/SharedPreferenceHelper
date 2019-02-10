package com.hugebelieve.sharedpreferencelibrary

import android.content.Context
import android.content.SharedPreferences
import org.json.JSONObject

class SharedPref(var currentContext: Context) {

    var SharedStringCode = currentContext.applicationContext.packageName + "_shared_private_preference";
    val sharedPref: SharedPreferences = currentContext.applicationContext.getSharedPreferences(SharedStringCode, Context.MODE_PRIVATE)
    private var mEditor: SharedPreferences.Editor? = sharedPref.edit()

    fun putData(key: String, value: Boolean) {
        mEditor!!.putBoolean(key, value)
        mEditor!!.apply()
    }

    fun putData(key: String, value: String) {
        mEditor!!.putString(key, value)
        mEditor!!.apply()
    }

    fun putData(key: String, value: Int) {
        mEditor!!.putInt(key, value)
        mEditor!!.apply()
    }

    fun putData(key: String, value: Long) {
        mEditor!!.putLong(key, value)
        mEditor!!.apply()
    }

    fun putData(key: String, value: Float) {
        mEditor!!.putFloat(key, value)
        mEditor!!.apply()
    }

    fun getDataBool(key: String): Boolean {
        return sharedPref.getBoolean(key, false)
    }

    fun getDataString(key: String): String {
        return sharedPref.getString(key, "")!!
    }

    fun getDataString(key: String, defaultS: String): String? {
        return sharedPref.getString(key, defaultS)
    }

    fun getDataInt(key: String): Int {
        return sharedPref.getInt(key, 0)
    }

    fun getDataInt(key: String, defaultInt: Int): Int {
        return sharedPref.getInt(key, defaultInt)
    }

    fun getDataLong(key: String): Long? {
        return sharedPref.getLong(key, 0)
    }

    fun getDataFloat(key: String): Float {
        return sharedPref.getFloat(key, 0f)
    }

    fun editorApply() {
        mEditor!!.apply()
    }

    fun putJsonWithOnlyStrings(json: JSONObject) {
        try {
            val keys = json.keys()
            while (keys.hasNext()) {
                val key = keys.next()
                val value = json.get(key) as String
                mEditor!!.putString(key, value)
                mEditor!!.apply()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
