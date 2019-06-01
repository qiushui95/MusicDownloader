package me.yangcx.recycler.live

import androidx.lifecycle.MediatorLiveData
import androidx.recyclerview.widget.DiffUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import me.yangcx.recycler.diff.DiffCallback
import me.yangcx.recycler.diff.DiffResult
import me.yangcx.recycler.diff.IEntity

class DiffMediatorLiveData<T : IEntity<T>>(private val viewModelScope: CoroutineScope) : MediatorLiveData<DiffResult<T>>() {

    fun getCurrentList() = value?.list ?: listOf()

    fun setCurrent(list: List<T>) {
        diff(list)
    }

    fun clear() {
        setCurrent(listOf())
    }

    fun hasData() = getCurrentList().isNotEmpty()

    @Deprecated("", level = DeprecationLevel.HIDDEN)
    override fun postValue(value: DiffResult<T>?) {

    }

    @Deprecated("", level = DeprecationLevel.HIDDEN)
    override fun setValue(value: DiffResult<T>?) {

    }

    private fun diff(newList: List<T>) {
        viewModelScope.launch(Dispatchers.IO) {
            val sortedList = newList.sortedBy {
                it.getSort()
            }
            val oldList = value?.list ?: listOf()
            val result = DiffUtil.calculateDiff(DiffCallback(oldList, sortedList), true)
            launch(Dispatchers.Main) {
                super.setValue(DiffResult(sortedList.map {
                    it.copySelf()
                }, result))
            }
        }
    }
}