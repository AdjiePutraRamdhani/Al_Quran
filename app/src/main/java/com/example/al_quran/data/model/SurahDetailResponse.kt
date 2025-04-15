package com.example.al_quran.data.model

data class SurahDetailResponse(
    val data: SurahDetail
)

data class SurahDetail(
    val name: String,
    val number: Int,
    val ayahs: List<Ayah>
)

data class Ayah(
    val numberInSurah: Int,
    val text: String,
    val translationId: String,
    val audio: String? = null
)