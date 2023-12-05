package com.example.coink.ui.register

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RegisterViewModel: ViewModel() {

    private val _numberText = MutableLiveData<String>()

    var numberTextValue: String?
        get() = _numberText.value
        set(value) {
            _numberText.value = value
            Log.d("TAGIZADO", "logNumberTextChange: $value")
        }
}