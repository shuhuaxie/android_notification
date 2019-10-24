package com.example.notificationtest

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.core.app.NotificationCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<TextView>(R.id.tv_show_notification).setOnClickListener {
            showNotification()
        }
        findViewById<TextView>(R.id.tv_show_other).setOnClickListener {
            startActivity(Intent(this, OtherActivity::class.java))
        }
        WoApplication.isOpened = true
    }

    private fun showNotification() {
        val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        val aDefault = "default"
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            var c: NotificationChannel? = null
            try {
                c = manager.getNotificationChannel(aDefault)
            } catch (e: Exception) {

            }

            if (c == null) {
                val channel = NotificationChannel(
                    aDefault,
                    "my_channel", NotificationManager.IMPORTANCE_DEFAULT
                )
                channel.enableLights(false) //是否在桌面icon右上角展示小红点
                channel.lightColor = Color.RED //小红点颜色
                channel.setShowBadge(true) //是否在久按桌面图标时显示此渠道的通知
                manager.createNotificationChannel(channel)
            }
        }
        val intentTarget = Intent(
            this, NotifyClickActivity::class.java
        )
        val intent = PendingIntent.getActivity(
            this, 0,
            intentTarget, PendingIntent.FLAG_UPDATE_CURRENT
        )
        val notification = NotificationCompat.Builder(this, aDefault)
            .setContentTitle("窝窝营销")
            .setContentIntent(intent)
            .setContentText("my content")
            .setSmallIcon(R.drawable.app_logo)
            .setAutoCancel(true)
            .build()
        manager.notify(1, notification)
    }

    override fun onNewIntent(intent: Intent?) {
        Log.e("xie","new intent")
    }
}
