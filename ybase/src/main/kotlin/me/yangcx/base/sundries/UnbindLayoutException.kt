package me.yangcx.base.sundries

/**
 * 未绑定布局文件错误
 * create by 97457
 * create at 2018/12/13 0013
 */
class UnbindLayoutException : RuntimeException("请使用@BindLayoutRes绑定布局文件,或重写setContentView方法")