//package org.d3if3128.booklend.ui.screen
//
//import android.content.res.Configuration
//import androidx.compose.foundation.BorderStroke
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.PaddingValues
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.width
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
//import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
//import androidx.compose.foundation.lazy.staggeredgrid.items
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Email
//import androidx.compose.material.icons.filled.Home
//import androidx.compose.material.icons.filled.Notifications
//import androidx.compose.material.icons.filled.Person
//import androidx.compose.material.icons.filled.Search
//import androidx.compose.material.icons.outlined.Email
//import androidx.compose.material.icons.outlined.Home
//import androidx.compose.material.icons.outlined.Notifications
//import androidx.compose.material.icons.outlined.Person
//import androidx.compose.material3.Badge
//import androidx.compose.material3.BadgedBox
//import androidx.compose.material3.Card
//import androidx.compose.material3.CardDefaults
//import androidx.compose.material3.Divider
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.Icon
//import androidx.compose.material3.IconButton
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.NavigationBar
//import androidx.compose.material3.NavigationBarItem
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.Text
//import androidx.compose.material3.TextField
//import androidx.compose.material3.TopAppBar
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableIntStateOf
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.saveable.rememberSaveable
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.vector.ImageVector
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.res.stringResource
//import androidx.compose.ui.text.TextStyle
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.style.TextOverflow
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.lifecycle.viewmodel.compose.viewModel
//import androidx.navigation.NavHostController
//import androidx.navigation.compose.rememberNavController
//import org.d3if3128.booklend.R
//import org.d3if3128.booklend.model.Buku
//import org.d3if3128.booklend.ui.theme.BookLendTheme
//
//data class BottomNavigationItem(
//    val title: String,
//    val selectedIcon: ImageVector,
//    val unselectedIcon: ImageVector,
//    val hasNews: Boolean,
//    val badgeCount: Int? = null
//)
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun HomeScreen(navController: NavHostController) {
//    var showList by remember { mutableStateOf(true) }
//
//    val items = listOf(
//        BottomNavigationItem(
//            title = "Beranda",
//            selectedIcon = Icons.Filled.Home,
//            unselectedIcon = Icons.Outlined.Home,
//            hasNews = false
//        ),
//        BottomNavigationItem(
//            title = "Riwayat",
//            selectedIcon = Icons.Filled.Email,
//            unselectedIcon = Icons.Outlined.Email,
//            hasNews = false
//        ),
//        BottomNavigationItem(
//            title = "Notifikasi",
//            selectedIcon = Icons.Filled.Notifications,
//            unselectedIcon = Icons.Outlined.Notifications,
//            hasNews = false,
//            badgeCount = 45
//        ),
//        BottomNavigationItem(
//            title = "Akun",
//            selectedIcon = Icons.Filled.Person,
//            unselectedIcon = Icons.Outlined.Person,
//            hasNews = false
//        )
//    )
//
//    var selectedItemIndex by rememberSaveable { mutableIntStateOf(0) }
//
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = {
//                    Image(
//                        modifier = Modifier
//                            .width(200.dp)
//                            .height(50.dp),
//                        painter = painterResource(id = R.drawable.logo_booklend_text),
//                        contentDescription = stringResource(R.string.logo)
//                    )
//                },
//                actions = {
//                    IconButton(onClick = { showList = !showList }) {
//                        Icon(
//                            painter = painterResource(
//                                if (showList) R.drawable.baseline_grid_view_24
//                                else R.drawable.baseline_view_list_24
//                            ),
//                            contentDescription = stringResource(if (showList) R.string.grid else R.string.list),
//                            tint = Color(0xFF2587DC)
//                        )
//                    }
//                }
//            )
//        },
//        bottomBar = {
//            NavigationBar {
//                items.forEachIndexed { index, item ->
//                    NavigationBarItem(
//                        selected = selectedItemIndex == index,
//                        onClick = {
//                            selectedItemIndex = index
//                            //navController.navigate(Screen.DetailBuku.route)
//                        },
//                        label = {
//                            Text(text = item.title)
//                        },
//                        icon = {
//                            BadgedBox(
//                                badge = {
//                                    if (item.badgeCount != null) {
//                                        Badge {
//                                            Text(text = item.badgeCount.toString())
//                                        }
//                                    } else if (item.hasNews) {
//                                        Badge()
//                                    }
//                                }
//                            ) {
//                                Icon(
//                                    imageVector = if (index == selectedItemIndex) {
//                                        item.selectedIcon
//                                    } else item.unselectedIcon,
//                                    contentDescription = item.title,
//                                    tint = Color(0xFF2587DC)
//                                )
//                            }
//                        }
//                    )
//                }
//            }
//        }
//    ) { padding ->
//        HomeScreenContent(
//            showList,
//            Modifier.padding(padding),
//            navController, searchData = {}
//        )
//
//    }
//}
//
//
//@Composable
//fun HomeScreenContent(
//    showList: Boolean,
//    modifier: Modifier,
//    navController: NavHostController,
//    // Fungsi yang akan dipanggil saat melakukan pencarian
//    searchData: (String) -> Unit
//) {
//    var searchText by remember { mutableStateOf("") } // State untuk menyimpan teks pencarian
//    val viewModel: MainViewModelBuku = viewModel()
//    val databuku = viewModel.databuku
//
//    if (databuku.isEmpty()) {
//        Column(
//            modifier = modifier
//                .fillMaxSize()
//                .padding(16.dp),
//            verticalArrangement = Arrangement.Center,
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            Text(text = stringResource(R.string.list_buku_kosong))
//        }
//    } else {
//        Column(modifier = modifier) {
//            // Search bar
//            TextField(
//                value = searchText,
//                onValueChange = {
//                    searchText = it
//                    searchData(it) // Panggil fungsi pencarian dengan teks yang baru
//                },
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(horizontal = 16.dp, vertical = 8.dp),
//                label = {
//                    Text(
//                        "Search",
//                        style = TextStyle(color = Color(0xFF2587DC)) // Mengubah warna teks label
//                    )
//                },// Label untuk search bar
//                leadingIcon = { Icon(Icons.Filled.Search, contentDescription = "Search Icon", tint = Color(0xFF2587DC)) }, // Icon pencarian
////                singleLine = true, // Menentukan search bar hanya satu baris
//                textStyle = TextStyle(color = Color(0xFF2587DC)), // Gaya teks search bar
//                shape = MaterialTheme.shapes.medium // Bentuk search bar
//            )
//
//            if (showList) {
//                LazyColumn(
//                    modifier = Modifier.fillMaxSize()
//                ) {
//                    items(databuku) {
//                        ListBuku(buku = it) {
////                    val pesan = context.getString(R.string.x_diklik, it.judulbuku)
////                    Toast.makeText(context, pesan, Toast.LENGTH_SHORT).show()
//                        }
//                        Divider()
//                    }
//                }
//            } else {
//                LazyVerticalStaggeredGrid(
//                    modifier = Modifier.fillMaxSize(),
//                    columns = StaggeredGridCells.Fixed(2),
//                    verticalItemSpacing = 8.dp,
//                    horizontalArrangement = Arrangement.spacedBy(8.dp),
//                    contentPadding = PaddingValues(8.dp, 8.dp, 8.dp, 84.dp),
//
//                    ) {
//                    items(databuku) {
//                        GridBuku(buku = it) {
//                            //navController.navigate(Screen.Home.route)
//                        }
//                    }
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun ListBuku(buku: Buku, onClick: () -> Unit) {
//    Row(
//        modifier = Modifier
//            .clickable { onClick() }
//            .padding(16.dp),
//        horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.Start),
//        verticalAlignment = Alignment.Top
//    ) {
//        Text(text = buku.gambarbuku)
//        Column(
//            verticalArrangement = Arrangement.spacedBy(5.dp, Alignment.Top),
//            horizontalAlignment = Alignment.Start
//        ) {
//            Text(
//                text = buku.judulbuku,
//                maxLines = 2,
//                overflow = TextOverflow.Ellipsis,
//                fontWeight = FontWeight.Bold
//            )
//            Text(
//                text = buku.genrebuku,
//                maxLines = 1,
//                overflow = TextOverflow.Ellipsis
//            )
//            Text(text = buku.jumlahbuku.toString())
//        }
//    }
//}
//
//@Composable
//fun GridBuku(buku: Buku, onClick: () -> Unit) {
//    Card(
//        modifier = Modifier
//            .fillMaxWidth()
//            .clickable { onClick() },
//        colors = CardDefaults.cardColors(Color.White), // Menentukan warna latar belakang card
//        border = BorderStroke(1.dp, Color(0xFF2587DC))
//    ) {
//        Column(
//            modifier = Modifier.padding(8.dp),
//            verticalArrangement = Arrangement.spacedBy(8.dp)
//        ) {
//            Text(text = buku.gambarbuku)
//            Text(
//                text = buku.judulbuku,
//                maxLines = 2,
//                overflow = TextOverflow.Ellipsis,
//                fontWeight = FontWeight.Bold
//            )
//            Text(
//                text = buku.genrebuku,
//                maxLines = 1,
//                overflow = TextOverflow.Ellipsis
//            )
//            Text(text = buku.jumlahbuku.toString())
//        }
//    }
//}
//
//@Preview(showBackground = true)
//@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
//@Composable
//fun HomeScreenPreview() {
//    BookLendTheme {
//        HomeScreen(rememberNavController())
//    }
//}