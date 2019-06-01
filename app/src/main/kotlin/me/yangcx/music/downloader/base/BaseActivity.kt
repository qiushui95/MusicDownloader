package me.yangcx.music.downloader.base

import android.os.Bundle
import com.noober.background.BackgroundLibrary
import me.yangcx.base.ui.FoundationActivity

abstract class BaseActivity:FoundationActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        BackgroundLibrary.inject(this)
        super.onCreate(savedInstanceState)
    }
}