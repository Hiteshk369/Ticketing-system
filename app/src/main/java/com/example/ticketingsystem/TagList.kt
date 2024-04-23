package com.example.ticketingsystem

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TagList(){
    val languages = listOf("Telugu", "Hindi", "English", "Malayalam")
    LazyRow(modifier = Modifier.padding(vertical = 20.dp, horizontal = 15.dp)) {
        items(languages.size){
            index -> TagButton(language = languages[index])
        }
    }
}


@Composable
fun TagButton(language : String){
    Box(modifier = Modifier.padding(horizontal = 5.dp).border(width = 1.dp, color = Colors.Slate, shape = RoundedCornerShape(15.dp))) {
       Text(text = language, modifier = Modifier.padding(horizontal = 15.dp, vertical = 5.dp), color = Colors.Red, fontWeight = FontWeight.Medium)
    }
}

