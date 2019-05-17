package id.co.gits.gitsutils.data.source.local

import android.content.Context
import id.co.gits.gitsutils.data.source.GitsDataSource
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by radhikayusuf on 17/05/19.
 */

class GitsLocalDataSource(context: Context) : GitsDataSource {

    private var compositeDisposable: CompositeDisposable? = null
    private var status = false

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