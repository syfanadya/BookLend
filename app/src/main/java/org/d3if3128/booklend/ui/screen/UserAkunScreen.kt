package org.d3if3128.booklend.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import org.d3if3128.booklend.R
import org.d3if3128.booklend.model.User
import org.d3if3128.booklend.navigation.Screen
import org.d3if3128.booklend.network.UserDataStore
import org.d3if3128.booklend.ui.theme.BookLendTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserAkunScreen(navController: NavController) {

    val context = LocalContext.current
    val dataStore = UserDataStore(context)

    val userData by dataStore.userFlow.collectAsState(initial = User(
        namalengkap = "",
        nohp = "",
        usia = "",
        email = "",
        password = "",
        tanggalbuatakun = ""
    ))

        val items = listOf(
            UserBottomNavigationItem(
                title = "Beranda",
                selectedIcon = Icons.Filled.Home,
                unselectedIcon = Icons.Outlined.Home,
                hasNews = false
            ),
            UserBottomNavigationItem(
                title = "Riwayat",
                selectedIcon = Icons.Filled.Email,
                unselectedIcon = Icons.Outlined.Email,
                hasNews = false
            ),
            UserBottomNavigationItem(
                title = "Akun",
                selectedIcon = Icons.Filled.Person,
                unselectedIcon = Icons.Outlined.Person,
                hasNews = false
            )
        )

        var selectedItemIndex by rememberSaveable { mutableIntStateOf(0) }

        // Observe the current back stack entry and update the selected item index accordingly
        val currentBackStackEntry by navController.currentBackStackEntryAsState()
        LaunchedEffect(currentBackStackEntry) {
            selectedItemIndex = when (currentBackStackEntry?.destination?.route) {
                Screen.UserRiwayat.route -> 1
                Screen.UserAkun.route -> 2 // Set the index to match the "Akun" page
                else -> 0
            }
        }

        Scaffold(
            bottomBar = {
                NavigationBar {
                    items.forEachIndexed { index, item ->
                        NavigationBarItem(
                            selected = selectedItemIndex == index,
                            onClick = {
                                selectedItemIndex = index
                                when (index) {
                                    0 -> navController.navigate(Screen.UserHome.route)
                                    1 -> navController.navigate(Screen.UserAkun.route) // Ensure this route exists
                                    2 -> navController.navigate(Screen.UserAkun.route)
                                }
                            },
                            label = {
                                Text(text = item.title)
                            },
                            icon = {
                                BadgedBox(
                                    badge = {
                                        if (item.badgeCount != null) {
                                            Badge {
                                                Text(text = item.badgeCount.toString())
                                            }
                                        } else if (item.hasNews) {
                                            Badge()
                                        }
                                    }
                                ) {
                                    Icon(
                                        imageVector = if (index == selectedItemIndex) {
                                            item.selectedIcon
                                        } else item.unselectedIcon,
                                        contentDescription = item.title,
                                        tint = Color(0xFF2587DC)
                                    )
                                }
                            }
                        )
                    }
                }
            }
        ) { padding ->
            UserAkunScreenContent(
                modifier = Modifier.padding(padding),
                navController = navController,
                email = userData.email,
                fullname = userData.namalengkap,
                noPhone = userData.nohp,
                navigateButtonToUbahProfil = {
                    navController.navigate(Screen.UbahProfil.withIdUser(userData.iduser))
                }
            )
        }

}

@Composable
fun UserAkunScreenContent(
    modifier: Modifier,
    email: String,
    fullname: String,
    noPhone: String,
    navigateButtonToUbahProfil: () -> Unit,
    navController: NavController
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(109.dp)
                    .background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(size = 10.dp)),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 6.dp
                ),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White,
                )
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp) // Pastikan row mengisi penuh ukuran card dan beri padding agar tidak menempel ke tepi
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_account_circle_24), // Ganti dengan ID resource logo Anda
                        contentDescription = null,
                        modifier = Modifier.size(80.dp),
                        tint = Color.Unspecified
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Column(
                        verticalArrangement = Arrangement.spacedBy(5.dp, Alignment.Top),
                        horizontalAlignment = Alignment.Start,
                    ) {
                        Text(
                            text = fullname,
                            style = TextStyle(
                                fontSize = 20.sp,
                                lineHeight = 24.sp,
                                fontWeight = FontWeight(800),
                                color = Color(0xFF49454F),
                                letterSpacing = 0.5.sp,
                            )
                        )
                        Text(
                            text = email,
                            style = TextStyle(
                                fontSize = 16.sp,
                                lineHeight = 24.sp,
                                fontWeight = FontWeight(400),
                                color = Color(0xFF49454F),
                                letterSpacing = 0.5.sp,
                            )
                        )
                        Text(
                            text = noPhone,
                            style = TextStyle(
                                fontSize = 16.sp,
                                lineHeight = 24.sp,
                                fontWeight = FontWeight(400),
                                color = Color(0xFF49454F),
                                letterSpacing = 0.5.sp,
                            )
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(36.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            Button(
                onClick = navigateButtonToUbahProfil,
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp) // 56.dp dikurangi 2.dp untuk memberikan ruang bagi border bawah
                    .align(Alignment.TopCenter), // Memposisikan tombol di atas border
                contentPadding = PaddingValues(),
                shape = RoundedCornerShape(size = 0.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.person_icon), // Ganti dengan resource ikon yang sesuai
                        contentDescription = stringResource(R.string.ini_icon_ubah_profile), // Deskripsi untuk ikon
                        modifier = Modifier.size(24.dp),
                        tint = Color(0xFF3C4550)
                    )
                    Spacer(modifier = Modifier.width(20.dp)) // Tambahkan sedikit spasi antara ikon dan teks
                    Text(
                        text = stringResource(R.string.ubah_profil),
                        fontWeight = FontWeight.Bold, // Gunakan FontWeight.Bold untuk font weight 700
                        fontSize = 20.sp,
                        color = Color(0xFF3C4550)
                    )
                }
            }

            // Border bawah
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .align(Alignment.BottomCenter)
                    .background(Color(0xFFB2BBC7))
            )
        }

        Spacer(modifier = Modifier.height(36.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            Button(
                onClick = {
                    navController.navigate(Screen.About.route)
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp) // 56.dp dikurangi 2.dp untuk memberikan ruang bagi border bawah
                    .align(Alignment.TopCenter), // Memposisikan tombol di atas border
                contentPadding = PaddingValues(),
                shape = RoundedCornerShape(size = 0.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.about_icon), // Ganti dengan resource ikon yang sesuai
                        contentDescription = stringResource(R.string.ini_icon_ubah_profile), // Deskripsi untuk ikon
                        modifier = Modifier.size(24.dp),
                        tint = Color(0xFF3C4550)
                    )
                    Spacer(modifier = Modifier.width(20.dp)) // Tambahkan sedikit spasi antara ikon dan teks
                    Text(
                        text = stringResource(R.string.tentang_booklend),
                        fontWeight = FontWeight.Bold, // Gunakan FontWeight.Bold untuk font weight 700
                        fontSize = 20.sp,
                        color = Color(0xFF3C4550)
                    )
                }
            }

            // Border bawah
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .align(Alignment.BottomCenter)
                    .background(Color(0xFFB2BBC7))
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            Button(
                onClick = {
                    navController.navigate(Screen.Home2.route) 
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp) // 56.dp dikurangi 2.dp untuk memberikan ruang bagi border bawah
                    .align(Alignment.TopCenter), // Memposisikan tombol di atas border
                contentPadding = PaddingValues(),
                shape = RoundedCornerShape(size = 0.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.majesticons_logout), // Ganti dengan resource ikon yang sesuai
                        contentDescription = stringResource(R.string.ini_icon_keluar),
                        modifier = Modifier.size(24.dp),
                        tint = Color(0xFF3C4550)
                    )
                    Spacer(modifier = Modifier.width(20.dp)) // Tambahkan sedikit spasi antara ikon dan teks
                    Text(
                        text = stringResource(R.string.keluar),
                        fontWeight = FontWeight.Bold, // Gunakan FontWeight.Bold untuk font weight 700
                        fontSize = 20.sp,
                        color = Color(0xFF3C4550)
                    )
                }
            }

            // Border bawah
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .align(Alignment.BottomCenter)
                    .background(Color(0xFFB2BBC7))
            )
        }
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun UserAkunScreenPreview() {
    BookLendTheme {
        UserAkunScreen(rememberNavController())
    }
}