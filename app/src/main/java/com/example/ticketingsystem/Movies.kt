package com.example.ticketingsystem

data class Movie(
    val id: Int,
    val title: String,
    val imageRes: Int,
    val rating: Float,
    val votes: Int,
    val dimension: String,
    val language: String,
)

val fakeMovieData  = listOf(
    Movie(1,"Tillu Square",R.drawable.tillu2,7.3f,96600,"2D","Telugu"),
    Movie(2,"Manjummel Boys", R.drawable.manjummel,8.5f, 19869,"2D","Malayalam"),
    Movie(3,"Premalu", R.drawable.premalu,8.9f, 99895,"2D","Telugu"),
    Movie(4,"Rockstar", R.drawable.rockstar,7.9f, 199742,"2D","Hindi"),
    Movie(5,"RRR", R.drawable.rrr,8.7f, 299568,"2D","Telugu"),
    Movie(6,"Whiplash", R.drawable.whiplash,9.5f, 457531,"2D","English"),
    Movie(7,"Zindagi Na Milegi Dobara", R.drawable.zindagi,8.2f, 327800,"2D","Hindi"),
    Movie(8,"Ee Nagaraniki Em Ayindhi", R.drawable.ee,9.0f, 204800,"2D","Telugu"),
    Movie(9,"Godzilla vs Kong", R.drawable.godzilla,6.7f, 308210,"3D","English"),
)