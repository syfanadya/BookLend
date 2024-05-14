package org.d3if3128.booklend.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.d3if3128.booklend.ui.screen.AdminLanding
import org.d3if3128.booklend.ui.screen.LoginScreen
import org.d3if3128.booklend.ui.screen.MainScreen
import org.d3if3128.booklend.ui.screen.MainScreen2
import org.d3if3128.booklend.ui.screen.RegisterScreen


@Composable
fun SetupNavGraph(navController: NavHostController = rememberNavController()){
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ){
        composable(route = Screen.Home.route){
            MainScreen(navController)
        }

        composable(route = Screen.Home2.route){
            MainScreen2(navController)
        }

        composable(route = Screen.Login.route){
            LoginScreen(navController)
        }

        composable(route = Screen.Register.route){
            RegisterScreen(navController)
        }

        composable(route = Screen.AdminLanding.route){
            AdminLanding(navController)
        }

    }
}