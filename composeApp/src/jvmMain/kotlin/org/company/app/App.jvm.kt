package org.company.app

import org.company.app.util.Platform
import platformUtil.JvmPlatform
import java.awt.Desktop
import java.net.URI

internal actual fun openUrl(url: String?) {
    val uri = url?.let { URI.create(it) } ?: return
    Desktop.getDesktop().browse(uri)
}

actual fun getPlatform(): Platform = JvmPlatform()