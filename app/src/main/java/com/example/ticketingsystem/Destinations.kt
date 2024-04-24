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

object Orders:Destinations{
    override val route = "Orders"
}

object Help:Destinations{
    override val route = "Help"
}

object Settings:Destinations{
    override val route = "Settings"
}

object Offers:Destinations{
    override val route = "Offers"
}