package me.yangcx.recycler.diff

import androidx.recyclerview.widget.DiffUtil

/**
 * create by 97457
 * create at 2019/03/13 0013
 */
class DiffCallback<T : IEntity<T>>(private val oldList: List<T>, private val newList: List<T>) : DiffUtil.Callback() {

    private fun getOldItem(position: Int) = oldList[position]

    private fun getNewItem(position: Int) = newList[position]

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return getOldItem(oldItemPosition) == getNewItem(newItemPosition)
    }

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = getOldItem(oldItemPosition)
        val newItem = getNewItem(newItemPosition)
        return oldItem.isContentSame(newItem)
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        val oldItem = getOldItem(oldItemPosition)
        val newItem = getNewItem(newItemPosition)
        val list = mutableListOf<String>()
        oldItem.getChangeList(newItem, list)
        return list
    }
}