package org.company.app

import org.company.app.util.Platform
import platform.Foundation.NSURL
import platform.UIKit.UIApplication

internal actual fun openUrl(url: String?) {
    val nsUrl = url?.let { NSURL.URLWithString(it) } ?: return
    UIApplication.sharedApplication.openURL(nsUrl)
}

internal actual fun getPlatform(): Platform {
    TODO("Not yet implemented")
}
