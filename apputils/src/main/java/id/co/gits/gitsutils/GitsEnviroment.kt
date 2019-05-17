package id.co.gits.gitsutils


/**
 * Created by radhikayusuf on 17/05/19.
 */


object GitsEnviroment {

    object ConstDate {
        const val DATE_TIME_GLOBAL = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'" //
        const val DATE_TIME_STANDARD = "yyyy-MM-dd HH:mm:ss" // 2018-10-02 12:12:12
        const val DATE_ENGLISH_YYYY_MM_DD = "yyyy-MM-dd" // 2018-10-02
        const val DATE_ENGLISH_YYYY_MM_DD_CLEAR = "yyyy MM dd" // 2018 10 02
        const val DATE_LOCALE_DD_MM_YYYY = "dd-MM-yyyy" // 02-10-2018
        const val DATE_LOCALE_DD_MM_YYYY_CLEAR = "dd MM yyyy" // 02-10-2018
        const val TIME_GENERAL_HH_MM_SS = "HH:mm:ss" // 12:12:12
        const val TIME_GENERAL_HH_MM = "HH:mm" // 12:12
        const val DAY_WITH_DATE_TIME_ENGLISH = "EEE, MMM dd yyyy HH:mm" // Mon, Aug 12 2018 12:12
        const val DAY_WITH_DATE_TIME_LOCALE = "EEE, dd MMM yyyy HH:mm" // Sen, 12 Agt 2019 12:12
        const val DAY_WITH_DATE_TIME_ENGLISH_FULL = "EEEE, MMMM dd yyyy HH:mm" // Monday, August 12 2018 12:12
        const val DAY_WITH_DATE_TIME_LOCALE_FULL = "EEEE, dd MMMM yyyy HH:mm" // Senin, 12 Agustus 2018 12:12
    }

    object ConstIntCode {

        const val REQUEST_CODE_GALLERY = 5115

    }

    object ConstKey {

        // INTENT KEY
        const val INTENT_EXTRA_MOVIE_ITEM = "EXTRA_MOVIE_ITEM"
        const val INTENT_EXTRA_MOVIE_ID = "EXTRA_MOVIE_ID"
        const val INTENT_EXTRA_GLOBAL = "EXTRA_GLOBAL"

        // ARGUMENTS KEY
        const val ARGUMENT_MOVIE_ITEM = "ARGUMENT_MOVIE_ITEM"
        const val ARGUMENT_MOVIE_ID = "ARGUMENT_MOVIE_ID"

        // PREFERENCES KEY
        const val SHARED_PREFERENCE_NAME_DEFAULT = "GITS-INDONESIA-KEY"
        const val PREF_USER_ID = "PREF_USER_ID"
    }

    object ConstNetwork {

        val BASE_URL = BuildConfig.BASE_URL
        val BASE_URL_IMAGE = BuildConfig.BASE_URL + "/image"

    }

    object ConstFile {
        val APP_FOLDER_DEFAULT = ""
    }

    object ConstOther {
        // TODO add uncategories const in here
        val HTTP_STRING = "http"

        val SNACKBAR_TIMER_SHOWING_DEFAULT = 2000
    }

}