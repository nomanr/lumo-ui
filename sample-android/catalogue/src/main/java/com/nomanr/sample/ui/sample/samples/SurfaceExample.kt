package com.nomanr.sample.ui.sample.samples

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.nomanr.sample.ui.AppTheme
import com.nomanr.sample.ui.components.Icon
import com.nomanr.sample.ui.components.Surface
import com.nomanr.sample.ui.components.Text

@Composable
fun SurfaceSample() {
    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Surface(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .defaultMinSize(minHeight = 80.dp),
        ) {
            Box(
                modifier =
                    Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = "Surface visually separates content layers and establishes clear spatial hierarchy.",
                    textAlign = TextAlign.Center,
                    style = AppTheme.typography.label1,
                )
            }
        }

        Surface(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .defaultMinSize(minHeight = 80.dp),
            color = AppTheme.colors.primary,
        ) {
            Column(
                modifier =
                    Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                Text(
                    text = "Content color changes based on the surface background.",
                    textAlign = TextAlign.Center,
                    style = AppTheme.typography.label1,
                )

                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(Icons.Default.Favorite, contentDescription = "Favorite")
                    Icon(Icons.Default.Home, contentDescription = "Home")
                    Icon(Icons.Default.Star, contentDescription = "Star")
                }
            }
        }

        Surface(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .defaultMinSize(minHeight = 80.dp),
            color = AppTheme.colors.primary,
            shape = CircleShape,
        ) {
            Column(
                modifier =
                    Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                Text(
                    text = "It can be of any shape.",
                    textAlign = TextAlign.Center,
                    style = AppTheme.typography.label1,
                )

                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(Icons.Default.Favorite, contentDescription = "Favorite")
                    Icon(Icons.Default.Home, contentDescription = "Home")
                    Icon(Icons.Default.Star, contentDescription = "Star")
                }
            }
        }

        Surface(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .defaultMinSize(minHeight = 80.dp),
            color = AppTheme.colors.secondary,
            shape = CircleShape,
            shadowElevation = 8.dp,
        ) {
            Column(
                modifier =
                    Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                Text(
                    text = "With elevation.",
                    textAlign = TextAlign.Center,
                    style = AppTheme.typography.label1,
                )

                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(Icons.Default.Favorite, contentDescription = "Favorite")
                    Icon(Icons.Default.Home, contentDescription = "Home")
                    Icon(Icons.Default.Star, contentDescription = "Star")
                }
            }
        }

        Surface(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .defaultMinSize(minHeight = 80.dp),
            color = AppTheme.colors.success,
            shape = RoundedCornerShape(8.dp),
            shadowElevation = 8.dp,
        ) {
            Column(
                modifier =
                    Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(Icons.Default.Favorite, contentDescription = "Favorite")
                    Icon(Icons.Default.Home, contentDescription = "Home")
                    Icon(Icons.Default.Star, contentDescription = "Star")
                }
            }
        }

        Surface(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .defaultMinSize(minHeight = 80.dp),
            color = AppTheme.colors.disabled,
            shape = RoundedCornerShape(8.dp),
            shadowElevation = 8.dp,
        ) {
            Column(
                modifier =
                    Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(Icons.Default.Favorite, contentDescription = "Favorite")
                    Icon(Icons.Default.Home, contentDescription = "Home")
                    Icon(Icons.Default.Star, contentDescription = "Star")
                }
            }
        }

        Surface(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .defaultMinSize(minHeight = 80.dp),
            color = AppTheme.colors.tertiary,
            shape = RoundedCornerShape(8.dp),
        ) {
            Column(
                modifier =
                    Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(Icons.Default.Favorite, contentDescription = "Favorite")
                    Icon(Icons.Default.Home, contentDescription = "Home")
                    Icon(Icons.Default.Star, contentDescription = "Star")
                }
            }
        }

        Surface(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .defaultMinSize(minHeight = 80.dp),
            color = AppTheme.colors.error,
            shape = RoundedCornerShape(8.dp),
        ) {
            Column(
                modifier =
                    Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(Icons.Default.Favorite, contentDescription = "Favorite")
                    Icon(Icons.Default.Home, contentDescription = "Home")
                    Icon(Icons.Default.Star, contentDescription = "Star")
                }
            }
        }
    }
}
