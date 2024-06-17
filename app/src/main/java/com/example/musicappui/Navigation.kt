package com.example.musicappui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.musicappui.ui.theme.AccountView
import com.example.musicappui.ui.theme.BrowseScreen
import com.example.musicappui.ui.theme.HomeView
import com.example.musicappui.ui.theme.Library
import com.example.musicappui.ui.theme.Subscription


@Composable
fun Navigation(navController: NavController, viewModel: MainViewModel, pd: PaddingValues){

    NavHost(navController = navController as NavHostController, startDestination =
    Screen.DrawerScreen.Account.route, modifier = Modifier.padding(pd)){

        composable(Screen.BottomScreen.Home.bRoute){
          HomeView()
        }
        composable(Screen.BottomScreen.Browse.bRoute){
            BrowseScreen()
        }

        composable(Screen.BottomScreen.Library.bRoute){
          Library()
        }

        composable(Screen.DrawerScreen.Account.route){
            AccountView()
        }
        composable(Screen.DrawerScreen.Subscription.route){
            Subscription()
        }
    }

}