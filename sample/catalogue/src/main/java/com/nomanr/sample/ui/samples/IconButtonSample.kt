package com.nomanr.sample.ui.samples


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nomanr.sample.ui.AppTheme
import com.nomanr.sample.ui.components.Icon
import com.nomanr.sample.ui.components.IconButton
import com.nomanr.sample.ui.components.IconButtonVariant
import com.nomanr.sample.ui.components.Text
import com.nomanr.sample.ui.components.progress_indicator.CircularProgressIndicator

@Composable
fun IconButtonSample(padding: PaddingValues) {
    Column(
        modifier = Modifier
            .padding(padding)
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {

        RealWorldIconButtonExamples()

        Spacer(modifier = Modifier.height(30.dp))

        PrimaryIconButtonSample()

        Spacer(modifier = Modifier.height(30.dp))

        SecondaryIconButtonSample()

        Spacer(modifier = Modifier.height(30.dp))

        DestructiveIconButtonSample()

        Spacer(modifier = Modifier.height(30.dp))

    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
internal fun RealWorldIconButtonExamples() {
    Column(
        modifier = Modifier
            .background(AppTheme.colors.background)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(text = "Icon Button Examples", style = AppTheme.typography.h4)

        FlowRow(
            modifier = Modifier.padding(top = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            IconButton(variant = IconButtonVariant.Primary) {
                Icon(Icons.Default.Star, contentDescription = "Favorite")
            }

            IconButton(variant = IconButtonVariant.SecondaryOutlined) {
                Icon(Icons.Default.Check, contentDescription = "Done")
            }

            IconButton(variant = IconButtonVariant.Primary) {
                CircularProgressIndicator(modifier = Modifier.size(24.dp))
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            IconButton(variant = IconButtonVariant.PrimaryElevated) {
                Icon(Icons.Default.Email, contentDescription = "Email")
            }

            IconButton(variant = IconButtonVariant.Destructive) {
                Icon(Icons.Default.Delete, contentDescription = "Delete")
            }
            IconButton(variant = IconButtonVariant.SecondaryGhost) {
                CircularProgressIndicator(modifier = Modifier.size(24.dp))
            }
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(variant = IconButtonVariant.Primary, shape = CircleShape) {
                Icon(Icons.Default.PhotoCamera, contentDescription = "Circle Icon")
            }
            IconButton(variant = IconButtonVariant.Secondary, shape = RoundedCornerShape(8.dp)) {
                Icon(Icons.Default.PhotoCamera, contentDescription = "Square Icon")
            }

            IconButton(variant = IconButtonVariant.Destructive, shape = RoundedCornerShape(0.dp)) {
                Icon(Icons.Default.PhotoCamera, contentDescription = "Square Icon")
            }

            IconButton(variant = IconButtonVariant.PrimaryGhost, shape = RoundedCornerShape(0.dp)) {
                Icon(Icons.Default.PhotoCamera, contentDescription = "Square Icon")
            }

        }


    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
internal fun PrimaryIconButtonSample() {
    Column(
        modifier = Modifier
            .background(AppTheme.colors.background)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(text = "Primary Icon Buttons", style = AppTheme.typography.h4)

        FlowRow(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            IconButton(variant = IconButtonVariant.Primary) {
                Icon(Icons.Default.Star, contentDescription = "PrimaryFilled")
            }
            IconButton(variant = IconButtonVariant.PrimaryOutlined) {
                Icon(Icons.Default.Star, contentDescription = "PrimaryOutlined")
            }
            IconButton(variant = IconButtonVariant.PrimaryElevated) {
                Icon(Icons.Default.Star, contentDescription = "PrimaryElevated")
            }
            IconButton(variant = IconButtonVariant.PrimaryGhost) {
                Icon(Icons.Default.Star, contentDescription = "PrimaryGhost")
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
internal fun SecondaryIconButtonSample() {
    Column(
        modifier = Modifier
            .background(AppTheme.colors.background)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(text = "Secondary Icon Buttons", style = AppTheme.typography.h4)

        FlowRow(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            IconButton(variant = IconButtonVariant.Secondary) {
                Icon(Icons.Default.Email, contentDescription = "SecondaryFilled")
            }
            IconButton(variant = IconButtonVariant.SecondaryOutlined) {
                Icon(Icons.Default.Email, contentDescription = "SecondaryOutlined")
            }
            IconButton(variant = IconButtonVariant.SecondaryElevated) {
                Icon(Icons.Default.Email, contentDescription = "SecondaryElevated")
            }
            IconButton(variant = IconButtonVariant.SecondaryGhost) {
                Icon(Icons.Default.Email, contentDescription = "SecondaryGhost")
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
internal fun DestructiveIconButtonSample() {
    Column(
        modifier = Modifier
            .background(AppTheme.colors.background)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(text = "Destructive Icon Buttons", style = AppTheme.typography.h4)

        FlowRow(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            IconButton(variant = IconButtonVariant.Destructive) {
                Icon(Icons.Default.Delete, contentDescription = "DestructiveFilled")
            }
            IconButton(variant = IconButtonVariant.DestructiveOutlined) {
                Icon(Icons.Default.Delete, contentDescription = "DestructiveOutlined")
            }
            IconButton(variant = IconButtonVariant.DestructiveElevated) {
                Icon(Icons.Default.Delete, contentDescription = "DestructiveElevated")
            }
            IconButton(variant = IconButtonVariant.DestructiveGhost) {
                Icon(Icons.Default.Delete, contentDescription = "DestructiveGhost")
            }
        }
    }
}
