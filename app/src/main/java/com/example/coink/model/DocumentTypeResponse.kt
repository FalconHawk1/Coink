package com.example.coink.model

import com.google.gson.annotations.SerializedName

data class DocumentTypeResponse (
    @SerializedName("id") var id: Int,
    @SerializedName("notation") var name: String,
    @SerializedName("description") var description: String)