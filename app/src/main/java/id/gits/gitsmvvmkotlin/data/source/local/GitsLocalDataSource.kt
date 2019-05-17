package id.gits.gitsmvvmkotlin.data.source.local

import android.annotation.SuppressLint
import android.content.Context
import id.gits.gitsmvvmkotlin.data.model.Movie
import id.gits.gitsmvvmkotlin.data.source.GitsDataSource
import id.gits.gitsmvvmkotlin.data.source.local.movie.MovieDao
import io.reactivex.Completable
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class GitsLocalDataSource(context: Context) : GitsDataSource {

    private var compositeDisposable: CompositeDisposable? = null
    private var status = false

    private val movieDao: MovieDao by lazy {
        GitsAppDatabase.getInstance(context).movieDao()
    }

    override fun onClearDisposables() {
        compositeDisposable?.clear()
    }

    @SuppressLint("CheckResult")
    override fun getMovies(callback: GitsDataSource.GetMoviesCallback) {
        movieDao.getMovieList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : SingleObserver<List<Movie>> {
                    override fun onSuccess(t: List<Movie>) {
                        callback.onSuccess(t)
                    }

                    override fun onSubscribe(d: Disposable) {
                        addSubscribe(d)
                    }

                    override fun onError(e: Throwable) {
                        callback.onHideProgressDialog()
                        callback.onFailed(404, e.message)
                    }
                })
    }

    @SuppressLint("CheckResult")
    override fun saveMovie(movie: List<Movie>) {
        Completable.fromAction { movieDao.insertAllMovies(movie) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    // Success block
                }, { error ->
                    // Error block
                })
    }

    fun addSubscribe(disposable: Disposable) {
        if (compositeDisposable == null) {
            compositeDisposable = CompositeDisposable()
            compositeDisposable?.add(disposable)
        }
    }

}