package cz.lastaapps.routes.compose.navigating

import android.content.Intent
import android.os.Bundle
import androidx.annotation.MainThread
import androidx.core.net.toUri
import androidx.navigation.*

@MainThread
fun NavController.navigate(
    route: String,
    args: Bundle,
    builder: NavOptionsBuilder.() -> Unit = {}
) {
    val options = navOptions(builder)

    navigate(
        NavDeepLinkRequest.Builder.fromUri(NavDestination.createRoute(route).toUri()).build(),
        args,
        options,
        null
    )
}

@MainThread
fun NavController.navigate(
    request: NavDeepLinkRequest,
    args: Bundle,
    navOptions: NavOptions?,
    navigatorExtras: Navigator.Extras?
) {
    val deepLinkMatch = graph.matchDeepLink(request)
    if (deepLinkMatch != null) {
        val destination = deepLinkMatch.destination
        val combinedArgs = destination.addInDefaultArgs(deepLinkMatch.matchingArgs) ?: Bundle()
        val node = deepLinkMatch.destination
        val intent = Intent().apply {
            setDataAndType(request.uri, request.mimeType)
            action = request.action
        }
        combinedArgs.putParcelable(NavController.KEY_DEEP_LINK_INTENT, intent)
        combinedArgs.putAll(args)
        privateNavigate(node, combinedArgs, navOptions, navigatorExtras)
    } else {
        throw IllegalArgumentException(
            "Navigation destination that matches request $request cannot be found in the " +
                    "navigation graph $graph"
        )
    }
}

//TODO resolve without a reflection
@MainThread
private fun NavController.privateNavigate(
    node: NavDestination,
    args: Bundle,
    navOptions: NavOptions?,
    navigatorExtras: Navigator.Extras?
) {
    callPrivateFunc("navigate", node, args, navOptions, navigatorExtras)
}

