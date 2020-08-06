package com.utildev.jetpack.data.remote.helper

import android.text.TextUtils
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

class HttpInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        if (response.code >= 400) {
            throw Exception(HttpError.getErrorString(response))
        }
        if (response.body != null) {
            var r = response.body!!.string()
            if (TextUtils.equals(r, "[]")) {
                //region Description
                r = "{\n" + "  \"message\": successful" + "\n}"
                //endregion
            }
            try {
                val rootObject = JSONObject(r)
                if (rootObject.has("error")) {
                    throw java.lang.Exception(rootObject.getString("error"))
                } else {
                    return response.newBuilder()
                        .message("successful")
                        .body(r.toResponseBody(response.body!!.contentType()))
                        .build()
                }
            } catch (e: JSONException) {
                throw IOException(e.message)
            }
        }
        return response
    }

}