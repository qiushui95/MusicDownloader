package me.yangcx.http.state

import me.yangcx.http.entity.ResponseFailData

/**
 * create by 97457
 * create at 2019/03/20 0020
 */
sealed class RequestState(val success: Boolean, val isComplete: Boolean) {

    inline fun <reified T> runSuccessData(block: (data: T) -> Unit) {
        if (this is SuccessWithData && data is T) {
            block(data)
        }
    }

    inline fun <reified T : Any> getSuccessData() = ((this as? SuccessWithData)?.data) as? T

    data class Error(val throwable: Throwable) : RequestState(false, true)

    data class InputError(val message: String) : RequestState(false, true)

    data class Fail(val data: ResponseFailData) : RequestState(false, true)

    open class Success(val message: String? = null) : RequestState(true, true)

    class SuccessWithData(val data: Any, message: String? = null) : Success(message)

    object Loading : RequestState(false, false)

    object NoMoreData : Success()

    object EmptyData : Success()

    data class Progress(val currentProgress: Long, val totalProgress: Long) : RequestState(false, false) {
        fun getPercentage() = currentProgress * 1f / totalProgress
        fun getPercentageText() = String.format("%.2f%%", currentProgress * 1f / totalProgress)
    }
}