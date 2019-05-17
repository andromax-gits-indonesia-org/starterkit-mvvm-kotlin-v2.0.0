package id.co.gits.gitsutils.helper.extensions

import android.content.Context
import android.content.Intent
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity

/**
 *
 * In syaa Allah created & modified
 * by mochadwi on 27/02/19
 * dedicated to build e-nutri
 *
 */

/**
 * Safer ways to use requireContext or requireActivity
 */
fun Fragment.safeRequireActivity(): FragmentActivity? = if (isAdded) requireActivity() else activity

fun Fragment.safeRequireContext(): Context? = if (isAdded) requireContext() else context
