package cz.lastaapps.routes.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import cz.lastaapps.routes.compose.navigating.navigate
import cz.lastaapps.routes.compose.ui.theme.RoutesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RoutesTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {

                    val controller = rememberNavController()

                    NavHost(navController = controller, Dests.Routes.home) {

                        composable(Dests.Routes.home) {
                            Home(controller)
                        }

                        composable(
                            Dests.Routes.routes,
                            listOf(navArgument(Dests.Args.name) { type = NavType.StringType }),
                        ) {
                            val arg =
                                controller.currentBackStackEntry!!.arguments!!.getString(Dests.Args.name)!!
                            Route(arg)
                        }

                        composable(Dests.Routes.parcelable) {
                            val arg =
                                controller.currentBackStackEntry!!.arguments!!.getParcelable<Message>(
                                    Dests.Args.message
                                )!!.message
                            Parcelable(arg)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Home(controller: NavController) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Text(text = "Gandalf goes whee!")

            Button(
                {
                    controller.navigate(
                        Dests.Routes.routes.replace("{${Dests.Args.name}}", "The One Ring")
                    )
                }
            ) {
                Text("Test route params")
            }

            Button({
                controller.navigate(
                    Dests.Routes.parcelable,
                    bundleOf(Dests.Args.message to Message("Rule them all!"))
                )
            }) {
                Text("Test parcelable params")
            }
        }
    }
}

@Composable
fun Route(text: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Text(text = text)
    }
}

@Composable
fun Parcelable(text: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Text(text = text)
    }
}