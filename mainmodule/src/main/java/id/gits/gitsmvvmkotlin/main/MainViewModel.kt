package id.gits.gitsmvvmkotlin.main;

import android.app.Application
import android.databinding.ObservableArrayList
import android.databinding.ObservableField
import android.databinding.ObservableList
import id.co.gits.gitsutils.base.BaseViewModel
import kotlinx.coroutines.*


class MainViewModel(context: Application) : BaseViewModel(context) {

    val bMainList: ObservableList<MainModel> = ObservableArrayList()
    val isRequesting = ObservableField(false)

    override fun start() {
        loadData()
    }


    fun loadData() = launch(Dispatchers.IO) {
        isRequesting.set(true)
        val result = mRepository.getMovies()

        withContext(Dispatchers.Main) {

            bMainList.clear()
            bMainList.addAll(result.data.orEmpty().map { MainModel(it.title ?: "") })
            isRequesting.set(false)

        }
    }


}