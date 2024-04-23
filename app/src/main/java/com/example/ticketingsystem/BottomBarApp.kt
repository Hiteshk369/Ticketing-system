package com.example.ticketingsystem

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
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
            Column(
                Modifier.fillMaxHeight(), Arrangement.Center, Alignment.CenterHorizontally
            ) {
                IconButton(onClick = { navController.navigate(Home.route) }, Modifier.fillMaxHeight(0.6f)) {
                    Icon(imageVector = Icons.Filled.Home, contentDescription = "Home", Modifier.size(30.dp), tint = if(currentRoute == Home.route) Colors.Red else Color.Black)
                }
                Text(text = "Home", modifier = Modifier.padding(vertical = 0.dp), color = if(currentRoute == Home.route) Colors.Red else Color.Black)
            }
            Column( verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxHeight()) {
                IconButton(onClick = { navController.navigate(Movies.route) },  Modifier.fillMaxHeight(0.6f)) {
                    Image(painter = if(currentRoute == Movies.route) painterResource(id = R.drawable.moviered) else painterResource(id = R.drawable.movie), contentDescription = "Movie", modifier = Modifier.size(30.dp))
                }
                Text(text = "Movies", modifier = Modifier.padding(vertical = 0.dp), color = if(currentRoute == Movies.route) Colors.Red else Color.Black)
            }
            Column(  verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxHeight()
            ) {
                IconButton(onClick = { navController.navigate(Profile.route) }, Modifier.fillMaxHeight(0.6f)) {
                    Icon(
                        imageVector = Icons.Filled.Person,
                        contentDescription = "Profile",
                        Modifier.size(30.dp),
                        tint = if (currentRoute == Profile.route) Colors.Red else Color.Black
                    )
                }
                Text(text = "Profile", modifier = Modifier.padding(vertical = 0.dp), fontWeight = FontWeight.Normal, color = if(currentRoute == Profile.route) Colors.Red else Color.Black )
            }
        }
    },
      Modifier.height(60.dp)  )
}
