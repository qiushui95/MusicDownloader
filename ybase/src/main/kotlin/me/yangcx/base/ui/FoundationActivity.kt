package me.yangcx.base.ui

import android.app.Activity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import me.yangcx.annotation.BindLayoutRes
import me.yangcx.base.sundries.UnbindLayoutException

abstract class FoundationActivity : AppCompatActivity() {
    /**
     * 获取布局文件id
     * create by 97457
     * create at 2018/12/13
     */
    @LayoutRes
    protected open fun getLayoutId(): Int {
        val annotation = javaClass.getAnnotation(BindLayoutRes::class.java)
        if (annotation != null && annotation.layoutRes > 0) {
            return annotation.layoutRes
        } else {
            throw UnbindLayoutException()
        }
    }

    /**
     * 设置布局
     */
    protected open fun setContentView() {
        setContentView(getLayoutId())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView()
        onViewCreated()
        onBindViewListener()
    }


    /**
     * 在UI创建之后初始化
     * create by 97457
     * create at 2018/11/04 0004
     * params description:
     * return description:
     */
    protected abstract fun onViewCreated()

    /**
     * 绑定控件事件
     * create by 97457
     * create at 2018/11/04 0004
     * params description:
     * return description:
     */
    protected abstract fun onBindViewListener()

    /**
     * 隐藏软键盘
     */

    fun hideSoft() {
        currentFocus?.apply {
            val service = this@FoundationActivity.getSystemService(Activity.INPUT_METHOD_SERVICE)
            if (service is InputMethodManager) {
                service.hideSoftInputFromWindow(windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
            }
        }
    }

}