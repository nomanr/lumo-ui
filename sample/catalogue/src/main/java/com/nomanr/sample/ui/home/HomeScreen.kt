package com.nomanr.sample.ui.home

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ColorLens
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Web
import androidx.compose.material.icons.outlined.ColorLens
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material.icons.outlined.Web
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nomanr.sample.ui.AppTheme
import com.nomanr.sample.ui.components.Icon
import com.nomanr.sample.ui.components.Scaffold
import com.nomanr.sample.ui.components.Text
import com.nomanr.sample.ui.components.card.OutlinedCard
import com.nomanr.sample.ui.components.icon_button.GhostIconButton
import com.nomanr.sample.ui.components.topbar.TopBar
import com.nomanr.sample.ui.data.AvailableComponents

@Composable
fun HomeScreen(navigateToDemo: (String) -> Unit = {}) {
    val components = AvailableComponents.entries
    Scaffold(topBar = {
        HomeTopBar()
    }) { padding ->
        LazyVerticalGrid(
            modifier = Modifier.padding(horizontal = 16.dp),
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            repeat(2) {
                item {
                    Spacer(modifier = Modifier.padding(padding))
                }
            }
            items(components.size) { index ->
                val component = components[index]
                ComponentListItem(component.label, onClick = {
                    navigateToDemo(component.name)
                })
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}


@Composable
internal fun HomeTopBar() {
    TopBar{
        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            GhostIconButton(onClick = {
                Log.d("TODO: NOMAN", "More Options Clicked")
            }) {
                Icon(Icons.Outlined.Web, contentDescription = "More Options")
            }
            GhostIconButton(onClick = {
                Log.d("TODO: NOMAN", "More Options Clicked")
            }) {
                Icon(Icons.Outlined.ColorLens, contentDescription = "More Options")
            }
            GhostIconButton(onClick = {
                Log.d("TODO: NOMAN", "More Options Clicked")
            }) {
                Icon(Icons.Outlined.MoreVert, contentDescription = "More Options")
            }
        }
    }
}


@Composable
internal fun ComponentListItem(name: String, onClick: () -> Unit = {}) {
    OutlinedCard(onClick = onClick) {
        Column(
            modifier = Modifier.padding(vertical = 16.dp, horizontal = 16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = name, style = AppTheme.typography.label1
            )
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    AppTheme {
        HomeScreen()
    }
}