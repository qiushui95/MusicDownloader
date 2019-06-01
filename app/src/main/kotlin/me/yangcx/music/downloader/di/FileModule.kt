package me.yangcx.music.downloader.di

import android.app.Application
import me.yangcx.base.utils.DirectoryUtils
import me.yangcx.music.downloader.sundries.Constants
import org.koin.core.qualifier.named
import org.koin.dsl.module
import java.io.File
import java.util.*

/**
 * 关于文件的注入提供类
 * create by 97457
 * create at 2018/11/08 0008
 */
object FileModule {
    val instance = module {

        single(named(Constants.NAME_CACHE_ROOT_DIR)) {
            DirectoryUtils.getRootCache(get<Application>())
        }
    }
}