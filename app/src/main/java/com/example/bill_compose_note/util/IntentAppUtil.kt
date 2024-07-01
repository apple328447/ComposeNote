package com.example.bill_compose_note.util

import android.content.Context
import android.content.pm.PackageManager

object IntentAppUtil {
    //手機是不是有安裝Telegram
    fun isTelegramInstalled(context: Context): Boolean {
        val packageManager = context.packageManager
        return try {
            packageManager.getPackageInfo("org.telegram.messenger", PackageManager.GET_ACTIVITIES)
            true
        } catch (e: PackageManager.NameNotFoundException) {
            false
        }
    }
    //手機是不是有安裝Twitter
    fun isTwitterInstalled(context: Context): Boolean {
        val packageManager = context.packageManager
        return try {
            packageManager.getPackageInfo("com.twitter.android", PackageManager.GET_ACTIVITIES)
            true
        } catch (e: PackageManager.NameNotFoundException) {
            false
        }
    }

    //手機是不是有安裝IG
    fun isInstagramInstalled(context: Context): Boolean {
        val packageManager = context.packageManager
        return try {
            packageManager.getPackageInfo("com.instagram.android", PackageManager.GET_ACTIVITIES)
            true
        } catch (e: PackageManager.NameNotFoundException) {
            false
        }
    }

}