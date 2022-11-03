package com.example.weather.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weather.MainActivity
import com.example.weather.databinding.FragmentWeatherBinding
import com.example.weather.viewmodel.WeatherViewModel

class WeatherFragment : Fragment() {

    private var _binding: FragmentWeatherBinding? = null
    private val binding get() = _binding!!
    private val viewModel: WeatherViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentWeatherBinding.inflate(inflater, container, false)
        viewModel.setParam(requireArguments().getSerializable("city") as String)

        binding.weatherRecycler.apply {
            adapter = viewModel.weatherAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        initObservers()

        return binding.root
    }

    private fun initObservers() {
        viewModel.param.observe(viewLifecycleOwner) { param ->
            if (param != null) {
                (activity as MainActivity).supportActionBar?.title = param
                viewModel.getWeather()
            }
        }

        viewModel.weather.observe(viewLifecycleOwner) { weather ->
            if (weather != null) {
                binding.noDataText.visibility = View.GONE
                viewModel.setAdapter()
                viewModel.saveWeatherData(requireContext())
                binding.progressbar.visibility = View.GONE
            } else {
                viewModel.loadWeatherData(requireContext())
                viewModel.setAdapter()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}