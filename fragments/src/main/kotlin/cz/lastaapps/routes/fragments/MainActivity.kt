package cz.lastaapps.routes.fragments

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import cz.lastaapps.routes.fragments.databinding.ActivityMainBinding
import cz.lastaapps.routes.fragments.navigating.navigate

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        navController.graph = navController.createAppGraph()

        val appBarConfiguration = AppBarConfiguration(
            navController.graph
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        navView.apply {
            setOnItemSelectedListener {
                when (it.itemId) {

                    R.id.navigation_home -> navController.navigate(Dests.Routes.home)
                    R.id.navigation_dashboard -> navController.navigate(
                        Dests.Routes.dashboard.replace(
                            "{${Dests.Args.name}}", "Jára Cimrman"
                        )
                    )
                    R.id.navigation_notifications -> navController.navigate(
                        Dests.Routes.notifications,
                        bundleOf(Dests.Args.message to Message("Dumb & Dumber"))
                    )
                    else -> throw IllegalArgumentException("Unknown menu item")
                }
                true
            }
        }
    }
}