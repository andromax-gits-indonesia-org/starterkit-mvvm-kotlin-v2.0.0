package id.co.gits.movies.main

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity

/**
 * Created by irfanirawansukirman on 26/01/18.
 */

fun <T : ViewModel> AppCompatActivity.obtainViewModels(viewModelClass: Class<T>) =
        ViewModelProviders.of(this, MainVMFactory.getInstance(application)).get(viewModelClass)