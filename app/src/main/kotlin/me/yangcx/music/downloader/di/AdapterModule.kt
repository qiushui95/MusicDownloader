package me.yangcx.music.downloader.di

import me.yangcx.recycler.adapter.CommonAdapter
import org.koin.dsl.module

object AdapterModule {
    val instance = module {
        
        factory {
            CommonAdapter()
        }
    }
}