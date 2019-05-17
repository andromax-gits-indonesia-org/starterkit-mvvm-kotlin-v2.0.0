package id.co.gits.gitsutils.base

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.databinding.ObservableField
import android.util.Log
import id.co.gits.gitsutils.data.source.GitsRepository
import id.co.gits.gitsutils.helper.other.Injection
import id.co.gits.gitsutils.helper.other.SingleLiveEvent

/**
 * Created by radhikayusuf on 17/05/19.
 */

abstract class BaseViewModel(application: Application, val mRepository: GitsRepository = Injection.provideGitsRepository(application)) : AndroidViewModel(application) {

    val eventShowProgress = SingleLiveEvent<Boolean>()
    val eventGlobalMessage = SingleLiveEvent<String>()

    fun showLogDebug(TAG: String, data: String) = Log.d(TAG, data)
    fun showLogVerbose(TAG: String, data: String) = Log.v(TAG, data)
    fun showLogError(TAG: String, errorMessage: String) = Log.e(TAG, errorMessage)


    open fun start() {}

    open fun onClearDisposable() {}

}