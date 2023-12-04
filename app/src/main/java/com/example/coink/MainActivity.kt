package com.example.coink

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.coink.databinding.ActivityMainBinding

class MainActivity: AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        goToHomeActivity()
    }

    private fun goToHomeActivity(){
        startActivity(Intent(this@MainActivity, ChooseLoginRegisterActivity::class.java))
        finish()
    }
}