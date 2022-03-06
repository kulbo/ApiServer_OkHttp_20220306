package kr.co.smartsoft.apiserver_okhttp_20220306

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import kr.co.smartsoft.apiserver_okhttp_20220306.databinding.ActivityMainBinding
import kr.co.smartsoft.apiserver_okhttp_20220306.utils.ServerUtil
import org.json.JSONObject

class MainActivity : BaseActivity() {

    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setupEvents()
        setValues()

    }

    override fun setupEvents() {
        binding.btnSignUp.setOnClickListener {
            val myIntent = Intent(mContext, SignUpActivity::class.java)
            startActivity(myIntent)
        }

        binding.btnLogIn.setOnClickListener {
            val id = binding.edtId.toString()
            val password = binding.edtPassword.toString()

            ServerUtil.postRequestLogin(id, password, object : ServerUtil.JsonResponseHandler{
                override fun onResponse(JsonObject: JSONObject) {
                    val code = JsonObject.getInt("code")
                    if (code == 200) {
                        runOnUiThread {
                            Toast.makeText(mContext, "로그인 성공", Toast.LENGTH_SHORT).show()
                        }
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