package com.example.ticketingsystem

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import java.util.concurrent.Flow

@OptIn(ExperimentalLayoutApi::class)
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
        LazyColumn(modifier = Modifier
            .padding(paddingValues)
            .fillMaxWidth()
            ) {
            item{
                Image(painter = painterResource(id = R.drawable.moviesbanner), contentDescription = "Banner" , modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 9f))
                TagList()
                UpcomingBox()
            }
            item {
                val screenWidthDp: Dp = LocalConfiguration.current.screenWidthDp.dp
                val chunkedMovies = fakeMovieData.chunked(2)
                val itemWidth: Dp = (screenWidthDp - 20.dp) / 2
                val itemHeight: Dp = itemWidth * 16 / 10
                FlowRow(modifier = Modifier.padding(vertical = 25.dp, horizontal = 10.dp)){
                    for (row in chunkedMovies) {
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth().padding(bottom = 15.dp)
                        ) {
                            for (movie in row) {
                                Column(modifier = Modifier.size(itemWidth, itemHeight).clickable { navController.navigate("movie/${movie.id}") }, horizontalAlignment = Alignment.Start, verticalArrangement = Arrangement.SpaceBetween) {
                                    Image(
                                        painter = painterResource(id = movie.imageRes),
                                        contentDescription = movie.title,
                                        modifier = Modifier
                                            .padding(5.dp)
                                            .fillMaxWidth(1f)
                                            .fillMaxHeight(0.8f)
                                            .clip(shape = RoundedCornerShape(10.dp)),
                                        contentScale = ContentScale.FillBounds
                                    )
                                    Row(horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically, modifier = Modifier
                                        .padding(horizontal = 5.dp)
                                        .fillMaxWidth()
                                        .clip(shape = RoundedCornerShape(8.dp))
                                        .background(color = Colors.LightSlate)
                                        .padding(horizontal = 10.dp, vertical = 5.dp)) {
                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            Icon(imageVector = Icons.Filled.Star, contentDescription = "Star", Modifier.size(20.dp), tint = Colors.Red)
                                            Text(text = movie.rating.toString(), fontWeight = FontWeight.Medium, fontSize = 14.sp, modifier = Modifier.padding(start = 2.dp))
                                        }
                                        Row {
                                            Text(text = movie.votes.toString(), fontWeight = FontWeight.Medium, fontSize = 14.sp)
                                            Text(text = " votes", color = Color.DarkGray)
                                        }
                                    }
                                    Text(
                                        text = movie.title,
                                        modifier = Modifier.padding(start = 5.dp),
                                        fontSize = 19.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        maxLines = 2,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UpcomingBox(){
    Column(modifier = Modifier
        .padding(horizontal = 15.dp)
        .fillMaxWidth()
        .height(60.dp)
        .clip(shape = RoundedCornerShape(5.dp))
        .background(Colors.Red)) {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)) {
                Text(text = "Coming Soon", fontWeight = FontWeight.SemiBold, fontSize = 18.sp, color = Colors.Pearl)
                Text(text = "Explore Upcoming Movies", fontWeight = FontWeight.Normal, fontSize = 13.sp, color = Colors.Pearl)
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Outlined.KeyboardArrowRight, contentDescription = "Chevron Right", Modifier.size(28.dp), tint = Colors.Pearl )
                }
            }
    }
}



