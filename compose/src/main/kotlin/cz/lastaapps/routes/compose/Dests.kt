package cz.lastaapps.routes.compose

@Suppress("SpellCheckingInspection")
object Dests {

    object Routes {
        const val home = "home"
        const val routes = "routes/{${Args.name}}"
        const val parcelable = "parcelable"
    }

    object Args {

        const val name = "name"

        const val message = "message"
    }
}