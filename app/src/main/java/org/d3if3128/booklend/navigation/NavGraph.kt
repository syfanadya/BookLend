package org.d3if3128.booklend.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import org.d3if3128.booklend.ui.screen.AboutScreen
import org.d3if3128.booklend.ui.screen.AdminAkunScreen
import org.d3if3128.booklend.ui.screen.AdminDaftarPeminjamScreen
import org.d3if3128.booklend.ui.screen.AdminDetailBuku
import org.d3if3128.booklend.ui.screen.AdminDetailPeminjaman
import org.d3if3128.booklend.ui.screen.AdminHomeScreen
import org.d3if3128.booklend.ui.screen.AdminLogin
import org.d3if3128.booklend.ui.screen.HomeScreen
import org.d3if3128.booklend.ui.screen.KEY_EMAIL_USER
import org.d3if3128.booklend.ui.screen.KEY_ID_BUKU
import org.d3if3128.booklend.ui.screen.KEY_ID_PEMINJAMAN
import org.d3if3128.booklend.ui.screen.KEY_ID_USER
import org.d3if3128.booklend.ui.screen.LoginScreen
import org.d3if3128.booklend.ui.screen.MainScreen
import org.d3if3128.booklend.ui.screen.MainScreen2
import org.d3if3128.booklend.ui.screen.RegisterScreen
import org.d3if3128.booklend.ui.screen.UbahProfil
import org.d3if3128.booklend.ui.screen.UserAkunScreen
import org.d3if3128.booklend.ui.screen.UserDetailBuku
import org.d3if3128.booklend.ui.screen.UserRiwayatScreen


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

        composable(route = Screen.AdminLogin.route){
            AdminLogin(navController)
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

        composable(route = Screen.AdminAkun.route){
            AdminAkunScreen(navController)
        }

        composable(route = Screen.UserAkun.route){
            UserAkunScreen(navController)
        }

        composable(route = Screen.About.route){
            AboutScreen(navController)
        }

        composable(
            route = Screen.UserHome.route,
            arguments = listOf (
                navArgument(KEY_EMAIL_USER){type = NavType.StringType}
            )
        ){navBackStackEntryUser ->
            val email = navBackStackEntryUser.arguments?.getString(KEY_EMAIL_USER)
            HomeScreen(navController, user = null)
        }

        composable(
            route = Screen.UserDetailBuku.route,
            arguments = listOf(
                navArgument(KEY_ID_BUKU){type = NavType.LongType}
            )
        ){navBackStackEntry ->
            val idBuku = navBackStackEntry.arguments?.getLong(KEY_ID_BUKU)
            UserDetailBuku(navController, idBuku)
        }

        composable(
            route = Screen.UbahProfil.route,
            arguments = listOf(
                navArgument(KEY_ID_USER){type = NavType.LongType}
            )
        ){navBackStackEntry ->
            val idUser = navBackStackEntry.arguments?.getLong(KEY_ID_USER)
            UbahProfil(navController, idUser)
        }


        composable(
            route = Screen.AdminDetailPeminjaman.route,
            arguments = listOf(
                navArgument(KEY_ID_PEMINJAMAN){type = NavType.LongType}
            )
        ){navBackStackEntry ->
            val idPeminjaman = navBackStackEntry.arguments?.getLong(KEY_ID_PEMINJAMAN)
            AdminDetailPeminjaman(navController, idPeminjaman)
        }


        composable(route = Screen.UserRiwayat.route){
            UserRiwayatScreen(navController)
        }

        composable(route = Screen.AdminDaftarPeminjaman.route){
            AdminDaftarPeminjamScreen(navController)
        }

    }
}