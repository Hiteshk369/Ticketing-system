package com.example.ticketingsystem

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material.icons.outlined.ThumbUp
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material3.Card
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
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.ticketingsystem.ui.theme.interFontFamily

import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailScreen(movieId: Int, navController: NavHostController) {
    val movie:Movie? = getMovieDetails(movieId)
    val title: String = movie?.title ?: "Loading"
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = title, fontFamily = interFontFamily, fontSize = 20.sp)} , navigationIcon = {
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
            Column(
                Modifier
                    .padding(paddingValues)
                    .verticalScroll(rememberScrollState())) {
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
                                fontFamily = interFontFamily,
                                modifier = Modifier.padding(start = 8.dp)
                            )
                            Row(Modifier.padding(horizontal = 5.dp)) {
                                Text(
                                    text = "(",
                                    color = Color.DarkGray,
                                    fontFamily = interFontFamily,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 14.sp
                                )
                                Text(
                                    text = movie.votes.toString(),
                                    color = Color.DarkGray,
                                    fontFamily = interFontFamily,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 14.sp
                                )
                                Text(
                                    text = " Votes",
                                    color = Color.DarkGray,
                                    fontFamily = interFontFamily,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 14.sp
                                )
                                Text(
                                    text = ")",
                                    color = Color.DarkGray,
                                    fontFamily = interFontFamily,
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
                                fontFamily = interFontFamily,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 14.sp,
                                color = Colors.Red
                            )
                        }
                    }
                    Row(Modifier.padding(bottom = 10.dp)) {
                        Badge(text = movie.dimension)
                        Badge(text = movie.language)
                    }
                    Row(
                        Modifier
                            .padding(bottom = 8.dp)
                            .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                        Text(text = movie.runtime, fontSize = 14.sp,fontFamily = interFontFamily,)
                        Text(text = "•", fontWeight = FontWeight.SemiBold, fontSize = 15.sp)
                        Row {
                            for(genre in movie.genres){
                                Text(text = genre, fontSize = 14.sp,fontFamily = interFontFamily,)
                                if(genre != movie.genres.last()){
                                    Text(text = ", ",fontSize = 14.sp,fontFamily = interFontFamily,)
                                }
                            }
                        }
                        Text(text = "•",fontWeight = FontWeight.SemiBold, fontSize = 15.sp)
                        Text(text = movie.certificate, fontSize = 14.sp,fontFamily = interFontFamily,)
                        Text(text = "•",fontWeight = FontWeight.SemiBold, fontSize = 15.sp)
                        Text(text = movie.releaseDate, fontSize = 14.sp,fontFamily = interFontFamily,)
                    }
                    Column(Modifier
                        .padding(bottom = 10.dp)) {
                        Text(text = movie.description, fontSize = 14.sp,fontFamily = interFontFamily, style = TextStyle(lineHeight = 20.sp), maxLines = 3, overflow = TextOverflow.Ellipsis)
                    }
                }
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .drawBehind {
                        val borderSize = 1.dp.toPx()
                        drawLine(
                            color = Colors.Slate,
                            start = Offset(0f, size.height),
                            end = Offset(size.width, size.height),
                            strokeWidth = borderSize
                        )
                    }) {}
                Column(Modifier.padding(bottom = 20.dp)) {
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 15.dp, vertical = 20.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                        Text(text = "Top reviews", fontFamily = interFontFamily, fontWeight = FontWeight.SemiBold, fontSize = 18.sp)
                        Row( verticalAlignment = Alignment.CenterVertically) {
                            Text(text = "44.7K reviews", fontFamily = interFontFamily, fontWeight = FontWeight.Normal, fontSize = 14.sp, color = Colors.Red)
                            Icon(imageVector = Icons.Outlined.KeyboardArrowRight, contentDescription = "Chevron", tint = Colors.Red, modifier = Modifier.size(20.dp) )
                        }
                    }
                    Row(
                        modifier = Modifier
                            .horizontalScroll(rememberScrollState())
                            .padding(horizontal = 15.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        repeat(5) { // Repeat for 5 review cards
                            Box(
                                modifier = Modifier
                                    .padding(end = 10.dp) // Add some horizontal padding between cards
                            ) {
                                ReviewCard()
                            }
                        }
                    }
                }
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .drawBehind {
                        val borderSize = 1.dp.toPx()
                        drawLine(
                            color = Colors.Slate,
                            start = Offset(0f, size.height),
                            end = Offset(size.width, size.height),
                            strokeWidth = borderSize
                        )
                    }) {}
                Column {
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 15.dp, vertical = 20.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                        Text(text = "You might also like", fontFamily = interFontFamily, fontWeight = FontWeight.SemiBold, fontSize = 18.sp)
                        Row( verticalAlignment = Alignment.CenterVertically) {
                            Text(text = "View All", fontFamily = interFontFamily, fontWeight = FontWeight.Normal, fontSize = 14.sp, color = Colors.Red)
                            Icon(imageVector = Icons.Outlined.KeyboardArrowRight, contentDescription = "Chevron", tint = Colors.Red, modifier = Modifier.size(20.dp) )
                        }
                    }
                    Row(
                        modifier = Modifier
                            .horizontalScroll(rememberScrollState())
                            .padding(horizontal = 15.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        repeat(5) { // Repeat for 5 review cards
                            Box(
//                                modifier = Modifier
//                                    .padding(end = 10.dp) // Add some horizontal padding between cards
                            ) {
                                MovieCard()
                            }
                        }
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
    Row(
        Modifier
            .padding(end = 5.dp)
            .clip(shape = RoundedCornerShape(5.dp))
            .background(Color.LightGray)
            .padding(horizontal = 8.dp, vertical = 4.dp)) {
        Text(text = text.toUpperCase(Locale.ROOT), fontFamily = interFontFamily, fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
    }
}

@Composable
fun ReviewCard(){
    val screenWidthDp: Dp = LocalConfiguration.current.screenWidthDp.dp
    val itemWidth: Dp = (screenWidthDp - 50.dp)
    Card( modifier = Modifier
        .width(itemWidth)
        .background(color = Colors.Pearl)
        .border(width = 1.dp, color = Colors.Slate, shape = RoundedCornerShape(8.dp))
        .padding(vertical = 10.dp, horizontal = 20.dp)) {
        Column(
            Modifier
                .fillMaxWidth()
                .background(Colors.Pearl)) {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.Rounded.AccountCircle, contentDescription = "Account", Modifier.size(35.dp))
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
                    fontFamily = interFontFamily, fontSize = 16.sp, style = TextStyle(lineHeight = 22.sp), maxLines = 4, overflow = TextOverflow.Ellipsis)
            }
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp)) {
//                Text(text = "1947 people found this helpful.", fontFamily = interFontFamily)
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

@Composable
fun MovieCard(){
    val screenWidthDp: Dp = LocalConfiguration.current.screenWidthDp.dp
    val itemWidth: Dp = (screenWidthDp+60.dp) / 3
    val itemHeight: Dp = itemWidth * 16 / 9

    Column(Modifier.size(itemWidth, itemHeight)) {
        Image(
            painter = painterResource(id = R.drawable.rockstar),
            contentDescription = "movie",
            modifier = Modifier
                .padding(horizontal = 5.dp)
                .fillMaxWidth(1f)
                .fillMaxHeight(0.8f)
                .clip(shape = RoundedCornerShape(10.dp)),
            contentScale = ContentScale.FillBounds
        )
        Text(text = "Rockstar", Modifier.padding(horizontal = 6.dp, vertical = 2.dp), fontSize = 18.sp, fontFamily = interFontFamily, fontWeight = FontWeight.Medium)
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