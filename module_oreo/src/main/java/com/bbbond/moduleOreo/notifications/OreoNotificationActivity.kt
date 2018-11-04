package com.bbbond.moduleOreo.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.RemoteInput
import android.content.*
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.NotificationCompat
import android.view.View
import android.widget.Toast
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.bbbond.moduleCommon.BaseActivity
import com.bbbond.moduleOreo.R
import java.util.*

@Route(path = "/oreo/notification_activity")
class OreoNotificationActivity : BaseActivity() {

    private val TAG = OreoNotificationActivity::class.java.simpleName

    @Autowired
    @JvmField
    var back: Boolean = false
    lateinit var nbr: NotificationBroadcastReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ARouter.getInstance().inject(this)
        setContentView(R.layout.module_oreo_notification_activity)
        initNavigation(back)

        registerBroadcastReceiver()
        createNotificationChannel()
    }

    override fun onResume() {
        super.onResume()
        MyNotificationManager.messages.clear()
    }

    private fun registerBroadcastReceiver() {
        nbr = NotificationBroadcastReceiver()
        val filter = IntentFilter()
        filter.addAction("ACTION1")
        filter.addAction("SEND")
        this.registerReceiver(nbr, filter)
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationManager = getSystemService(NotificationManager::class.java)

            var channel = NotificationChannel("basic", "basic", NotificationManager.IMPORTANCE_DEFAULT)
            channel.description = "Basic Notification"
            notificationManager?.createNotificationChannel(channel)

            channel = NotificationChannel("bigPic", "bigPic", NotificationManager.IMPORTANCE_DEFAULT)
            channel.description = "Big Picture Notification"
            notificationManager?.createNotificationChannel(channel)

            channel = NotificationChannel("chats", "chats", NotificationManager.IMPORTANCE_DEFAULT)
            channel.description = "Chats Notification"
            notificationManager?.createNotificationChannel(channel)

            channel = NotificationChannel("progress", "progress", NotificationManager.IMPORTANCE_DEFAULT)
            channel.description = "Progress Notification"
            channel.setShowBadge(false)
            notificationManager?.createNotificationChannel(channel)

            channel = NotificationChannel("custom", "custom", NotificationManager.IMPORTANCE_DEFAULT)
            channel.description = "Custom Notification"
            notificationManager?.createNotificationChannel(channel)

        }
    }

    fun showNotification(view: View) {
        when (view.id) {
            R.id.btn_basic -> {
                MyNotificationManager.showBasicNotification(this)
                toast("Basic Notification")
            }
            R.id.btn_group -> {
                MyNotificationManager.showGroupNotification(this)
                toast("Group Notification")
            }
            R.id.btn_big_pic -> {
                MyNotificationManager.showBigPictureNotification(this)
                toast("Big Picture Notification")
            }
            R.id.btn_chats -> {
                MyNotificationManager.messages.add(NotificationCompat.MessagingStyle.Message("Hi~", Date().time, "Her"))
                MyNotificationManager.showChatsNotification(this, MyNotificationManager.messages)
                toast("Chats Notification")
            }
            R.id.btn_progress -> {
                MyNotificationManager.showProgressNotification(this)
                toast("Progress Notification")
            }
            R.id.btn_custom -> {
                MyNotificationManager.showCustomNotification(this)
                toast("Custom Notification")
            }
            R.id.btn_notification_listener -> {
                openNotificationAccess()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(nbr)
    }

    private fun openNotificationAccess() {
        startActivity(Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS"))
    }

    class NotificationBroadcastReceiver : BroadcastReceiver() {

        private val TAG = NotificationBroadcastReceiver::class.java.simpleName

        override fun onReceive(context: Context?, intent: Intent?) {
            when (intent?.action) {
                "ACTION1" -> {
                    Toast.makeText(context, "ACTION1", Toast.LENGTH_SHORT).show()
                }
                "SEND" -> {
                    Toast.makeText(context, "SEND " + getMessageText(intent), Toast.LENGTH_SHORT).show()
                    MyNotificationManager.messages.add(NotificationCompat.MessagingStyle.Message(
                            getMessageText(intent)?.toString() ?: "",
                            Date().time,
                            "Me"))
                    context?.let {
                        MyNotificationManager.showChatsNotification(it, MyNotificationManager.messages)
                        Handler().postDelayed({
                            var message: NotificationCompat.MessagingStyle.Message? = null
                            if (Math.random() > 0.5) {
                                message = NotificationCompat.MessagingStyle.Message(
                                        "Here you are [image]",
                                        Date().time,
                                        "She")
                                message.setData("image/*", getUriFromDrawableRes(context, R.drawable.oreo_01))
                            } else {
                                message = NotificationCompat.MessagingStyle.Message(
                                        "OK",
                                        Date().time,
                                        "She")
                            }
                            MyNotificationManager.messages.add(message)
                            MyNotificationManager.showChatsNotification(it, MyNotificationManager.messages)
                        }, 1000)
                    }

                }
            }
        }

        private fun getMessageText(intent: Intent): CharSequence? {
            val remoteInput = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT_WATCH) {
                RemoteInput.getResultsFromIntent(intent)
            } else {
                intent.extras
            }
            return remoteInput?.getCharSequence(MyNotificationManager.KEY_TEXT_REPLY)
        }

        private fun getUriFromDrawableRes(context: Context, id: Int): Uri {
            val resources = context.resources
            val path = (ContentResolver.SCHEME_ANDROID_RESOURCE + "://"
                    + resources.getResourcePackageName(id) + "/"
                    + resources.getResourceTypeName(id) + "/"
                    + resources.getResourceEntryName(id))
            return Uri.parse(path)
        }
    }

}
