package com.example.ticketingsystem

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailScreen(movieId: Int, navController: NavHostController) {
    val movie:Movie? = getMovieDetails(movieId)
    val title: String = movie?.title ?: "Loading"
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = title)} , navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.Outlined.KeyboardArrowLeft,
                        contentDescription = "Chevron Left",
                        Modifier.size(30.dp)
                    )
                }
            })
        }
    ) {
        paddingValues ->
        if (movie != null) {
            Column(Modifier.padding(paddingValues)) {
                Column(Modifier.padding(horizontal = 15.dp, vertical = 10.dp)) {
                    Image(painter = painterResource(id = movie.imageRes), contentDescription = movie.title,
                        Modifier
                            .fillMaxWidth()
                            .clip(shape = RoundedCornerShape(8.dp))
                            .height(200.dp), contentScale = ContentScale.FillWidth)
                    Row(
                        Modifier
                            .padding(vertical = 20.dp)
                            .fillMaxWidth()
                            .clip(shape = RoundedCornerShape(8.dp))
                            .background(color = Colors.LightSlate)
                            .padding(horizontal = 15.dp, vertical = 10.dp),
                        Arrangement.SpaceBetween,
                        Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Filled.Star,
                                contentDescription = "Star",
                                Modifier.size(30.dp),
                                tint = Colors.Red
                            )
                            Text(
                                text = movie.rating.toString() + "/10",
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 18.sp,
                                modifier = Modifier.padding(start = 8.dp)
                            )
                            Row(Modifier.padding(horizontal = 5.dp)) {
                                Text(
                                    text = "(",
                                    color = Color.DarkGray,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 14.sp
                                )
                                Text(
                                    text = movie.votes.toString(),
                                    color = Color.DarkGray,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 14.sp
                                )
                                Text(
                                    text = " Votes",
                                    color = Color.DarkGray,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 14.sp
                                )
                                Text(
                                    text = ")",
                                    color = Color.DarkGray,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 14.sp
                                )
                            }
                            Icon(
                                imageVector = Icons.Outlined.KeyboardArrowRight,
                                contentDescription = "Chevron Right",
                                Modifier.size(18.dp),
                                tint = Color.DarkGray
                            )
                        }
                        Row(
                            Modifier
                                .border(
                                    width = 1.dp,
                                    color = Colors.Red,
                                    shape = RoundedCornerShape(10.dp)
                                )
                                .background(color = Colors.Pearl, shape = RoundedCornerShape(10.dp))
                                .padding(horizontal = 8.dp, vertical = 5.dp)) {
                            Text(
                                text = "Rate Now",
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 14.sp,
                                color = Colors.Red
                            )
                        }
                    }
                    Row {
                        Badge(text = movie.dimension)
                        Badge(text = movie.language)
                    }
                }
            }
        } else {
            Column(Modifier.padding(paddingValues)) {
                Text(text = "Movie Details not found")
            }
        }
    }

}

@Composable
fun Badge(text: String){
    Row(Modifier.padding(end = 5.dp).clip(shape = RoundedCornerShape(5.dp)).background(Color.LightGray).padding(horizontal = 8.dp, vertical = 4.dp)) {
        Text(text = text.toUpperCase(Locale.ROOT), fontWeight = FontWeight.SemiBold)
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