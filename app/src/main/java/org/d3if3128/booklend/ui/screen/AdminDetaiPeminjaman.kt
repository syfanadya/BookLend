package org.d3if3128.booklend.ui.screen

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.d3if3128.booklend.R
import org.d3if3128.booklend.database.BooklendDb
import org.d3if3128.booklend.ui.theme.BookLendTheme
import org.d3if3128.booklend.util.ViewModelFactoryPeminjaman

const val KEY_ID_PEMINJAMAN = "idPeminjaman"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminDetailPeminjaman(navController: NavHostController, idpeminjaman: Long? = null) {
    val context = LocalContext.current
    val db = BooklendDb.getInstance(context)
    val factoryPeminjaman = ViewModelFactoryPeminjaman(db.dao)
    val viewModel: DetailViewModelPeminjaman = viewModel(factory = factoryPeminjaman)

    var kodePeminjaman by remember { mutableStateOf("") }
    var peminjam by remember { mutableStateOf("N/A") }
    var usiaPeminjam by remember { mutableStateOf("N/A") }
    var judulBuku by remember { mutableStateOf("N/A") }
    var genre by remember { mutableStateOf("N/A") }
    var status by remember { mutableStateOf("N/A") }
    var tanggalPinjam by remember { mutableStateOf("N/A") }
    var tanggalKembali by remember { mutableStateOf("N/A") }


    LaunchedEffect(idpeminjaman) {
        if (idpeminjaman == null) return@LaunchedEffect
        val dataPeminjaman = viewModel.getPeminjaman(idpeminjaman)
        if (dataPeminjaman != null) {
            kodePeminjaman = dataPeminjaman.peminjaman.idpeminjaman.toString()
            peminjam = dataPeminjaman.user.namalengkap
            usiaPeminjam = dataPeminjaman.user.usia
            judulBuku = dataPeminjaman.buku.judulbuku
            genre = dataPeminjaman.buku.genrebuku
            status = dataPeminjaman.peminjaman.status
            tanggalPinjam = dataPeminjaman.peminjaman.tanggalpinjam
            tanggalKembali = dataPeminjaman.peminjaman.tanggalkembali ?: ""
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                        Text(text = stringResource(id = R.string.detail_peminjaman))
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = {
                        if (status.isEmpty() || tanggalKembali.isEmpty()) {
                            Toast.makeText(context, R.string.invalid, Toast.LENGTH_LONG).show()
                        } else {
                            viewModel.updatePeminjamanStatus(
                                idpeminjaman = idpeminjaman ?: 0,
                                status = status,
                                tanggalKembali = tanggalKembali
                            )
                            Toast.makeText(context, "Status berhasil diperbarui", Toast.LENGTH_SHORT).show()
                            navController.popBackStack()
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Outlined.Check,
                            contentDescription = stringResource(R.string.simpan),
                            tint = Color(0xFF2587DC)
                        )
                    }
                }
            )
        }
    ) { padding ->
        if (kodePeminjaman.isNotEmpty()) {
            FormPeminjaman(
                borrowCode = kodePeminjaman.toLong(),
                borrower = peminjam,
                age = usiaPeminjam,
                title = judulBuku,
                genre = genre,
                status = status,
                onStatusChange = { status = it },
                borrowDate = tanggalPinjam,
                dateBack = tanggalKembali,
                onDateBackChange = { tanggalKembali = it },
                modifier = Modifier.padding(padding)
            )
        } else {
            Text(text = "Loading...", modifier = Modifier.padding(padding))
        }
    }
}


@Composable
fun FormPeminjaman(
    borrowCode: Long,
    borrower: String,
    age: String,
    title: String,
    genre: String,
    status: String, onStatusChange: (String) -> Unit,
    borrowDate: String,
    dateBack: String,onDateBackChange: (String) -> Unit,
    modifier: Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    val statusOptions = listOf("Menunggu Persetujuan", "Ditolak", "Selesai")

    Column(
        modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = borrowCode.toString(),
            onValueChange = {},
            label = { Text(text = "Kode Peminjaman") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            enabled = false
        )
        OutlinedTextField(
            value = borrower,
            onValueChange = {},
            label = { Text(text = "Peminjam") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            enabled = false
        )
        OutlinedTextField(
            value = age,
            onValueChange = {},
            label = { Text(text = "Usia Peminjam") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            enabled = false
        )
        OutlinedTextField(
            value = title,
            onValueChange = {},
            label = { Text(text = "Judul Buku") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            enabled = false
        )
        OutlinedTextField(
            value = genre,
            onValueChange = {},
            label = { Text(text = "Genre Buku") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            enabled = false
        )
        OutlinedTextField(
            value = borrowDate,
            onValueChange = {},
            label = { Text(text = "Tanggal Pinjam") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            enabled = false
        )
        OutlinedTextField(
            value = status,
            onValueChange = { onStatusChange(it) },
            label = { Text(text = "Status Peminjaman") },
            singleLine = true,
            readOnly = true,
            trailingIcon = {
                val icon = if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown
                Icon(icon, contentDescription = null, Modifier.clickable { expanded = !expanded })
            },
            modifier = Modifier.fillMaxWidth()
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            statusOptions.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        onStatusChange(option)
                        expanded = false
                    }
                )
            }
        }
        OutlinedTextField(
            value = dateBack,
            onValueChange = { onDateBackChange(it) },
            label = { Text(text = "Tanggal Kembali") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            modifier = Modifier.fillMaxWidth()
        )

    }
}

@Preview(showBackground = true)
@Composable
fun AdminDetailPeminjamanScreenPreview() {
    BookLendTheme {
        AdminDetailPeminjaman(rememberNavController())
    }
}
