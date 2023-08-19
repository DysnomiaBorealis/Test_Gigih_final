package com.example.gigih_final2.worker

import android.app.NotificationManager
import android.content.Context
import androidx.work.WorkerParameters
import com.example.gigih_final2.domain.UseCase.WaterLevelUseCase
import com.example.gigih_final2.domain.Worker.WaterLevelWorker
import com.example.gigih_final2.utils.ResultState
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class WaterLevelWorkerTest {

    @Mock
    private lateinit var mockContext: Context

    @Mock
    private lateinit var mockWorkerParams: WorkerParameters

    @Mock
    private lateinit var mockNotificationManager: NotificationManager

    @Mock
    private lateinit var mockWaterLevelUseCase: WaterLevelUseCase

    private lateinit var worker: WaterLevelWorker

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        worker = WaterLevelWorker(mockContext, mockWorkerParams, mockNotificationManager, mockWaterLevelUseCase)
    }

    @Test
    fun `test doWork with success result`() = runBlocking {
        Mockito.`when`(mockWaterLevelUseCase.checkWaterLevelThreshold()).thenReturn(ResultState.Success(true))

        val result = worker.doWork()

        assert(result == androidx.work.ListenableWorker.Result.success())
    }

    @Test
    fun `test doWork with error result`() = runBlocking {
        val mockException = Exception("Error")
        Mockito.`when`(mockWaterLevelUseCase.checkWaterLevelThreshold()).thenReturn(ResultState.Error(mockException))

        val result = worker.doWork()

        assert(result == androidx.work.ListenableWorker.Result.failure())
    }


}
