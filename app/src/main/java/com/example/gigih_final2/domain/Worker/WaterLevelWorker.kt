package com.example.gigih_final2.domain.Worker

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.gigih_final2.R
import com.example.gigih_final2.domain.UseCase.WaterLevelUseCase
import com.example.gigih_final2.utils.ResultState
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class WaterLevelWorker @AssistedInject constructor(
    @Assisted private val context: Context,
    @Assisted private val workerParams: WorkerParameters,
    private val notificationManager: NotificationManager,
    private val waterLevelUseCase: WaterLevelUseCase
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {
        when (val result = waterLevelUseCase.checkWaterLevelThreshold()) {
            is ResultState.Success -> {
                if (result.data == true) {
                    showNotification(context.getString(R.string.water_level_alert_message))
                }
                return Result.success()
            }
            is ResultState.Error -> {
                return Result.failure()
            }
            else -> return Result.retry()
        }
    }

    private fun showNotification(text: String) {
        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.round_flood_24)
            .setContentTitle(context.getString(R.string.water_level_alert))
            .setContentText(text)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = CHANNEL_NAME
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = text
            }
            notificationManager.createNotificationChannel(channel)
        }

        with(NotificationManagerCompat.from(context)) {
            try {
                notify(NOTIFICATION_ID, builder.build())
            } catch (e: SecurityException) {
            }
        }
    }

    companion object {
        private const val CHANNEL_ID = "WaterLevelChannel"
        private const val CHANNEL_NAME = "Water Level Alerts"
        private const val NOTIFICATION_ID = 1001
    }
}
