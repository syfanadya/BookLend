package org.d3if3128.booklend.ui.screen

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import org.d3if3128.booklend.R
import org.d3if3128.booklend.database.BooklendDb
import org.d3if3128.booklend.ui.theme.BookLendTheme
import org.d3if3128.booklend.util.ViewModelFactoryBuku
import android.provider.MediaStore

const val KEY_ID_BUKU = "idBuku"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminDetailBuku(navController: NavHostController, idbuku: Long? = null) {
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

    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri: Uri? ->
        if (uri != null) {
            val bitmap = MediaStore.Images.Media.getBitmap(context.contentResolver, uri)
            val savedUri = viewModel.saveImageToInternalStorage(context, bitmap, "book_cover_${System.currentTimeMillis()}.png")
            imageUri = savedUri
        }
    }

    LaunchedEffect(idbuku) {
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
                title = {
                    Text(text = if (idbuku == null) stringResource(id = R.string.tambah_buku) else stringResource(id = R.string.edit_buku))
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = {
                        if (imageUri.toString().isEmpty() || judul.isEmpty() || genre.isEmpty() || penulis.isEmpty() ||
                            tahunterbit.isEmpty() || jumlah.isEmpty() || deskripsi.isEmpty()) {
                            Toast.makeText(context, R.string.invalid, Toast.LENGTH_LONG).show()
                            return@IconButton
                        }
                        if (idbuku == null) {
                            viewModel.insert(imageUri.toString(), judul, genre, penulis, tahunterbit, jumlah.toInt(), deskripsi)
                        } else {
                            viewModel.update(idbuku, imageUri.toString(), judul, genre, penulis, tahunterbit, jumlah.toInt(), deskripsi)
                        }
                        navController.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.Outlined.Check,
                            contentDescription = stringResource(R.string.simpan),
                            tint = Color(0xFF2587DC)
                        )
                    }
                    if (idbuku != null) {
                        DeleteAction {
                            viewModel.delete(idbuku)
                            navController.popBackStack()
                        }
                    }
                }
            )
        }
    ) { padding ->
        FormBuku(
            title = judul,
            onTitleChange = { judul = it },
            genre = genre,
            onGenreChange = { genre = it },
            author = penulis,
            onAuthorChange = { penulis = it },
            publication = tahunterbit,
            onPublicationChange = { tahunterbit = it },
            stock = jumlah,
            onStockChange = { jumlah = it },
            desc = deskripsi,
            onDescChange = { deskripsi = it },
            imageUri = imageUri,
            onImageClick = { launcher.launch("image/*") },
            modifier = Modifier.padding(padding)
        )
    }
}



@Composable
fun DeleteAction(delete: () -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }

    IconButton(onClick = { expanded = true }) {
        Icon(imageVector = Icons.Filled.MoreVert, contentDescription = stringResource(R.string.lainnya), tint = Color(0xFF2587DC))
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            DropdownMenuItem(
                text = { Text(text = stringResource(id = R.string.hapus)) },
                onClick = {
                    expanded = false
                    showDialog = true
                }
            )
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text(text = stringResource(id = R.string.konfirmasi_hapus)) },
            text = { Text(text = stringResource(id = R.string.pesan_konfirmasi_hapus)) },
            confirmButton = {
                TextButton(onClick = {
                    showDialog = false
                    delete()
                }) {
                    Text(text = stringResource(id = R.string.ya))
                }
            },
            dismissButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text(text = stringResource(id = R.string.tidak))
                }
            }
        )
    }
}

@Composable
fun FormBuku(
    title: String, onTitleChange: (String) -> Unit,
    genre: String, onGenreChange: (String) -> Unit,
    author: String, onAuthorChange: (String) -> Unit,
    publication: String, onPublicationChange: (String) -> Unit,
    stock: String, onStockChange: (String) -> Unit,
    desc: String, onDescChange: (String) -> Unit,
    imageUri: Uri?, onImageClick: () -> Unit,
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

        Button(
            onClick = onImageClick,
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2587DC)),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            contentPadding = PaddingValues(),
            shape = RoundedCornerShape(size = 20.dp)
        ) {
            Text(text = "Upload Cover Buku")
        }

        OutlinedTextField(
            value = title,
            onValueChange = { onTitleChange(it) },
            label = { Text(text = "Judul") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Words,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = genre,
            onValueChange = { onGenreChange(it) },
            label = { Text(text = "Genre") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Sentences,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = author,
            onValueChange = { onAuthorChange(it) },
            label = { Text(text = "Penulis") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Words,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = publication,
            onValueChange = { onPublicationChange(it) },
            label = { Text(text = "Tahun Terbit") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = stock,
            onValueChange = { onStockChange(it) },
            label = { Text(text = "Jumlah") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = desc,
            onValueChange = { onDescChange(it) },
            label = { Text(text = "Deskripsi") },
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Sentences,
                imeAction = ImeAction.Done
            ),
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AdminDetailBukuScreenPreview() {
    BookLendTheme {
        AdminDetailBuku(rememberNavController())
    }
}
