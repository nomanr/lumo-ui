package com.nomanr.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.nomanr.sample.ui.CatalogueApp
import com.nomanr.sample.ui.CatalogueAppProviders

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        installSplashScreen()
        setContent {
            CatalogueAppProviders {
                CatalogueApp()
            }
        }
    }
}
