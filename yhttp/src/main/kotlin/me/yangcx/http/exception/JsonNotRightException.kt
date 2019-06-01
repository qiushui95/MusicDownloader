package me.yangcx.http.exception

import com.google.gson.JsonElement

class JsonNotRightException(val jsonElement: JsonElement) : Exception("Gson解析失败")