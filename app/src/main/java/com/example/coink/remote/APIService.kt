package com.example.coink.remote

import com.example.coink.model.DocumentTypeResponse
import com.example.coink.model.GendersResponse
import retrofit2.Response
import retrofit2.http.GET

interface APIService {

    @GET("signup/documentTypes")
    suspend fun consultDocumentType(): Response<List<DocumentTypeResponse>?>?

    @GET("signup/genders")
    suspend fun consultGenders(): Response<GendersResponse?>?
}