package com.nomanr.sample.ui.samples

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nomanr.lumo.sample.R
import com.nomanr.sample.ui.AppTheme
import com.nomanr.sample.ui.components.Icon
import com.nomanr.sample.ui.components.Text

@OptIn(ExperimentalLayoutApi::class)
@Composable
internal fun IconSample() {
    Column(
        modifier = Modifier
            .background(AppTheme.colors.background)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(text = "Different examples, vector icons and drawable resources ", style = AppTheme.typography.h4)
        Spacer(modifier = Modifier.height(16.dp))

        FlowRow(
            verticalArrangement = Arrangement.spacedBy(8.dp), horizontalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Home,
                contentDescription = "Home Icon",
                tint = AppTheme.colors.primary,
            )
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = "Star Icon",
                tint = AppTheme.colors.secondary,
                modifier = Modifier.size(32.dp)
            )
            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = "Favorite Icon",
                tint = AppTheme.colors.error,
                modifier = Modifier.size(48.dp)
            )
        }

        FlowRow(
            verticalArrangement = Arrangement.spacedBy(8.dp), horizontalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Icon(
                resourceId = R.drawable.logo,
                contentDescription = "Email Icon",
                tint = AppTheme.colors.onSurface,
            )
            Icon(
                resourceId = R.drawable.logo_with_name,
                contentDescription = "Delete Icon",
                tint = AppTheme.colors.error,
                modifier = Modifier.height(26.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        FlowRow(
            verticalArrangement = Arrangement.spacedBy(8.dp), horizontalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = "Check Icon",
                tint = AppTheme.colors.secondary,
                modifier = Modifier.size(16.dp)
            )
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add Icon",
                tint = AppTheme.colors.error,
                modifier = Modifier.size(40.dp)
            )
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "Close Icon",
                tint = AppTheme.colors.primary,
                modifier = Modifier.size(64.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}