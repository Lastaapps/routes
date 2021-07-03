package cz.lastaapps.routes.fragments

import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.createGraph
import androidx.navigation.fragment.fragment
import cz.lastaapps.routes.fragments.ui.dashboard.DashboardFragment
import cz.lastaapps.routes.fragments.ui.home.HomeFragment
import cz.lastaapps.routes.fragments.ui.notifications.NotificationsFragment

fun NavController.createAppGraph() = createGraph(startDestination = Dests.Routes.starting) {

    fragment<HomeFragment>(Dests.Routes.home)

    fragment<DashboardFragment>(Dests.Routes.dashboard) {
        argument(Dests.Args.name) {
            type = NavType.StringType
        }
    }

    fragment<NotificationsFragment>(Dests.Routes.notifications)

}
