package care.intouch.app.core.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import care.intouch.app.core.navigation.navhost.BottomNavHost
import care.intouch.uikit.ui.navigation.CustomBottomNavBar
import care.intouch.uikit.ui.navigation.currentRoute


@Composable
fun BottomNav() {

    val screensWithBottomBar = listOf(
        Route.HOME, Route.PLAN, Route.DIARY, Route.PROFILE
    )

    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            if (screensWithBottomBar.contains(currentRoute(navController = navController))) {
                BottomBar(navController = navController)
            }
        }
    ) {
        Modifier.padding(it)
        BottomNavHost(
            navController = navController,
            startDestination = Route.HOME
        )

    }
}

@Composable
fun BottomBar(
    navController: NavHostController
) {
    CustomBottomNavBar(
        screenRoute1 = Route.HOME,
        screenRoute2 = Route.PLAN,
        screenRoute3 = "", // TODO This screenRoure for Plus Button
        screenRoute4 = Route.DIARY,
        screenRoute5 = Route.PROFILE,
        currentRoute = currentRoute(navController = navController),
        firstItemClick = {
            navController.navigate(Route.HOME)  {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        },
        secondItemClick = {
            navController.navigate(Route.PLAN)  {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        },
        thirdItemClick = {
            navController.navigate(Route.DIARY)  {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        },
        fourthItemClick = {
            navController.navigate(Route.PROFILE) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        },
    )
}