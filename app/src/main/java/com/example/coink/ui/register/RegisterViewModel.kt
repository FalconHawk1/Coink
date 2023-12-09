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
    private val _documentTypeText = MutableLiveData<String>()
    private val _documentNumberText = MutableLiveData<String>()
    private val _documentDateExpeditionText = MutableLiveData<String>()
    private val _documentDateBirthText = MutableLiveData<String>()
    private val _genderText = MutableLiveData<String>()
    private val _emailText = MutableLiveData<String>()
    private val _passwordText = MutableLiveData<String>()

    private val _documentTypes = MutableLiveData<List<DocumentTypeResponse>>()
    val documentTypes: LiveData<List<DocumentTypeResponse>> get() = _documentTypes

    private val _error:MutableLiveData<Boolean> = MutableLiveData()
    val error: LiveData<Boolean> get() = _error

    var numberTextValue: String?
        get() = _numberText.value
        set(value) {
            _numberText.value = value
            Log.d("TAGIZADO", "numberTextValue: ${_numberText.value}")
        }
    var documentTypeTextValue: String?
        get() = _documentTypeText.value
        set(value) {
                _documentTypeText.value = value
            Log.d("TAGIZADO", "documentTypeTextValue: ${_documentTypeText.value}")
        }
    var documentNumberTextValue: String?
        get() = _documentNumberText.value
        set(value) {
                _documentNumberText.value = value
            Log.d("TAGIZADO", "documentNumberTextValue: ${_documentNumberText.value}")
        }
    var documentDateExpeditionTextValue: String?
        get() = _documentDateExpeditionText.value
        set(value) {
                _documentDateExpeditionText.value = value
            Log.d("TAGIZADO", "documentDateExpeditionTextValue: ${_documentDateExpeditionText.value}")
        }
    var documentDateBirthTextValue: String?
        get() = _documentDateBirthText.value
        set(value) {
                _documentDateBirthText.value = value
            Log.d("TAGIZADO", "documentDateBirthTextValue: ${_documentDateBirthText.value}")
        }
    var genderTextValue: String?
        get() = _genderText.value
        set(value) {
                _genderText.value = value
            Log.d("TAGIZADO", "documentDateBirthTextValue: ${_genderText.value}")
        }
    var emailTextValue: String?
        get() = _emailText.value
        set(value) {
                _emailText.value = value
            Log.d("TAGIZADO", "emailTextValue: ${_emailText.value}")
        }
    var passwordTextValue: String?
        get() = _passwordText.value
        set(value) {
                _passwordText.value = value
            Log.d("TAGIZADO", "passwordTextValue: ${_passwordText.value}")
        }

    fun getDocumentType() {
        viewModelScope.launch {
                registerRepository.consultDocumentType({
                    _documentTypes.postValue(it)
                },{
                    _error.postValue(it)
                })
        }
    }
}