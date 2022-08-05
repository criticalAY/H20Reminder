package com.criticalay.h20reminder.utils

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationBuilderWithBuilderAccessor
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.criticalay.h20reminder.R
import com.criticalay.h20reminder.ui.MainActivity
import com.criticalay.h20reminder.ui.fragments.HomeFragment

class AlarmReciever : BroadcastReceiver() {

    private val RequestCode = 1234
    override fun onReceive(context: Context?, intent: Intent?) {


         val i = Intent(context,MainActivity::class.java)
        intent!!.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        val pendingIntent = PendingIntent.getActivity(context,RequestCode,i,0)
        val builder = NotificationCompat.Builder(context!!,"H2OReminder").setSmallIcon(R.drawable.waterdrop)
            .setContentTitle("H2O REMINDER")
            .setContentText("Did you drink water? If not please have a sip")
            .setAutoCancel(true)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)


        val notificationManager = NotificationManagerCompat.from(context)
        notificationManager.notify(123,builder.build())

    }
}