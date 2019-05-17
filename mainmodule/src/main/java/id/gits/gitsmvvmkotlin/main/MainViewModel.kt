package id.gits.gitsmvvmkotlin.main;

import android.app.Application
import android.databinding.ObservableArrayList
import android.databinding.ObservableField
import android.databinding.ObservableList
import id.co.gits.gitsutils.base.BaseViewModel


class MainViewModel(context: Application) : BaseViewModel(context) {

    val bMainList: ObservableList<MainModel> = ObservableArrayList()
    val isRequesting = ObservableField(true)


    override fun start() {
        loadData()
    }


    fun loadData() {
        bMainList.clear()
        bMainList.add(MainModel("Radhika"))
        bMainList.add(MainModel("Yusuf"))
        bMainList.add(MainModel("Alifiansyah"))
        bMainList.add(MainModel("GITS Indonesia"))
        isRequesting.set(false)
    }


}