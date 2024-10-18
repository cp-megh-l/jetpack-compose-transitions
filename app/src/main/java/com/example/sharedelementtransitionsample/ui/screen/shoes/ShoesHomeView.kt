package com.example.sharedelementtransitionsample.ui.screen.shoes

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.sharedelementtransitionsample.data.shoes.ShoesUtils
import com.example.sharedelementtransitionsample.model.shoes.Brand
import com.example.sharedelementtransitionsample.model.shoes.Shoe

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun ShoesHomeView() {

    val brandsList by remember {
        mutableStateOf(ShoesUtils.brandList)
    }
    val shoesList by remember {
        mutableStateOf(ShoesUtils.shoeList)
    }

    SharedTransitionLayout(modifier = Modifier.fillMaxSize()) {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = "home") {
            composable("home") {
                ShoesView(
                    navController = navController,
                    brandsList = brandsList,
                    shoesList = shoesList,
                    sharedTransitionScope = this@SharedTransitionLayout,
                    animatedVisibilityScope = this@composable
                )
            }
            composable(
                "shoe_detail/{index}",
                arguments = listOf(navArgument("index") { type = NavType.IntType })
            ) { backStackEntry ->
                val index = backStackEntry.arguments?.getInt("index")
                val shoeDetails = shoesList.getOrNull(index ?: 0)
                if (shoeDetails != null) {
                    ShoesDetailView(
                        index = index ?: 0,
                        shoe = shoeDetails,
                        sharedTransitionScope = this@SharedTransitionLayout,
                        animatedVisibilityScope = this@composable
                    ) {
                        navController.popBackStack()
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalSharedTransitionApi::class)
@Composable
fun ShoesView(
    navController: NavController,
    brandsList: List<Brand>,
    shoesList: List<Shoe>,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope
) {
    var selectedBrand by remember {
        mutableStateOf(brandsList.first())
    }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(title = {
                Text(text = "Discover Shoes")
            }, actions = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search"
                    )
                }
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Outlined.Notifications,
                        contentDescription = "Alerts"
                    )
                }
            })
        }
    ) { innerPadding ->
        LazyColumn(modifier = Modifier.padding(innerPadding)) {
            item {
                BrandsListView(brandsList = brandsList) {
                    selectedBrand = it
                }
            }

            item {
                ShoesListView(
                    navController = navController,
                    shoesList = shoesList/*.filter { it.brand == selectedBrand }*/,
                    sharedTransitionScope = sharedTransitionScope,
                    animatedVisibilityScope = animatedVisibilityScope
                )
            }

            item {
                MoreShoesBottomView(shoesList = shoesList)
            }
        }
    }
}