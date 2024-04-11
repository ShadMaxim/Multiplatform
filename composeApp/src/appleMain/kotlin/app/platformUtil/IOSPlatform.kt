package app.platformUtil

import org.company.app.util.Platform

class IOSPlatform : Platform {
    override val name: String
        get() = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
}