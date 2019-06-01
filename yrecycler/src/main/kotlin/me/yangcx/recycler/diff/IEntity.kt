package me.yangcx.recycler.diff

/**
 * create by 97457
 * create at 2019/03/13 0013
 */
interface IEntity<T : IEntity<T>> {
    /***
     * 复制自身,方便使用DiffUtils
     * create by 97457
     * create at 2019/03/13
     */
    fun copySelf(): T

    /**
     * 经过isItemSame()检查为同一对象之后，调用此方法检查对象内容是否相同
     * create at 2018/11/29
     * @param other 另一元素
     * @return true->俩元素内容完全相同
     * @return false->俩元素内容有差异
     */
    fun isContentSame(other: T): Boolean {
        return true
    }

    /**
     * 经过isContentSame()检查对象内容有差异之后,调用获取新旧元素具体差异内容
     * create by 97457
     * create at 2018/11/29
     * @param other 另一元素
     * @param changeList 差异内容列表
     */
    fun getChangeList(other: T, changeList: MutableList<String>) {

    }

    /**
     * 获取排序索引
     * @return 排序位次,值越大,越靠前
     */
    fun getSort(): Int = 0
}