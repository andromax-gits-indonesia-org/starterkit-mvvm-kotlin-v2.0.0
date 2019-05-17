package id.gits.gitsmvvmkotlin.settings.setting;

import android.app.Application
import android.databinding.ObservableField
import id.co.gits.gitsutils.base.BaseViewModel


class SettingViewModel(context: Application) : BaseViewModel(context) {

    val isRequesting = ObservableField(true)

    override fun start() {

    }

}