package org.d3if3128.booklend.navigation

sealed class Screen(val route: String) {
    data object Home: Screen("mainscreen")
    data object Home2: Screen("mainscreen2")
    data object Login: Screen("loginscreen")
    data object Register: Screen("registerscreen")
    data object AdminLanding: Screen("adminlanding")

    data object AdminLogin: Screen("adminlogin")

    data object Profil: Screen("profile")
}