package com.example.al_quran.ui.theme.surahDetail

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.al_quran.R
import com.example.al_quran.ui.theme.surahdetail.AyahAdapter

class SurahDetailActivity : ComponentActivity() {

    private lateinit var viewModel: SurahDetailViewModel
    private lateinit var rvAyat: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var tvTitle: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_surah_detail)

        rvAyat = findViewById(R.id.rvAyat)
        progressBar = findViewById(R.id.progressBar)
        tvTitle = findViewById(R.id.tvSurahTitle)

        val surahNumber = intent.getIntExtra("surah_number", 1)
        val surahName = intent.getStringExtra("surah_name") ?: "Surah"

        tvTitle.text = surahName

        viewModel = ViewModelProvider(this)[SurahDetailViewModel::class.java]
        rvAyat.layoutManager = LinearLayoutManager(this)

        viewModel.fetchSurahDetail(surahNumber)

        viewModel.arabicAyah.observe(this) { arabicList ->
            val translationList = viewModel.translationAyah.value ?: emptyList()
            rvAyat.adapter = AyahAdapter(arabicList, translationList)
        }

        viewModel.translationAyah.observe(this) {
            val arabicList = viewModel.arabicAyah.value ?: emptyList()
            rvAyat.adapter = AyahAdapter(arabicList, it)
        }

        viewModel.isLoading.observe(this) {
            progressBar.visibility = if (it) View.VISIBLE else View.GONE
        }

        viewModel.error.observe(this) {
            it?.let { msg ->
                Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
            }
        }
    }
}