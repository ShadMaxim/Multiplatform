package org.company.app.platformUtil

import org.company.app.util.Platform

class AndroidPlatform: Platform {

    override val name: String
        get() = "Android ${android.os.Build.VERSION.SDK_INT}"
}