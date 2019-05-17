package id.co.gits.gitsutils.helper.extensions

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Toast

/**
 * Created by irfanirawansukirman on 26/01/18.
 */
fun AppCompatActivity.replaceFragmentInActivity(
        fragment: Fragment,
        frameId: Int
) {
    supportFragmentManager.transact {
        replace(frameId, fragment)
    }
}

fun AppCompatActivity.replaceFragmentInActivityWithBackStack(
        fragment: Fragment,
        frameId: Int,
        TAG: String?
) {
    supportFragmentManager.transact {
        replace(frameId, fragment)
        addToBackStack(TAG)
    }
}

private inline fun FragmentManager.transact(
        action: FragmentTransaction.() -> Unit
) {
    beginTransaction().apply {
        action()
    }.commit()
}

@SuppressLint("ObsoleteSdkInt")
fun AppCompatActivity.transparentStatusBar(
        decorView: View
) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
        decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
    }
}

inline fun <reified T : AppCompatActivity> AppCompatActivity.navigator(

) {
    val intent = Intent(this, T::class.java)
    startActivity(intent)
}

inline fun <reified T : AppCompatActivity> AppCompatActivity.navigator(
        param: String
) {
    val intent = Intent(this, T::class.java)
    intent.putExtra("param", param)
    startActivity(intent)
}

/**
 * @param activityPackage => exp : id.co.gits.feature_home_detail.HomeDetailActivity
 */
fun AppCompatActivity.navigatorImplicit(
        context: Context,
        activityPackage: String
) {
    val intent = Intent()
    try {
        intent.setClass(
                context,
                Class.forName(activityPackage)
        )
        startActivity(intent)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun AppCompatActivity.logD(
        classs: Class<*>,
        message: String
) {
    Log.d(classs::class.java.simpleName, message)
}

fun AppCompatActivity.logV(
        classs: Class<*>,
        message: String
) {
    Log.v(classs::class.java.simpleName, message)
}

fun AppCompatActivity.logE(
        classs: Class<*>,
        message: String
) {
    Log.e(classs::class.java.simpleName, message)
}


fun Context.navigatorImplicit(
        activityPackage: String,
        className: String,
        extras: Bundle = Bundle(),
        clearStack: Boolean = false,
        option: Bundle? = null) {
    val intent = Intent()
    try {
        intent.setClassName(activityPackage, className).putExtras(extras)

        if (clearStack) {
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        if (option != null) {
            startActivity(intent, option)
        } else {
            startActivity(intent)
        }

    } catch (e: Exception) {
        Toast.makeText(this, "Activity not found", Toast.LENGTH_SHORT).show()
        e.printStackTrace()
    }
}

/**
 * @param activityPackage => exp : id.co.gits.feature_home_detail.HomeDetailActivity
 */
fun Fragment.navigatorImplicit(
        activityPackage: String,
        className: String,
        extras: Bundle = Bundle(),
        clearStack: Boolean = false, option: Bundle? = null) {
    val intent = Intent()
    try {
        intent.setClassName(activityPackage, className).putExtras(extras)

        if (clearStack) {
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        if (option != null) {
            startActivity(intent, option)
        } else {
            startActivity(intent)
        }

    } catch (e: Exception) {
        Toast.makeText(requireContext(), "Activity not found", Toast.LENGTH_SHORT).show()
        e.printStackTrace()
    }
}

fun Activity.navigatorImplicit(
        activityPackage: String,
        className: String,
        extras: Bundle = Bundle(),
        clearStack: Boolean = false, option: Bundle? = null) {
    val intent = Intent()
    try {
        intent.setClassName(activityPackage, className).putExtras(extras)

        if (clearStack) {
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        if (option != null) {
            startActivity(intent, option)
        } else {
            startActivity(intent)
        }

    } catch (e: Exception) {
        Toast.makeText(this, "Activity not found", Toast.LENGTH_SHORT).show()
        e.printStackTrace()
    }
}


fun Fragment.navigatorImplicitWithResult(
        activityPackage: String,
        className: String,
        requestCode: Int,
        extras: Bundle = Bundle(),
        option: Bundle? = null) {
    val intent = Intent()
    try {
        intent.setClassName(activityPackage, className).putExtras(extras)

        if (option != null) {
            startActivityForResult(intent, requestCode, option)
        } else {
            startActivityForResult(intent, requestCode)
        }

    } catch (e: Exception) {
        Toast.makeText(requireContext(), "Activity not found", Toast.LENGTH_SHORT).show()
        e.printStackTrace()
    }
}

fun Activity.navigatorImplicitWithResult(
        activityPackage: String,
        className: String,
        requestCode: Int,
        extras: Bundle = Bundle(),
        option: Bundle? = null) {
    val intent = Intent()
    try {
        intent.setClassName(activityPackage, className).putExtras(extras)

        if (option != null) {
            startActivityForResult(intent, requestCode, option)
        } else {
            startActivityForResult(intent, requestCode)
        }

    } catch (e: Exception) {
        Toast.makeText(this, "Activity not found", Toast.LENGTH_SHORT).show()
        e.printStackTrace()
    }
}