package com.example.ticketingsystem

data class Movie(
    val title: String,
    val imageRes: Int,
    val rating: Float,
    val votes: Int
)

val fakeMovieData  = listOf(
    Movie("Tillu2",R.drawable.tillu2,7.3f,96600),
    Movie("Manjummel Boys", R.drawable.manjummel,8.5f, 19869),
    Movie("Premalu", R.drawable.premalu,8.9f, 99895),
    Movie("Rockstar", R.drawable.rockstar,7.9f, 199742),
    Movie("RRR", R.drawable.rrr,8.7f, 299568),
    Movie("Whiplash", R.drawable.whiplash,9.5f, 457531),
    Movie("Zindagi Na Milegi Dobara", R.drawable.zindagi,8.2f, 327800),
    Movie("Ee Nagaraniki Em Ayindhi", R.drawable.ee,9.0f, 204800),
    Movie("Godzilla vs Kong", R.drawable.godzilla,6.7f, 308210),
)