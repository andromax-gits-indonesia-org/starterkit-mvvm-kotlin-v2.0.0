package id.co.gits.gitsutils.data.source.local

import android.content.Context
import id.co.gits.gitsutils.data.model.Movie
import id.co.gits.gitsutils.data.source.GitsDataSource
import id.co.gits.gitsutils.data.source.remote.ApiResult

/**
 * Created by radhikayusuf on 17/05/19.
 */

class GitsLocalDataSource(context: Context) : GitsDataSource {

    override suspend fun getMovies(): ApiResult<List<Movie>> {
        throw Exception("This Function only available for RemoteDataSource")
    }

}