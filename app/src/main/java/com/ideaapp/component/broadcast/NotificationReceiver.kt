package com.ideaapp.component.broadcast


import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.ideaapp.utils.ManagerNotification
import com.ideaapp.utils.postTaskNotification
import com.ideasapp.domain.utils.Arguments
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NotificationReceiver : BroadcastReceiver() {

    @Inject
    lateinit var notificationManager: ManagerNotification
    override fun onReceive(context: Context?, intent: Intent?) {
        if (context == null || intent == null) return

        val taskName = intent.getStringExtra(Arguments.Name.key)
        val taskDescription = intent.getStringExtra(Arguments.Description.key)
        val reminderId = intent.getLongExtra(Arguments.ReminderId.key, -1)
        if (taskName != null) {
            notificationManager.postTaskNotification(
                name = taskName,
                description = taskDescription ,
                pendingIntent = pendingIntent(
                    context = context,
                    id = reminderId.toInt(),
                    intent = intent
                )
            )

            Log.e("Notification id", "id is  $reminderId, $taskName and $taskDescription ")

        } else {
            Log.e("Notification Broadcast", "$taskName and $taskDescription or  is null")
        }
    }

    private fun pendingIntent(
        id: Int,
        intent: Intent,
        context: Context
    ): PendingIntent = PendingIntent.getBroadcast(
        context,
        id,
        intent,
        PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
    )
}