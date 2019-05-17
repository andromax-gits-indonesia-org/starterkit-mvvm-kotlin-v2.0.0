package id.co.gits.gitsutils.data.source.local.movie

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import id.co.gits.gitsutils.data.model.Movie
import kotlinx.coroutines.Deferred

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie WHERE id = :id")
    fun getMovieById(id: Int): Movie

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: Movie)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllMovies(movies: List<Movie>)

    @Query("DELETE FROM movie")
    fun deleteAllHeroes()

    @Query("SELECT * FROM movie")
    fun getAllMovies(): List<Movie>

}