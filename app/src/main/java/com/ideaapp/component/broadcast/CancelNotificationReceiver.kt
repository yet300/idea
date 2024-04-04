package com.ideaapp.component.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.ideaapp.utils.ManagerNotification
import com.ideasapp.domain.utils.Arguments
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CancelNotificationReceiver : BroadcastReceiver() {

    @Inject
    lateinit var notificationManager: ManagerNotification
    override fun onReceive(context: Context?, intent: Intent?) {
        if (context == null || intent == null) return
        val notificationId =
            intent.getIntExtra(Arguments.ItemId.key, Arguments.ItemId.emptyValue.toInt())

        notificationManager.cancel(notificationId)
    }
}