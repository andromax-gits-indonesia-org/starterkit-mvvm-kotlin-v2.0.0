package id.co.gits.gitsutils.helper.extensions

import android.content.Context
import android.net.ConnectivityManager
import android.provider.Settings
import android.telephony.PhoneNumberUtils
import android.text.TextUtils
import android.util.DisplayMetrics
import android.view.WindowManager
import com.google.gson.Gson
import id.co.gits.gitsdriver.utils.GitsHelper
import id.co.gits.gitsutils.base.BaseApiModel
import id.co.gits.gitsutils.data.source.remote.ApiResult
import kotlinx.coroutines.Deferred
import retrofit2.HttpException
import java.lang.Exception
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern


suspend fun <T> Deferred<BaseApiModel<T>>.getResult(): ApiResult<T> {
    return try {
        val data = this.await()
        if (data.code == 200 && data.results != null) {
            ApiResult(data.code, data.message?:"", data.results)
        } else {
            ApiResult(data.code, data.message?:"", data.results)
        }

    } catch (e: Exception) {
        e.printStackTrace()
        when (e) {
            is HttpException -> {
                val code = e.code()
                var msg = e.message()
                val baseDao: BaseApiModel<T>?
                try {
                    val body = e.response().errorBody()
                    baseDao = Gson().fromJson<BaseApiModel<T>>(body!!.string(), BaseApiModel::class.java)
                } catch (exception: Exception) {
                    return ApiResult(-1, exception.message?:exception.localizedMessage, null)
                }

                when (code) {
                    504 -> {
                        msg = baseDao?.message ?: "Error Response"
                    }
                    502, 404 -> {
                        msg = baseDao?.message ?: "Error Connect or Resource Not Found"
                    }
                    400 -> {
                        msg = baseDao?.message ?: "Bad Request"
                    }
                    401 -> {
                        msg = baseDao?.message ?: "Not Authorized"
                    }
                }

                return ApiResult(baseDao.code, msg, null)
            }
            is UnknownHostException -> return ApiResult(-1, "Telah terjadi kesalahan ketika koneksi ke server: ${e.message}", null)
            is SocketTimeoutException -> return ApiResult(-1, "Telah terjadi kesalahan ketika koneksi ke server: ${e.message}", null)
            else -> return ApiResult(-1, "Unknown error occured", null)
        }
    }


}

fun String.getDeviceId(
        context: Context
): String {
    return Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
}

fun String.currencyFormatToRupiah(
        data: Double
): String {
    val kursIndonesia = DecimalFormat.getCurrencyInstance() as DecimalFormat
    val formatRp = DecimalFormatSymbols()

    formatRp.currencySymbol = "Rp. "
    formatRp.monetaryDecimalSeparator = '.'
    formatRp.groupingSeparator = ','

    kursIndonesia.decimalFormatSymbols = formatRp
    return kursIndonesia.format(data)
}

fun String.setClearWebviewContent(
        bodyHTML: String
): String {
    val head = "<head><style>img{max-width: 100%; height: auto;} body { margin: 0; }" +
            "iframe {display: block; background: #000; border-top: 4px solid #000; border-bottom: 4px solid #000;" +
            "top:0;left:0;width:100%;height:235;}</style></head>"
    return "<html>$head<body>$bodyHTML</body></html>"
}

fun Boolean.isLocaleDate(
        isLocale: Boolean
): Locale {
    return if (isLocale) Locale("id", "ID")
    else Locale("en", "EN")
}

fun Boolean.emailValidate(
        email: String
): Boolean {
    val emailPattern = Pattern.compile(
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
                    "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
    )
    return emailPattern.matcher(email).matches()
}

fun Boolean.phoneValidate(
        phone: String
): Boolean {
    return PhoneNumberUtils.isGlobalPhoneNumber(phone)
}

fun Boolean.isNetworkAvailable(
        context: Context
): Boolean {
    val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    if (cm != null) {
        val activeNetwork = cm.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting
    }
    return false
}

fun Int.getScreenHeight(
        context: Context
): Int {
    val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
    val dm = DisplayMetrics()
    windowManager.defaultDisplay.getMetrics(dm)
    return dm.heightPixels
}

fun Int.getScreenWidth(
        context: Context
): Int {
    val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
    val dm = DisplayMetrics()
    windowManager.defaultDisplay.getMetrics(dm)
    return dm.widthPixels
}

fun Int.getStatusBarHeight(
        context: Context
): Int {
    var result = 0
    val resourceId = context.resources.getIdentifier("status_bar_height", "dimen", "android")
    if (resourceId > 0) {
        result = context.resources.getDimensionPixelSize(resourceId)
    }
    return result
}

fun Int.autofitColumnsGrid(
        context: Context
): Int {
    val displayMetrics = context.resources.displayMetrics
    val dpWidth = displayMetrics.widthPixels / displayMetrics.density
    return (dpWidth / 180).toInt()
}