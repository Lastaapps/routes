package cz.lastaapps.routes.fragments

@Suppress("SpellCheckingInspection")
object Dests {

    object Routes {
        const val home = "home"
        const val dashboard = "dashboard/{${Args.name}}"
        const val notifications = "notifications"

        const val starting = home
    }

    object Args {
        const val name = "name"
        const val message = "message"
    }

}