package platformUtil

import org.company.app.util.Platform

class JvmPlatform: Platform {
    override val name: String
        get() = "Jvm "
}