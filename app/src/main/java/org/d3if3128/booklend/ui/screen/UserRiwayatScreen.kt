package org.d3if3128.booklend.ui.screen

import android.content.res.Configuration
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import org.d3if3128.booklend.R
import org.d3if3128.booklend.database.BooklendDb
import org.d3if3128.booklend.model.User
import org.d3if3128.booklend.navigation.Screen
import org.d3if3128.booklend.network.UserDataStore
import org.d3if3128.booklend.ui.theme.BookLendTheme
import org.d3if3128.booklend.util.ViewModelFactoryPeminjaman

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserRiwayatScreen(navController: NavHostController){

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
            Screen.UserAkun.route -> 2
            Screen.UserRiwayat.route -> 1 // Set the index to match the "Akun" page
            else -> 0
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Riwayat",
                        style = TextStyle(
                            fontSize = 23.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            )
        },
        bottomBar = {
            NavigationBar {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = selectedItemIndex == index,
                        onClick = {
                            selectedItemIndex = index
                            when (index) {
                                0 -> navController.navigate(Screen.UserHome.route)
                                1 -> navController.navigate(Screen.UserRiwayat.route) // Ensure this route exists
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
        UserRiwayatScreenContent(
            modifier = Modifier.padding(padding),
            userEmail = userData.email,
        )

    }
}

@Composable
fun UserRiwayatScreenContent(
    modifier: Modifier,
    userEmail: String,
) {
    val context = LocalContext.current
    val db = BooklendDb.getInstance(context)
    val factoryPeminjaman = ViewModelFactoryPeminjaman(db.dao)
    val viewModel: MainViewModelPeminjaman = viewModel(factory = factoryPeminjaman)
    val datapeminjaman by viewModel.datapeminjaman.collectAsState()

    val filteredPeminjaman = datapeminjaman.filter { it.user.email == userEmail }

    if (filteredPeminjaman.isEmpty()) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = stringResource(R.string.riwayat_kosong))
        }
    } else {
        LazyColumn(
            modifier = modifier,
            contentPadding = PaddingValues(16.dp)
        ) {
            items(filteredPeminjaman) { peminjamanWithDetails ->
                ListPeminjaman(peminjamanWithDetails)
            }
        }
    }
}

@Composable
fun ListPeminjaman(peminjamanWithDetails: PeminjamanWithDetails) {
    val peminjaman = peminjamanWithDetails.peminjaman
    val buku = peminjamanWithDetails.buku

    val statusTextColor = if (peminjaman.status == "Ditolak") Color.Red else Color(0xFF2587DC)

    Card(
        modifier = Modifier
            .padding(bottom = 16.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp,
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(5.dp, Alignment.Top),
            horizontalAlignment = Alignment.Start,
        ) {
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.Start),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = peminjaman.status,
                    style = TextStyle(
                        fontSize = 14.sp,
                        lineHeight = 22.sp,
                        fontWeight = FontWeight(500),
                        color = statusTextColor,
                    )
                )
                Text(
                    text = "Kode Pinjam : " + peminjaman.idpeminjaman.toString(),
                    style = TextStyle(
                        fontSize = 14.sp,
                        lineHeight = 22.sp,
                        fontWeight = FontWeight(400),
                        color = Color(0xFF9D9EA8),
                        textAlign = TextAlign.Right,
                    )
                )
            }

            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.Start),
                verticalAlignment = Alignment.CenterVertically
            ) {
                val imageUri = Uri.parse(buku.gambarbuku)
                Image(
                    painter = rememberAsyncImagePainter(model = imageUri),
                    contentDescription = null,
                    modifier = Modifier
                        .width(71.dp)
                        .height(108.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.FillBounds
                )
                Column(
                    verticalArrangement = Arrangement.spacedBy(5.dp, Alignment.Top),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = "Judul Buku : " + buku.judulbuku,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Penulis : " + buku.penulisbuku,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = "Genre : " + buku.genrebuku,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(text = "Tanggal Peminjaman : " + peminjaman.tanggalpinjam)
                }
            }
        }
    }
}
@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun UserRiwayatScreenPreview() {
    BookLendTheme {
        UserRiwayatScreen(rememberNavController())
    }
}