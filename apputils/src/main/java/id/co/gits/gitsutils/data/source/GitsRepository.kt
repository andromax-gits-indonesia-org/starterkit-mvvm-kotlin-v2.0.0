package id.co.gits.gitsutils.data.source

import id.co.gits.gitsutils.data.source.local.GitsLocalDataSource
import id.co.gits.gitsutils.data.source.remote.GitsRemoteDataSource

/**
 * Created by radhikayusuf on 17/05/19.
 */

open class GitsRepository(private val remoteDataSource: GitsDataSource,
                          private val localDataSource: GitsDataSource) : GitsDataSource {

    override fun onClearDisposables() {
        remoteDataSource.onClearDisposables()
        localDataSource.onClearDisposables()
    }


    companion object {

        var mRepository: GitsRepository? = null

        @JvmStatic
        fun getInstance(gitsRemoteDataSource: GitsRemoteDataSource, gitsLocalDataSource: GitsLocalDataSource): GitsRepository {
            if (mRepository == null) {
                mRepository = GitsRepository(remoteDataSource = gitsRemoteDataSource, localDataSource = gitsLocalDataSource)
            }
            return mRepository!!
        }


    }

}