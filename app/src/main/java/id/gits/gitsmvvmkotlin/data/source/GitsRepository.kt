package id.gits.gitsmvvmkotlin.data.source

import android.util.Log
import com.google.gson.Gson
import id.gits.gitsmvvmkotlin.data.model.Movie

/**
 * Created by irfanirawansukirman on 26/01/18.
 */

open class GitsRepository(private val remoteDataSource: GitsDataSource,
                          private val localDataSource: GitsDataSource) : GitsDataSource {

    override fun onClearDisposables() {
        remoteDataSource.onClearDisposables()
        localDataSource.onClearDisposables()
    }

    override fun saveMovie(movie: List<Movie>) {
        localDataSource.saveMovie(movie)
    }

    override fun getMovies(callback: GitsDataSource.GetMoviesCallback) {
        remoteDataSource.getMovies(object : GitsDataSource.GetMoviesCallback {
            override fun onShowProgressDialog() {

            }

            override fun onHideProgressDialog() {

            }

            override fun onSuccess(data: List<Movie>) {
                saveMovie(data)
                loadMovies(callback)
            }

            override fun onFinish() {
                callback.onFinish()
            }

            override fun onFailed(statusCode: Int, errorMessage: String?) {
                callback.onFailed(statusCode, errorMessage)
            }
        })
    }

    private fun loadMovies(callback: GitsDataSource.GetMoviesCallback) {
        localDataSource.getMovies(object : GitsDataSource.GetMoviesCallback {
            override fun onShowProgressDialog() {

            }

            override fun onHideProgressDialog() {

            }

            override fun onSuccess(data: List<Movie>) {
                callback.onSuccess(data)
            }

            override fun onFinish() {
                callback.onFinish()
            }

            override fun onFailed(statusCode: Int, errorMessage: String?) {
                callback.onFailed(statusCode, errorMessage)
            }
        })
    }

}