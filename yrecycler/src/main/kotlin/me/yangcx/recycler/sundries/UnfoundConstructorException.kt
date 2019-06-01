package me.yangcx.recycler.sundries

/**
 * create by 97457
 * create at 2019/03/13 0013
 */
class UnfoundConstructorException(clzName: String?) : RuntimeException("未发现${clzName}的构造函数,请确保其构造函数未被设置为私有")