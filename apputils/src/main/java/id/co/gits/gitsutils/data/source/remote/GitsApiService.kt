package id.co.gits.gitsutils.data.source.remote

import id.co.gits.gitsdriver.utils.GitsHelper
import id.co.gits.gitsutils.BuildConfig
import id.co.gits.gitsutils.GitsEnviroment
import id.co.gits.gitsutils.base.BaseApiModel
import io.reactivex.Observable
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

/**
 * Created by radhikayusuf on 17/05/19.
 */

interface GitsApiService {

    @GET("3/discover/movie?api_key=1b2f29d43bf2e4f3142530bc6929d341&sort_by=popularity.desc")
    fun getMovies(): Observable<BaseApiModel<List<Any>>>

    companion object Factory {

        val getApiService: GitsApiService by lazy {
            val mLoggingInterceptor = HttpLoggingInterceptor()
            mLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

            val mClient = if (BuildConfig.DEBUG) {
                OkHttpClient.Builder()
                        .addInterceptor(mLoggingInterceptor)
                        .addInterceptor { chain ->
                            val request = chain.request()
                            val url = request.url().newBuilder().build()
                            val newRequest = request.newBuilder().url(url).build()
                            chain.proceed(newRequest)
                        }
                        .readTimeout(30, TimeUnit.SECONDS)
                        .connectTimeout(30, TimeUnit.SECONDS)
                        .build()
            } else {
                OkHttpClient.Builder()
                        .addInterceptor { chain ->
                            val request = chain.request()
                            val url = request.url().newBuilder().build()
                            val newRequest = request.newBuilder().url(url).build()
                            chain.proceed(newRequest)
                        }
                        .readTimeout(30, TimeUnit.SECONDS)
                        .connectTimeout(30, TimeUnit.SECONDS)
                        .build()
            }

            val mRetrofit = Retrofit.Builder()
                    .baseUrl(GitsEnviroment.ConstNetwork.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(mClient)
                    .build()

            mRetrofit.create(GitsApiService::class.java)
        }
    }
}