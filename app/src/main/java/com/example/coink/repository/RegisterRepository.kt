package com.example.coink.repository

import android.util.Log
import com.example.coink.model.DocumentTypeResponse
import com.example.coink.remote.APIService
import com.example.coink.remote.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RegisterRepository {

    private val apiService = RetrofitClient.instance.create(APIService::class.java)

    suspend fun consultDocumentType(): List<DocumentTypeResponse> {
        return withContext(Dispatchers.IO){
            val response = apiService.consultDocumentType()
                response?.body()!!
        }
    }
}