package kr.co.smartsoft.apiserver_okhttp_20220306

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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
                        Log.d("서버응답", "가입성공")
                    } else {
                        Log.d("서버응답", "가입실패")
                    }
                }

            })
        }

    }

    override fun setValues() {
    }

}