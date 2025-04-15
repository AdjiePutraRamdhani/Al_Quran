package com.example.al_quran.ui.theme.surahdetail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.al_quran.R
import com.example.al_quran.data.model.Ayah

class AyahAdapter(
    private val arabicList: List<Ayah>,
    private val translationList: List<Ayah>
) : RecyclerView.Adapter<AyahAdapter.AyahViewHolder>() {

    inner class AyahViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvAyahNumber: TextView = itemView.findViewById(R.id.tvAyahNumber)
        val tvArabic: TextView = itemView.findViewById(R.id.tvArabicAyah)
        val tvTranslation: TextView = itemView.findViewById(R.id.tvTranslationAyah)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AyahViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_ayah, parent, false)
        return AyahViewHolder(view)
    }

    override fun onBindViewHolder(holder: AyahViewHolder, position: Int) {
        val ayah = arabicList[position]
        holder.tvAyahNumber.text = ayah.numberInSurah.toString()
        holder.tvArabic.text = ayah.text
        holder.tvTranslation.text = translationList.getOrNull(position)?.text ?: ""
    }

    override fun getItemCount() = arabicList.size
}