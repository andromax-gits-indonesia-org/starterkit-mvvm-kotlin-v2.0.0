package id.co.gits.gitsutils.helper.other

import android.content.Context
import id.co.gits.gitsutils.data.source.GitsRepository
import id.co.gits.gitsutils.data.source.local.GitsLocalDataSource
import id.co.gits.gitsutils.data.source.remote.GitsRemoteDataSource

object Injection {

    fun provideGitsRepository(context: Context): GitsRepository {
        return GitsRepository.getInstance(GitsRemoteDataSource,
                GitsLocalDataSource(context))
    }
}
