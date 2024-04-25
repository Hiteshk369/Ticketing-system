package com.example.ticketingsystem

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material.icons.outlined.ThumbUp
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardElevation
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.ticketingsystem.ui.theme.interFontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReviewsScreen(movieId: Int, navController: NavHostController) {
    val movie:Movie? = getMovieDetails(movieId)
    val title: String = movie?.title ?: "Loading"
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { Text(text = title) }, navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(imageVector = Icons.Outlined.KeyboardArrowLeft, contentDescription = "Chevron", Modifier.size(30.dp))
                }
            }, modifier = Modifier.drawBehind {
                val borderSize = 2.dp.toPx()
                drawLine(
                    color = Colors.Slate,
                    start = Offset(0f, size.height),
                    end = Offset(size.width, size.height),
                    strokeWidth = borderSize
                )
            })
        }
    ) {paddingValues ->
        Column(
            Modifier
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = 18.dp)
                    .drawBehind {
                        val borderSize = 2.dp.toPx()
                        drawLine(
                            color = Colors.LightSlate,
                            start = Offset(0f, size.height),
                            end = Offset(size.width, size.height),
                            strokeWidth = borderSize
                        )
                    }, horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "RATINGS", fontFamily = interFontFamily, fontWeight = FontWeight.ExtraBold, fontSize = 24.sp, color = Color.DarkGray)
                Row(Modifier.padding(6.dp), verticalAlignment = Alignment.CenterVertically) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(imageVector = Icons.Filled.Star, contentDescription = "Star", tint = Colors.Red, modifier = Modifier.size(25.dp))
                        Text(text = "${movie?.rating}/10", fontFamily = interFontFamily, fontWeight = FontWeight.Medium, fontSize = 16.sp, modifier = Modifier.padding(horizontal = 4.dp))
                    }
                    Text(text = "${movie?.votes} Votes", fontFamily = interFontFamily, fontWeight = FontWeight.Medium, fontSize = 16.sp, modifier = Modifier.padding(horizontal = 10.dp))
                }
                Button(onClick = { navController.navigate("shows/${movieId}") }, colors = ButtonDefaults.buttonColors(Colors.Red),  shape = RoundedCornerShape(8.dp), elevation = ButtonDefaults.buttonElevation(5.dp), modifier = Modifier.padding(bottom = 25.dp)) {
                    Text(text = "Book Tickets", fontFamily = interFontFamily, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
                }
            }
            Column(
                Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()) {
                Column(Modifier.padding(horizontal = 15.dp)) {
                    Text(text = "Most helpful reviews", fontWeight = FontWeight.SemiBold, fontFamily = interFontFamily, fontSize = 18.sp)
                    Column(Modifier.padding(vertical = 15.dp)
                    ) {
                        repeat(5) { // Repeat for 5 review cards
                            Box(
                                modifier = Modifier
                                    .padding(bottom = 10.dp)
                            ) {
                                ReviewCard()
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ReviewCard(){
    val screenWidthDp: Dp = LocalConfiguration.current.screenWidthDp.dp
    val itemWidth: Dp = (screenWidthDp)
    Card( modifier = Modifier
        .width(itemWidth)
        .background(color = Colors.Pearl)
        .border(width = 1.dp, color = Colors.Slate, shape = RoundedCornerShape(8.dp))
        .padding(vertical = 10.dp, horizontal = 20.dp), ) {
        Column(
            Modifier
                .fillMaxWidth()
                .background(Colors.Pearl)) {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.Rounded.AccountCircle, contentDescription = "Account", Modifier.size(35.dp), tint = Color.Gray)
                    Text(text = "Hitesh", fontFamily = interFontFamily, fontWeight = FontWeight.Medium, fontSize = 18.sp, modifier = Modifier.padding(10.dp))
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.Filled.Star, contentDescription = "Account", Modifier.size(25.dp), tint = Colors.Red)
                    Text(text = "10/10", fontFamily = interFontFamily, fontWeight = FontWeight.Normal, fontSize = 18.sp, modifier = Modifier.padding(4.dp))
                }
            }
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp)) {
                Text(text = "Once again repeat same full of extra fun comedy non-stop Siddu (Dj Tillu) superb performance we'll & Actress Anupama in Radhika Role Awesome. This movie will ultimate blockbuster. Recent times one of the best movie.",
                    fontFamily = interFontFamily, fontSize = 16.sp, style = TextStyle(lineHeight = 22.sp))
            }
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp)) {
                Text(text = "1947 people found this helpful.", fontFamily = interFontFamily)
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(imageVector = Icons.Outlined.ThumbUp, contentDescription = "Like", Modifier.size(20.dp))
                        Text(text = "1.9K", fontFamily = interFontFamily, fontSize = 15.sp, fontWeight = FontWeight.Medium, modifier = Modifier.padding(horizontal = 10.dp))
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(text = "26 Days ago", fontFamily = interFontFamily, fontSize = 15.sp, fontWeight = FontWeight.Medium, color = Color.Gray, modifier = Modifier.padding(horizontal = 10.dp))
                        Icon(imageVector = Icons.Outlined.MoreVert, contentDescription = "Morevert", Modifier.size(20.dp))
                    }
                }
            }
        }
    }
}

private fun getMovieDetails(movieId: Int): Movie?{
    for(movie in fakeMovieData){
        if(movie.id == movieId){
            return movie
        }
    }
    return null
}