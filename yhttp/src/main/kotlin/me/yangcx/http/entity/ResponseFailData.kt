package me.yangcx.http.entity


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ResponseFailData(
        @Expose
        @SerializedName("status")
        val status: Int,
        @Expose
        @SerializedName("message")
        val message: String
)