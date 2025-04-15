package com.example.al_quran.ui.theme.surahdetail

import android.annotation.SuppressLint
import android.media.AudioManager
import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.al_quran.R
import com.example.al_quran.data.model.Ayah

class AyahAdapter(
    private val arabicList: List<Ayah>,
    private val translationList: List<Ayah>
) : RecyclerView.Adapter<AyahAdapter.AyahViewHolder>() {

    private var mediaPlayer: MediaPlayer? = null
    private var isPlaying = false
    private var currentPlayingPosition = -1

    inner class AyahViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvAyahNumber: TextView = itemView.findViewById(R.id.tvAyahNumber)
        val tvArabic: TextView = itemView.findViewById(R.id.tvArabicAyah)
        val tvTranslation: TextView = itemView.findViewById(R.id.tvTranslationAyah)
        val btnPlayAudio: ImageButton = itemView.findViewById(R.id.imageButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AyahViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_ayah, parent, false)
        return AyahViewHolder(view)
    }

    override fun onBindViewHolder(holder: AyahViewHolder, @SuppressLint("RecyclerView") position: Int) {
        val ayah = arabicList[position]

        holder.tvAyahNumber.text = ayah.numberInSurah.toString()
        holder.tvArabic.text = ayah.text
        holder.tvTranslation.text = translationList.getOrNull(position)?.text ?: ""

        // Atur ikon awal
        holder.btnPlayAudio.setImageResource(
            if (position == currentPlayingPosition && isPlaying) R.drawable.pause_24px
            else R.drawable.play_circle_24px
        )

        holder.btnPlayAudio.setOnClickListener {
            if (position == currentPlayingPosition) {
                if (isPlaying) {
                    mediaPlayer?.pause()
                    isPlaying = false
                    holder.btnPlayAudio.setImageResource(R.drawable.play_circle_24px)
                } else {
                    mediaPlayer?.start()
                    isPlaying = true
                    holder.btnPlayAudio.setImageResource(R.drawable.pause_24px)
                }
            } else {
                mediaPlayer?.release()
                mediaPlayer = MediaPlayer()
                try {
                    mediaPlayer?.apply {
                        setAudioStreamType(AudioManager.STREAM_MUSIC)
                        setDataSource(ayah.audio)
                        prepare()
                        start()
                    }
                    isPlaying = true
                    notifyItemChanged(currentPlayingPosition)
                    currentPlayingPosition = position
                    holder.btnPlayAudio.setImageResource(R.drawable.pause_24px)
                } catch (e: Exception) {
                    e.printStackTrace()
                    Toast.makeText(holder.itemView.context, "Gagal memutar audio", Toast.LENGTH_SHORT).show()
                }

                mediaPlayer?.setOnCompletionListener {
                    isPlaying = false
                    notifyItemChanged(currentPlayingPosition)
                }
            }
        }
    }

    override fun getItemCount() = arabicList.size

    fun releasePlayer() {
        mediaPlayer?.release()
        mediaPlayer = null
    }
}
