package me.yangcx.music.downloader.sundries

import android.app.Application
import com.smartzheng.launcherstarter.LauncherStarter
import me.yangcx.music.downloader.init.KoinInitTask
import me.yangcx.music.downloader.init.LoggerInitTask

class UsefulApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initSdk()
    }

    private fun initSdk() {
        LauncherStarter.init(this)
        val starter = LauncherStarter.createInstance()
            .addTask(KoinInitTask())
            .addTask(LoggerInitTask(true))
        starter.start()
        starter.await()
    }
}