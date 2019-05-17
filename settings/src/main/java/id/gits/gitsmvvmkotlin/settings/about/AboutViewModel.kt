package id.gits.gitsmvvmkotlin.settings.about;

import android.app.Application
import android.databinding.ObservableField
import id.co.gits.gitsutils.base.BaseViewModel


class AboutViewModel(context: Application) : BaseViewModel(context) {

    val isRequesting = ObservableField(true)

    override fun start() {

    }

}