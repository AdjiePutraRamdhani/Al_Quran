package com.example.al_quran.data

import com.example.al_quran.data.network.RetrofitInstance

class QuranRepository {
    suspend fun getAllSurahs() = RetrofitInstance.api.getAllSurahs()
    suspend fun getSurahDetail(number: Int) = RetrofitInstance.api.getSurahDetail(number)
}