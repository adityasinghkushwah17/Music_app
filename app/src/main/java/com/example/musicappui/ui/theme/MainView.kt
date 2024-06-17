package com.example.musicappui.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.IconButton
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.ScaffoldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu


import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.musicappui.MainViewModel
import com.example.musicappui.Screen
import com.example.musicappui.screenInBottom
import com.example.musicappui.screenInDrawer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import com.example.musicappui.Navigation
import com.example.musicappui.R
@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun MainView(){
    val scaffoldState:ScaffoldState= rememberScaffoldState()
    val scope:CoroutineScope= rememberCoroutineScope()

    //Modal Sheet
    val isSheetFullScreen by remember {
    mutableStateOf(false)
     }
    val modifier=if(isSheetFullScreen) Modifier.fillMaxSize() else Modifier.fillMaxWidth()

    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmValueChange = { it != ModalBottomSheetValue.HalfExpanded }
    )

    val roundedcornerradius= if(isSheetFullScreen) 0.dp else 12.dp

    // These three below tells us on which screen we are currently
    val Controller:NavController= rememberNavController()
    val navBackStackEntry by Controller.currentBackStackEntryAsState()
    val currentRoute=navBackStackEntry?.destination?.route
     val viewModel:MainViewModel= viewModel()
    val currentScreen=remember{
        viewModel.currentScreen.value
    }
    val title =remember{
        mutableStateOf(currentScreen.title) }

    val dialogopen=remember{ mutableStateOf(false) }


    val bottombar:@Composable () -> Unit ={
        if(currentScreen is Screen.DrawerScreen||currentScreen==Screen.BottomScreen.Home){
          BottomNavigation(modifier = Modifier.wrapContentSize()) {
           screenInBottom.forEach { item ->
               val isSelected=currentRoute==item.bRoute
               val tint=if(isSelected) Color.White else Color.Black
               BottomNavigationItem(selected = currentRoute == item.bRoute,
                   onClick = { Controller.navigate(item.bRoute)
                             title.value=item.bTitle}, icon = {
                       Icon(tint=tint,
                           painter = painterResource(id = item.Icon),
                           contentDescription = item.bTitle
                       )
                   },
                   label = { Text(text = item.bTitle, color = tint) },
                   selectedContentColor = Color.White,
                   unselectedContentColor = Color.Black
               )
           }

          }
        }


    }
    ModalBottomSheetLayout(sheetState=sheetState,
        sheetShape = RoundedCornerShape(topStart = roundedcornerradius, topEnd = roundedcornerradius),
        sheetContent = {
       MoreBottomSheet(modifier =modifier )
    }) {
        Scaffold(bottomBar = bottombar, topBar = {
            TopAppBar(title = { Text(text = title.value)},
                actions = {
                          IconButton(onClick = { scope.launch {
                              if(sheetState.isVisible) sheetState.hide()
                              else{
                                  sheetState.show()
                              }
                          }} 
                          ){
                            Icon(imageVector = Icons.Default.MoreVert, contentDescription = null)
                          }
                },
                navigationIcon = {IconButton(onClick = {
                    //Drawer
                    scope.launch {
                        scaffoldState.drawerState.open()
                    }
                } ) {
                    Icon(imageVector = Icons.Default.Menu, contentDescription ="Menu" )
                }

                }
            )
        },scaffoldState=scaffoldState,
            drawerContent = {
                LazyColumn(Modifier.padding(16.dp)){
                    items(screenInDrawer){
                            item->
                        DrawerItem(selected = currentRoute==item.dRoute, item =item ) {
                            scope.launch {
                                scaffoldState.drawerState.close()
                            }
                            if(item.dRoute=="add_account"){
                                //open dialog
                                dialogopen.value=true
                            }
                            else{
                                Controller.navigate(item.dRoute)
                                title.value= item.dTitle
                            }
                        }

                    }
                }
            }
        )

        {

            Navigation(navController =Controller, viewModel =viewModel , pd =it )
            AccountDialog(dialogopen = dialogopen)
        }
    }
    


}


@Composable
fun DrawerItem(
    selected:Boolean,
    item:Screen.DrawerScreen,
    onDrawerItemClicked: ()->Unit
){
    val background=if(selected) Color.Gray else Color.White

    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 8.dp, vertical = 16.dp)
        .background(background)
        .clickable {
            onDrawerItemClicked()
        }
    ) {
        Icon(
            painter = painterResource(id = item.Icon), contentDescription = item.dTitle,
            Modifier.padding(end = 8.dp, top = 4.dp)
        )
        Text(text = item.dTitle, style = MaterialTheme.typography.headlineMedium)
    }
}


@Composable
fun MoreBottomSheet(modifier: Modifier){
    Box(modifier = modifier
        .fillMaxWidth()
        .background(MaterialTheme.colorScheme.primary)){
        Column(modifier.padding(16.dp), verticalArrangement = Arrangement.SpaceBetween) {
            Row(modifier.padding(16.dp)) {
                Icon(painter = painterResource(id = R.drawable.baseline_settings_24),
                    contentDescription ="Settings" )
                Text(text = "Settings")
            }

            Row(modifier.padding(16.dp)) {
                Icon(painter = painterResource(id = R.drawable.baseline_share_24),
                    contentDescription ="Share" )
                Text(text = "Share")
            }

            Row(modifier.padding(16.dp)) {
                Icon(painter = painterResource(id = R.drawable.baseline_help_center_24),
                    contentDescription ="Help" )
                Text(text = "Help")
            }

        }
    }
}



    
    


