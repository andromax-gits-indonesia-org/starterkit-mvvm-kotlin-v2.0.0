package id.co.gits.moviesdetail

import android.annotation.SuppressLint
import android.app.Application
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import id.gits.gitsmvvmkotlin.data.source.GitsRepository
import id.gits.gitsmvvmkotlin.util.Injection

/**
 * Created by irfanirawansukirman on 26/01/18.
 */

class MainDetailVMFactory private constructor(
        private val mApplication: Application,
        private val gitsRepository: GitsRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>) =
            with(modelClass) {
                when {
                    isAssignableFrom(MainDetailViewModel::class.java) ->
                        MainDetailViewModel(mApplication, gitsRepository)
                    else ->
                        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
                }
            } as T

    companion object {

        @SuppressLint("StaticFieldLeak")
        @Volatile private var INSTANCE: MainDetailVMFactory? = null

        fun getInstance(mApplication: Application) =
                INSTANCE ?: synchronized(MainDetailVMFactory::class.java) {
                    INSTANCE ?: MainDetailVMFactory(mApplication,
                            Injection.provideGitsRepository(mApplication.applicationContext))
                            .also { INSTANCE = it }
                }
    }
}