package com.example.gigih_final2.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gigih_final2.domain.Entity.Entities
import com.example.gigih_final2.domain.UseCase.GetDisasterReportsUseCase
import com.example.gigih_final2.utils.ResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReportViewModel @Inject constructor(
    private val useCase: GetDisasterReportsUseCase
) : ViewModel() {

    val availableProvince = useCase.getAvailableProvince()

    private var _reports: MutableLiveData<ResultState<List<Entities>>> = MutableLiveData()
    val reports: LiveData<ResultState<List<Entities>>> get() = _reports


    init {
        callApi(
            provinceName = null,
            disasterType = null
        )
    }

    fun callApi(provinceName: String?, disasterType: String? = null) {
        viewModelScope.launch {
            useCase.getAllReportData(provinceName, disasterType).collect {
                _reports.value = it
            }
        }
    }


}