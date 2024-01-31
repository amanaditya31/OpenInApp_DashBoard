package com.example.openinapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import com.example.openinapp.navigation.AppNavigation
import com.example.openinapp.navigation.BottomNavigationBar
import com.example.openinapp.ui.theme.OpenInAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window,false)
        setContent {
            OpenInAppTheme {
                MainApp()
            }
        }
    }
}


@Preview
@Composable
fun MainApp(){
    val navController= rememberNavController()
    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {     val scrollState= rememberScrollState()
        val navController= rememberNavController()
        Scaffold(
            bottomBar = {
                BottomNavigationBar(navController = navController)
            },
        ){

            AppNavigation(navController , scrollState ,it)
        }

    }
}