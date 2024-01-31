package com.example.openinapp.screens.links

import androidx.annotation.DrawableRes
import com.example.openinapp.R

sealed class Card(
    @DrawableRes val Icon: Int?,
    var Title:String?,
    val Heading:String?

){
    data object Clicks: Card(Icon = R.drawable.clicks, Title = "Today's Clicks", Heading = "123")
    data object Location: Card(Icon = R.drawable.location, Title = "Top Location", Heading = "Ahmedabad")
    data object Sources: Card(Icon = R.drawable.instagram, Title = "Top Sources", Heading = "Instagram")
}
