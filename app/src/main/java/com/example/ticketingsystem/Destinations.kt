package com.example.ticketingsystem

interface Destinations{
    val route: String
}

object Home:Destinations{
    override val route = "Home"
}

object Movies:Destinations{
    override val route = "Movies"
}

object Profile:Destinations{
    override val route = "Profile"
}

