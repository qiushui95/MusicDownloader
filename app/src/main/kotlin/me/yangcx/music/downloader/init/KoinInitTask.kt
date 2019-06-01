package me.yangcx.music.downloader.init

import com.smartzheng.launcherstarter.task.Task
import me.yangcx.music.downloader.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

open class KoinInitTask : Task() {
    override fun needRunAsSoon(): Boolean {
        return true
    }

    override fun runOnMainThread(): Boolean {
        return true
    }

    override fun needWait(): Boolean {
        return true
    }

    override fun run() {
        val list = listOf(
                AdapterModule.instance,
                FileModule.instance,
                JsonModule.instance,
                ServiceModule.instance,
                ViewModelModule.instance,
                RepositoryModule.instance,
                RemoteModule().instance
        )
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(mApplication)
            modules(list)
        }
    }
}