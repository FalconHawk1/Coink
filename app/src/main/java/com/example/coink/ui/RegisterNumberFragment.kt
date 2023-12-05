package com.example.coink.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.coink.R
import com.example.coink.databinding.FragmentRegisterNumberBinding

class RegisterNumberFragment: Fragment() {

    lateinit var binding: FragmentRegisterNumberBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterNumberBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.one.setOnClickListener {
            handleNumberButtonClick("1")
            checkTextView()
        }
        binding.two.setOnClickListener {
            handleNumberButtonClick("2")
            checkTextView()
        }
        binding.three.setOnClickListener {
            handleNumberButtonClick("3")
            checkTextView()
        }
        binding.four.setOnClickListener {
            handleNumberButtonClick("4")
            checkTextView()
        }
        binding.five.setOnClickListener {
            handleNumberButtonClick("5")
            checkTextView()
        }
        binding.six.setOnClickListener {
            handleNumberButtonClick("6")
            checkTextView()
        }
        binding.seven.setOnClickListener {
            handleNumberButtonClick("7")
            checkTextView()
        }
        binding.eight.setOnClickListener {
            handleNumberButtonClick("8")
            checkTextView()
        }
        binding.nine.setOnClickListener {
            handleNumberButtonClick("9")
            checkTextView()
        }
        binding.zero.setOnClickListener {
            handleNumberButtonClick("0")
            checkTextView()
        }
        binding.erase.setOnClickListener {
            val number = binding.txtNumber.text.toString()
            if (number.isNotEmpty()) {
                binding.txtNumber.text = number.substring(0, number.length - 1)
            }
            checkTextView()
        }
        binding.ok.setOnClickListener {
            Toast.makeText(context, "${binding.txtNumber.text}", Toast.LENGTH_SHORT).show()
        }
    }
    private fun handleNumberButtonClick(digit: String) {
        // Get the current text
        var currentText = binding.txtNumber.text.toString()

        // Append the digit to the existing text of txtNumber
        if (currentText.length == 2) {
            // If exactly 3 characters, append the digit and a space
            currentText += "$digit "
            binding.txtNumber.text = currentText
        } else {
            // If less than 3 characters, just append the digit
            binding.txtNumber.text = currentText + digit
        }
    }
    private fun checkTextView() {
        val number = binding.txtNumber.text.toString()
        if (number.length > 10) {
            binding.ok.isEnabled = true
            binding.ok.setCompoundDrawablesWithIntrinsicBounds(
                0,
                R.drawable.baseline_check_green_circle_24,
                0,
                0
            )
        } else {
            binding.ok.isEnabled = false
            binding.ok.setCompoundDrawablesWithIntrinsicBounds(
                0,
                R.drawable.baseline_check_circle_24,
                0,
                0
            )
        }
    }
}