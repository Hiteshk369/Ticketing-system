package com.example.ticketingsystem

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.lightspark.composeqr.QrCodeView
import androidx.core.content.ContextCompat.startActivity
import android.content.Intent
import android.net.Uri
import androidx.compose.material.icons.outlined.Call
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.platform.LocalContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderById(orderId: Int, navController: NavHostController){
    val order:Order? = getOrderDetails(orderId)
    val title: String = order?.name ?: "Loading"
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "Your Ticket",
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(80.dp,0.dp,0.dp,0.dp)
                        )

                    }

                        } ,
                actions = {
                    Row(horizontalArrangement = Arrangement.Center) {
                        IconButton(onClick = {    }) {
                            Icon(imageVector = Icons.Outlined.Share, tint = Colors.Red, contentDescription = "close", modifier = Modifier
                                .border(
                                    width = 1.dp,
                                    color = Colors.Red,
                                    shape = RoundedCornerShape(50.dp)
                                )
                                .padding(5.dp)
                                .size(20.dp) )
                        }
                        IconButton(onClick = {  navController.popBackStack()  }) {
                            Icon(imageVector = Icons.Outlined.Close, tint = Colors.Red, contentDescription = "close", modifier = Modifier
                                .border(
                                    width = 1.dp,
                                    color = Colors.Red,
                                    shape = RoundedCornerShape(50.dp)
                                )
                                .padding(5.dp)
                                .size(20.dp) )
                        }
                    }

                },
                modifier = Modifier.drawBehind {
                    val borderSize = 2.dp.toPx()
                    drawLine(
                        color = Colors.Slate,
                        start = Offset(0f, size.height),
                        end = Offset(size.width, size.height),
                        strokeWidth = borderSize
                    )
                }
                )
        }
    ) {paddingValues ->
        if (order != null) {
            Column(
                Modifier
                    .padding(paddingValues)
                    .background(color = Colors.Pearl)
                    .padding(20.dp, 10.dp, 20.dp, 120.dp)) {
                Column(verticalArrangement = Arrangement.Center,modifier = Modifier
                    .border(width = 0.25.dp, color = Colors.Gray_100, shape = RoundedCornerShape(15.dp))
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .background(color = Colors.Gray_100  , shape = RoundedCornerShape(15.dp))) {
                    Row(modifier = Modifier.padding(15.dp), verticalAlignment = Alignment.CenterVertically) {
                        Image(painter = painterResource(id = order.imageId),
                            contentDescription = "Movies",
                            contentScale = ContentScale.FillBounds,
                            modifier = Modifier
                                .width(80.dp)
                                .height(110.dp)
                                .clip(MaterialTheme.shapes.small),

                            )
                        Spacer(modifier = Modifier.width(14.dp))
                        Column {
                            Text(
                                text =  order.name ,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color.Black
                            )
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
                                modifier = Modifier
                                    .padding(vertical = 2.dp)
                                    .width(220.dp),
                                overflow = TextOverflow.Ellipsis,
                                maxLines = 1,
                            )
                        }

                    }
                    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
                        .fillMaxWidth()
                        .height(270.dp)
                    ) {
                        Text(text = "2Tickets",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color.Gray)
                        Text(text = "SCREEN 2",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Black,
                            modifier = Modifier.padding(0.dp,10.dp,0.dp,0.dp))
                        Text(text = "GOLD - E8,E9",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color.Gray)
                        QrCodeView(
                            data = order.id.toString(),
                            modifier = Modifier
                                .size(150.dp)
                                .padding(0.dp, 10.dp, 0.dp, 10.dp)
                        )
                        Text(text = "BOOKING ID: ${order.id}",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Black,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .size(150.dp)
                                .padding(0.dp, 15.dp, 0.dp, 10.dp)
                        )

                    }
                    Column(modifier = Modifier
                        .background(color = Colors.Gray_200, shape = RoundedCornerShape(10.dp))
                        .fillMaxWidth()) {
                        Text(
                            text = "Cancellation unavailable : cut-off time of 4hrs before showtime has passed",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color.Black,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .padding(horizontal = 15.dp, vertical =  5.dp)
                        )
                    }
                    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
                        .fillMaxWidth()
                        .padding(0.dp, 5.dp, 0.dp, 5.dp)
                    ) {
                        ContactSupportButton()
                    }
                    var totalAmount = 0.0
                    Column(modifier = Modifier
                        .background(color = Colors.Gray_200, shape = RoundedCornerShape(15.dp))
//                        .fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 10.dp)){
                        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                            Text(text = "Ticket Price (2)",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Normal,
                                color = Color.Gray)
                            Text(text = "₹590.00",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Normal,
                                color = Color.Gray)
                            totalAmount += 590.00
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                            Text(text = "Convenience fee",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Normal,
                                color = Color.Gray)
                            Text(text = "₹80.00",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Normal,
                                color = Color.Gray)
                            totalAmount += 66.08
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                            Text(text = "Discount",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Normal,
                                color = Color.Gray)
                            Text(text = "₹0.00",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Normal,
                                color = Color.Gray)
                            totalAmount += 0.00
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                            Text(text = "BookASmile(2)",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Normal,
                                color = Color.Gray)
                            Text(text = "₹2.00",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Normal,
                                color = Color.Gray)
                            totalAmount += 2.00
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                            Text(text = "Total Amount",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color.Black)
                            Text(text = "₹${totalAmount.toFloat()}",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color.Black)
                        }
                    }

                }
            }
        }}

}

@Composable
fun ContactSupportButton() {
    val context = LocalContext.current

    val phoneNumber = "123"

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(vertical = 5.dp)
            .background(color = Colors.Red, shape = RoundedCornerShape(5.dp))
            .padding(10.dp)
            .clickable {
                // Open the phone's call log
                val callLogIntent = Intent(Intent.ACTION_VIEW)
                callLogIntent.type = "vnd.android.cursor.dir/calls"
                context.startActivity(callLogIntent)

                // Initiate a call
                val dialIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))
                context.startActivity(dialIntent)
            }) {
        Icon(imageVector = Icons.Outlined.Call, contentDescription = "call", tint = Color.White, modifier = Modifier
            .size(22.dp)
            .padding(0.dp, 0.dp, 5.dp, 0.dp))
        Text(
            text = "Contact support",
            fontSize = 15.sp,
            color = Color.White,
            fontWeight = FontWeight.Normal,

        )
    }


}

private fun getOrderDetails(orderId: Int): Order?{
    for(order in orders){
        if(order.id == orderId){
            return order
        }
    }
    return null
}