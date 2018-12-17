package id.co.gits.movies.main

import android.annotation.SuppressLint
import android.app.Application
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import id.gits.gitsmvvmkotlin.data.source.GitsRepository
import id.gits.gitsmvvmkotlin.util.Injection

/**
 * Created by irfanirawansukirman on 26/01/18.
 */

class MainVMFactory private constructor(
        private val mApplication: Application,
        private val gitsRepository: GitsRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>) =
            with(modelClass) {
                when {
                    isAssignableFrom(MainViewModel::class.java) ->
                        MainViewModel(mApplication, gitsRepository)
                    else ->
                        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
                }
            } as T

    companion object {

        @SuppressLint("StaticFieldLeak")
        @Volatile private var INSTANCE: MainVMFactory? = null

        fun getInstance(mApplication: Application) =
                INSTANCE ?: synchronized(MainVMFactory::class.java) {
                    INSTANCE ?: MainVMFactory(mApplication,
                            Injection.provideGitsRepository(mApplication.applicationContext))
                            .also { INSTANCE = it }
                }
    }
}