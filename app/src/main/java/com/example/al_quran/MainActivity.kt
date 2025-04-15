package com.example.al_quran

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.al_quran.ui.theme.surahList.SurahListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Tampilkan SurahListFragment saat pertama kali
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, SurahListFragment())
                .commit()
        }
    }
}
