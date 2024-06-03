package org.d3if3128.booklend.navigation

import org.d3if3128.booklend.ui.screen.KEY_EMAIL_USER
import org.d3if3128.booklend.ui.screen.KEY_ID_BUKU
import org.d3if3128.booklend.ui.screen.KEY_ID_USER

sealed class Screen(val route: String) {
    data object Home: Screen("mainscreen")
    data object Home2: Screen("mainscreen2")
    data object Login: Screen("loginscreen")
    data object Register: Screen("registerscreen")
    data object Akun: Screen("akun")
    data object UserAkun: Screen("userAkun")
    data object About: Screen("about")
    data object AdminLogin: Screen("adminlogin")
//    data object AdminHome: Screen("adminhome")
    data object FormBaru: Screen("detailBukuScreen")
    data object FormUbah: Screen("detailBukuScreen/{$KEY_ID_BUKU}"){
        fun withIdBuku(idBuku: Long) = "detailBukuScreen/$idBuku"
    }

    data object UbahProfil: Screen("ubahProfilScreen/{$KEY_ID_USER}"){
        fun withIdUser(idUser: Long) = "ubahProfilScreen/$idUser"
    }
    data object AdminHome: Screen("adminhome/{$KEY_EMAIL_USER}"){
        fun withEmail(email: String) = "adminhome/$email"
    }
    data object UserHome: Screen("userhome/{$KEY_EMAIL_USER}"){
        fun withEmail(email: String) = "userhome/$email"
    }
    data object UserDetailBuku: Screen("userDetailBukuScreen/{$KEY_ID_BUKU}"){
        fun withIdBuku(idBuku: Long) = "userDetailBukuScreen/$idBuku"
    }
}