package com.example.gigih_final2.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.CameraUpdateFactory
import com.example.gigih_final2.R
import com.example.gigih_final2.databinding.FragmentReportBinding
import com.example.gigih_final2.domain.Entity.FloodEntity
import com.example.gigih_final2.presentation.viewmodel.ReportViewModel
import com.example.gigih_final2.utils.ResultState
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReportFragment : Fragment(), OnMapReadyCallback {

    private var _binding: FragmentReportBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ReportViewModel by viewModels()
    private lateinit var reportAdapter: ReportAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentReportBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupObservers()
        setupSearch()
        setupDisasterFilter()
        setupRefreshButton()
        setupGoogleMaps()
    }

    private fun setupRecyclerView() {
        reportAdapter = ReportAdapter(listOf())
        binding.rvReportList.adapter = reportAdapter
    }

    private fun setupObservers() {
        viewModel.reports.observe(viewLifecycleOwner) { resultState ->
            when (resultState) {
                is ResultState.Loading -> {
                    binding.loadingReport.visibility = View.VISIBLE
                }
                is ResultState.Success -> {
                    binding.loadingReport.visibility = View.GONE
                    resultState.data?.let { reportAdapter.updateData(it) }
                }
                is ResultState.Error -> {
                    binding.loadingReport.visibility = View.GONE
                    Toast.makeText(requireContext(), resultState.message, Toast.LENGTH_SHORT).show()
                }
                else -> {}
            }
        }
    }

    private fun setupSearch() {
        val autoCompleteTextView = binding.acTvSearchReport.editText as? AutoCompleteTextView
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, viewModel.availableProvince)
        autoCompleteTextView?.setAdapter(adapter)
        autoCompleteTextView?.setOnItemClickListener { _, _, position, _ ->
            val selectedProvince = autoCompleteTextView.adapter.getItem(position) as String
            viewModel.callApi(provinceName = selectedProvince)
            Toast.makeText(requireContext(), "Fetching reports for $selectedProvince", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupDisasterFilter() {
        binding.rgReport.setOnCheckedChangeListener { _, checkedId ->
            val disasterType = when (checkedId) {
                R.id.rbReportFlood -> "Flood"
                R.id.rbReportEarthquake -> "Earthquake"
                R.id.rbReportFire -> "Fire"
                R.id.rbReportHaze -> "Haze"
                R.id.rbReportWind -> "Wind"
                R.id.rbReportVolcano -> "Volcano"
                else -> null
            }
            viewModel.callApi(provinceName = null, disasterType = disasterType)
            Toast.makeText(requireContext(), "Filtering reports by $disasterType", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupRefreshButton() {
        binding.btnReportRefresh.setOnClickListener {
            viewModel.callApi(provinceName = null, disasterType = null)
            Toast.makeText(requireContext(), "Refreshing reports", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupGoogleMaps() {
        val mapFragment = childFragmentManager.findFragmentById(R.id.mapFragment) as? SupportMapFragment
        mapFragment?.getMapAsync(this)
    }

    override fun onMapReady(maps: GoogleMap) {
        viewModel.reports.observe(viewLifecycleOwner) {
            when (it) {
                is ResultState.Error -> {
                    //already handled in reports
                }

                is ResultState.Loading -> {
                    //already handled in reports
                }

                is ResultState.Idle -> {
                    // Handle the idle state if needed
                }

                is ResultState.Success -> {
                    maps.clear()

                    val reports = it.data ?: return@observe
                    val coordinates = reports.getOrNull(0)?.coordinates ?: return@observe
                    val latLng = LatLng(coordinates.lat, coordinates.lng)
                    maps.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 8f))

                    reports.forEach { data ->
                        var snippet = "Disaster : ${data.disasterType}"
                        val position = LatLng(data.coordinates.lat, data.coordinates.lng)

                        if (data is FloodEntity) {
                            snippet += ", Depth : ${data.floodDepth}"
                        }

                        val markerOpt = MarkerOptions()
                            .position(position)
                            .title(data.title)
                            .snippet(snippet)

                        maps.addMarker(markerOpt)
                    }
                }
            }
        }
    }

}
