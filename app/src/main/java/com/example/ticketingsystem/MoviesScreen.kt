package com.example.ticketingsystem

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun MoviesScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopMoviesBar()
        },
        bottomBar = {
            BottomBarApp(navController = navController)
        }
    ) {paddingValues ->
        Column(modifier = Modifier
            .padding(paddingValues)
            .fillMaxWidth()) {
            Image(painter = painterResource(id = R.drawable.moviesbanner), contentDescription = "Banner" , modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(16f / 9f))
            TagList()
            Text(text = "Upcoming Field Comes Here")
        }
    }
}