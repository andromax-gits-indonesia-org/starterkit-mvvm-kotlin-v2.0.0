package id.co.gits.gitsutils.extensions

import android.content.Context
import id.co.gits.gitsutils.EnutriHelper.Const.BOGUS_JWT
import id.co.gits.gitsutils.EnutriHelper.Const.PREF_AUTH_TOKEN
import id.co.gits.gitsutils.EnutriHelper.Const.PREF_EMAIL
import id.co.gits.gitsutils.EnutriHelper.Const.PREF_LOGGED_IN
import id.co.gits.gitsutils.EnutriHelper.Const.PREF_PASSWORD
import id.co.gits.gitsutils.EnutriHelper.Const.PREF_REFRESH_TOKEN
import id.co.gits.gitsutils.EnutriHelper.Const.PREF_USER_CODE
import id.co.gits.gitsutils.SharedPreferencesFactory
import id.co.gits.gitsutils.SharedPreferencesFactory.get
import id.co.gits.gitsutils.SharedPreferencesFactory.set

/**
 * Created by mochadwi on 12/26/2018.
 */

/**
 * {@link SharedPreferencesFactory#initPreferences()} // doesn't work
 * @see SharedPreferencesFactory#initPreferences.
 */
val Context.prefs
    get() = SharedPreferencesFactory.initPreferences(this)

var Context.isLoggedIn: Boolean
    get() = prefs[PREF_LOGGED_IN] ?: false
    set(value) {
        prefs[PREF_LOGGED_IN] = value
    }

var Context.authToken: String?
    get() = prefs[PREF_AUTH_TOKEN] ?: BOGUS_JWT
    set(value) {
        prefs[PREF_AUTH_TOKEN] = value
    }

var Context.refreshToken: String?
    get() = prefs[PREF_REFRESH_TOKEN] ?: BOGUS_JWT
    set(value) {
        prefs[PREF_REFRESH_TOKEN] = value
    }

var Context.userCode: String
    get() = (prefs[PREF_USER_CODE] ?: "").decryptText()
    set(value) {
        prefs[PREF_USER_CODE] = value.encryptText()
    }

var Context.email: String
    get() = prefs[PREF_EMAIL] ?: ""
    set(value) {
        prefs[PREF_EMAIL] = value
    }

var Context.password: String
    get() = (prefs[PREF_PASSWORD] ?: "").decryptText()
    set(value) {
        prefs[PREF_PASSWORD] = value.encryptText()
    }

//}
//
//fun Context.clearPrefs() {
////    prefs.edit().clear().apply()
//}
//
//// Ref: https://stackoverflow.com/a/49136401/3763032
fun Context.readPreference(key: String): Any? {
    val keys = prefs.all
    if (keys != null) {
        for (entry in keys) {
            if (entry.key == key) {
                return entry.value
            }
        }
    }
    return null
}