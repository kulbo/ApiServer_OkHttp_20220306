package kr.co.smartsoft.apiserver_okhttp_20220306.utils

import okhttp3.*
import java.io.IOException

class ServerUtil {

    companion object {
        val BASE_URL = "http://54.180.52.26"
        val EMAIL = "email"
        val PASSWORD = "password"

        fun postRequestLogin(id:String, pw:String) {

            val urlString = "${BASE_URL}/user"

            val formData = FormBody.Builder()
                .add(EMAIL, id)
                .add(PASSWORD, pw)
                .build()

            val request = Request.Builder()
                .url(BASE_URL)
                .post(formData)
                .build()

            val client = OkHttpClient()

//            OkHttpClient 객체를 이용 -> 서버에 로그인 기능 실제 호출
//            호출을 했으면 서버가 수행할 결과를 받아서 처리
//            => 서버에 다녀와서 할 일을 등록 : enqueue(Callback)
            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {

                }

                override fun onResponse(call: Call, response: Response) {
                }

            } )


        }
    }
}