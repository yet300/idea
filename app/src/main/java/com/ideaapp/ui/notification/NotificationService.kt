package com.ideaapp.ui.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.ideaapp.R

class NotificationService : Service() {

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // Проверяем, был ли предоставлен Intent
        if (intent != null) {
            // Получаем данные о задаче из Intent
            val id = intent.getLongExtra(TASK_ID, -1)
            val taskName = intent.getStringExtra(TASK_NAME_KEY)
            val taskDescription = intent.getStringExtra(TASK_DESCRIPTION_KEY)

            if (taskName != null) {
                // Логика отправки уведомлений
                val notificationManager =
                    getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

                // Создание канала уведомлений (требуется для версии Android 8.0 и выше)
                createNotificationChannel(notificationManager)

                // Создание и отправка уведомления
                val taskNotification = NotificationCompat.Builder(this, CHANNEL_ID)
                    .setContentTitle(taskName)
                    .setContentText(taskDescription)
                    .setSmallIcon(R.drawable.baseline_tips)
                    .setGroup(GROUP_KEY_WORK_TASK)
                    .build()

                // Создание уведомления-заголовка группы
                val groupNotification = NotificationCompat.Builder(this, CHANNEL_ID)
                    .setContentTitle(getString(R.string.Task)) // Заголовок уведомления-заголовка группы
                    .setSmallIcon(R.drawable.baseline_task_24) // Иконка уведомления-заголовка группы
                    .setGroup(GROUP_KEY_WORK_TASK) // Устанавливаем ключ группы
                    .setGroupSummary(true) // Устанавливаем флаг, что это уведомление-заголовок группы


                NotificationManagerCompat.from(this).apply {
                    notificationManager.notify(TASK_GROUP_ID, groupNotification.build())

                    notificationManager.notify(id.toInt(), taskNotification)
                }

                Log.e("Notification id", "id is  $id ")

            } else {
                Log.e("Notification Service", "$taskName or $taskDescription is null")
            }
        }

        // Возвращаем START_NOT_STICKY, чтобы сервис не был восстановлен, если его остановит система
        return START_NOT_STICKY
    }

    // Создание канала уведомлений
    private fun createNotificationChannel(notificationManager: NotificationManager) {
        val channel = NotificationChannel(
            CHANNEL_ID,
            "Task chanel",
            NotificationManager.IMPORTANCE_DEFAULT
        )
        channel.description = "Task Description"
        notificationManager.createNotificationChannel(channel)
    }


    companion object {
        private const val CHANNEL_ID = "notification_channel"

        const val TASK_GROUP_ID = 1
        const val GROUP_KEY_WORK_TASK = "com.android.example.TASK"

        const val TASK_ID = "task_id"
        const val TASK_NAME_KEY = "task_name"
        const val TASK_DESCRIPTION_KEY = "task_description"
    }
}
