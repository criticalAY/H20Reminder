package com.criticalay.h20reminder.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.criticalay.h20reminder.R
import com.criticalay.h20reminder.databinding.FragmentHistoryBinding
import com.criticalay.h20reminder.databinding.FragmentHomeBinding
import com.criticalay.h20reminder.model.DrinkViewModel
import com.criticalay.h20reminder.ui.fragments.adapters.DrinkListAdapter
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import java.security.KeyStore
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class HistoryFragment : Fragment() {
  // private lateinit var linelist: ArrayList<Entry>
  // lateinit var lineData: LineDataSet
    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!

    private lateinit var mDrinkViewModel  : DrinkViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)

        val adapter = DrinkListAdapter()
        val recyclerView = _binding!!.DrinkLogRv
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        mDrinkViewModel= ViewModelProvider(this)[DrinkViewModel::class.java]

//
       // val today= SimpleDateFormat("dd-MM-yyyy").format(Date())
///////////test commit





        mDrinkViewModel.readAllData.observe(viewLifecycleOwner, androidx.lifecycle.Observer { drinkLog->
            adapter.setData(drinkLog)

        })





        return binding.root
    }


}