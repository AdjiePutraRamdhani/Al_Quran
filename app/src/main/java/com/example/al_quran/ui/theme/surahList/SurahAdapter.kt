package com.example.al_quran.ui.theme.surahList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.al_quran.R
import com.example.al_quran.data.model.Surah

class SurahAdapter(
    private val list: List<Surah>,
    private val onItemClick: (Surah) -> Unit
) : RecyclerView.Adapter<SurahAdapter.SurahViewHolder>() {

    inner class SurahViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val surahName: TextView = itemView.findViewById(R.id.tvSurahName)
        val surahNumber: TextView = itemView.findViewById(R.id.tvSurahNumber)
        val surahAyahCount: TextView = itemView.findViewById(R.id.tvAyahCount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SurahViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_surah, parent, false)
        return SurahViewHolder(view)
    }

    override fun onBindViewHolder(holder: SurahViewHolder, position: Int) {
        val surah = list[position]
        holder.surahName.text = surah.englishName
        holder.surahNumber.text = "Surah ${surah.number}"
        holder.surahAyahCount.text = "${surah.numberOfAyahs} Ayat"
        holder.itemView.setOnClickListener {
            onItemClick(surah)
        }
    }

    override fun getItemCount() = list.size
}