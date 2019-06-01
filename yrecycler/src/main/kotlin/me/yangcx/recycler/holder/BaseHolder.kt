package me.yangcx.recycler.holder

import android.content.ComponentCallbacks
import android.content.res.Configuration
import android.view.View
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

abstract class BaseHolder<T : Any>(itemView: View) : RecyclerView.ViewHolder(itemView), ComponentCallbacks {
    protected lateinit var data: T

    protected var itemCount: Int = 0

    fun setData(data: T, itemCount: Int) {
        this.data = data
        this.itemCount = itemCount
    }

    /**
     * 绘制item界面
     */
    abstract fun drawUi(data: T)

    /**
     * 数据局部变化、重绘局部界面
     */
    open fun dataChanged(data: T, changeList: List<String>) {}

    /**
     * View创建之后的回调
     */
    open fun onViewCreated(itemView: View) {}


    protected fun getString(@StringRes resId: Int, vararg formatArgs: Any): String = itemView.context.getString(resId, formatArgs)


    protected fun getColor(@ColorRes resId: Int): Int = ContextCompat.getColor(itemView.context, resId)

    override fun onLowMemory() {

    }

    override fun onConfigurationChanged(newConfig: Configuration?) {

    }
}