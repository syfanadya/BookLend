package org.d3if3128.booklend.ui.screen

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
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
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.d3if3128.booklend.R
import org.d3if3128.booklend.database.BooklendDb
import org.d3if3128.booklend.navigation.Screen
import org.d3if3128.booklend.ui.theme.BookLendTheme
import org.d3if3128.booklend.util.ViewModelFactoryUser

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(navController: NavHostController) {
    val context = LocalContext.current
    val db = BooklendDb.getInstance(context)
    val factoryUser = ViewModelFactoryUser(db.dao)
    val viewModel: DetailViewModelUser = viewModel(factory = factoryUser)

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmationPassword by remember { mutableStateOf("") }

    var registerRequested by remember { mutableStateOf(false) }

    LaunchedEffect(registerRequested) {
        if (registerRequested) {
            when {
                email.isBlank() || password.isBlank() || confirmationPassword.isBlank() -> {
                    Toast.makeText(context, R.string.invalid, Toast.LENGTH_LONG).show()
                }
                password != confirmationPassword -> {
                    Toast.makeText(context, R.string.password_mismatch, Toast.LENGTH_LONG).show()
                }
                else -> {
                    if (viewModel.isEmailRegistered(email)) {
                        Toast.makeText(context,R.string.email_sudah_terdaftar, Toast.LENGTH_LONG).show()
                    } else {
                        viewModel.insert(email, password)
                        navController.navigate(Screen.Login.route)
                    }
                }
            }
            // Reset the registration request state
            registerRequested = false
        }
    }

    val registerAction: () -> Unit = {
        registerRequested = true
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Register") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
            )
        }
    ) { padding ->
        // FormLogin call updated here if needed to use navController
        FormRegister(
            email = email,
            onEmailChange = { email = it },
            password = password,
            onPasswordChange = { password = it },
            confirmationPassword = confirmationPassword,
            onConfirmationPasswordChange = { confirmationPassword = it },
            modifier = Modifier.padding(padding),
            navController = navController,
            regisButton = registerAction
        )
    }
}



@Composable
fun FormRegister(
    email: String,
    onEmailChange: (String) -> Unit,
    password: String,
    onPasswordChange: (String) -> Unit,
    confirmationPassword: String,
    onConfirmationPasswordChange: (String) -> Unit,
    regisButton : () -> Unit,
    modifier: Modifier,
    navController: NavController,

) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    var passwordVisible by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp,  Alignment.Top),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .width(200.dp)
                .height(50.dp),
            painter = painterResource(R.drawable.logoversi2),
            contentDescription = stringResource(id = R.string.logo),
            contentScale = ContentScale.FillBounds,
        )
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = "Masuk ke akun anda! untuk menikmati fitur - fitur BookLend.",
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight(400),
                color = Color(0xFF49454F),
                letterSpacing = 0.07.sp
            )
        )
        OutlinedTextField(
            value = email,
            onValueChange = { onEmailChange(it) },
            label = { Text(text = "Email") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = password,
            onValueChange = { onPasswordChange(it) },
            label = { Text(text = "Password") },
            singleLine = true,
            visualTransformation =
            if (passwordVisible) VisualTransformation.None
            else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Next
            ),trailingIcon = {
                val image: Painter = if (passwordVisible) {
                    painterResource(id = R.drawable.baseline_visibility_24)
                } else {
                    painterResource(id = R.drawable.baseline_visibility_off_24)
                }
                val description = if (passwordVisible) "Hide password" else "Show password"

                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(painter = image, contentDescription = description)
                }
            },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = confirmationPassword,
            onValueChange = { onConfirmationPasswordChange(it) },
            label = { Text(text = "Konfirmasi Password") },
            singleLine = true,
            visualTransformation =
            if (passwordVisible) VisualTransformation.None
            else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),


            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = regisButton,
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2587DC)),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            contentPadding = PaddingValues(),
            shape = RoundedCornerShape(size = 20.dp)
        ) {
            Text(
                text = "Daftar",
                fontWeight = FontWeight(700),
                fontSize = 16.sp
            )
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically,
        ) {

            Text(
                text = "Atau masuk dengan",
                style = TextStyle(
                    fontSize = 14.sp,
                    lineHeight = 24.sp,
                    fontWeight = FontWeight(400),
                    color = Color(0xFF49454F),

                    letterSpacing = 0.5.sp,
                )
            )
        }
        OutlinedButton(
            onClick = {},
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            contentPadding = PaddingValues(),
            shape = RoundedCornerShape(size = 4.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    modifier = Modifier
                        .width(16.dp)
                        .height(16.dp),
                    painter = painterResource(R.drawable.icon_google),
                    contentDescription = stringResource(id = R.string.icon_google),
                    contentScale = ContentScale.FillBounds
                )

                Text(
                    text = "Masuk dengan Google",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight(700),
                        color = Color(0xFF222222),
                    )
                )

            }

        }

        Text(
            text = "Sudah punya akun ? Masuk",
            style = TextStyle(
                fontSize = 16.sp,
                lineHeight = 24.sp,
                fontWeight = FontWeight(400),
                letterSpacing = 0.5.sp,
                color = if (isPressed) Color(0xFF2587DC) else Color(0xFF49454F),
            ),
            modifier = Modifier.clickable(
                interactionSource = interactionSource,
                indication = null
            ) { navController.navigate(Screen.Login.route) }
        )
    }
}


@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    BookLendTheme {
        val navController = rememberNavController()
        RegisterScreen(navController)
    }
}
