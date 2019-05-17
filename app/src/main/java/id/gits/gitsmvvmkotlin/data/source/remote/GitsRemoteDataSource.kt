package id.gits.gitsmvvmkotlin.data.source.remote

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import id.gits.gitsmvvmkotlin.base.BaseApiModel
import id.gits.gitsmvvmkotlin.data.model.Movie
import id.gits.gitsmvvmkotlin.data.source.GitsDataSource
import id.gits.gitsmvvmkotlin.util.GitsNullAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by irfanirawansukirman on 26/01/18.
 */
object GitsRemoteDataSource : GitsDataSource {

    private val movieListAdapter: Gson by lazy {
        GsonBuilder()
                .registerTypeAdapter(Movie::class.java, GitsNullAdapter())
                .create()
    }

    private var compositeDisposable : CompositeDisposable? = null

    override fun getMovies(callback: GitsDataSource.GetMoviesCallback) {
        GitsApiService.getApiService
                .getMovies()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : ApiCallback<BaseApiModel<List<Movie>>>() {
                    override fun onSuccess(model: BaseApiModel<List<Movie>>) {
                        val oldData = model.results
                        val newData = ArrayList<Movie>()

                        for (i in 0 until oldData!!.size) {
                            newData.add(Gson().fromJson(movieListAdapter.toJson(oldData[i]),
                                    Movie::class.java))
                        }

                        callback.onSuccess(newData)
                    }

                    override fun onFailure(code: Int, errorMessage: String) {
                        callback.onFailed(code, errorMessage)
                    }

                    override fun onFinish() {
                        callback.onFinish()
                    }

                    override fun onStartObserver(disposable: Disposable) {
                        addSubscribe(disposable)
                    }
                })
    }

    override fun saveMovie(movie: List<Movie>) {
        // Tidak digunakan
    }

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