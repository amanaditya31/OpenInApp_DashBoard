package com.example.openinapp.navigation

import android.window.SplashScreen

enum class AppScreens {
    AddScreen,
    CampaignScreen,
    CoursesScreen,
    LinksScreen,
    SettingScreen,
    ProfileScreen;

    companion object {
        fun fromRoute(route: String?): AppScreens = when (route?.substringBefore("/")) {
            AddScreen.name -> AddScreen
            CampaignScreen.name-> CampaignScreen
            CoursesScreen.name -> CoursesScreen
            LinksScreen.name -> LinksScreen
            SettingScreen.name -> SettingScreen
            ProfileScreen.name -> ProfileScreen
            null-> LinksScreen
            else -> throw IllegalArgumentException("Route is ${route} is not recognized")
        }
    }
}