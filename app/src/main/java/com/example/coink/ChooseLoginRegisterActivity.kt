package com.example.coink

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.coink.databinding.ActivityChooseLoginRegisterBinding

class ChooseLoginRegisterActivity: AppCompatActivity() {

    private lateinit var binding: ActivityChooseLoginRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChooseLoginRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.registerButton.setOnClickListener {
            goToRegisterActivity()
        }
    }

    private fun goToRegisterActivity(){
        startActivity(Intent(this@ChooseLoginRegisterActivity, RegisterActivity::class.java))
        finish()
    }
}