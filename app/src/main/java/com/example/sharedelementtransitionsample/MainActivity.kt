package com.example.sharedelementtransitionsample

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Scaffold
import com.example.sharedelementtransitionsample.ui.screen.movies.MoviePager
import com.example.sharedelementtransitionsample.ui.theme.SharedElementTransitionSampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        window.statusBarColor = Color.BLACK
        window.navigationBarColor = Color.BLACK
        setContent {
            SharedElementTransitionSampleTheme {
                Scaffold {
                    MoviePager(paddingValues = it)
                }
            }
        }
    }
}