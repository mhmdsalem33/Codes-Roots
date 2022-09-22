package com.example.mohamedelsayedsalemandroidtask.data.BestSellerData

data class Restaurant(
    val RestauranthId: Int,
    val cover: String,
    val cuisines: List<Cuisine>,
    val logo: String,
    val name: String
)