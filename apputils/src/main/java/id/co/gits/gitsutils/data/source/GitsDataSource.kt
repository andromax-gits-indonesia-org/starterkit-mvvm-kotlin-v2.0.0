package id.co.gits.gitsutils.data.source

import id.co.gits.gitsutils.base.BaseDataSource
import id.co.gits.gitsutils.data.model.Movie
import id.co.gits.gitsutils.data.source.remote.ApiResult


/**
 * Created by radhikayusuf on 17/05/19.
 */

interface GitsDataSource: BaseDataSource {

    suspend fun getMovies(): ApiResult<List<Movie>>

}