package org.d3if3128.booklend.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import org.d3if3128.booklend.R
import org.d3if3128.booklend.navigation.Screen
import org.d3if3128.booklend.ui.theme.BookLendTheme


@Composable
fun MainScreen2(navController: NavController) {
    // Scaffold with ScreenContent
    Scaffold(
        topBar = {
            // TopAppBar setup if needed
        }
    ) { padding ->
        // ScreenContent displayed within Scaffold
        ScreenContent2(navController = navController, modifier = Modifier.padding(padding))
    }
}


@Composable
fun ScreenContent2(navController: NavController,modifier: Modifier) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        // Gambar latar belakang di bagian paling bawah
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter), // Meletakkan gambar di bagian bawah tengah
            painter = painterResource(id = R.drawable.bg_biru),
            contentDescription = null,
            contentScale = ContentScale.FillBounds
        )

        // Konten di atas gambar latar belakang
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp), // Padding untuk konten di dalam Box
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Text di atas gambar
            val textStyle = TextStyle(
                fontFamily = FontFamily.Serif,
                fontSize = 14.sp
            )


            // Spacer untuk memberi ruang di atas teks

            // Gambar lain di atas latar belakang
            val logoPainter = painterResource(id = R.drawable.logogambar)
            Image(
                painter = logoPainter,
                contentDescription = "Logo",
                modifier = Modifier
                    .size(width = 106.dp, height = 97.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))


            val logoTextPainter = painterResource(id = R.drawable.logo_booklend_text)
            Image(
                painter = logoTextPainter,
                contentDescription = "Logo Text",
                modifier = Modifier
                    .alpha(1f)
                    .size(width = 200.dp, height = 50.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            Text(text = stringResource(id = R.string.s3), style = textStyle)
            Text(text = stringResource(id = R.string.s4), style = textStyle)

            // Button "Masuk" ditampilkan di atas gambar latar belakang

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.8f)
                    .padding(16.dp), // Padding untuk konten di dalam Box
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = { navController.navigate(Screen.AdminLanding.route) },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                    modifier = Modifier
                        .width(249.dp)
                        .height(56.dp),
                    contentPadding = PaddingValues(),
                    shape = RoundedCornerShape(size = 18.dp)

                ) {
                    Text(
                        text = stringResource(id = R.string.admin),
                        fontWeight = FontWeight(700),
                        fontSize = 16.sp,
                        color = Color.Black
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))
                Text(text = stringResource(id = R.string.atau), style = textStyle, color = Color.White)
                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = {  navController.navigate(Screen.Login.route) },
                    colors = ButtonDefaults.buttonColors(Color.Transparent), // Mengatur warna latar belakang menjadi transparan
                    modifier = Modifier
                        .width(249.dp)
                        .height(56.dp),
                    contentPadding = PaddingValues(),
                    shape = RoundedCornerShape(size = 18.dp),
                    border = BorderStroke(0.5.dp, Color.White),

                    ) {
                    Text(
                        text = stringResource(id = R.string.pengguna),
                        fontWeight = FontWeight(700),
                        fontSize = 16.sp,
                        color = Color.White // Atur warna teks (opsional)
                    )
                }
            }



        }
    }
}





@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun GreetingPreview2() {
    BookLendTheme{
        MainScreen2(rememberNavController())
    }
}