package com.example.al_quran.data.network

import com.example.al_quran.data.model.SurahResponse
import com.example.al_quran.data.model.SurahDetailEditionResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface QuranApiService {
    @GET("v1/surah")
    suspend fun getAllSurahs(): Response<SurahResponse>

    @GET("v1/surah/{number}/editions/ar.abdurrahmaansudais,id.indonesian")
    suspend fun getSurahDetail(
        @Path("number") number: Int
    ): Response<SurahDetailEditionResponse>
}