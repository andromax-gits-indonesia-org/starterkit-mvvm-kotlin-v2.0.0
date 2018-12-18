package id.co.gits.moviesdetail

import android.app.Application
import id.co.gits.gitsbase.BaseViewModel
import id.gits.gitsmvvmkotlin.util.SingleLiveEvent

class MainDetailViewModel(context: Application) : BaseViewModel(context) {


    val movieTitle = SingleLiveEvent<String>()
    var movieRating = SingleLiveEvent<String>()
    var movieDateRelease = SingleLiveEvent<String>()
    var movieDescription = SingleLiveEvent<String>()
    var movieImageBackdropUrl = SingleLiveEvent<String>()
    var movieImagePosterUrl = SingleLiveEvent<String>()

    fun getMovieById(movieId: Int) {

    }
}
