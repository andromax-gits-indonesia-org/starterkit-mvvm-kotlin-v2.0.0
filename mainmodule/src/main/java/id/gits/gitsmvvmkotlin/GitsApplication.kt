package id.gits.gitsmvvmkotlin

import android.app.Application
import android.content.Context
import id.co.gits.gitsdriver.utils.GitsHelper

/**
 * Created by radhikayusuf on 17/05/19.
 */
class GitsApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        instance = this
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(GitsHelper.SystemLocale.onAttach(base, "en"))
    }

    companion object {
        lateinit var instance: GitsApplication

        fun getContext(): Context = instance.applicationContext
    }

}