package com.example.gigih_final2.worker

import android.app.NotificationManager
import android.content.Context
import androidx.work.Data
import androidx.work.WorkerParameters
import androidx.work.testing.TestListenableWorkerBuilder
import com.example.gigih_final2.domain.UseCase.WaterLevelUseCase
import com.example.gigih_final2.domain.Worker.WaterLevelWorker
import com.example.gigih_final2.utils.ResultState
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.util.*

import org.robolectric.RobolectricTestRunner


@RunWith(RobolectricTestRunner::class)
class WaterLevelWorkerTest {


    @Mock
    private lateinit var workerParams: WorkerParameters
    @Mock
    private lateinit var context: Context
    @Mock
    private lateinit var notificationManager: NotificationManager
    @Mock
    private lateinit var waterLevelUseCase: WaterLevelUseCase

    private lateinit var worker: WaterLevelWorker

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)

        // Mock the WorkerParameters
        Mockito.`when`(workerParams.inputData).thenReturn(Data.EMPTY)
        Mockito.`when`(workerParams.tags).thenReturn(emptySet())
        Mockito.`when`(workerParams.runAttemptCount).thenReturn(0)
        Mockito.`when`(workerParams.id).thenReturn(UUID.randomUUID())
        Mockito.`when`(workerParams.network).thenReturn(null)
        Mockito.`when`(workerParams.triggeredContentUris).thenReturn(emptyList())
        Mockito.`when`(workerParams.triggeredContentAuthorities).thenReturn(emptyList())

        worker = TestListenableWorkerBuilder<WaterLevelWorker>(context).build()
    }
    @Test
    fun `test doWork with success result`() = runBlocking {
        Mockito.`when`(waterLevelUseCase.checkWaterLevelThreshold()).thenReturn(ResultState.Success(true))

        val result = worker.doWork()

        assert(result == androidx.work.ListenableWorker.Result.success())
    }

    @Test
    fun `test doWork with error result`() = runBlocking {
        val mockException = Exception("Error")
        Mockito.`when`(waterLevelUseCase.checkWaterLevelThreshold()).thenReturn(ResultState.Error(mockException))

        val result = worker.doWork()

        assert(result == androidx.work.ListenableWorker.Result.failure())
    }



}
