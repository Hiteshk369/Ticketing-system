package com.example.ticketingsystem

data class MovieShow(
    val movieId: Int,
    val theatres: List<Theatre>
)

data class Theatre(
    val name: String,
    val timings: List<Timing>,
    val address: String
)

data class Timing(
    val showTime: String,
    val totalTickets: Int,
    val date: String
)

val fakeTicketsAsianTillu = listOf(
    Timing(
        showTime = "07: 20 PM",
        totalTickets = 240,
        date = "25 Apr, 2024"
    ),
    Timing(
        showTime = "10: 20 PM",
        totalTickets = 240,
        date = "25 Apr, 2024"
    )
)

val fakeTicketsGalaxyTillu = listOf(
    Timing(
        showTime = "07: 20 PM",
        totalTickets = 240,
        date = "25 Apr, 2024"
    ),
    Timing(
        showTime = "10: 20 AM",
        totalTickets = 240,
        date = "26 Apr, 2024"
    ),
    Timing(
        showTime = "07: 20 PM",
        totalTickets = 240,
        date = "26 Apr, 2024"
    )
)

val fakeTicketsAsianManjummel = listOf(
    Timing(
        showTime = "10: 20 PM",
        totalTickets = 240,
        date = "25 Apr, 2024"
    ),
    Timing(
        showTime = "10: 20 PM",
        totalTickets = 240,
        date = "26 Apr, 2024"
    )
)

val fakeTheatresTillu = listOf(
    Theatre(
        name = "Asian Sridevi Multiplex",
        timings = fakeTicketsAsianTillu, // Sample timings
        address = "Hanumakonda"
    ),
    Theatre(
        name = "Galaxy Cinema",
        timings = fakeTicketsGalaxyTillu, // Sample timings
        address = "Warangal"
    ),
)

val fakeTheatresManjummel = listOf(
    Theatre(
        name = "Asian Sridevi Multiplex",
        timings = fakeTicketsAsianManjummel, // Sample timings
        address = "Hanumakonda"
    ),
)

val fakeShowsData = listOf(
    MovieShow(1, fakeTheatresTillu),
    MovieShow(2, fakeTheatresManjummel)
)