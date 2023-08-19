package com.example.gigih_final2.presentation.fragment.Setting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.work.ExistingPeriodicWorkPolicy
import com.example.gigih_final2.R
import com.example.gigih_final2.databinding.FragmentSettingBinding
import com.example.gigih_final2.presentation.viewmodel.SettingViewModel
import com.example.gigih_final2.utils.Logger
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import dagger.hilt.android.AndroidEntryPoint
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.gigih_final2.domain.Worker.WaterLevelWorker
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class SettingFragment : Fragment(R.layout.fragment_setting) {

    private var _binding: FragmentSettingBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SettingViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbarSetting.setupWithNavController(findNavController())

        val darkModeStatus = viewModel.isDarkModeActive
        binding.switchDarkMode.isChecked = darkModeStatus

        Logger.e("Before Switch Change: $darkModeStatus")

        binding.switchDarkMode.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                viewModel.activateDarkTheme()
            } else {
                viewModel.deactivateDarkTheme()
            }
        }

        Logger.e("After Switch Change: ${viewModel.isDarkModeActive}")

        (binding.acTvTimePeriod as? MaterialAutoCompleteTextView)?.setSimpleItems(viewModel.displayAvailableTimeIntervals())
        binding.acTvTimePeriod.setText(viewModel.userTimePreference, false)

        binding.acTvTimePeriod.setOnItemClickListener { _, _, position, _ ->
            viewModel.adjustTimePeriod(viewModel.displayAvailableTimeIntervals()[position])
        }

        binding.btnStartWorker.setOnClickListener {
            startWaterLevelWorker()
        }
    }

    private fun startWaterLevelWorker() {
        val workRequest = PeriodicWorkRequestBuilder<WaterLevelWorker>(15, TimeUnit.MINUTES)
            .build()

        WorkManager.getInstance(requireContext()).enqueueUniquePeriodicWork(
            "WaterLevelWorker",
            ExistingPeriodicWorkPolicy.KEEP,
            workRequest
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}