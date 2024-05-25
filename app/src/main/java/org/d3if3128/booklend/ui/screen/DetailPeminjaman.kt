package org.d3if3128.booklend.ui.screen


import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import org.d3if3128.booklend.R
import org.d3if3128.booklend.ui.theme.BookLendTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailPeminjaman() {

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = " Detail Peminjaman" ,
                    style = TextStyle(
                        fontSize = 23.sp,
                        fontWeight = FontWeight.Bold )
                ) },
                navigationIcon = {
                    IconButton(onClick = { /* Handle back button click */ }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        FormPeminjaman(
            kodePeminjam = "1",
            onKodePeminjamChange = { /* handle kodePeminjam change */ },
            namaPeminjam = "Dyna Rosalina Pangaribuan",
            onNamaPeminjamChange = { /* handle nama change */ },
            judulBuku = "A New Program for Graphic Design",
            onJudulBukuChange = { /* handle judulBuku change */ },
            genre = "Edukasi",
            onGenreChange = { /* handle genre change */ },
            status = "------",
            onStatusChange = { /* handle status change */ },
            tanggalPinjam = "2024-05-05",
            onTanggalPinjamChange = { /* handle tanggalPinjam change */ },
            tanggalKembali = "2024-05-05",
            onTanggalKembaliChange = { /* handle tanggalKembali change */ },
            modifier = Modifier.padding(padding)

        )
    }
}


@Composable
fun FormPeminjaman(
    kodePeminjam: String,
    onKodePeminjamChange: (String) -> Unit,
    namaPeminjam: String,
    onNamaPeminjamChange: (String) -> Unit,
    judulBuku: String,
    onJudulBukuChange: (String) -> Unit,
    genre: String,
    onGenreChange: (String) -> Unit,
    status: String,
    onStatusChange: (String) -> Unit,
    tanggalPinjam: String,
    onTanggalPinjamChange: (String) -> Unit,
    tanggalKembali: String,
    onTanggalKembaliChange: (String) -> Unit,
    modifier: Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(32.dp, Alignment.Top),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = kodePeminjam,
            onValueChange = { onKodePeminjamChange(it) },
            readOnly = true,
            label = { Text(text = "Kode Pinjam") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier
                .padding(0.dp)
                .fillMaxWidth()
                .height(56.dp),

            // M3/body/small
            textStyle = TextStyle(
                fontSize = 12.sp,
                lineHeight = 16.sp,
                fontWeight = FontWeight(400),
                color = Color(0xFF49454F),
            )
        )

        OutlinedTextField(
            value = namaPeminjam,
            onValueChange = { onNamaPeminjamChange(it) },
            readOnly = true,
            label = { Text(text = "Peminjam") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier
                .padding(0.dp)
                .fillMaxWidth()
                .height(56.dp),

            // M3/body/small
            textStyle = TextStyle(
                fontSize = 12.sp,
                lineHeight = 16.sp,
                fontWeight = FontWeight(400),
                color = Color(0xFF49454F),
            )
        )

        OutlinedTextField(
            value = judulBuku,
            onValueChange = { onJudulBukuChange(it) },
            readOnly = true,
            label = { Text(text = "Judul Buku") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier
                .padding(0.dp)
                .fillMaxWidth()
                .height(56.dp),

            // M3/body/small
            textStyle = TextStyle(
                fontSize = 12.sp,
                lineHeight = 16.sp,
                fontWeight = FontWeight(400),
                color = Color(0xFF49454F),
            )
        )


        OutlinedTextField(
            value = genre,
            onValueChange = { onGenreChange(it) },
            readOnly = true,
            label = { Text(text = "Genre") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            modifier = Modifier
                .padding(0.dp)
                .fillMaxWidth()
                .height(56.dp),

            // M3/body/small
            textStyle = TextStyle(
                fontSize = 12.sp,
                lineHeight = 16.sp,
                fontWeight = FontWeight(400),
                color = Color(0xFF49454F),
            )
        )

        StatusDropdown(
            status = status,
            onStatusChange = onStatusChange,
            statusError = status.isEmpty()
        )

        OutlinedTextField(
            value = tanggalPinjam,
            onValueChange = { onTanggalPinjamChange(it) },
            readOnly = true,
            label = { Text(text = "Tanggal Pinjam") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier
                .padding(0.dp)
                .fillMaxWidth()
                .height(56.dp),

            // M3/body/small
            textStyle = TextStyle(
                fontSize = 12.sp,
                lineHeight = 16.sp,
                fontWeight = FontWeight(400),
                color = Color(0xFF49454F),
            )
        )

        OutlinedTextField(
            value = tanggalKembali,
            onValueChange = { onTanggalKembaliChange(it) },
            readOnly = true,
            label = { Text(text = "Tanggal Kembali") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier
                .padding(0.dp)
                .fillMaxWidth()
                .height(56.dp),

            // M3/body/small
            textStyle = TextStyle(
                fontSize = 12.sp,
                lineHeight = 16.sp,
                fontWeight = FontWeight(400),
                color = Color(0xFF49454F),
            )
        )

        Button(
            onClick = {},
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2587DC)),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            contentPadding = PaddingValues(),
            shape = RoundedCornerShape(size = 66.dp)
        ) {
            Text(
                text = stringResource(R.string.simpan),
                fontWeight = FontWeight(700),
                fontSize = 16.sp
            )
        }
    }
}

@Composable
fun StatusDropdown(
    status: String,
    onStatusChange: (String) -> Unit,
    statusError: Boolean
) {
    var expanded by remember { mutableStateOf(false) }
    var textFieldSize by remember { mutableStateOf(Size.Zero) }
    val statusOptions = listOf("Setujui", "Tolak", "Selesai")
    val icon = Icons.Filled.ArrowDropDown

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            value = status,
            onValueChange = { onStatusChange(it) },
            readOnly = true,
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    textFieldSize = coordinates.size.toSize()
                },
            label = { Text(text = "Status") },
            isError = statusError,
            trailingIcon = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(icon, "", Modifier.clickable { expanded = !expanded })
                }
            },
            supportingText = {
                if (statusError) {
                    Text(text = "Error: Please select a status", color = Color.Red)
                }
            },
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.width(with(LocalDensity.current) { textFieldSize.width.toDp() })
        ) {
            statusOptions.forEach { label ->
                DropdownMenuItem(
                    text = { Text(text = label) },
                    onClick = {
                        onStatusChange(label)
                        expanded = false
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PeminjamanPreview() {
    BookLendTheme {
        DetailPeminjaman()
    }
}
