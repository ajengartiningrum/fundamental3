package com.dicoding.picodiploma.myappsubmis2.ALARM

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.dicoding.picodiploma.myappsubmis2.ALARM.AlarmReceiver.Companion.ID_REPEAT
import com.dicoding.picodiploma.myappsubmis2.MAIN.MainActivity
import com.dicoding.picodiploma.myappsubmis2.R
import java.util.*

class AlarmReceiver : BroadcastReceiver() {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
        companion object {
            const val EXTRA_MODE = "repeat_alarm"
            const val ID_REPEAT = 100
            const val EXTRA_MESSAGE = "message"
            const val EXTRA_TITLE = "title"
            private const val TIME_DAILY = "09.00"
        }

        private val TAG = AlarmReceiver::class.java.simpleName

        override fun onReceive(context: Context, intent: Intent) {
            val message = intent.getStringExtra(EXTRA_MESSAGE)
            val title = intent.getStringExtra(EXTRA_TITLE)
            showNotification(context, title, message)
        }
    }

        fun setRepeatAlarm(
            context: Context,
            type: String,
            title: String?
        ){
            val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            val intent = Intent(context, AlarmReceiver::class.java)
            intent.putExtra(EXTRA_MODE, type)
            intent.putExtra(EXTRA_TITLE, title)

            val calendar = Calendar.getInstance()
            calendar.set(Calendar.HOUR_OF_DAY, 9)
            calendar.set(Calendar.MINUTE, 0)
            calendar.set(Calendar.SECOND, 0)

            val pendingIntent = PendingIntent.getBroadcast(context, ID_REPEAT, intent, 0)
            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, AlarmManager.INTERVAL_DAY, pendingIntent)
            Toast.makeText(context, "Alarm is ON", Toast.LENGTH_SHORT).show()
        }

        fun cancelAlarm(context: Context, type:String){
            val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            val intent = Intent(context, AlarmReceiver::class.java)
            val requestCode = ID_REPEAT
            val pendingIntent = PendingIntent.getBroadcast(context, requestCode, intent, 0)
            pendingIntent.cancel()
            alarmManager.cancel(pendingIntent)
            Toast.makeText(context, "Alarm is OFF", Toast.LENGTH_SHORT).show()
        }

        fun showNotification(context: Context, title: String?, message: String?){
            val channelId = "GithubSubmis"
            val channelName = "notification"

            val intent = Intent(context, MainActivity::class.java)
            val pendingIntent = PendingIntent.getActivity(context, 0, intent, 0)

            val notificationManagerCompat = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val builder = NotificationCompat.Builder(context, channelId)
                    .setContentIntent(pendingIntent)
                    .setSmallIcon(R.drawable.ic_baseline_notifications_active_24)
                    .setContentTitle(title)
                    .setContentText(message)
                    .setColor(ContextCompat.getColor(context, android.R.color.transparent))
                    .setVibrate(longArrayOf(1000, 1000, 1000, 1000, 1000))
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channel = NotificationChannel(channelId,
                        channelName,
                        NotificationManager.IMPORTANCE_DEFAULT)
                channel.enableVibration(true)
                channel.vibrationPattern = longArrayOf(1000, 1000, 1000, 1000, 1000)
                builder.setChannelId(channelId)
                notificationManagerCompat.createNotificationChannel(channel)
            }
            val notification = builder.build()
            notificationManagerCompat.notify(ID_REPEAT, notification)
        }
}