package id.co.gits.movies.main

import id.gits.gitsmvvmkotlin.data.model.Movie

/**
 * Created by irfanirawansukirman on 26/01/18.
 */

interface MainItemUserActionListener {
    fun onMovieClicked(movie: Movie)
}