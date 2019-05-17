package id.co.gits.gitsutils.base

/**
 * Dibuat oleh Irfan Irawan Sukirman
 * @Copyright 2018
 */
interface BaseDataSource {

    interface GitsResponseCallback<T> {
        fun onSuccess(data: T)
        fun onFinish()
        fun onFailed(statusCode: Int, errorMessage: String? = "")
    }

    fun onClearDisposables()
}