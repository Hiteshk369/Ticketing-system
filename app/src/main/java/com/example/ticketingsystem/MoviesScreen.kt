package com.example.ticketingsystem

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController

@Composable
fun MoviesScreen(navController: NavHostController) {
    Scaffold(
        bottomBar = {
            BottomBarApp(navController = navController)
        }
    ) {paddingValues -> 
        Column(modifier = Modifier.padding(paddingValues)) {
            Text(text = "Movies")
        }
    }
}