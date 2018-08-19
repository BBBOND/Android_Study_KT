package com.bbbond.module_oreo.notifications

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Looper
import android.support.v4.app.NotificationCompat
import android.support.v4.app.NotificationManagerCompat
import android.support.v4.app.RemoteInput
import com.bbbond.module_common.utils.LogUtil
import com.bbbond.module_oreo.R
import java.util.*

object MyNotificationManager {

    private val TAG: String = MyNotificationManager::class.java.simpleName
    val messages: ArrayList<NotificationCompat.MessagingStyle.Message> = ArrayList()
    const val KEY_TEXT_REPLY = "key_text_reply"

    /**
     * 显示基本通知
     */
    fun showBasicNotification(context: Context) {
        val mBuilder: NotificationCompat.Builder = NotificationCompat.Builder(context, "basic")
                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle("Notification Title")
                .setContentText("Notification content")
                .setStyle(NotificationCompat.BigTextStyle()
                        .bigText("1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890"))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .addAction(R.drawable.ic_notification, "ACTION1", createActionPendingIntent(context, "ACTION1", PendingIntent.FLAG_UPDATE_CURRENT, 0))
                .setContentIntent(createPendingIntent(context))
                .setAutoCancel(true)

        val notificationManager = NotificationManagerCompat.from(context)
        notificationManager.notify(2018, mBuilder.build())
    }

    /**
     * 显示图片通知
     */
    fun showBigPictureNotification(context: Context) {
        val bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.oreo_01)
        val mBuilder: NotificationCompat.Builder = NotificationCompat.Builder(context, "bigPic")
                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle("ContentTitle")
                .setContentText("ContentText")
                .setLargeIcon(bitmap)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .addAction(R.drawable.ic_notification, "ACTION1", createActionPendingIntent(context, "ACTION1", PendingIntent.FLAG_UPDATE_CURRENT, 0))
                .setContentIntent(createPendingIntent(context))
                .setAutoCancel(true)
                .setStyle(
                        NotificationCompat.BigPictureStyle()
                                .bigLargeIcon(null)
                                .bigPicture(bitmap)
                                .setBigContentTitle("Big Content Title")
                                .setSummaryText("Summary Text")
                )

        val notificationManager = NotificationManagerCompat.from(context)
        notificationManager.notify(2019, mBuilder.build())
    }

    /**
     * 显示消息通知 及 发送的功能
     */
    fun showChatsNotification(context: Context, messageList: List<NotificationCompat.MessagingStyle.Message>?) {
        val mBuilder: NotificationCompat.Builder = NotificationCompat.Builder(context, "chats")
                .setSmallIcon(R.drawable.ic_notification)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .addAction(createRemoteInputAction(context))
                .setContentIntent(createPendingIntent(context))
                .setAutoCancel(true)

        messageList?.let { it ->
            val messagingStyle = NotificationCompat.MessagingStyle("Her Chats")
                    .setConversationTitle("Her Chats")
            it.forEach {
                messagingStyle.addMessage(it)
            }
            mBuilder.setStyle(messagingStyle)
        }

        val notificationManager = NotificationManagerCompat.from(context)
        notificationManager.notify(2020, mBuilder.build())
    }


    var timer: Timer? = null
    var timerTask: TimerTask? = null
    var progress: Int = 0

    /**
     * 显示进度通知
     */
    fun showProgressNotification(context: Context) {
        if (timer == null) {
            progress = 0
            timer = Timer()
            timerTask = object : TimerTask() {
                override fun run() {
                    progress += 1
                    Looper.getMainLooper().run {
                        showProgressNotification(context)
                    }
                    if (progress == 100) {
                        timer?.cancel()
                        timer = null
                    }
                }
            }
            timer?.schedule(timerTask, 0, 300)
        }

        val mBuilder: NotificationCompat.Builder = NotificationCompat.Builder(context, "progress")
                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle("Notification Title")
                .setContentText("Downloading")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(createPendingIntent(context))

        if (progress < 100) {
            mBuilder.setProgress(100, progress, false)
                    .setAutoCancel(false)
        } else {
            mBuilder.setProgress(0, 0, false)
                    .setContentText("Download Complete")
                    .setAutoCancel(true)
        }

        val notificationManager = NotificationManagerCompat.from(context)
        notificationManager.notify(2018, mBuilder.build())
    }


    /* ============================================================ */

    private fun createPendingIntent(context: Context): PendingIntent {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse("askt://startapp/oreo/notification_activity?back=true")
        return PendingIntent.getActivity(context, 0, intent, 0)
    }

    private fun createActionPendingIntent(context: Context, action: String, flags: Int, requestCode: Int): PendingIntent {
        val intent = Intent()
        intent.action = action
        return PendingIntent.getBroadcast(context, requestCode, intent, flags)
    }

    private fun createRemoteInputAction(context: Context): NotificationCompat.Action {
        return NotificationCompat.Action.Builder(R.drawable.ic_notification, "SEND",
                createActionPendingIntent(context, "SEND", PendingIntent.FLAG_UPDATE_CURRENT, 1))
                .addRemoteInput(RemoteInput.Builder(KEY_TEXT_REPLY).setLabel("tap to reply").build())
                .build()
    }
}