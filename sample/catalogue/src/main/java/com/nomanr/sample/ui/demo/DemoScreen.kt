package com.nomanr.sample.ui.demo

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nomanr.sample.ui.AppTheme
import com.nomanr.sample.ui.components.Icon
import com.nomanr.sample.ui.components.IconButton
import com.nomanr.sample.ui.components.IconButtonVariant
import com.nomanr.sample.ui.components.Scaffold
import com.nomanr.sample.ui.components.Text
import com.nomanr.sample.ui.components.topbar.TopBar
import com.nomanr.sample.ui.components.topbar.TopBarDefaults
import com.nomanr.sample.ui.components.topbar.TopBarScrollBehavior
import com.nomanr.sample.ui.data.Component
import com.nomanr.sample.ui.samples.Samples

@Composable
fun DemoScreen(component: Component, navigateUp: () -> Unit = {}) {

    val sample = Samples.components[component]
    var interceptNavigateUp by remember {
        mutableStateOf(false)
    }

    var triggerBackAction by remember {
        mutableIntStateOf(0)
    }

    BackHandler {
        if (!interceptNavigateUp) {
            navigateUp()
        } else {
            triggerBackAction++
        }
    }

    Scaffold(topBar = {
        DemoTopBar(component = component.label, onBack = {
            if (!interceptNavigateUp) {
                navigateUp()
            } else {
                triggerBackAction++
            }
        })
    }) { padding ->
        if (sample != null) {
            sample(padding, triggerBackAction) { intercept ->
                interceptNavigateUp = intercept
            }
        } else {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "Sample not found for ${component.label}")
            }
        }
    }
}

@Composable
fun DemoTopBar(
    modifier: Modifier = Modifier, scrollBehavior: TopBarScrollBehavior? = null, component: String, onBack: () -> Unit = {}
) {

    TopBar(
        modifier = modifier,
        scrollBehavior = scrollBehavior,
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

            Text(text = component, style = AppTheme.typography.h3)

        }
    }
}
