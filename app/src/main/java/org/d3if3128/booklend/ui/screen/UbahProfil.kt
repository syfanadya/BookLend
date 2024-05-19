package org.d3if3128.booklend.ui.screen


import android.annotation.SuppressLint
import androidx.compose.foundation.Image
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.d3if3128.booklend.R
import org.d3if3128.booklend.model.DataGambar
import org.d3if3128.booklend.ui.theme.BookLendTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UbahProfil() {

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = " Ubah Profil" ,
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
        FormUbahProfil(
            nama = "Dyna Rosalina Pangaribuan",
            onNamaChange = { /* handle nama change */ },
            email = "dyna@gmail.com",
            onEmailChange = { /* handle email change */ },
            nomorHp = "+6282275849613",
            onNomorHPChange = { /* handle nomorHP change */ },
            umur = "19  Tahun",
            onUmurChange = { /* handle umur change */ },
            modifier = Modifier.padding(padding)

        )
    }
}


@Composable
fun FormUbahProfil(
    nama: String,
    onNamaChange: (String) -> Unit,
    email: String,
    onEmailChange: (String) -> Unit,
    nomorHp: String,
    onNomorHPChange: (String) -> Unit,
    umur: String,
    onUmurChange: (String) -> Unit,
    modifier: Modifier,
) {
    val data = getData()
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(32.dp, Alignment.Top),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .width(100.dp)
                .height(100.dp),
            painter = painterResource(id = data[0].imageResId),
            contentDescription = stringResource(id = R.string.profil),
            contentScale = ContentScale.FillBounds,
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(0.dp, Alignment.Top),
            horizontalAlignment = Alignment.Start,
        ) {
            OutlinedTextField(
                value = nama,
                onValueChange = { onNamaChange(it) },
                label = { Text(text = "Nama Lengkap") },
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
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(0.dp, Alignment.Top),
            horizontalAlignment = Alignment.Start,
        ) {
            OutlinedTextField(
                value = email,
                onValueChange = { onEmailChange(it) },
                label = { Text(text = "Email") },
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
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(0.dp, Alignment.Top),
            horizontalAlignment = Alignment.Start,
        ) {
            OutlinedTextField(
                value = nomorHp,
                onValueChange = { onNomorHPChange(it) },
                label = { Text(text = "Nomor Hp") },
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
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.clickable {  }
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(0.dp, Alignment.Top),
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.weight(1f)
            ) {
                OutlinedTextField(
                    value = umur,
                    onValueChange = { onUmurChange(it) },
                    label = { Text(text = "Umur") },
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
            }
        }

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

private fun getData(): List<DataGambar> {
    return listOf(
        DataGambar("Profil", R.drawable.profil)
    )
}

@Preview(showBackground = true)
@Composable
fun UbahProfilPreview() {
    BookLendTheme {
        UbahProfil()
    }
}
