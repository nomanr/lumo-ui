package com.nomanr.sample.ui.home

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
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key.Companion.R
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
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
import com.nomanr.sample.ui.sample.Component
import com.nomanr.sample.ui.sample.Samples
import lumo_ui.sample_multiplatform.catalogue.composeapp.generated.resources.Res
import lumo_ui.sample_multiplatform.catalogue.composeapp.generated.resources.logo_with_name
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun HomeScreen(navigateToDemo: (Component) -> Unit, navigateToUpdateTheme: () -> Unit) {

    Scaffold(topBar = {
        HomeTopBar(navigateToUpdateTheme)
    }) { padding ->
        ComponentList(padding = padding, navigateToDemo)
    }

}


@Composable
fun HomeTopBar(
    navigateToUpdateTheme: () -> Unit
) {
    var isConfigModalVisible by remember {
        mutableStateOf(false)
    }


    TopBar(
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
                painter = painterResource(Res.drawable.logo_with_name),
                contentDescription = "Logo"
            )

            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                IconButton(variant = IconButtonVariant.Ghost, onClick = {
                    println("TODO: NOMAN - More Options Clicked")
                }) {
                    Icon(Icons.Outlined.Add, contentDescription = "More Options")
                }
                IconButton(variant = IconButtonVariant.Ghost, onClick = navigateToUpdateTheme) {
                    Icon(Icons.Outlined.AddCircle, contentDescription = "More Options")
                }
                IconButton(variant = IconButtonVariant.Ghost, onClick = {
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
            Spacer(modifier = Modifier.height(24.dp))
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
        HomeScreen({}, {})
    }
}