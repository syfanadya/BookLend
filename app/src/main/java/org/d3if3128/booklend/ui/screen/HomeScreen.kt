package org.d3if3128.booklend.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.d3if3128.booklend.R
import org.d3if3128.booklend.ui.theme.BookLendTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(){
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Image(
                        modifier = Modifier
                            .width(200.dp)
                            .height(50.dp),
                        painter = painterResource(id = R.drawable.logo_booklend_text),
                        contentDescription = "Ini logo booklend",
                        contentScale = ContentScale.FillBounds,

                        )
                },
                actions = {
                    IconButton(onClick = { }) {
                        Icon(
                            painter = painterResource(R.drawable.baseline_grid_view_24),
                            contentDescription = stringResource(R.string.grid),
                            tint = Color(0xFF2587DC)
                        )
                    }
                }
            )
        }
    ) {padding ->
        HomeScreenContent(Modifier.padding(padding))
    }
}
@Composable
fun HomeScreenContent(modifier: Modifier){
    Column (
        modifier
            .fillMaxSize()
            .padding(16.dp)
    ){

    }
}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    BookLendTheme {
        HomeScreen()
    }
}