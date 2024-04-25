package com.example.ticketingsystem

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.ticketingsystem.ui.theme.interFontFamily
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

@SuppressLint("RememberReturnType")
@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieShowScreen(movieId: Int, navController: NavHostController) {
    val movie:Movie? = getMovieDetails(movieId)
    val title: String = movie?.title ?: "Loading"

    val selectedDate = remember { mutableStateOf(LocalDate.now()) }

    val movieShows = remember(movieId, selectedDate.value) {
        getMovieShowDetails(movieId, selectedDate.value)
    }
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = title, fontFamily = interFontFamily, fontSize = 20.sp) } , navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.Outlined.KeyboardArrowLeft,
                        contentDescription = "Chevron Left",
                        Modifier.size(30.dp)
                    )
                }
            }, actions = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Outlined.Search, contentDescription = "Search")
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
        if( movie != null){
            Column(
                Modifier
                    .padding(paddingValues)
                    ) {
                Column() {
                    WeekCalender( onDateSelected = { selectedDate.value = it })
                }
                Column( modifier = Modifier
                    .fillMaxWidth()
                    .drawBehind {
                        val borderSize = 2.dp.toPx()
                        drawLine(
                            color = Colors.Slate,
                            start = Offset(0f, size.height),
                            end = Offset(size.width, size.height),
                            strokeWidth = borderSize
                        )
                    })
                {}
                Row(
                    Modifier
                        .fillMaxWidth()
                        .drawBehind {
                            val borderSize = 2.dp.toPx()
                            drawLine(
                                color = Colors.Slate,
                                start = Offset(0f, size.height),
                                end = Offset(size.width, size.height),
                                strokeWidth = borderSize
                            )
                        }
                        .padding(horizontal = 20.dp, vertical = 10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = movie.language, fontSize = 16.sp,
                        fontFamily = interFontFamily,
                        fontWeight = FontWeight.SemiBold,)
                    Text(text = "•", fontWeight = FontWeight.SemiBold, fontSize = 16.sp, modifier = Modifier.padding(horizontal = 10.dp))
                    Text(text = movie.dimension, fontSize = 16.sp,
                        fontFamily = interFontFamily,
                        fontWeight = FontWeight.Medium,)
                }
                Column(
                    Modifier
                        .fillMaxSize(1f)
                        .background(Color.LightGray)
                        .verticalScroll(rememberScrollState())) {
                    Column(
                        Modifier
                            .padding(horizontal = 15.dp, vertical = 20.dp)
                            .fillMaxSize()
                            .background(Colors.Pearl)
                            .padding(horizontal = 15.dp, vertical = 10.dp)) {
                        movieShows?.let { shows ->
                            if(shows.isNotEmpty()){
                                shows.forEach { theatre ->
                                    TheatreItem(theatre = theatre, movieId=movieId, selectedDate=selectedDate.value, navController=navController)
                                }
                            }else{
                                Text(text = "No shows!", fontFamily = interFontFamily, fontSize = 20.sp, fontWeight = FontWeight.Medium)
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

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TheatreItem(theatre: Theatre, movieId: Int, selectedDate: LocalDate, navController: NavHostController){
    Column(Modifier.padding(bottom = 15.dp, top = 15.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth(0.8f)) {
                Icon(imageVector = Icons.Outlined.FavoriteBorder, contentDescription = "Favourite", Modifier.size(18.dp))
                Column {
                    Text(text = "${theatre.name}: ${theatre.address}", fontFamily = interFontFamily, fontSize = 17.sp, fontWeight = FontWeight.SemiBold, maxLines = 2, lineHeight = 25.sp, color = Color.DarkGray, modifier = Modifier.padding(horizontal = 8.dp))
                }
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = Icons.Outlined.Info, contentDescription = "Favourite", Modifier.size(18.dp))
                Text(text = "INFO", fontFamily = interFontFamily, fontSize = 14.sp, fontWeight = FontWeight.Normal, color = Color.DarkGray, modifier = Modifier.padding(horizontal = 4.dp))
            }
        }
        Row(Modifier.padding(vertical = 15.dp)) {
            theatre.timings.map { timing ->
                Column(
                    Modifier
                        .padding(horizontal = 5.dp)
                        .clickable {  navController.navigate("tickets/movieId=${movieId}&date=${selectedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))}") }
                        .border(width = 1.dp, color = Color.Gray, shape = RoundedCornerShape(5.dp))
                        .padding(horizontal = 15.dp, vertical = 10.dp)) {
                    Text(text = timing.showTime, fontFamily = interFontFamily, fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Colors.Emerald)
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WeekCalender(onDateSelected: (LocalDate) -> Unit){
    val today = LocalDate.now()
    val dates = mutableListOf<String>()
    val formatter = DateTimeFormatter.ofPattern("dd")
    val monthFormatter = DateTimeFormatter.ofPattern("MMM")

    val selectedDate = remember {
        mutableStateOf(today)
    }
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        (0 until 7).forEach { i ->
            val date = today.plusDays(i.toLong())
            val dayName = date.dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.ENGLISH)
            val formattedDay = date.format(formatter)
            val month = date.format(monthFormatter)
            val isSelected = date == selectedDate.value

            DateItem(dayName = dayName, formattedDay = formattedDay, month=month, selected = isSelected,onDateSelected = {
                selectedDate.value = date
                onDateSelected(date)
            })

        }
    }
}

@Composable
fun DateItem(
    dayName: String,
    formattedDay: String,
    month: String,
    selected: Boolean,
    onDateSelected: () -> Unit
){
    val screenWidthDp: Dp = LocalConfiguration.current.screenWidthDp.dp
    val itemWidth = screenWidthDp / 7
    val itemHeight: Dp = itemWidth * 16 / 11
    val backgroundColor = if (selected) Colors.Red else Color.Transparent
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .size(itemWidth, itemHeight)
            .background(color = backgroundColor)
            .clickable { onDateSelected() }
    ){
    Text(
        text = dayName.uppercase(),
        fontSize = 15.sp,
        fontFamily = interFontFamily,
        fontWeight = FontWeight.Medium,
        color = if(selected) Colors.Slate else Color.Gray,
        modifier = Modifier.padding(horizontal = 8.dp)
    )
    Text(
        text = formattedDay,
        fontSize = 20.sp,
        fontFamily = interFontFamily,
        fontWeight = FontWeight.SemiBold,
        color = if(selected) Colors.Pearl else Color.DarkGray,
        modifier = Modifier.padding( horizontal = 8.dp)
    )
    Text(
        text = month.uppercase(),
        fontSize = 15.sp,
        fontFamily = interFontFamily,
        fontWeight = FontWeight.Medium,
        color = if(selected) Colors.Slate else Color.Gray,
        modifier = Modifier.padding(horizontal = 8.dp)
    )
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

@RequiresApi(Build.VERSION_CODES.O)
private fun getMovieShowDetails(movieId: Int, date: LocalDate): List<Theatre>? {
    val showData = fakeShowsData.find { it.movieId == movieId }
    return showData?.theatres?.map { theatre ->
        val filteredTimings = theatre.timings.filter { timing ->
            val timingDate = LocalDate.parse(timing.date, DateTimeFormatter.ofPattern
                ("dd MMM, yyyy"))
            timingDate == date
        }
        if (filteredTimings.isNotEmpty()) {
            Theatre(name = theatre.name, timings = filteredTimings, address = theatre.address)
        } else {
            null
        }
    }?.filterNotNull()
}
