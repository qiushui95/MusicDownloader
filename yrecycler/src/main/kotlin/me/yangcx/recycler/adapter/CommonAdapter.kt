package me.yangcx.recycler.adapter

import androidx.annotation.CheckResult
import me.drakeet.multitype.MultiTypeAdapter
import me.drakeet.multitype.OneToManyFlow
import me.yangcx.recycler.binder.CommonBinder
import me.yangcx.recycler.holder.BaseHolder
import me.yangcx.recycler.diff.IEntity
import kotlin.reflect.KClass

/**
 * create by 97457
 * create at 2019/03/13 0013
 */
class CommonAdapter : MultiTypeAdapter() {

    inline fun <reified T : Any, reified VH : BaseHolder<T>> registerForSingle(holderClz: KClass<VH>) {
        super.register(T::class.java, CommonBinder(holderClz))
    }

    @CheckResult
    inline fun <reified T : IEntity<T>> registerForMultiple(clz: KClass<T>): OneToManyFlow<T> {
        return super.register(clz.java)
    }
}