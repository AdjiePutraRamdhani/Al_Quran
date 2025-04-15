package com.example.al_quran.data.model

data class SurahDetailEditionResponse(
    val data: List<SurahDetailEdition>
)

data class SurahDetailEdition(
    val name: String,
    val number: Int,
    val edition: Edition,
    val ayahs: List<Ayah>
)

data class Edition(
    val identifier: String,
    val language: String
)