package me.yangcx.http.converter

import com.google.gson.Gson
import com.google.gson.JsonIOException
import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonToken
import okhttp3.ResponseBody
import retrofit2.Converter

class GsonResponseBodyConverter<T>(private val gson: Gson, private val adapter: TypeAdapter<T>) : Converter<ResponseBody, T> {
    override fun convert(value: ResponseBody): T? {
        try {
            val jsonReader = gson.newJsonReader(value.charStream())
            val result = adapter.read(jsonReader)
            if (jsonReader.peek() != JsonToken.END_DOCUMENT) {
                throw JsonIOException("JSON document was not fully consumed.")
            }
            return result
        } catch (ex: Exception) {
            throw ex
        } finally {
            value.close()
        }
    }
}