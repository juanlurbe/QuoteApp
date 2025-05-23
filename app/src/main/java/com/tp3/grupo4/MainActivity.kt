package com.tp3.grupo4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.navigation.compose.rememberNavController
import com.tp3.grupo4.presentation.navigation.AppNavGraph
import com.tp3.grupo4.ui.theme.Challenge3Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            Challenge3Theme {
                Surface {
                    AppNavGraph(navController = navController)
                }
            }
        }
    }
}
