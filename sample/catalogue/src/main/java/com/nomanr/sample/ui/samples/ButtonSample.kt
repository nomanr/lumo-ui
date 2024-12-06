package com.nomanr.sample.ui.samples

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Facebook
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nomanr.sample.ui.AppTheme
import com.nomanr.sample.ui.components.Button
import com.nomanr.sample.ui.components.ButtonVariant
import com.nomanr.sample.ui.components.Icon
import com.nomanr.sample.ui.components.Text
import com.nomanr.sample.ui.components.progress_indicator.CircularProgressIndicator

@Composable
fun ButtonSample(padding: PaddingValues) {
    Column(
        modifier = Modifier
            .padding(paddingValues = padding)
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {

        ButtonRealWorldExamples()

        Spacer(modifier = Modifier.height(30.dp))

        PrimaryButtonSample()

        Spacer(modifier = Modifier.height(30.dp))

        SecondaryButtonSample()

        Spacer(modifier = Modifier.height(30.dp))

        DestructiveButtonSample()


    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
internal fun ButtonRealWorldExamples() {
    Column(
        modifier = Modifier
            .background(AppTheme.colors.background)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Examples", style = AppTheme.typography.h4
        )

        FlowRow(
            modifier = Modifier.padding(top = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Button(variant = ButtonVariant.Primary) {
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
                    Text("Add to favorites", style = AppTheme.typography.button)
                    Icon(Icons.Default.Star, contentDescription = "Star Icon")
                }
            }

            Button(variant = ButtonVariant.SecondaryOutlined) {
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
                    Text("Done", style = AppTheme.typography.button)
                    Icon(Icons.Default.Check, contentDescription = "Check Icon")
                }
            }
        }

        FlowRow(
            verticalArrangement = Arrangement.spacedBy(8.dp), horizontalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Button(variant = ButtonVariant.PrimaryElevated) {
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Email, contentDescription = "Email Icon")
                    Text("Email", style = AppTheme.typography.button)
                }
            }

            Button(variant = ButtonVariant.Destructive) {
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Delete, contentDescription = "Delete Icon")
                    Text("Delete", style = AppTheme.typography.button)
                }
            }
        }


        Button(
            modifier = Modifier.fillMaxWidth(), variant = ButtonVariant.PrimaryOutlined
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Default.PhotoCamera, contentDescription = "Photo")
                Text("Take Photo", style = AppTheme.typography.button)
                Icon(Icons.Default.ChevronRight, contentDescription = "Next")

            }
        }


        Button(
            modifier = Modifier.fillMaxWidth(), variant = ButtonVariant.DestructiveOutlined, text = "Logout"
        )

        FlowRow(
            verticalArrangement = Arrangement.spacedBy(8.dp), horizontalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Button(variant = ButtonVariant.DestructiveGhost) {
                Text("Cancel", style = AppTheme.typography.button)
            }

            Button(variant = ButtonVariant.Primary) {
                Text("Save", style = AppTheme.typography.button)
            }
        }

        Button(
            modifier = Modifier.fillMaxWidth(), variant = ButtonVariant.Primary
        ) {
            Box(
                modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterStart
            ) {
                Icon(Icons.Outlined.Facebook, contentDescription = "Photo")
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Login", style = AppTheme.typography.button)
                }
            }
        }

        FlowRow(
            verticalArrangement = Arrangement.spacedBy(8.dp), horizontalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Button(variant = ButtonVariant.SecondaryOutlined) {
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
                    Text("Previous", style = AppTheme.typography.button)
                }
            }

            Button(variant = ButtonVariant.Secondary) {
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
                    Text("Next", style = AppTheme.typography.button)
                }
            }

            Button(
                modifier = Modifier, variant = ButtonVariant.SecondaryGhost
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("See All", style = AppTheme.typography.button)
                    Icon(Icons.Default.ChevronRight, contentDescription = "Next")
                }
            }

        }

        FlowRow(
            verticalArrangement = Arrangement.spacedBy(8.dp), horizontalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Button(variant = ButtonVariant.Primary, enabled = false) {
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
                    Text("Disabled", style = AppTheme.typography.button)
                    CircularProgressIndicator(modifier = Modifier.size(24.dp))
                }
            }

            Button(variant = ButtonVariant.SecondaryOutlined) {
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
                    Text("Submitting", style = AppTheme.typography.button)
                    CircularProgressIndicator(modifier = Modifier.size(24.dp))
                }
            }

        }

        Button(
            modifier = Modifier.fillMaxWidth(),
            variant = ButtonVariant.DestructiveElevated,
        ) {
            CircularProgressIndicator(modifier = Modifier.size(24.dp))
        }

    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
internal fun PrimaryButtonSample() {
    Column(
        modifier = Modifier
            .background(AppTheme.colors.background)
            .padding(16.dp),
    ) {
        Text(text = "Primary Buttons", style = AppTheme.typography.h4)

        Spacer(modifier = Modifier.height(24.dp))

        FlowRow(verticalArrangement = Arrangement.spacedBy(8.dp), horizontalArrangement = Arrangement.spacedBy(24.dp)) {
            Button(text = "Filled", variant = ButtonVariant.Primary, onClick = {})
            Button(
                text = "Filled Disabled", variant = ButtonVariant.Primary, enabled = false
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        FlowRow(
            verticalArrangement = Arrangement.spacedBy(8.dp), horizontalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Button(text = "Outlined", variant = ButtonVariant.PrimaryOutlined, onClick = {})
            Button(text = "Outlined Disabled", variant = ButtonVariant.PrimaryOutlined, enabled = false)
        }

        Spacer(modifier = Modifier.height(16.dp))
        FlowRow(
            verticalArrangement = Arrangement.spacedBy(8.dp), horizontalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Button(text = "Elevated", variant = ButtonVariant.PrimaryElevated, onClick = {})
            Button(text = "Elevated Disabled", variant = ButtonVariant.PrimaryElevated, enabled = false)
        }

        Spacer(modifier = Modifier.height(16.dp))
        FlowRow(
            verticalArrangement = Arrangement.spacedBy(8.dp), horizontalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Button(text = "Ghost", variant = ButtonVariant.PrimaryGhost, onClick = {})
            Button(text = "Ghost Disabled", variant = ButtonVariant.PrimaryGhost, enabled = false)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(modifier = Modifier.fillMaxWidth(), text = "Full Width", variant = ButtonVariant.Primary, onClick = {})


    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
internal fun SecondaryButtonSample() {
    Column(
        modifier = Modifier
            .background(AppTheme.colors.background)
            .padding(16.dp),
    ) {
        Text(text = "Secondary Buttons", style = AppTheme.typography.h4)

        Spacer(modifier = Modifier.height(24.dp))

        FlowRow(verticalArrangement = Arrangement.spacedBy(8.dp), horizontalArrangement = Arrangement.spacedBy(24.dp)) {
            Button(text = "Filled", variant = ButtonVariant.Secondary, onClick = {})
            Button(text = "Filled Disabled", variant = ButtonVariant.Secondary, enabled = false)
        }

        Spacer(modifier = Modifier.height(16.dp))

        FlowRow(verticalArrangement = Arrangement.spacedBy(8.dp), horizontalArrangement = Arrangement.spacedBy(24.dp)) {
            Button(text = "Outlined", variant = ButtonVariant.SecondaryOutlined, onClick = {})
            Button(text = "Outlined Disabled", variant = ButtonVariant.SecondaryOutlined, enabled = false)
        }

        Spacer(modifier = Modifier.height(16.dp))

        FlowRow(verticalArrangement = Arrangement.spacedBy(8.dp), horizontalArrangement = Arrangement.spacedBy(24.dp)) {
            Button(text = "Elevated", variant = ButtonVariant.SecondaryElevated, onClick = {})
            Button(text = "Elevated Disabled", variant = ButtonVariant.SecondaryElevated, enabled = false)
        }

        Spacer(modifier = Modifier.height(16.dp))

        FlowRow(verticalArrangement = Arrangement.spacedBy(8.dp), horizontalArrangement = Arrangement.spacedBy(24.dp)) {
            Button(text = "Ghost", variant = ButtonVariant.SecondaryGhost, onClick = {})
            Button(text = "Ghost Disabled", variant = ButtonVariant.SecondaryGhost, enabled = false)
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
internal fun DestructiveButtonSample() {
    Column(
        modifier = Modifier
            .background(AppTheme.colors.background)
            .padding(16.dp),
    ) {
        Text(text = "Destructive Buttons", style = AppTheme.typography.h4)

        Spacer(modifier = Modifier.height(24.dp))

        FlowRow(verticalArrangement = Arrangement.spacedBy(8.dp), horizontalArrangement = Arrangement.spacedBy(24.dp)) {
            Button(text = "Filled", variant = ButtonVariant.Destructive, onClick = {})
            Button(text = "Filled Disabled", variant = ButtonVariant.Destructive, enabled = false)
        }

        Spacer(modifier = Modifier.height(16.dp))

        FlowRow(verticalArrangement = Arrangement.spacedBy(8.dp), horizontalArrangement = Arrangement.spacedBy(24.dp)) {
            Button(text = "Outlined", variant = ButtonVariant.DestructiveOutlined, onClick = {})
            Button(text = "Outlined Disabled", variant = ButtonVariant.DestructiveOutlined, enabled = false)
        }

        Spacer(modifier = Modifier.height(16.dp))

        FlowRow(verticalArrangement = Arrangement.spacedBy(8.dp), horizontalArrangement = Arrangement.spacedBy(24.dp)) {
            Button(text = "Elevated", variant = ButtonVariant.DestructiveElevated, onClick = {})
            Button(text = "Elevated Disabled", variant = ButtonVariant.DestructiveElevated, enabled = false)
        }

        Spacer(modifier = Modifier.height(16.dp))

        FlowRow(verticalArrangement = Arrangement.spacedBy(8.dp), horizontalArrangement = Arrangement.spacedBy(24.dp)) {
            Button(text = "Ghost", variant = ButtonVariant.DestructiveGhost, onClick = {})
            Button(text = "Ghost Disabled", variant = ButtonVariant.DestructiveGhost, enabled = false)
        }
    }
}

