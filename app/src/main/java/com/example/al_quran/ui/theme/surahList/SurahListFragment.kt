package com.example.al_quran.ui.theme.surahList

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.al_quran.R
import com.example.al_quran.ui.theme.surahDetail.SurahDetailActivity

class SurahListFragment : Fragment() {

    private lateinit var viewModel: SurahListViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_surah_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = view.findViewById(R.id.rvSurahList)
        progressBar = view.findViewById(R.id.progressBar)

        viewModel = ViewModelProvider(this)[SurahListViewModel::class.java]

        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.fetchSurahList()

        viewModel.surahList.observe(viewLifecycleOwner) { list ->
            recyclerView.adapter = SurahAdapter(list) { surah ->
                val intent = Intent(requireContext(), SurahDetailActivity::class.java)
                intent.putExtra("surah_number", surah.number)
                intent.putExtra("surah_name", surah.englishName)
                startActivity(intent)
            }
        }

        viewModel.isLoading.observe(viewLifecycleOwner) {
            progressBar.visibility = if (it) View.VISIBLE else View.GONE
        }

        viewModel.error.observe(viewLifecycleOwner) {
            it?.let { msg ->
                Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
            }
        }
    }
}