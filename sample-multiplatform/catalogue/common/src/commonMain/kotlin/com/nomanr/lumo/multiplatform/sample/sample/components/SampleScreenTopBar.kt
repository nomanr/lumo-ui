package com.nomanr.lumo.multiplatform.sample.sample.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nomanr.lumo.multiplatform.ui.AppTheme
import com.nomanr.lumo.multiplatform.ui.components.Icon
import com.nomanr.lumo.multiplatform.ui.components.IconButton
import com.nomanr.lumo.multiplatform.ui.components.IconButtonVariant
import com.nomanr.lumo.multiplatform.ui.components.Text
import com.nomanr.lumo.multiplatform.ui.components.topbar.TopBar
import com.nomanr.lumo.multiplatform.ui.components.topbar.TopBarDefaults
import com.nomanr.lumo.multiplatform.ui.components.topbar.TopBarScrollBehavior

@Composable
fun SampleScreenTopBar(
    modifier: Modifier = Modifier,
    scrollBehavior: TopBarScrollBehavior? = null,
    title: String,
    onBack: () -> Unit = {},
) {
    TopBar(
        modifier = modifier,
        scrollBehavior = scrollBehavior,
        colors = TopBarDefaults.topBarColors(),
    ) {
        Row(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(horizontal = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            IconButton(
                variant = IconButtonVariant.Ghost,
                onClick = onBack,
            ) {
                Icon(Icons.AutoMirrored.Outlined.ArrowBack, contentDescription = "More Options")
            }

            Text(text = title, style = AppTheme.typography.h3)
        }
    }
}
