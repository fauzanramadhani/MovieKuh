package com.ndc.moviekuh

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.ndc.moviekuh.ui.navigation.SetupNavHost
import com.ndc.moviekuh.ui.theme.MovieKuhTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()

        super.onCreate(savedInstanceState)
        setContent {
            MovieKuhTheme {
                val navHostController = rememberNavController()
                SetupNavHost(navHostController = navHostController)
            }
        }
    }
}