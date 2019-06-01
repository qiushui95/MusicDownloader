package me.yangcx.recycler.diff

import androidx.recyclerview.widget.DiffUtil
import me.drakeet.multitype.MultiTypeAdapter

data class DiffResult<T : IEntity<T>>(val list: List<T>, val diffResult: DiffUtil.DiffResult) {
    inline fun <reified A : MultiTypeAdapter> dispatchUpdatesToAdapter(adapter: A) {
        adapter.items = list
        diffResult.dispatchUpdatesTo(adapter)
    }
}