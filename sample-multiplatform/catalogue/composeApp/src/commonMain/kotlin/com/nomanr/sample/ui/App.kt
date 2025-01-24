package com.nomanr.sample.ui


import androidx.compose.runtime.Composable
import com.nomanr.sample.ui.CatalogueApp
import com.nomanr.sample.ui.CatalogueAppProviders
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    CatalogueAppProviders{
        CatalogueApp()
    }
}