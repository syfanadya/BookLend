package org.d3if3128.booklend.ui.screen

import android.content.res.Configuration
import android.net.Uri
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import org.d3if3128.booklend.R
import org.d3if3128.booklend.database.BooklendDb
import org.d3if3128.booklend.model.Buku
import org.d3if3128.booklend.model.User
import org.d3if3128.booklend.navigation.Screen
import org.d3if3128.booklend.network.UserDataStore
import org.d3if3128.booklend.ui.theme.BookLendTheme
import org.d3if3128.booklend.util.ViewModelFactoryBuku

const val KEY_EMAIL_USER = "email"
data class UserBottomNavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val hasNews: Boolean,
    val badgeCount: Int? = null
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController, user: User? = null) {
    val context = LocalContext.current
    val dataStore = UserDataStore(context)
    val userData = user ?: dataStore.userFlow.collectAsState(initial = User(
        email = "",
        namalengkap = "",
        nohp = "",
        usia = "",
        password = "",
        tanggalbuatakun = ""
    )).value

    var showListBuku by remember { mutableStateOf(true) }

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

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Image(
                        modifier = Modifier
                            .width(200.dp)
                            .height(50.dp),
                        painter = painterResource(id = R.drawable.logoversii2),
                        contentDescription = stringResource(R.string.logo)
                    )
                },
                actions = {
                    IconButton(onClick = { showListBuku = !showListBuku }) {
                        Icon(
                            painter = painterResource(
                                if (showListBuku) R.drawable.baseline_grid_view_24
                                else R.drawable.baseline_view_list_24
                            ),
                            contentDescription = stringResource(if (showListBuku) R.string.grid else R.string.list),
                            tint = Color(0xFF2587DC)
                        )
                    }
                }
            )
        },
        bottomBar = {
            NavigationBar( containerColor = Color(0xFFE5F0FE)) {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = selectedItemIndex == index,
                        onClick = {
                            selectedItemIndex = index
                            when (index) {
                                0 -> navController.navigate(Screen.UserHome.route)
                                1 -> navController.navigate(Screen.UserRiwayat.route)
                                2 -> navController.navigate(Screen.UserAkun.route)
                            }
                        },
                        label = { Text(text = item.title) },
                        icon = {
                            BadgedBox(
                                badge = {
                                    if (item.badgeCount != null) {
                                        Badge { Text(text = item.badgeCount.toString()) }
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
        UserHomeScreenContent(
            showListBuku = showListBuku,
            modifier = Modifier.padding(padding),
            navController = navController
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserHomeScreenContent(
    showListBuku: Boolean,
    modifier: Modifier,
    navController: NavHostController
) {
    val context = LocalContext.current
    val db = BooklendDb.getInstance(context)
    val factoryBuku = ViewModelFactoryBuku(db.dao)
    val viewModel: MainViewModelBuku = viewModel(factory = factoryBuku)
    val databuku by viewModel.databuku.collectAsState()
    val searchText by viewModel.searchText.collectAsState()

    val filteredBooks = if (searchText.isEmpty()) {
        databuku
    } else {
        databuku.filter { it.doesMactchSearchQuery(searchText) }
    }

    if (databuku.isEmpty()) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = stringResource(R.string.list_buku_kosong))
        }
    } else {
        Column(modifier = modifier) {
            // Search bar
            TextField(
                value = searchText,
                onValueChange = viewModel::onSearchTextChange,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                placeholder = { Text(text = "Search") },
                leadingIcon = { Icon(Icons.Filled.Search,
                contentDescription = "Search Icon",
                tint = Color(0xFF2587DC)) },
                textStyle = TextStyle(color = Color(0xFF2587DC)),
                shape = MaterialTheme.shapes.medium,
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color(0xFFE5F0FE) // Warna latar belakang TextField
                ),
            )
            if (showListBuku) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(bottom = 84.dp)
                ) {
                    items(filteredBooks) { buku ->
                        ListBuku2(buku = buku) {
                            navController.navigate(Screen.UserDetailBuku.withIdBuku(buku.idbuku))
                        }
                        Divider()
                    }
                }
            } else {
                LazyVerticalStaggeredGrid(
                    modifier = Modifier.fillMaxSize(),
                    columns = StaggeredGridCells.Fixed(2),
                    verticalItemSpacing = 8.dp,
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding = PaddingValues(8.dp, 8.dp, 8.dp, 84.dp)
                ) {
                    items(filteredBooks) { buku ->
                        GridBuku2(buku = buku) {
                            navController.navigate(Screen.UserDetailBuku.withIdBuku(buku.idbuku))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ListBuku2(buku: Buku, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .clickable { onClick() }
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
                text = buku.judulbuku,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Genre : " + buku.genrebuku,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(text = "Stok : " + buku.jumlahbuku.toString())
        }
    }
}

@Composable
fun GridBuku2(buku: Buku, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        colors = CardDefaults.cardColors(Color.White),
        border = BorderStroke(1.dp, Color(0xFF2587DC))
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
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
            Text(
                text = buku.judulbuku,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Genre : " + buku.genrebuku,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(text = "Stok : " + buku.jumlahbuku.toString())
        }
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun HomeScreenPreview() {
    BookLendTheme {
        HomeScreen(rememberNavController())
    }
}