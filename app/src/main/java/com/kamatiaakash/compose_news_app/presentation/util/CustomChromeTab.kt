package com.kamatiaakash.compose_news_app.presentation.util

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.browser.customtabs.CustomTabColorSchemeParams
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import com.kamatiaakash.compose_news_app.R

fun customChromeTab(context: Context,url:String){
    val builder = CustomTabsIntent.Builder()
    val params = CustomTabColorSchemeParams.Builder()
    params.setToolbarColor(ContextCompat.getColor(context, R.color.purple_700))
    builder.setDefaultColorSchemeParams(params.build())
    builder.setShowTitle(true)
    builder.setShareState(CustomTabsIntent.SHARE_STATE_ON)
    builder.setInstantAppsEnabled(true)
    val customBuilder = builder.build()

    if (context.isPackageInstalled("com.android.chrome")) {

        customBuilder.intent.setPackage("com.android.chrome")
        customBuilder.launchUrl(context, Uri.parse(url))
    } else {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        context.startActivity(intent)
    }
}

fun Context.isPackageInstalled(packageName: String): Boolean {
    return try {
        packageManager.getPackageInfo(packageName, 0)
        true
    } catch (e: PackageManager.NameNotFoundException) {
        false
    }
}