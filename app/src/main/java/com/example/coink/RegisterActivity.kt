package com.example.coink

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.coink.databinding.ActivityRegisterBinding

class RegisterActivity: AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbarTitle = binding.toolbarTitle
        toolbarTitle.text = "NÚMERO DE CELULAR"
        val toolbar = binding.toolbar
        toolbar.setNavigationOnClickListener {
            var intent =  Intent(this, ChooseLoginRegisterActivity::class.java)
            startActivity(intent)
        }
    }
}