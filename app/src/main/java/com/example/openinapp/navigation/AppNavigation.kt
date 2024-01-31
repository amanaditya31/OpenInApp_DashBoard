package com.example.openinapp.navigation

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.openinapp.screens.campaign.CampaignScreen
import com.example.openinapp.screens.courses.CoursesScreen
import com.example.openinapp.screens.links.LinksScreen
import com.example.openinapp.screens.links.LinksViewModel
import com.example.openinapp.screens.profile.ProfileScreen
import com.example.openinapp.screens.setting.SettingScreen

@Composable
fun AppNavigation(navController: NavHostController, scrollState: ScrollState, paddingValues: PaddingValues){

    NavHost(navController=navController, startDestination=AppScreens.LinksScreen.name){

        composable(AppScreens.LinksScreen.name){
            val linksViewModel = hiltViewModel<LinksViewModel>()

           LinksScreen(navController = navController, viewModel =linksViewModel )
        }

        composable(AppScreens.CoursesScreen.name){
            CoursesScreen(navController = navController)
        }

        composable(AppScreens.CampaignScreen.name){
            CampaignScreen(navController = navController)
        }

        composable(AppScreens.ProfileScreen.name){
            ProfileScreen(navController = navController)
        }

        composable(AppScreens.SettingScreen.name){
            SettingScreen(navController = navController)
        }
    }
}

//fun NavGraphBuilder.bottomNavigation(navController: NavController,
//                                    ){
//    composable(AppScreens.LinksScreen.name){
//        LinksScreen(navController = navController)
//    }
//
//    composable(AppScreens.CoursesScreen.name){
//        CoursesScreen(navController = navController)
//    }
//
//    composable(AppScreens.CampaignScreen.name){
//        CampaignScreen(navController = navController)
//    }
//
//    composable(AppScreens.ProfileScreen.name){
//        ProfileScreen(navController = navController)
//    }
//
//    composable(AppScreens.SettingScreen.name){
//        SettingScreen(navController = navController)
//    }
//}