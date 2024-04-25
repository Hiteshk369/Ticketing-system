package com.example.ticketingsystem

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.ticketingsystem.ui.theme.interFontFamily
import kotlinx.coroutines.launch
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SeatsScreen(movieId: Int, navController: NavHostController, selectedDate: LocalDate) {
    val movie:Movie? = getMovieDetails(movieId)
    val title: String = movie?.title ?: "Loading"

    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet = remember {
        mutableStateOf(true)
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
        if(showBottomSheet.value){
            ModalBottomSheet(onDismissRequest = { showBottomSheet.value = false }, sheetState = sheetState) {
                Column(Modifier.fillMaxWidth()) {
                    Image(painter = painterResource(id = R.drawable.bike), contentDescription = "bike")
                    Button(onClick = { scope.launch { sheetState.hide() }.invokeOnCompletion {
                        if(!sheetState.isVisible){
                            showBottomSheet.value = false
                        }
                    }
                    }, colors = ButtonDefaults.buttonColors(Colors.Red), shape = RoundedCornerShape(8.dp), modifier = Modifier.padding(horizontal = 10.dp).fillMaxWidth().padding(bottom = 20.dp)) {
                        Text(text = "Select Seats", fontFamily = interFontFamily, fontWeight = FontWeight.SemiBold, fontSize = 18.sp)
                    }
                }
            }
        }
        Column(Modifier.padding(paddingValues)) {
            Text(text = selectedDate.toString())
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