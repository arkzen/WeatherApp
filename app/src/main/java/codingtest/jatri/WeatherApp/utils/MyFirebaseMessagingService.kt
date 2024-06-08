package codingtest.jatri.WeatherApp.utils

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.provider.Settings
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import codingtest.jatri.WeatherApp.R
import codingtest.jatri.WeatherApp.ui.MainActivity
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import kotlin.random.Random

class MyFirebaseMessagingService : FirebaseMessagingService() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        remoteMessage.notification?.let {
            showNotification(this,it.title ?: "Weather Update", it.body ?: "Check the latest weather update.",remoteMessage.notification?.channelId.toString(),
                Random.nextInt())


        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun showNotification(
        context: Context,
        title: String,
        message: String,
        channelId: String,
        notificationId: Int,
        shouldDisplayAlert: Boolean = false,
        requestId: Int = 0,
    ) {
        val notificationBuilder: NotificationCompat.Builder =
            NotificationCompat.Builder(context, channelId)

        notificationBuilder.setSmallIcon(R.mipmap.ic_launcher_round)
        notificationBuilder.color = 0x474E54


        val intent = Intent(context, MainActivity::class.java)
        if (shouldDisplayAlert && requestId > 0) {
            intent.putExtra("requestId", requestId)
        }

        val pendingIntent = PendingIntent.getActivity(context,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)

        notificationBuilder.setContentTitle(title)
            .setContentText(message)
            .setStyle(NotificationCompat.BigTextStyle())
            .setSound(Settings.System.DEFAULT_NOTIFICATION_URI)
            .setDefaults(Notification.DEFAULT_ALL)
            .setContentIntent(pendingIntent)
            .setAutoCancel(false)
            .setCategory(NotificationCompat.CATEGORY_SOCIAL)

        val notificationManager: NotificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val importance = NotificationManager.IMPORTANCE_HIGH
        val notificationChannel = NotificationChannel(
            channelId,
            channelId,
            importance
        )

        notificationChannel.enableLights(true)
        notificationChannel.lightColor = Color.RED
        notificationChannel.enableVibration(true)
        notificationChannel.vibrationPattern = longArrayOf(0, 1000, 500, 1000)
        notificationBuilder.setChannelId(channelId)
        notificationManager.createNotificationChannel(notificationChannel)

        notificationManager.notify(notificationId, notificationBuilder.build())

    }

    companion object {
        private const val CHANNEL_ID = "weather_update_channel"
        private const val NOTIFICATION_ID = 1

        fun createNotificationChannel(context: Context) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val name = "Weather Updates"
                val descriptionText = "Channel for daily weather updates"
                val importance = NotificationManager.IMPORTANCE_DEFAULT
                val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                    description = descriptionText
                }
                val notificationManager: NotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                notificationManager.createNotificationChannel(channel)
            }
        }
    }
}