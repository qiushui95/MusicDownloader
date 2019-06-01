package me.yangcx.music.downloader.di

import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import org.koin.dsl.module

/**
 * 关于json的注入提供类
 * create by 97457
 * create at 2018/11/08 0008
 */
object JsonModule {
    val instance = module {

        single {
            GsonBuilder()
                .create()
        }

        single {
            JsonParser()
        }
    }
}