package com.example.ticketingsystem

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun HomeScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopBarApp()
        },
        bottomBar = {
            BottomBarApp(navController)
        }
    ) { paddingValues ->
        Column(Modifier.padding(paddingValues)) {
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 20.dp)) {
                Image(painter = painterResource(id = R.drawable.banner), contentDescription = "Banner" , modifier = Modifier.fillMaxWidth())
            }
        }

    }
}

@Preview
@Composable
fun HomeScreenPreview(navController: NavHostController){
    HomeScreen(navController)
}