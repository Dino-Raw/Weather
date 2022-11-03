package com.example.weather.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weather.databinding.FragmentCitiesBinding
import com.example.weather.viewmodel.CitiesViewModel


class CitiesFragment : Fragment() {

    private var _binding: FragmentCitiesBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CitiesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCitiesBinding.inflate(inflater, container, false)

        binding.citiesRecycler.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = viewModel.citiesAdapter
        }

        // инициализация наблюдателей
        initObservers()

        return binding.root
    }

    private fun initObservers() {
        // как только получили данные о городах, заполняем список
        viewModel.cities.observe(viewLifecycleOwner) { cities ->
            if (cities != null) viewModel.setAdapter()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}