package id.co.gits.movies.main

import id.gits.gitsmvvmkotlin.base.BaseUserActionListener
import id.gits.gitsmvvmkotlin.data.model.Movie

/**
 * Created by irfanirawansukirman on 26/01/18.
 */

interface MainItemUserActionListener: BaseUserActionListener {
    fun onMovieClicked(movie: Movie)
}