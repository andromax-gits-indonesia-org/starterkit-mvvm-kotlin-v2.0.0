package id.co.gits.gitsutils.data.source.remote

import id.co.gits.gitsutils.data.source.GitsDataSource
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by irfanirawansukirman on 26/01/18.
 */
object GitsRemoteDataSource : GitsDataSource {


    private var compositeDisposable : CompositeDisposable? = null


    override fun onClearDisposables() {
        compositeDisposable?.clear()
    }

    fun addSubscribe(disposable: Disposable) {
        if (compositeDisposable == null) {
            compositeDisposable = CompositeDisposable()
            compositeDisposable?.add(disposable)
        }
    }
}