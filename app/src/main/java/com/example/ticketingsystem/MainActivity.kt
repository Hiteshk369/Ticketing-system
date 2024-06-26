package com.example.ticketingsystem

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyNavigation()
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MyNavigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Movies.route ){
        composable(Home.route){
            HomeScreen(navController)
        }
        composable(Movies.route){
            MoviesScreen(navController)
        }
        composable(Profile.route){
            ProfileScreen(navController)
        }
        composable(
            route = "movie/{movieId}",
            arguments = listOf(navArgument("movieId"){type= NavType.IntType})
        ){
            backStackEntry ->
            val movieId = backStackEntry.arguments?.getInt("movieId")
            if(movieId != null){
                MovieDetailScreen(movieId = movieId, navController=navController)
            }
        }
        composable(
            route = "reviews/{movieId}",
            arguments = listOf(navArgument("movieId"){type= NavType.IntType})
        ){
                backStackEntry ->
            val movieId = backStackEntry.arguments?.getInt("movieId")
            if(movieId != null){
                ReviewsScreen(movieId = movieId, navController=navController)
            }
        }
        composable(
            route = "shows/{movieId}",
            arguments = listOf(navArgument("movieId"){type= NavType.IntType})
        ){
                backStackEntry ->
            val movieId = backStackEntry.arguments?.getInt("movieId")
            if(movieId != null){
                MovieShowScreen(movieId = movieId, navController=navController)
            }
        }
        composable(
            route = "tickets/movieId={movieId}&date={selectedDate}",
            arguments = listOf(
                navArgument("movieId") { type = NavType.IntType },
                navArgument("selectedDate") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val movieId = backStackEntry.arguments?.getInt("movieId")
            val selectedDateString = backStackEntry.arguments?.getString("selectedDate")
            val selectedDate = selectedDateString?.let {
                LocalDate.parse(it, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
            }
            if (movieId != null && selectedDate != null) {
                SeatsScreen(movieId = movieId, selectedDate = selectedDate, navController = navController)
            }
        }
        composable(
            route="order/{orderId}",
            arguments = listOf(navArgument("orderId"){type= NavType.IntType})
        ){
            backStackEntry ->
            val orderId = backStackEntry.arguments?.getInt("orderId")
            if(orderId != null){
                OrderById(orderId = orderId, navController=navController)
            }
        }
        composable(Orders.route){
            OrdersScreen(navController)
        }
        composable(Help.route){
            HelpScreen(navController)
        }
        composable(Settings.route){
            SettingsScreen(navController)
        }
        composable(Offers.route){
            OffersScreen(navController)

        }
    }
}



