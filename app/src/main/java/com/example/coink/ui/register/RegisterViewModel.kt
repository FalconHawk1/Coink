package com.example.coink.ui.register

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coink.model.DocumentTypeResponse
import com.example.coink.repository.RegisterRepository
import kotlinx.coroutines.launch

class RegisterViewModel: ViewModel() {

    private val registerRepository = RegisterRepository()
    private val _numberText = MutableLiveData<String>()

    private val _documentTypes = MutableLiveData<List<DocumentTypeResponse>>()
    val documentTypes: LiveData<List<DocumentTypeResponse>> get() = _documentTypes

    private val _error:MutableLiveData<Boolean> = MutableLiveData()
    val error: LiveData<Boolean> get() = _error

    var numberTextValue: String?
        get() = _numberText.value
        set(value) {
            _numberText.value = value
            Log.d("TAGIZADO", "logNumberTextChange: $value")
        }

    fun getDocumentType() {
        viewModelScope.launch {
                val response = registerRepository.consultDocumentType()
                _documentTypes.value = response
                Log.d("TAGI", "getDocumentType: ${documentTypes.value}")
        }
    }
}