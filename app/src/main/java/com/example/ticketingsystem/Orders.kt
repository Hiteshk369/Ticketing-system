package com.example.ticketingsystem

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.NavController

@Composable
fun OrdersScreen(navController: NavHostController){
    Scaffold (
        topBar = {
            TopOrdersBar(navController = navController)
        },
    ){ paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            OrdersList(navController = navController)
        }
    }
}

data class Order(
    val id: Int,
    val date: String,
    val imageId: Int,
    val name: String,
    val time: String,
    val theater: String,
    val address: String,
)
val orders = listOf(
    Order(1, "13 May,2024", R.drawable.movie1, "Manjummel Boys", "01:10:16 PM", "Miraj Cinemas", "A2A central mall, Balanagar"),
    Order(2, "14 May,2024", R.drawable.movie2, "Hanuman", "02:40:26 PM", "Cine Planet", "Kompally"),
    Order(3, "15 May,2024", R.drawable.movie3, "Zindagi Na Milegi Dobara", "11:50:36 PM", "Pvr Inox", "Kukatpally"),
    Order(4, "16 May,2024", R.drawable.movie4, "Aa Dil Hai Mushkil", "07:11:11 PM", "Cine Polis", "Lulu Mall,KPHB")
)

@Composable
fun OrdersList(navController: NavHostController){

    LazyColumn(modifier = Modifier.padding(10.dp)) {
        items(orders) { order ->
            OrdersCard(order = order, navController = navController)
        }
    }
}

@Composable
fun OrdersCard(order: Order, navController: NavController ){
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
                text = "${order.date} at ${order.time}",
                modifier = Modifier.padding(vertical = 5.dp),
                fontSize = 16.sp, fontWeight = FontWeight.SemiBold
            )
        }
        Column(
            modifier = Modifier
                .border(width = 0.5.dp, color = Colors.Slate, shape = RoundedCornerShape(10.dp))
                .fillMaxWidth()
                .padding(vertical = 10.dp, horizontal = 10.dp)
                .clickable { navController.navigate("order/${order.id}") }
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
                    painter = painterResource(id = order.imageId),
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
                            text =  order.name ,
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
                        text ="day, ${order.date} | ${order.time}".capitalize(),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black,
                        modifier = Modifier.padding(0.dp,10.dp,0.dp,0.dp)
                    )
                    Text(
                        text ="${order.theater}: ${order.address}".capitalize(),
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