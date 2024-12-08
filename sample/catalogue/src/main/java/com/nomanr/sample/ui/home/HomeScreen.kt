package com.nomanr.sample.ui.home

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ColorLens
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material.icons.outlined.Web
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nomanr.lumo.sample.R
import com.nomanr.sample.ui.AppTheme
import com.nomanr.sample.ui.components.Icon
import com.nomanr.sample.ui.components.IconButton
import com.nomanr.sample.ui.components.IconButtonVariant
import com.nomanr.sample.ui.components.Scaffold
import com.nomanr.sample.ui.components.Text
import com.nomanr.sample.ui.components.card.OutlinedCard
import com.nomanr.sample.ui.components.topbar.TopBar
import com.nomanr.sample.ui.components.topbar.TopBarDefaults
import com.nomanr.sample.ui.home.components.ConfigModal
import com.nomanr.sample.ui.samples.Component
import com.nomanr.sample.ui.samples.Samples

@Composable
fun HomeScreen(navigateToDemo: (Component) -> Unit = {}) {

    Scaffold(topBar = {
        HomeTopBar()
    }) { padding ->
        ComponentList(padding = padding, navigateToDemo)
    }

}


@Composable
fun HomeTopBar(
    modifier: Modifier = Modifier,
) {
    var isConfigModalVisible by remember {
        mutableStateOf(false)
    }


    TopBar(
        modifier = modifier,
        colors = TopBarDefaults.topBarColors(),
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                modifier = Modifier.height(26.dp),
                painter = painterResource(id = R.drawable.logo_with_name),
                contentDescription = "Logo"
            )

            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                IconButton(variant = IconButtonVariant.PrimaryGhost, onClick = {
                    Log.d("TODO: NOMAN", "More Options Clicked")
                }) {
                    Icon(Icons.Outlined.Web, contentDescription = "More Options")
                }
                IconButton(variant = IconButtonVariant.PrimaryGhost, onClick = {
                    Log.d("TODO: NOMAN", "More Options Clicked")
                }) {
                    Icon(Icons.Outlined.ColorLens, contentDescription = "More Options")
                }
                IconButton(variant = IconButtonVariant.PrimaryGhost, onClick = {
                    isConfigModalVisible = true
                }) {
                    Icon(Icons.Outlined.MoreVert, contentDescription = "More Options")
                }
            }
        }
    }

    ConfigModal(isVisible = isConfigModalVisible, onDismiss = {
        isConfigModalVisible = false
    })

}

@Composable
internal fun ComponentList(padding: PaddingValues, navigateToDemo: (Component) -> Unit) {
    val componentSamples = Component.getAll()

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
        items(componentSamples.size) { index ->
            val component = componentSamples[index]
            ComponentListItem(component.label, onClick = {
                navigateToDemo(component)
            })
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}


@Composable
internal fun ComponentListItem(name: String, onClick: () -> Unit) {
    val hasSample = remember { Samples.hasComponent(name) }
    OutlinedCard(modifier = Modifier.fillMaxSize(), onClick = onClick) {
        Column(
            modifier = Modifier.padding(vertical = 16.dp, horizontal = 16.dp), verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = name, style = AppTheme.typography.label1, minLines = 2, maxLines = 2
            )

            if (!hasSample) {
                Text(
                    text = "Coming Soon", style = AppTheme.typography.body2, textAlign = TextAlign.Center, color = Color.Red
                )
            }
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