package me.yangcx.recycler.live

import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.DiffUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import me.yangcx.recycler.diff.DiffCallback
import me.yangcx.recycler.diff.DiffResult
import me.yangcx.recycler.diff.IEntity

class DiffLiveData<T : IEntity<T>>(private val viewModelScope: CoroutineScope) : LiveData<DiffResult<T>>() {
    fun getCurrentList() = value?.list ?: listOf()

    fun setCurrent(list: List<T>) {
        diff(list)
    }

    fun clear() {
        setCurrent(listOf())
    }

    fun remove(list: List<T>) {
        setCurrent(getCurrentList().filter { filter ->
            list.none {
                it == filter
            }
        })
    }

    fun remove(index: Int) {
        getCurrentList().getOrNull(index)
                ?.apply {
                    remove(listOf(this))
                }
    }

    fun remove(value: T) {
        remove(listOf(value))
    }

    fun insertAll(list: List<T>, index: Int = getCurrentList().size) {
        val mutableList = getCurrentList().toMutableList()
        mutableList.addAll(index, list)
        setCurrent(mutableList)
    }

    fun insert(value: T, index: Int = getCurrentList().size) {
        insertAll(listOf(value), index)
    }

    fun hasData() = getCurrentList().isNotEmpty()


    private fun diff(newList: List<T>) {
        viewModelScope.launch(Dispatchers.IO) {
            val sortedList = newList.sortedBy {
                it.getSort()
            }
            val oldList = getCurrentList()
            val result = DiffUtil.calculateDiff(DiffCallback(oldList, sortedList), true)
            postValue(DiffResult(sortedList.map {
                it.copySelf()
            }, result))
        }
    }
}