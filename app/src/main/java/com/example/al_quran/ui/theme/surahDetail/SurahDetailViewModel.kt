package com.example.al_quran.ui.theme.surahDetail

import androidx.lifecycle.*
import com.example.al_quran.data.QuranRepository
import com.example.al_quran.data.model.Ayah
import kotlinx.coroutines.launch

class SurahDetailViewModel : ViewModel() {

    private val repository = QuranRepository()

    private val _arabicAyah = MutableLiveData<List<Ayah>>()
    val arabicAyah: LiveData<List<Ayah>> = _arabicAyah

    private val _translationAyah = MutableLiveData<List<Ayah>>()
    val translationAyah: LiveData<List<Ayah>> = _translationAyah

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    fun fetchSurahDetail(surahNumber: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = repository.getSurahDetail(surahNumber)
                if (response.isSuccessful) {
                    val data = response.body()?.data
                    _arabicAyah.value = data?.find { it.edition.identifier == "ar.abdurrahmaansudais" }?.ayahs ?: emptyList()
                    _translationAyah.value = data?.find { it.edition.identifier == "id.indonesian" }?.ayahs ?: emptyList()

                    _error.value = null
                } else {
                    _error.value = "Gagal memuat detail surah."
                }
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }
}