package org.d3if3128.booklend.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.d3if3128.booklend.R
import org.d3if3128.booklend.ui.theme.BookLendTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Profile(navController: NavHostController) {
    var showList by remember { mutableStateOf(true) }

    val items = listOf(
        BottomNavigationItem(
            title = "Beranda",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
            hasNews = false
        ),
        BottomNavigationItem(
            title = "Riwayat",
            selectedIcon = Icons.Filled.Email,
            unselectedIcon = Icons.Outlined.Email,
            hasNews = false
        ),
        BottomNavigationItem(
            title = "Notifikasi",
            selectedIcon = Icons.Filled.Notifications,
            unselectedIcon = Icons.Outlined.Notifications,
            hasNews = false,
            badgeCount = 45
        ),
        BottomNavigationItem(
            title = "Akun",
            selectedIcon = Icons.Filled.Person,
            unselectedIcon = Icons.Outlined.Person,
            hasNews = false
        )
    )

    var selectedItemIndex by rememberSaveable { mutableIntStateOf(0) }

    Scaffold(
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp) // Sesuaikan dengan dimensi gambar Anda
            ) {
                Image(
                    modifier = Modifier.fillMaxSize(),
                    painter = painterResource(id = R.drawable.bgb),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds
                )
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Kembali")
                }
            }
        },
        bottomBar = {
            NavigationBar {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = selectedItemIndex == index,
                        onClick = {
                            selectedItemIndex = index
                            //navController.navigate(Screen.DetailBuku.route)
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
        HomeScreenContentt(
            showList,
            Modifier.padding(padding),
            navController, searchData = {}
        )
    }
}

@Composable
fun HomeScreenContentt(
    showList: Boolean,
    modifier: Modifier,
    navController: NavHostController,
    // Fungsi yang akan dipanggil saat melakukan pencarian
    searchData: (String) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .zIndex(2f)
            .padding(16.dp), // Padding untuk konten di dalam Box
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Tambahkan spacer untuk menghindari tumpang tindih dengan top bar
        Spacer(modifier = Modifier.height(102.dp))
        Column(
            modifier = Modifier
                .height(109.dp) // Atur tinggi dari Column di sini
                .width(340.dp)
                .zIndex(2f)
                .shadow(
                    elevation = 19.dp, // Besarnya elevasi bayangan
                    shape = RoundedCornerShape(8.dp), // Bentuk sudut
                    clip = true // Gunakan true agar bayangan tidak melampaui batas konten
                )
                .background(Color.White)
                //.border(2.dp, Color.Black)
                .padding(16.dp), // Padding untuk konten di dalam Box
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                val logoTextPainter = painterResource(id = R.drawable.profile)
                Image(
                    painter = logoTextPainter,
                    contentDescription = "profile",
                    modifier = Modifier
                        .size(width = 150.dp, height = 150.dp)
                        .clip(RoundedCornerShape(75.dp))
                )

                Column {
                    Text(
                        text = "Dyna Rosalina",
                        style = TextStyle(
                            fontWeight = FontWeight.Bold, // Mengatur ketebalan huruf (font weight) sesuai keinginan Anda
                            fontSize = 16.sp // Mengatur ukuran huruf (font size) sesuai keinginan Anda
                        )
                    )
                    Text(text = "dyna@gmail.com")
                    Text(text = "+62 823 8909 8269")
                }

            }
        }


        Spacer(modifier = Modifier.height(45.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = { /* Your click action */ },
                colors = ButtonDefaults.buttonColors(Color.Transparent),
                modifier = Modifier
                    .width(340.dp)
                    .height(56.dp)
                    .padding(bottom = 2.dp) // Menambahkan padding hanya di bagian bawah
                    .clip(RectangleShape)
                    .background(color = Color.Transparent)
                    .then(Modifier.drawBehind {
                        drawLine(
                            color = Color.Black,
                            start = Offset(0f, size.height - 1f),
                            end = Offset(size.width, size.height - 1f),
                            strokeWidth = 1f
                        )
                    }),
                contentPadding = PaddingValues(),
                shape = RectangleShape
                ) {

                val imagePainter1 = painterResource(id = R.drawable.baseline_person_outline_24)
                Image(
                    painter = imagePainter1,
                    contentDescription = ""
                )
                Spacer(modifier = Modifier.width(30.dp))
                Text(
                    text = "Ubah Profile",
                    fontWeight = FontWeight(700),
                    fontSize = 16.sp,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.width(150.dp))
                val imagePainter = painterResource(id = R.drawable.baseline_arrow_forward_ios_24)
                Image(
                    painter = imagePainter,
                    contentDescription = ""
                )


            }
        }

//===============================================

        Spacer(modifier = Modifier.height(20.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = { /* Your click action */ },
                colors = ButtonDefaults.buttonColors(Color.Transparent),
                modifier = Modifier
                    .width(340.dp)
                    .height(56.dp)
                    .padding(bottom = 1.dp) // Menambahkan padding hanya di bagian bawah
                    .clip(RectangleShape)
                    .background(color = Color.Transparent)
                    .then(Modifier.drawBehind {
                        drawLine(
                            color = Color.Black,
                            start = Offset(0f, size.height - 1f),
                            end = Offset(size.width, size.height - 1f),
                            strokeWidth = 1f
                        )
                    }),
                contentPadding = PaddingValues(),
                shape = RectangleShape
            ) {

                val imagePainter1 = painterResource(id = R.drawable.baseline_info_outline_24)
                Image(
                    painter = imagePainter1,
                    contentDescription = ""
                )
                Spacer(modifier = Modifier.width(25.dp))
                Text(
                    text = "Tentang BookLend",
                    fontWeight = FontWeight(700),
                    fontSize = 16.sp,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.width(110.dp))
                val imagePainter = painterResource(id = R.drawable.baseline_arrow_forward_ios_24)
                Image(
                    painter = imagePainter,
                    contentDescription = ""
                )


            }
        }

//        ======================================================


        Spacer(modifier = Modifier.height(20.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = { /* Your click action */ },
                colors = ButtonDefaults.buttonColors(Color.Transparent),
                modifier = Modifier
                    .width(340.dp)
                    .height(56.dp)
                    .padding(bottom = 2.dp) // Menambahkan padding hanya di bagian bawah
                    .clip(RectangleShape)
                    .background(color = Color.Transparent)
                    .then(Modifier.drawBehind {
                        drawLine(
                            color = Color.Black,
                            start = Offset(0f, size.height - 1f),
                            end = Offset(size.width, size.height - 1f),
                            strokeWidth = 1f
                        )
                    }),
                contentPadding = PaddingValues(),
                shape = RectangleShape
            ) {

                val imagePainter1 = painterResource(id = R.drawable.baseline_exit_to_app_24)
                Image(
                    painter = imagePainter1,
                    contentDescription = ""
                )
                Spacer(modifier = Modifier.width(25.dp))
                Text(
                    text = "Keluar",
                    fontWeight = FontWeight(700),
                    fontSize = 16.sp,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.width(200.dp))
                val imagePainter = painterResource(id = R.drawable.baseline_arrow_forward_ios_24)
                Image(
                    painter = imagePainter,
                    contentDescription = ""
                )


            }
        }

    }
}




@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun ProfilePreview() {
    BookLendTheme {
        Profile(rememberNavController())
    }
}