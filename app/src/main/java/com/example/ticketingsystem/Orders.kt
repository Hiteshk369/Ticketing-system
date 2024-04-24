package com.example.ticketingsystem

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun OrdersScreen(navController: NavHostController){
    Scaffold (
        topBar = {
            TopOrdersBar(navController = navController)
        },
    ){ paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            OrdersList()
        }
    }
}

@Composable
fun OrdersList(){
    val date = listOf("13 May,2024","14 May,2024","15 May,2024","16 May,2024")
    val imageIds = listOf(R.drawable.movie1,R.drawable.movie2,R.drawable.movie3,R.drawable.movie4)
    val name= listOf("Manjummel Boys","Hanuman","Zindagi Na Milegi Dobara", "Aa Dil Hai Mushkil")
    val time= listOf("01:10:16 PM","02:40:26 PM","11:50:36 PM", "07:11:11 PM")
    val theater= listOf("Miraj Cinemas","Cine Planet","Pvr Inox", "Cine Polis")
    val address= listOf("A2A central mall, Balanagar","Kompally","Kukatpally", "Lulu Mall,KPHB")
    LazyColumn(modifier = Modifier.padding(10.dp)) {
        items(date.size){index->
            OrdersCard(
                date =date[index],
                imageId = imageIds.getOrNull(index) ?: -1,
                name = name[index],
                time = time[index],
                theater=theater[index],
                address=address[index],
            )
        }

    }
}

@Composable
fun OrdersCard(date: String, imageId: Int,name:String,time:String,theater:String,address:String){
    Column(modifier = Modifier.padding(vertical = 5.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically){
            Text(
                text = "Ordered on: ",
                modifier = Modifier.padding(vertical = 5.dp),
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Black
            )
            Text(
                text = "$date at $time",
                modifier = Modifier.padding(vertical = 5.dp),
                fontSize = 16.sp, fontWeight = FontWeight.SemiBold
            )
        }
        Column(
            modifier = Modifier
                .border(width = 0.5.dp, color = Colors.Slate, shape = RoundedCornerShape(10.dp))
                .fillMaxWidth()
                .padding(vertical = 10.dp, horizontal = 10.dp)
        ) {
            Row(modifier = Modifier
                .drawBehind {
                    val borderSize = 0.5.dp.toPx()
                    drawLine(
                        color = Colors.Slate,
                        start = Offset(0f, size.height),
                        end = Offset(size.width, size.height),
                        strokeWidth = borderSize
                    )
                }
                .padding(bottom = 10.dp)
            ) {
                Image(
                    painter = painterResource(id = imageId),
                    contentDescription = "Movies",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .width(80.dp)
                        .height(110.dp)
                        .clip(MaterialTheme.shapes.small),

                    )
                Spacer(modifier = Modifier.width(14.dp))
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()){
                        Text(
                            text = name,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Black
                        )
                        Text(
                            text = "M-Ticket",
                            fontSize = 13.sp,
                            color = Color.Gray,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                    Text(
                        text = "telugu".capitalize(),
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.Gray
                    )
                    Text(
                        text ="day, $date | 10:10 PM".capitalize(),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black,
                        modifier = Modifier.padding(0.dp,10.dp,0.dp,0.dp)
                    )
                    Text(
                        text ="$theater: $address".capitalize(),
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.Gray,
                        modifier = Modifier.padding(vertical = 2.dp)
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "4 ticket (S):Executive - D14,D15,D15",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Black,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1,
                            modifier = Modifier
                                .width(220.dp)
                        )
                        Text(
                            text = "SCREEN 2",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Black,
                            modifier = Modifier
                                .width(60.dp)
                        )
                    }

                }
            }
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceEvenly) {
                Text(
                    text = "FInished".uppercase(),
                    modifier = Modifier
                        .padding(top = 10.dp, bottom = 5.dp, end = 10.dp)
                        .background(color = Colors.Red, shape = RoundedCornerShape(4.dp))
                        .width(100.dp)
                        .height(25.dp)
                        .padding(2.dp),
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center,
                    color = Color.White

                )
                Text(
                    text ="|",
                    fontSize = 42.sp,
                    fontWeight = FontWeight.ExtraLight,
                    color = Color.Gray,
                    modifier = Modifier
                        .padding(top = 0.dp, bottom = 0.dp, end = 10.dp)
                )
                Text(
                    text ="Hope you enjoyed The Show",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Gray
                )
            }
        }

        
    }

}