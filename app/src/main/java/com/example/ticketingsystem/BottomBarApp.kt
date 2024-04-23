package com.example.ticketingsystem

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomBarApp(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    BottomAppBar(actions = {
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier
            .weight(1f)
            .padding(horizontal = 15.dp)) {
            IconButton(onClick = { navController.navigate(Home.route) }) {
                Icon(imageVector = Icons.Filled.Home, contentDescription = "Home", Modifier.size(30.dp), tint = if(currentRoute == Home.route) Colors.Red else Color.Black)
            }
            IconButton(onClick = { navController.navigate(Movies.route) }) {
                Image(painter = if(currentRoute == Movies.route) painterResource(id = R.drawable.moviered) else painterResource(id = R.drawable.movie), contentDescription = "Movie", modifier = Modifier.size(30.dp))
            }
            IconButton(onClick = { navController.navigate(Profile.route) }) {
                Icon(imageVector = Icons.Filled.Person, contentDescription = "Profile", Modifier.size(30.dp), tint = if(currentRoute == Profile.route) Colors.Red else Color.Black)
            }
        }
    },
      Modifier.height(60.dp)  )
}
