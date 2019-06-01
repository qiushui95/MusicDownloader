package me.yangcx.music.downloader.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import me.yangcx.music.downloader.sundries.Constants
import okhttp3.Cache
import okhttp3.OkHttpClient
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.CallAdapter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

/**
 * 关于网络请求的注入提供类
 * create by 97457
 * create at 2018/11/08 0008
 */
abstract class CommonRemoteModule {
    protected open val okHttpClient: OkHttpClient.Builder = OkHttpClient.Builder()
        .connectTimeout(5 * 1000, TimeUnit.MILLISECONDS)
        .retryOnConnectionFailure(true)
        .readTimeout(5 * 1000, TimeUnit.MILLISECONDS)
        .writeTimeout(5 * 1000, TimeUnit.MILLISECONDS)

    val instance = module {
        single {
            Cache(File(get<File>(named(Constants.NAME_CACHE_ROOT_DIR)), "http"), 100 * 1024 * 1024)
        }

        single<CallAdapter.Factory> {
            CoroutineCallAdapterFactory.invoke()
        }

        single {
            GsonConverterFactory.create(get())
        }

        single {
            ScalarsConverterFactory.create()
        }

        single {
            okHttpClient
                .cache(get())
                .build()
        }

        single {
            Retrofit.Builder()
                .baseUrl("https://v1.itooi.cn")
                .addConverterFactory(get<ScalarsConverterFactory>())
                .addConverterFactory(get<GsonConverterFactory>())
                .addCallAdapterFactory(get())
                .client(get())
                .build()
        }
    }
}