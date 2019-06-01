package me.yangcx.base.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import me.yangcx.annotation.BindLayoutRes
import me.yangcx.base.sundries.UnbindLayoutException

/**
 * 基础Fragment
 * create by 97457
 * create at 2018/11/04 0004
 */
abstract class FoundationFragment : Fragment() {
    private var isForeground = false

    /**
     * 设置布局
     * create by 97457
     * create at 2018/12/13
     */
    protected open fun getLayoutResId(): Int {
        val annotation = javaClass.getAnnotation(BindLayoutRes::class.java)
        if (annotation != null && annotation.layoutRes > 0) {
            return annotation.layoutRes
        } else {
            throw UnbindLayoutException()
        }
    }

    /**
     * 获取View
     */
    protected open fun createContentView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        try {
            return inflater.inflate(getLayoutResId(), container, false)
        } catch (ex: Exception) {
            throw ex
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        isForeground = true
        return createContentView(inflater, container, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        isForeground = false
    }

    protected fun isForegroundFragment() = isForeground
}