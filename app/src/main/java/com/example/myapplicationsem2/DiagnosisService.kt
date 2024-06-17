package com.example.myapplicationsem2

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface DiagnosisService {
    @GET("diagnose")
    fun getDiagnosis(
        @Query("symptoms") symptoms: String
    ): Call<DiagnosisResponse>
}
