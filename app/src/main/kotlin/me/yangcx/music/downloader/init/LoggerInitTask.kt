package me.yangcx.music.downloader.init

import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.DiskLogAdapter
import com.orhanobut.logger.Logger
import com.smartzheng.launcherstarter.task.Task


class LoggerInitTask(private val isDebug: Boolean) : Task() {
    override fun run() {
        if (isDebug) {
            Logger.addLogAdapter(AndroidLogAdapter())
        } else {
            Logger.addLogAdapter(DiskLogAdapter())
        }
    }
}