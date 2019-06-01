package me.yangcx.recycler.extends

import androidx.annotation.CheckResult
import me.drakeet.multitype.OneToManyEndpoint
import me.drakeet.multitype.OneToManyFlow
import me.yangcx.recycler.binder.CommonBinder
import me.yangcx.recycler.holder.BaseHolder
import kotlin.reflect.KClass

@CheckResult
inline fun <reified T : Any, reified VH : BaseHolder<T>> OneToManyFlow<T>.to(vararg holderClz: KClass<out VH>): OneToManyEndpoint<T> {
    val binderList = holderClz.map {
        CommonBinder(it)
    }
    return to(*Array(binderList.size) {
        binderList[it]
    })
}