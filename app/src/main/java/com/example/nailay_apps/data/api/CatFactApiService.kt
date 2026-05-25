package com.example.nailay_apps.data.api

import com.example.nailay_apps.data.model.CatFactModel
import retrofit2.http.GET

interface CatFactApiService {
    @GET("fact")
    suspend fun getCatFact(): CatFactModel
}