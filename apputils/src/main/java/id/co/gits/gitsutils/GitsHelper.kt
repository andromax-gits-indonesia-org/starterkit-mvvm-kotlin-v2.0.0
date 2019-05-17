package id.co.gits.gitsdriver.utils

import android.annotation.TargetApi
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Build
import android.preference.PreferenceManager
import android.telephony.PhoneNumberUtils
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*
import java.util.regex.Pattern

/**
 * Created by radhikayusuf on 17/05/19.
 */


object GitsHelper {

    object DateFunc {

    }

    object Func {

        fun isNetworkAvailable(context: Context): Boolean? {
            var isConnected: Boolean? = false // Initial Value
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
            if (activeNetwork != null && activeNetwork.isConnected) isConnected = true
            return isConnected
        }

        fun TAG(nameTag: String) = nameTag

        fun currencyFormatToRupiah(data: Double): String {
            val kursIndonesia = DecimalFormat.getCurrencyInstance() as DecimalFormat
            val formatRp = DecimalFormatSymbols()

            formatRp.currencySymbol = "Rp. "
            formatRp.monetaryDecimalSeparator = '.'
            formatRp.groupingSeparator = ','

            kursIndonesia.decimalFormatSymbols = formatRp
            return kursIndonesia.format(data)
        }

        fun setClearWebviewContent(bodyHTML: String): String {
            val head = "<head><style>img{max-width: 100%; height: auto;} body { margin: 0; }" +
                    "iframe {display: block; background: #000; border-top: 4px solid #000; border-bottom: 4px solid #000;" +
                    "top:0;left:0;width:100%;height:235;}</style></head>"
            return "<html>$head<body>$bodyHTML</body></html>"
        }

        fun emailValidate(email: String): Boolean {
            val emailPattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
                    "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
            return emailPattern.matcher(email).matches()
        }

        fun phoneValidate(phone: String): Boolean {
            return PhoneNumberUtils.isGlobalPhoneNumber(phone)
        }

        fun getFirstWord(text: String): String {
            return if (text.indexOf(' ') > -1) { // Check if there is more than one word.
                text.substring(0, text.indexOf(' ')) // Extract first word.
            } else {
                text // Text is the first word itself.
            }
        }

        fun setCapitalFirstChar(text: String?): String {
            if (text == null) {
                return ""
            }
            val tempString = text.split(" ")

            return tempString.joinToString(" ") {
                it.capitalize()
            }
        }

        private fun isLocaleDate(isLocale: Boolean): Locale {
            return if (isLocale) Locale("id", "ID")
            else Locale("en", "EN")
        }

        fun <T> jsonStringToList(jsonString: String): List<T> {
            val initList: List<T>

            val collectionType = object : TypeToken<List<T>>() {}.type
            initList = Gson().fromJson<List<T>>(jsonString, collectionType)

            return initList
        }
    }

    /**
     * This class is used to change your application locale and persist this change for the next time
     * that your app is going to be used.
     *
     *
     * You can also change the locale of your application on the fly by using the setLocale method.
     *
     *
     * Created by gunhansancar on 07/10/15.
     */
    object SystemLocale {

        private val SELECTED_LANGUAGE = "Locale.Helper.Selected.Language"

        fun onAttach(context: Context): Context {
            val lang = getPersistedData(context, Locale.getDefault().language)
            return setLocale(context, lang)
        }

        fun onAttach(context: Context, defaultLanguage: String): Context {
            val lang = getPersistedData(context, defaultLanguage)
            return setLocale(context, lang)
        }

        fun getLanguage(context: Context): String? {
            return getPersistedData(context, Locale.getDefault().language)
        }

        fun setLocale(context: Context, language: String?): Context {
            persist(context, language)

            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                updateResources(context, language)
            } else updateResourcesLegacy(context, language)

        }

        private fun getPersistedData(context: Context, defaultLanguage: String): String? {
            val preferences = PreferenceManager.getDefaultSharedPreferences(context)
            return preferences.getString(SELECTED_LANGUAGE, defaultLanguage)
        }

        private fun persist(context: Context, language: String?) {
            val preferences = PreferenceManager.getDefaultSharedPreferences(context)
            val editor = preferences.edit()

            editor.putString(SELECTED_LANGUAGE, language)
            editor.apply()
        }

        @TargetApi(Build.VERSION_CODES.N)
        private fun updateResources(context: Context, language: String?): Context {
            val locale = Locale(language)
            Locale.setDefault(locale)

            val configuration = context.resources.configuration
            configuration.setLocale(locale)
            configuration.setLayoutDirection(locale)

            return context.createConfigurationContext(configuration)
        }

        private fun updateResourcesLegacy(context: Context, language: String?): Context {
            val locale = Locale(language)
            Locale.setDefault(locale)

            val resources = context.resources

            val configuration = resources.configuration
            configuration.locale = locale
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                configuration.setLayoutDirection(locale)
            }

            resources.updateConfiguration(configuration, resources.displayMetrics)

            return context
        }
    }

}