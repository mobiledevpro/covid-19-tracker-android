package com.mobiledevpro.remote.service.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.mobiledevpro.data.LOG_TAG_DEBUG
import com.mobiledevpro.data.NOTIFICATIONS_CHANNEL_ID_MAIN_NOTIFICATIONS
import com.mobiledevpro.data.NOTIFICATIONS_CHANNEL_NAME_MAIN_NOTIFICATIONS
import com.mobiledevpro.remote.R

/**
 * Class for displaying status bar notifications from Firebase (FCM)
 *
 * Created by Dmitriy Chernysh on Mar 31, 2020.
 *
 * http://androiddev.pro
 *
 */
class FirebaseMessagingService : FirebaseMessagingService() {

    private var notificationNumber = 0

    override fun onMessageReceived(msg: RemoteMessage) {
        Log.d(LOG_TAG_DEBUG, "From: ${msg.from}")

        msg.notification?.let {
            Log.d(LOG_TAG_DEBUG, "Message Notification Title: ${it.title}")
            Log.d(LOG_TAG_DEBUG, "Message Notification Body: ${it.body}")
            sendNotification(it.title, it.body)
        }
    }


    private fun sendNotification(title: String?, messageBody: String?) {
        if (title == null && messageBody == null) return

        notificationNumber++

        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notificationBuilder = NotificationCompat.Builder(this, NOTIFICATIONS_CHANNEL_ID_MAIN_NOTIFICATIONS)
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle(title ?: "")
            .setContentText(messageBody ?: "")
            .setAutoCancel(true)
            .setSound(defaultSoundUri)

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Since android Oreo notification channel is needed.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                NOTIFICATIONS_CHANNEL_ID_MAIN_NOTIFICATIONS,
                NOTIFICATIONS_CHANNEL_NAME_MAIN_NOTIFICATIONS,
                NotificationManager.IMPORTANCE_DEFAULT)
            notificationManager.createNotificationChannel(channel)
        }

        notificationManager.notify(notificationNumber, notificationBuilder.build())
    }

}