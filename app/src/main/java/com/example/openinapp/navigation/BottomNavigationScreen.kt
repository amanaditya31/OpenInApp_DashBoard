package com.example.openinapp.navigation

import androidx.annotation.DrawableRes
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.openinapp.R


val paddingOne=50.dp
val paddingTwo= 34.dp
sealed class BottomNavigationScreen(val route: String, @DrawableRes val icon: Int, val title: String , val padding: Dp) {

    object  LinksScreen: BottomNavigationScreen(AppScreens.LinksScreen.name,
        icon =  R.drawable.link, "Links" , paddingOne)
    object  CoursesScreen: BottomNavigationScreen(AppScreens.CoursesScreen.name,
        icon = R.drawable.magazine, "Courses",paddingOne)
    object  CampaignScreen: BottomNavigationScreen(AppScreens.CampaignScreen.name,
        icon = R.drawable.fast_forward, "Campaign", paddingOne)
    object  AddScreen: BottomNavigationScreen(AppScreens.AddScreen.name,
        icon = R.drawable.qr, "", paddingTwo)
    object  ProfileScreen: BottomNavigationScreen(AppScreens.ProfileScreen.name,
        icon = R.drawable.user, "Profile" , paddingOne )
}