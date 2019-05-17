package id.co.gits.gitsutils.data.source.remote

import id.co.gits.gitsutils.data.model.Movie
import id.co.gits.gitsutils.data.source.GitsDataSource
import id.co.gits.gitsutils.helper.extensions.getResult

/**
 * Created by radhikayusuf on 17/05/19.
 */

object GitsRemoteDataSource : GitsDataSource {

    private val mApiService = GitsApiService.getApiService

    override suspend fun getMovies(): ApiResult<List<Movie>> {
        return mApiService.getMovies().getResult()
    }


}