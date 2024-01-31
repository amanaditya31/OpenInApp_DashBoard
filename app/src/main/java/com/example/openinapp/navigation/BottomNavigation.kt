package com.example.openinapp.navigation

import android.graphics.drawable.Icon
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.navigation.NavController
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Colors
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.primarySurface
import androidx.compose.material3.Icon
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.openinapp.R
import com.example.openinapp.ui.theme.GreyNav

@Composable
fun BottomNavigationBar(navController: NavController){


    val menuItems=listOf(BottomNavigationScreen.LinksScreen,
        BottomNavigationScreen.CoursesScreen,
        BottomNavigationScreen.AddScreen,
        BottomNavigationScreen.CampaignScreen,
        BottomNavigationScreen.ProfileScreen)

    BottomNavigation(contentColor= GreyNav,backgroundColor= Color.White, modifier = Modifier.height(119.dp)
        .clip(BottomNavCurve())){
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute=navBackStackEntry?.destination?.route

        menuItems.forEach{
            BottomNavigationItem(
                modifier = Modifier.padding(top=it.padding),
                label={Text(text=it.title,overflow = TextOverflow.Ellipsis)},
                alwaysShowLabel=true,
                selectedContentColor= Color.Black,
                unselectedContentColor= Color.Gray,
                selected = currentRoute==it.route,
                onClick={
                    navController.navigate(it.route)
                    {
                        navController.graph.startDestinationRoute?.let{
                                route->
                            popUpTo(route){
                                saveState=true
                            }
                        }
                        launchSingleTop=true
                        restoreState=true
                    }
                },
                icon={ Icon( contentDescription = it.title, painter = painterResource(id =it.icon) ) }
            )


        }
    }

}
