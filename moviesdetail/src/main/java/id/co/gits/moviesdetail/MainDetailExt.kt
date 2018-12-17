package id.co.gits.moviesdetail

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity

/**
 * Created by irfanirawansukirman on 26/01/18.
 */
fun <T : ViewModel> AppCompatActivity.obtainViewModel(viewModelClass: Class<T>) =
        ViewModelProviders.of(this, MainDetailVMFactory.getInstance(application)).get(viewModelClass)