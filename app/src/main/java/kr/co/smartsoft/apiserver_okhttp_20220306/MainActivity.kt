package kr.co.smartsoft.apiserver_okhttp_20220306

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import kr.co.smartsoft.apiserver_okhttp_20220306.databinding.ActivityMainBinding
import kr.co.smartsoft.apiserver_okhttp_20220306.utils.ServerUtil

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setupEvents()
        setValues()

    }

    fun setupEvents() {
        val id = binding.edtId.toString()
        val password = binding.edtPassword.toString()

        binding.btnLogIn.setOnClickListener {
            ServerUtil.postRequestLogin(id, password)
        }
    }

    fun setValues() {

    }
}