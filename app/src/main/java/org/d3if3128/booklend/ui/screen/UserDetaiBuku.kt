package org.d3if3128.booklend.ui.screen

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import org.d3if3128.booklend.R
import org.d3if3128.booklend.database.BooklendDb
import org.d3if3128.booklend.ui.theme.BookLendTheme
import org.d3if3128.booklend.util.ViewModelFactoryBuku

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserDetailBuku(navController: NavHostController, idbuku: Long? = null) {
    val context = LocalContext.current
    val db = BooklendDb.getInstance(context)
    val factoryBuku = ViewModelFactoryBuku(db.dao)
    val viewModel: DetailViewModelBuku = viewModel(factory = factoryBuku)

    var judul by remember { mutableStateOf("") }
    var genre by remember { mutableStateOf("") }
    var penulis by remember { mutableStateOf("") }
    var tahunterbit by remember { mutableStateOf("") }
    var jumlah by remember { mutableStateOf("") }
    var deskripsi by remember { mutableStateOf("") }
    var imageUri by remember { mutableStateOf<Uri?>(null) }

    LaunchedEffect(true) {
        if (idbuku == null) return@LaunchedEffect
        val dataBuku = viewModel.getBuku(idbuku) ?: return@LaunchedEffect
        imageUri = if (dataBuku.gambarbuku.isNotEmpty()) Uri.parse(dataBuku.gambarbuku) else null
        judul = dataBuku.judulbuku
        genre = dataBuku.genrebuku
        penulis = dataBuku.penulisbuku
        tahunterbit = dataBuku.tahunterbit
        jumlah = dataBuku.jumlahbuku.toString()
        deskripsi = dataBuku.deskripsibuku
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(
                    text = stringResource(id = R.string.detail_buku),
                    style = TextStyle(
                        fontSize = 23.sp,
                        fontWeight = FontWeight.Bold )
                ) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        ReadOnlyBookDetails(
            title = judul,
            genre = genre,
            author = penulis,
            publication = tahunterbit,
            stock = jumlah,
            desc = deskripsi,
            imageUri = imageUri,
            modifier = Modifier.padding(padding)
        )
    }
}

@Composable
fun ReadOnlyBookDetails(
    title: String,
    genre: String,
    author: String,
    publication: String,
    stock: String,
    desc: String,
    imageUri: Uri?,
    modifier: Modifier
) {
    Column(
        modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        imageUri?.let { uri ->
            Image(
                painter = rememberAsyncImagePainter(uri),
                contentDescription = null,
                modifier = Modifier
                    .width(142.dp)
                    .height(216.dp)
                    .clip(RoundedCornerShape(16.dp)),
                contentScale = ContentScale.FillBounds
            )
        }

        Text(
            text = title,
            style = TextStyle(
                fontSize = 24.sp,
                lineHeight = 32.sp,
//                fontFamily = FontFamily(Font(R.font.poppins)),
                fontWeight = FontWeight(500),
                color = Color(0xFF0F0F10),
                textAlign = TextAlign.Center,
            )
        )
        Text(
            text = "$author - $publication - $genre",
            style = TextStyle(
                fontSize = 16.sp,
                lineHeight = 18.sp,
                fontWeight = FontWeight(400),
                color = Color(0xFF9D9EA8),

                textAlign = TextAlign.Center,
            )
        )
        Text(
            text = "Jumlah : $stock",
            style = TextStyle(
                fontSize = 16.sp,
                lineHeight = 18.sp,
                fontWeight = FontWeight(400),
                color = Color(0xFF9D9EA8),

                textAlign = TextAlign.Center,
            )
        )
        Text(
            text = desc,
            style = TextStyle(
                fontSize = 16.sp,
                lineHeight = 24.sp,
//                fontFamily = FontFamily(Font(R.font.inter)),
                fontWeight = FontWeight(400),
                color = Color(0xFF9D9EA8),
                textAlign = TextAlign.Center,
            )
        )

        Button(
            onClick = {},
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2587DC)),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            contentPadding = PaddingValues(),
            shape = RoundedCornerShape(size = 20.dp)
        ) {
            Text(
                text = "Pinjam Buku",
                fontWeight = FontWeight(700),
                fontSize = 16.sp
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun UserDetailBukuScreenPreview() {
    BookLendTheme {
        UserDetailBuku(rememberNavController())
    }
}
