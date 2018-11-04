package com.bbbond.moduleOreo.notifications

import android.app.NotificationChannel
import android.app.NotificationChannelGroup
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.os.UserHandle
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.util.Log

class MyNotificationListenerService : NotificationListenerService() {

    private val TAG: String = MyNotificationListenerService::class.java.simpleName

    override fun onNotificationChannelGroupModified(pkg: String?, user: UserHandle?, group: NotificationChannelGroup?, modificationType: Int) {
        Log.d(TAG, "onNotificationChannelGroupModified pkg: $pkg")
        super.onNotificationChannelGroupModified(pkg, user, group, modificationType)
    }

    override fun onNotificationRemoved(sbn: StatusBarNotification?) {
        Log.d(TAG, "onNotificationRemoved")
        super.onNotificationRemoved(sbn)
    }

    override fun onNotificationRemoved(sbn: StatusBarNotification?, rankingMap: RankingMap?) {
        Log.d(TAG, "onNotificationRemoved")
        super.onNotificationRemoved(sbn, rankingMap)
    }

    override fun onNotificationRemoved(sbn: StatusBarNotification?, rankingMap: RankingMap?, reason: Int) {
        Log.d(TAG, "onNotificationRemoved")
        super.onNotificationRemoved(sbn, rankingMap, reason)
    }

    override fun onNotificationRankingUpdate(rankingMap: RankingMap?) {
        Log.d(TAG, "onNotificationRankingUpdate")
        super.onNotificationRankingUpdate(rankingMap)
    }

    override fun onInterruptionFilterChanged(interruptionFilter: Int) {
        Log.d(TAG, "onInterruptionFilterChanged")
        super.onInterruptionFilterChanged(interruptionFilter)
    }

    override fun onListenerHintsChanged(hints: Int) {
        Log.d(TAG, "onListenerHintsChanged")
        super.onListenerHintsChanged(hints)
    }

    override fun onListenerConnected() {
        Log.d(TAG, "onListenerConnected")
        super.onListenerConnected()
    }

    override fun onBind(intent: Intent?): IBinder? {
        Log.d(TAG, "onBind")
        return super.onBind(intent)
    }

    override fun onNotificationPosted(sbn: StatusBarNotification?) {
        Log.d(TAG, "onNotificationPosted")
        super.onNotificationPosted(sbn)
    }

    override fun onNotificationPosted(sbn: StatusBarNotification?, rankingMap: RankingMap?) {
        Log.d(TAG, "onNotificationPosted")
        super.onNotificationPosted(sbn, rankingMap)
    }

    override fun onNotificationChannelModified(pkg: String?, user: UserHandle?, channel: NotificationChannel?, modificationType: Int) {
        Log.d(TAG, "onNotificationChannelModified")
        super.onNotificationChannelModified(pkg, user, channel, modificationType)
    }

    override fun onListenerDisconnected() {
        Log.d(TAG, "onListenerDisconnected")
        super.onListenerDisconnected()
    }

    override fun getActiveNotifications(): Array<StatusBarNotification> {
        Log.d(TAG, "getActiveNotifications")
        return super.getActiveNotifications()
    }

    override fun getActiveNotifications(keys: Array<out String>?): Array<StatusBarNotification> {
        Log.d(TAG, "getActiveNotifications")
        return super.getActiveNotifications(keys)
    }

    override fun getCurrentRanking(): RankingMap {
        Log.d(TAG, "getCurrentRanking")
        return super.getCurrentRanking()
    }

    override fun attachBaseContext(base: Context?) {
        Log.d(TAG, "attachBaseContext")
        super.attachBaseContext(base)
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy")
        super.onDestroy()
    }
}