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
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.d3if3128.booklend.R
import org.d3if3128.booklend.navigation.Screen
import org.d3if3128.booklend.ui.theme.BookLendTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AkunScreen(navController : NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Akun") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
            )
        }
    ) { padding ->
        AkunScreenContent(
            Modifier.padding(padding),
            navController = navController,
            )
    }
}

@Composable
fun AkunScreenContent(modifier: Modifier = Modifier, navController: NavController) {
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
                    .shadow(
                        elevation = 20.dp,
                        spotColor = Color(0x33000000),
                        ambientColor = Color(0x33000000)
                    )
                    .background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(size = 10.dp))
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxSize().padding(16.dp) // Pastikan row mengisi penuh ukuran card dan beri padding agar tidak menempel ke tepi
                ) {
                    Column {
                        Text(
                            text = "Dyna Rosalina",
                            style = TextStyle(
                                fontSize = 20.sp,
                                lineHeight = 24.sp,
                                fontWeight = FontWeight(800),
                                color = Color(0xFF49454F),
                                letterSpacing = 0.5.sp,
                            )
                        )
                        Text(
                            text = "dyna@gmail.com",
                            style = TextStyle(
                                fontSize = 16.sp,
                                lineHeight = 24.sp,
                                fontWeight = FontWeight(400),
                                color = Color(0xFF49454F),
                                letterSpacing = 0.5.sp,
                            )
                        )
                        Text(
                            text = "+62 823 8909 8269",
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
                onClick = {
                    // Tambahkan aksi yang diinginkan di sini
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
                    .height(2.dp)
                    .align(Alignment.BottomCenter)
                    .background(Color.Black)
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
                    .height(2.dp)
                    .align(Alignment.BottomCenter)
                    .background(Color.Black)
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
                    // Tambahkan aksi yang diinginkan di sini
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
                        painter = painterResource(id = R.drawable.person_icon), // Ganti dengan resource ikon yang sesuai
                        contentDescription = stringResource(R.string.ini_icon_ubah_profile), // Deskripsi untuk ikon
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
                    .height(2.dp)
                    .align(Alignment.BottomCenter)
                    .background(Color.Black)
            )
        }
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun AkunScreenPreview() {
    BookLendTheme {
        AkunScreen(rememberNavController())
    }
}

