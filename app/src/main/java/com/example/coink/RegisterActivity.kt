package com.example.coink

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.coink.databinding.ActivityRegisterBinding

class RegisterActivity: AppCompatActivity() {

    lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}