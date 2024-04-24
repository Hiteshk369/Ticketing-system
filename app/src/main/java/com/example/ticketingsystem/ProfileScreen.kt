package com.example.ticketingsystem

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController


@Composable
fun ProfileScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopProfileBar()
        },
        bottomBar = {
            BottomBarApp(navController = navController)
        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            ProfileOptionsList(navController = navController)
        }
    }
}

@Composable
fun ProfileOptionsList(navController: NavController) {
    val name = listOf("Your Orders", "Help", "Settings", "Offers")
    val icon = listOf(Icons.Outlined.ShoppingCart, Icons.Outlined.Info, Icons.Outlined.Settings, Icons.Outlined.Warning)
    val description = listOf(
        "View all your bookings & purchases",
        "Need help or have questions?",
        "Location,Payments,Permissions & More",
        ""
    )
    val destinations = listOf(
        Orders.route,
        Help.route,
        Settings.route,
        Offers.route
    )
    LazyColumn(modifier = Modifier.padding(vertical = 0.dp, horizontal = 0.dp)) {
        items(name.size) { index ->
            ProfileOptionButton(
                name = name[index],
                icon = icon[index],
                description = description[index],
                destinations = destinations[index],
                navController = navController
            )
        }
    }
}

@Composable
fun ProfileOptionButton(
    name: String,
    icon: ImageVector,
    description: String,
    destinations: String,
    navController: NavController
) {
    val configuration = LocalConfiguration.current
    val windowWidth = with(LocalDensity.current) { configuration.screenWidthDp.dp.toPx() }
    Box(
        modifier = Modifier.clickable {
            navController.navigate(destinations)
        }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .drawBehind {
                    val borderSize = 1.dp.toPx()
                    drawLine(
                        color = Color.Gray,
                        start = Offset(0f, size.height),
                        end = Offset(windowWidth, size.height),
                        strokeWidth = borderSize
                    )
                }
                .fillMaxWidth()
                .padding(vertical = 10.dp, horizontal = 10.dp)
        ) {
            Row( verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween){
                Icon(
                    imageVector = icon,
                    contentDescription = "icon",
                    tint = Color.Red,
                    modifier = Modifier.padding(0.dp,0.dp,10.dp,0.dp)
                )
                Column {
                    Text(
                        text = name,
                        color = Color.Black,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    )
                    Text(
                        text = description,
                        color = Color.Gray,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal
                    )
                }
            }
            Icon(
                imageVector = Icons.Outlined.KeyboardArrowRight,
                contentDescription = "chevronRight",
                tint = Color.Gray
            )
        }
    }
}