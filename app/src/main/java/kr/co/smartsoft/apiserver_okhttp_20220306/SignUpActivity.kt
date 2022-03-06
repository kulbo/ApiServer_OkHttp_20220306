package kr.co.smartsoft.apiserver_okhttp_20220306

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import kr.co.smartsoft.apiserver_okhttp_20220306.databinding.ActivitySignUpBinding
import kr.co.smartsoft.apiserver_okhttp_20220306.utils.ServerUtil
import org.json.JSONObject

class SignUpActivity : BaseActivity() {
    lateinit var binding:ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)
        setupEvents()
        setValues()
    }
    override fun setupEvents() {
        binding.btnSignUp.setOnClickListener {
            val inputEmail = binding.edtEmailId.text.toString()
            val inputPassword = binding.edtPassword.text.toString()
            val inputNickname = binding.edtNickName.text.toString()

            ServerUtil.putRequestSignUp(inputEmail, inputPassword, inputNickname, object : ServerUtil.JsonResponseHandler{
                override fun onResponse(JsonObject: JSONObject) {
                    val code = JsonObject.getInt("code")
                    if (code == 200) {
                        val dataObject = JsonObject.getJSONObject("data")
                        val userObject = dataObject.getJSONObject("user")
                        val nickname = userObject.getString("nick_name")
                        runOnUiThread {
                            Toast.makeText(mContext, "${nickname}님 가입축하드립니다.", Toast.LENGTH_SHORT).show()
                        }
                        Log.d("서버응답", "가입성공")
                    } else {
                        val message = JsonObject.getString("message")
                        runOnUiThread {
                            Toast.makeText(mContext, "에러 : ${message}", Toast.LENGTH_SHORT).show()
                        }
                    }
                }

            })
        }

    }

    override fun setValues() {
    }

}