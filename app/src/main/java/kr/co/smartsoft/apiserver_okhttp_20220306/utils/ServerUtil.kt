package kr.co.smartsoft.apiserver_okhttp_20220306.utils

import android.util.Log
import okhttp3.*
import org.json.JSONObject
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
                .url(urlString)
                .post(formData)
                .build()

            val client = OkHttpClient()

//            OkHttpClient 객체를 이용 -> 서버에 로그인 기능 실제 호출
//            호출을 했으면 서버가 수행할 결과를 받아서 처리
//            => 서버에 다녀와서 할 일을 등록 : enqueue(Callback)
            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
//                인터넷의 연결이 안되어서 또는 서버 다운 등으로  정상적인 연결이 안된경우
                }

//                인터넷은 연결되어서 응답이 정상적으로 온 경우
                override fun onResponse(call: Call, response: Response) {
                    val bodyString = response.body!!.string()
//                  응답의 본문을 string으로 변환하면 json 적용된 상태
    
                    val jsonObj = JSONObject(bodyString)
                    Log.d("서버응답 : ", jsonObj.toString())

                }

            } )


        }
    }
}