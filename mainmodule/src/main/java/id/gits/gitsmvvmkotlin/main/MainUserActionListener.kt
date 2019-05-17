package id.gits.gitsmvvmkotlin.main;

import id.co.gits.gitsutils.base.BaseUserActionListener

interface MainUserActionListener : BaseUserActionListener {

    fun onClickItem(data: MainModel)

}