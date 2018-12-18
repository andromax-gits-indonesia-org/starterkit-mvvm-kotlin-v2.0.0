package id.co.gits.movies.main

import android.app.Application
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

/**
 * Created by radhikayusuf on 17/11/2018.
 */

class MainVMFactory private constructor(
        private val mApplication: Application
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>) =
            with(modelClass) {
                when {
                    isAssignableFrom(MainViewModel::class.java) ->
                        MainViewModel(mApplication)
                    else ->
                        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
                }
            } as T

    companion object {

        @Volatile private var INSTANCE: MainVMFactory? = null
        fun getInstance(mApplication: Application) =
                INSTANCE ?: synchronized(MainVMFactory::class.java) {
                    INSTANCE ?: MainVMFactory(mApplication)
                            .also { INSTANCE = it }
                }
    }
}