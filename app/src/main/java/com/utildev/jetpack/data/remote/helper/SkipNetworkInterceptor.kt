package com.utildev.jetpack.data.remote.helper

import com.google.gson.Gson
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.json.JSONObject

class SkipNetworkInterceptor : Interceptor {
    private var lastResult: String = ""
    private var attempts = 0
    val gson = Gson()

    /**
     * Stop the request from actually going out to the network.
     */
    override fun intercept(chain: Interceptor.Chain): Response {
        TODO("Not yet implemented")
    }

    /**
     * Return true if this request should error.
     */
    private fun wantRandomError() = attempts++ % 5 == 0

    /**
     * Pretend to "block" interacting with the network.
     *
     * Really: sleep for 500ms.
     */
    private fun pretendToBlockForNetworkRequest() = Thread.sleep(500)

    /**
     * Generate an error result.
     *
     * ```
     * HTTP/1.1 500 Bad server day
     * Content-type: application/json
     *
     * {"cause": "not sure"}
     * ```
     */
    private fun makeErrorResult(request: Request): Response {
        val jsonObject = JSONObject()
        jsonObject.put("cause", "not sure")
        return Response.Builder()
            .code(500)
            .request(request)
            .protocol(Protocol.HTTP_1_1)
            .message("Bad server day")
            .body(
                jsonObject.toString()
                    .toResponseBody(
                        "application/json; charset=utf-8"
                            .toMediaTypeOrNull()
                    )
            )
            .build()
    }

    /**
     * Generate a success response.
     *
     * ```
     * HTTP/1.1 200 OKp
     * Content-type: application/json
     *
     * "$random_string"
     * ```
     */
//    private fun makeOkResult(request: Request): Response {
//        var nextResult = lastResult
//        while (nextResult == lastResult) {
//            nextResult = FAKE_RESULTS.random()
//        }
//        lastResult = nextResult
//        return Response.Builder()
//            .code(200)
//            .request(request)
//            .protocol(Protocol.HTTP_1_1)
//            .message("OK")
//            .body(ResponseBody.create(
//                MediaType.get("application/json"),
//                gson.toJson(nextResult)))
//            .build()
//    }
}