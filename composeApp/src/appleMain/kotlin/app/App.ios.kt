package org.company.app

import app.platformUtil.IOSPlatform
import org.company.app.util.Platform
import platform.Foundation.NSURL
import platform.UIKit.UIApplication

internal actual fun openUrl(url: String?) {
    val nsUrl = url?.let { NSURL.URLWithString(it) } ?: return
    UIApplication.sharedApplication.openURL(nsUrl)
}

actual fun getPlatform(): Platform = IOSPlatform()