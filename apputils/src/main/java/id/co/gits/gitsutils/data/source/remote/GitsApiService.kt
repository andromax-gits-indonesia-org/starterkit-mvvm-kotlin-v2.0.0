package id.co.gits.gitsutils.data.source.remote

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import id.co.gits.gitsutils.BuildConfig
import id.co.gits.gitsutils.GitsEnviroment
import id.co.gits.gitsutils.base.BaseApiModel
import id.co.gits.gitsutils.data.model.Movie
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

/**
 * Created by radhikayusuf on 17/05/19.
 */

interface GitsApiService {

    @GET("3/discover/movie?api_key=1b2f29d43bf2e4f3142530bc6929d341&sort_by=popularity.desc")
    fun getMovies(): Deferred<BaseApiModel<List<Movie>>>

    companion object Factory {

        val getApiService: GitsApiService by lazy {
            val mLoggingInterceptor = HttpLoggingInterceptor()
            mLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

            val mClient = if (BuildConfig.DEBUG) {
                OkHttpClient.Builder()
                        .addInterceptor(mLoggingInterceptor)
                        .readTimeout(30, TimeUnit.SECONDS)
                        .connectTimeout(30, TimeUnit.SECONDS)
                        .build()
            } else {
                OkHttpClient.Builder()
                        .readTimeout(30, TimeUnit.SECONDS)
                        .connectTimeout(30, TimeUnit.SECONDS)
                        .build()
            }

            val mRetrofit = Retrofit.Builder()
                    .baseUrl(GitsEnviroment.ConstNetwork.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(CoroutineCallAdapterFactory())
                    .client(mClient)
                    .build()

            mRetrofit.create(GitsApiService::class.java)
        }
    }
}