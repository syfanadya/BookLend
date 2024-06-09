package org.d3if3128.booklend.ui.screen

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.d3if3128.booklend.R
import org.d3if3128.booklend.database.BooklendDb
import org.d3if3128.booklend.model.User
import org.d3if3128.booklend.network.UserDataStore
import org.d3if3128.booklend.ui.theme.BookLendTheme
import org.d3if3128.booklend.util.ViewModelFactoryUser

const val KEY_ID_USER = "idUser"

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UbahProfil(navController: NavHostController, iduser: Long? = null) {
    val context = LocalContext.current
    val db = BooklendDb.getInstance(context)
    val dao = db.dao // Dapatkan dao dari BooklendDb
    val userDataStore = UserDataStore(context)
    val factoryUser = ViewModelFactoryUser(dao, userDataStore)
    val viewModel: DetailViewModelUser = viewModel(factory = factoryUser)

    var namalengkap by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var nohp by remember { mutableStateOf("") }
    var usia by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var tanggalbuatakun by remember { mutableStateOf("") }

    LaunchedEffect(true) {
        if (iduser == null) return@LaunchedEffect
        val dataUser = viewModel.getUser2(iduser) ?: return@LaunchedEffect
        namalengkap = dataUser.namalengkap
        email = dataUser.email
        nohp = dataUser.nohp
        usia = dataUser.usia
        password = dataUser.password
        tanggalbuatakun = dataUser.tanggalbuatakun
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Ubah Profil",
                        style = TextStyle(
                            fontSize = 23.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        FormUbahProfil(
            fullname = namalengkap,
            onFullnameChange = { namalengkap = it },
            email = email,
            noPhone = nohp,
            onNoPhoneChange = { nohp = it },
            age = usia,
            onAgeChange = { usia = it },
            saveButton = {
                if (namalengkap.isEmpty() || nohp.isEmpty() || usia.isEmpty()) {
                    Toast.makeText(context, R.string.invalid, Toast.LENGTH_LONG).show()
                } else {
                    viewModel.updateUser(
                        User(
                            iduser = iduser ?: 0,
                            namalengkap = namalengkap,
                            nohp = nohp,
                            usia = usia,
                            email = email,
                            password = password,
                            tanggalbuatakun = tanggalbuatakun
                        )
                    )
                    Toast.makeText(context, "Profil berhasil diperbarui", Toast.LENGTH_SHORT).show()
                    navController.popBackStack()
                }
            },
            modifier = Modifier.padding(padding)
        )
    }
}

@Composable
fun FormUbahProfil(
    fullname: String,
    onFullnameChange: (String) -> Unit,
    noPhone: String,
    onNoPhoneChange: (String) -> Unit,
    age: String,
    onAgeChange: (String) -> Unit,
    email: String,
    saveButton: () -> Unit,
    modifier: Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            modifier = Modifier
                .width(150.dp)
                .height(150.dp),
            painter = painterResource(id = R.drawable.baseline_account_circle_24),
            contentDescription = stringResource(id = R.string.profil),
            contentScale = ContentScale.FillBounds,
        )
        OutlinedTextField(
            value = fullname,
            onValueChange = { onFullnameChange(it) },
            label = { Text(text = "Nama Lengkap") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Words,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = email,
            onValueChange = { },
            label = { Text(text = "Email") },
            singleLine = true,
            readOnly = true,
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = noPhone,
            onValueChange = { onNoPhoneChange(it) },
            label = { Text(text = "Nomor Hp") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = age,
            onValueChange = { onAgeChange(it) },
            label = { Text(text = "Usia") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = saveButton,
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2587DC)),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            contentPadding = PaddingValues(),
            shape = RoundedCornerShape(size = 20.dp)
        ) {
            Text(
                text = "Simpan",
                fontWeight = FontWeight(700),
                fontSize = 16.sp
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun UbahProfilPreview() {
    BookLendTheme {
        UbahProfil(rememberNavController())
    }
}
