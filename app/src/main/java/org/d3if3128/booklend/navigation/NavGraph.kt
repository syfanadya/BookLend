package org.d3if3128.booklend.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import org.d3if3128.booklend.ui.screen.AboutScreen
import org.d3if3128.booklend.ui.screen.AdminDetailBuku
import org.d3if3128.booklend.ui.screen.AdminHomeScreen
import org.d3if3128.booklend.ui.screen.AdminLanding
import org.d3if3128.booklend.ui.screen.AkunScreen
import org.d3if3128.booklend.ui.screen.HomeScreen
import org.d3if3128.booklend.ui.screen.KEY_ID_BUKU
import org.d3if3128.booklend.ui.screen.LoginScreen
import org.d3if3128.booklend.ui.screen.RegisterScreen


@Composable
fun SetupNavGraph(navController: NavHostController = rememberNavController()){
    NavHost(
        navController = navController,
        startDestination = Screen.Register.route
    ){

        composable(route = Screen.Login.route){
            LoginScreen(navController)
        }

        composable(route = Screen.Register.route){
            RegisterScreen(navController)
        }

        composable(route = Screen.AdminLanding.route){
            AdminLanding(navController)
        }

        composable(route = Screen.AdminHome.route){
            AdminHomeScreen(navController)
        }

        composable(route = Screen.FormBaru.route){
            AdminDetailBuku(navController)
        }

        composable(
            route = Screen.FormUbah.route,
            arguments = listOf(
                navArgument(KEY_ID_BUKU){type = NavType.LongType}
            )
        ){navBackStackEntry ->
            val idBuku = navBackStackEntry.arguments?.getLong(KEY_ID_BUKU)
            AdminDetailBuku(navController, idBuku)
        }

        composable(route = Screen.Akun.route){
            AkunScreen(navController)
        }
        composable(route = Screen.About.route){
            AboutScreen(navController)
        }

        composable(route = Screen.UserHome.route){
            HomeScreen(navController)
        }
    }
}