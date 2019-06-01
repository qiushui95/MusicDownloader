package me.yangcx.base.extends

import android.widget.TextView
import androidx.annotation.StringRes

fun TextView.setText(@StringRes res: Int, vararg args: Any) {
    text = context.getString(res, *args)
}