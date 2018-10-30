package br.com.douglas.tvshow.data.network.factory.api

import android.support.annotation.IntRange
import android.util.Log
import com.google.gson.Gson
import okhttp3.ResponseBody
import retrofit2.Response
import java.io.IOException
import java.nio.charset.Charset

class ApiResponse<T> {

    var TAG = ApiResponse::class.java.simpleName

    @IntRange(from = 200, to = 299)
    val code: Int

    val body: T?

    val errorCode: String?

    val errorMessage: String?

    val isSuccessful: Boolean
        get() = code in 200..299

    constructor(error: Throwable) {
        code = 400 or 404 or 500
        body = null
        errorCode = "000"
        errorMessage = error.message
    }

    constructor(response: Response<T>) {
        code = response.code()
        if (response.isSuccessful) {
            body = response.body()
            errorMessage = null
            errorCode = null
        } else {
            var code: String? = "uknow exception"
            var message: String? = "404"
            if (response.errorBody() != null) {
                try {
                    var json = readResponse(response.errorBody()!!)
                    var result = Gson().fromJson(json, ResponseError::class.java)
                    message = result?.message
                    code = result?.code
                } catch (ignored: IOException) {
                    message = "uknow exception"
                    code = "404"
                    //Log.e(TAG, "error while parsing response: $ignored")
                }
            }
            if (message == null || message.trim { it <= ' ' }.isEmpty()) {
                message = response.message()
            }
            errorMessage = message
            errorCode = code
            body = null
        }
    }

    private fun readResponse(responseBody: ResponseBody): String? {
        var json:String? = null
        val UTF8 = Charset.forName("UTF-8")
        try {
            var source = responseBody.source()
            source.request(Long.MAX_VALUE)
            var buffer = source.buffer()
            var charset = UTF8
            var contentType = responseBody.contentType()
            if (contentType != null) {
                charset = contentType.charset(UTF8)
            }
            json = buffer.clone().readString(charset)
        } catch (e: Exception) {
            Log.e("SESSION_EXCEPTION", e.message)
        }
        return json
    }
}
