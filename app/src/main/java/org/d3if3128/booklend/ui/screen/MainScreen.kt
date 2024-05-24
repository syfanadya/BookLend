package org.d3if3128.booklend.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.delay
import org.d3if3128.booklend.R
import org.d3if3128.booklend.navigation.Screen
import org.d3if3128.booklend.ui.theme.BookLendTheme


@Composable
fun MainScreen(navController: NavHostController) {
    // State to track whether we should navigate to the AboutScreen


    var navigateToLoginScreen by remember { mutableStateOf(false) }

    // LaunchedEffect to navigate after 5 seconds
    LaunchedEffect(true) {
        delay(5000) // Delay for 5 seconds
        navigateToLoginScreen = true // Set the flag to navigate
    }

    // Scaffold with ScreenContent
    Scaffold(
        topBar = {
            // TopAppBar setup if needed
        }
    ) { padding ->
        // ScreenContent displayed within Scaffold
        ScreenContent(modifier = Modifier.padding(padding))
    }

    if (navigateToLoginScreen) {
        navController.navigate(Screen.Home2.route) {
            // Pop up to the home screen to avoid a back stack of screens
            popUpTo(Screen.Home.route) { inclusive = true }
        }
    }
}

@Composable
fun ScreenContent(modifier: Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val imagePainter: Painter = painterResource(id = R.drawable.logogambar)
        Image(
            painter = imagePainter,
            contentDescription = "Logo",
            modifier = Modifier
                .size(width = 106.dp, height = 97.dp) // Set the desired width and height
                .padding(8.dp),
            contentScale = ContentScale.Fit
        )
        val imagePainterr: Painter = painterResource(id = R.drawable.logo_booklend_text)
        Image(
            painter = imagePainterr,
            contentDescription = "Logo",
            modifier = Modifier
                .size(width = 200.dp, height = 50.dp) // Set the desired width and height
                .padding(8.dp),
            contentScale = ContentScale.Fit
        )

    }
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        val textStyle = TextStyle(
            fontFamily = FontFamily.Serif, // Menggunakan font Roboto
            fontSize = 14.sp, // Ukuran font 12sp
            // Anda juga dapat menambahkan properti lain seperti fontWeight, fontStyle, dll. sesuai kebutuhan
        )
        Spacer(modifier = Modifier.height(550.dp))

        Text(text = stringResource(id = R.string.s1), style = textStyle )
        Text(text = stringResource(id = R.string.s2), style = textStyle )
    }
}







@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun GreetingPreview() {
    BookLendTheme {
        MainScreen(rememberNavController())
    }
}