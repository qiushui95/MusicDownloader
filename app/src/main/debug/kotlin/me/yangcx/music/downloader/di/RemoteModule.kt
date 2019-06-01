package me.yangcx.music.downloader.di

import com.facebook.stetho.okhttp3.StethoInterceptor
import me.yangcx.music.downloader.sundries.Lo
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

/**
 * 关于网络请求的注入提供类
 * create by 97457
 * create at 2018/11/08 0008
 */
class RemoteModule : CommonRemoteModule() {
    override val okHttpClient: OkHttpClient.Builder
        get() = super.okHttpClient
            .addNetworkInterceptor(StethoInterceptor())
            .addInterceptor(
                HttpLoggingInterceptor() {
                    Lo.i(it)
                }.setLevel(HttpLoggingInterceptor.Level.BASIC)
            )
}