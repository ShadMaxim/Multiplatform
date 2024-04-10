package org.company.app.data

sealed class Scenes(
    open val route: String,
    open val argument: String? = null
) {

    class Home: Scenes("/home")
    data class Greeting(override val argument: String? = null): Scenes("/greeting", argument)
    class NoteList(override val argument: String? = null): Scenes("/list", argument)

    val setRouteAndArgument
        get() = "${route}${if (argument != null) "/${argument}" else ""}"

    val routeToDestination
        get() = "${route}/{argument}"
//        get() = "${route}${if (argument != null) "/{argument}" else ""}"

    companion object {
        const val ARGUMENT = "argument"
    }
}

