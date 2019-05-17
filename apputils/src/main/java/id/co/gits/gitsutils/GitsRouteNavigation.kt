package id.co.gits.gitsutils

import android.content.Context
import id.co.gits.gitsutils.helper.extensions.navigatorImplicit

/**
 * Created by radhikayusuf on 17/05/19.
 */

object GitsRouteNavigation {

    const val PACKAGE_PACKAGE_NAME = "id.gits.gitsmvvmkotlin"

    /** Main */
    const val PACKAGE_MAIN_PAGE = "id.gits.gitsmvvmkotlin.main.MainActivity"



    /** Setting */
    const val PACKAGE_SETTING_PAGE = "id.gits.gitsmvvmkotlin.settings.setting.SettingActivity"
    const val PACKAGE_ABOUT_PAGE = "id.gits.gitsmvvmkotlin.settings.about.AboutActivity"



    /**
     * Main
     */
    fun openClearMainPage(context: Context){
        context.navigatorImplicit(GitsRouteNavigation.PACKAGE_PACKAGE_NAME, GitsRouteNavigation.PACKAGE_MAIN_PAGE, clearStack = true)
    }

    fun openMainPage(context: Context){
        context.navigatorImplicit(GitsRouteNavigation.PACKAGE_PACKAGE_NAME, GitsRouteNavigation.PACKAGE_MAIN_PAGE)
    }


    /**
     * Setting
     */
    fun openSettingPage(context: Context){
        context.navigatorImplicit(GitsRouteNavigation.PACKAGE_PACKAGE_NAME, GitsRouteNavigation.PACKAGE_SETTING_PAGE)
    }

    fun openAboutPage(context: Context){
        context.navigatorImplicit(GitsRouteNavigation.PACKAGE_PACKAGE_NAME, GitsRouteNavigation.PACKAGE_ABOUT_PAGE)
    }
}

