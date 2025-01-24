package com.nomanr.sample.ui.update_theme

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.nomanr.sample.ui.update_theme.components.ColorPickerModal
import com.nomanr.sample.ui.AppColors
import com.nomanr.sample.ui.AppTheme
import com.nomanr.sample.ui.components.Icon
import com.nomanr.sample.ui.components.IconButton
import com.nomanr.sample.ui.components.IconButtonVariant
import com.nomanr.sample.ui.components.Scaffold
import com.nomanr.sample.ui.components.Text
import com.nomanr.sample.ui.components.topbar.TopBar
import com.nomanr.sample.ui.components.topbar.TopBarDefaults
import com.nomanr.sample.ui.configs.LocalAppConfigState
import com.nomanr.sample.ui.update_theme.utils.getANewAppColorsCopy
import com.nomanr.sample.ui.update_theme.utils.getColorData

@Composable
fun UpdateThemeScreen(navigateUp: () -> Unit) {

    var colorToUpdate by remember { mutableStateOf<ColorToUpdate?>(null) }

    val appConfig = LocalAppConfigState.current
    val themeColors = appConfig.colors



    Scaffold(topBar = {
        UpdateThemeTopBar(onBack = navigateUp)
    }) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            ListHeader()

            Spacer(modifier = Modifier.size(16.dp))

            ThemeColors(themeColors) { title, isLight, lightColor, darkColor ->
                colorToUpdate = ColorToUpdate(title, isLight, lightColor, darkColor)
            }

        }

    }

    ColorPickerModal(isVisible = colorToUpdate != null, onSelectColor = { color ->
        colorToUpdate?.let { colorToUpdate ->
            val lightColor = if (colorToUpdate.isLight) color else colorToUpdate.prevDarkColor
            val darkColor = if (!colorToUpdate.isLight) color else colorToUpdate.prevLightColor
            appConfig.updateColors(getANewAppColorsCopy(colorToUpdate.title, lightColor, darkColor, appConfig.colors))
        }
        colorToUpdate = null
    }, onDismiss = {
        colorToUpdate = null
    })

}


@Composable
fun UpdateThemeTopBar(
    modifier: Modifier = Modifier, onBack: () -> Unit = {}
) {

    TopBar(
        modifier = modifier,
        colors = TopBarDefaults.topBarColors(),
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            IconButton(
                variant = IconButtonVariant.PrimaryGhost, onClick = onBack
            ) {
                Icon(Icons.AutoMirrored.Outlined.ArrowBack, contentDescription = "More Options")
            }

            Text(text = "Update theme colors", style = AppTheme.typography.h3)

        }
    }
}

@Composable
fun ListHeader() {
    UpdateThemeListItemContainer(
        content1 = {
            Text(
                text = "Name", style = AppTheme.typography.h4
            )
        },
        content2 = {
            Text(
                text = "Light", style = AppTheme.typography.h4
            )
        },
        content3 = {
            Text(
                text = "Dark", style = AppTheme.typography.h4
            )
        },
    )
}

@Composable
fun ThemeColors(themeColors: AppColors, onUpdateColor: (String, Boolean, Color, Color) -> Unit) {
    val colors = remember(themeColors) { getColorData(themeColors) }

    colors.entries.forEach { entry ->
        UpdateThemeListItemContainer(
            content1 = {
                Text(
                    text = entry.key, style = AppTheme.typography.body1
                )
            },
            content2 = {
                ColorItem(entry.value.first) {
                    onUpdateColor(entry.key, true, entry.value.first, entry.value.second)
                }
            },
            content3 = {
                ColorItem(entry.value.second) {
                    onUpdateColor(entry.key, false, entry.value.first, entry.value.second)
                }
            },
        )
    }
}

@Composable
fun ColorItem(color: Color, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .border(2.dp, AppTheme.colors.onBackground, CircleShape)
            .padding(2.dp)
            .size(60.dp)
            .clip(CircleShape)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center,

        ) {
        Box(
            modifier = Modifier
                .size(56.dp)
                .background(color, CircleShape),
        )
    }
}

@Composable
fun UpdateThemeListItemContainer(
    content1: @Composable () -> Unit, content2: @Composable () -> Unit, content3: @Composable () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.CenterStart) {
            content1()
        }

        Box(modifier = Modifier.weight(0.75f), contentAlignment = Alignment.Center) {
            content2()
        }

        Box(modifier = Modifier.weight(0.75f), contentAlignment = Alignment.Center) {
            content3()
        }
    }
}

data class ColorToUpdate(val title: String, val isLight: Boolean, val prevLightColor: Color, val prevDarkColor: Color) {}
