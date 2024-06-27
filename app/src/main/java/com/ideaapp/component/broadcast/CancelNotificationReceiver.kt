package com.ideaapp.component.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.ideaapp.domain.utils.Arguments
import com.ideaapp.utils.ManagerNotification
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class CancelNotificationReceiver : BroadcastReceiver(), KoinComponent {

    private val notificationManager: ManagerNotification by inject()

    override fun onReceive(context: Context?, intent: Intent?) {
        if (context == null || intent == null) return
        val notificationId =
            intent.getIntExtra(Arguments.ItemId.key, Arguments.ItemId.emptyValue.toInt())

        notificationManager.cancel(notificationId)
    }
}