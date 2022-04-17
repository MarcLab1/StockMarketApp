package com.compose.stockmarket.ui.presentation

import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.compose.stockmarket.ui.presentation.stock.MainScreen
import com.compose.stockmarket.ui.presentation.stocklist.StockListScreen
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun Home() {
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    val nav: NavController = rememberNavController()
    val scope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = scaffoldState,
        drawerContent = {
            DrawerContent(nav, scope, scaffoldState)
        },
    ) {
        Navigation(nav)
    }
}

private val routes = listOf(
    Routes.Stock,
    Routes.StockList,
)

@Composable
fun DrawerContent(nav: NavController, scope: CoroutineScope, scaffoldState: ScaffoldState) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 10.dp, start = 10.dp, end = 10.dp)
    ) {
        routes.forEach { route ->
            Spacer(Modifier.height(24.dp))
            Text(
                text = route.route,
                style = MaterialTheme.typography.h4,
                modifier = Modifier.clickable {
                    nav.navigate(route.route)

                    scope.launch {
                        scaffoldState.drawerState.close()
                    }
                }
            )
        }
    }
}

@Composable
fun Navigation(nav: NavController) {

    NavHost(navController = nav as NavHostController, startDestination = Routes.Stock.route)
    {
        composable(Routes.Stock.route)
        {
            MainScreen()
        }
        composable(Routes.StockList.route)
        {
            StockListScreen()
        }
    }
}

sealed class Routes(val route: String) {
    object Stock : Routes("stock")
    object StockList : Routes("stock list")
}