package com.utildev.jetpack.data.remote.helper

import android.text.TextUtils
import okhttp3.Interceptor
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import org.json.JSONObject

class HttpInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        if (response.code >= 400) {
            throw Exception(HttpError.getErrorString(response))
        }
        if (response.body != null) {
            val r = response.body!!.string()
            val resultObject = JSONObject()
            if (TextUtils.equals(r, "[]")) {
                /**
                 * Generate a success response.
                 * HTTP/1.1 200 OK
                 * Content-type: application/json
                 * "$random_string"
                 */
                resultObject.put("message", "unknown result")
                return response.newBuilder()
                    .code(200)
                    .protocol(Protocol.HTTP_1_1)
                    .message("OK")
                    .body(resultObject.toString().toResponseBody(response.body!!.contentType()))
                    .build()
            } else if (JSONObject(r).has("error")) {
                /**
                 * Generate an error result.
                 * HTTP/1.1 500 Bad server day
                 * Content-type: application/json
                 * {"message": "unknown error"}
                 */
                resultObject.put("message", "unknown error")
                return response.newBuilder()
                    .code(500)
                    .protocol(Protocol.HTTP_1_1)
                    .message("ERROR")
                    .body(resultObject.toString().toResponseBody(response.body!!.contentType()))
                    .build()
            }
        }
        return response
    }

}