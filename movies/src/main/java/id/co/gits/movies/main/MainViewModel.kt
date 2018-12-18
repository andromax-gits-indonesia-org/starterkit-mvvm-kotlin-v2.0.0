package id.co.gits.movies.main

import android.app.Application
import id.co.gits.gitsbase.BaseViewModel
import id.gits.gitsmvvmkotlin.data.model.Movie
import id.gits.gitsmvvmkotlin.data.source.GitsDataSource
import id.gits.gitsmvvmkotlin.util.SingleLiveEvent
import radhika.yusuf.id.mvvmkotlin.utils.chocohelper.ChocoChips

/**
 * Created by irfanirawansukirman on 26/01/18.
 */
class MainViewModel(context: Application) : BaseViewModel(context) {

    var movieListLive = SingleLiveEvent<List<Movie>>()
    val snackBarMessageRemote = SingleLiveEvent<String>()

    override fun start() {
        super.start()
        ChocoChips.inject(this)
        getMovies()
    }

    override fun onClearDisposable() {
        super.onClearDisposable()
        gitsRepository.onClearDisposables()
    }


    private fun getMovies() {
        gitsRepository.getMovies(object : GitsDataSource.GetMoviesCallback {
            override fun onShowProgressDialog() {

            }

            override fun onHideProgressDialog() {

            }

            override fun onSuccess(data: List<Movie>) {
                movieListLive.apply {
                    value = data
                }
            }

            override fun onFinish() {

            }

            override fun onFailed(statusCode: Int, errorMessage: String?) {
                snackBarMessageRemote.value = errorMessage
            }
        })
    }
}