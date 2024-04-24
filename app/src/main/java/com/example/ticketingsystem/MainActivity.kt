package com.example.ticketingsystem

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyNavigation()
        }
    }
}

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
            }}
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



