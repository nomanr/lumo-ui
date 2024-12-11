package com.nomanr.sample.ui.sample.samples

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Chat
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.filled.CircleNotifications
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nomanr.sample.ui.AppTheme
import com.nomanr.sample.ui.components.Badge
import com.nomanr.sample.ui.components.BadgedBox
import com.nomanr.sample.ui.components.Button
import com.nomanr.sample.ui.components.ButtonVariant
import com.nomanr.sample.ui.components.Icon
import com.nomanr.sample.ui.components.IconButton
import com.nomanr.sample.ui.components.IconButtonVariant
import com.nomanr.sample.ui.components.Scaffold
import com.nomanr.sample.ui.components.Text
import com.nomanr.sample.ui.components.card.Card
import com.nomanr.sample.ui.components.topbar.TopBar
import com.nomanr.sample.ui.components.topbar.TopBarDefaults

@Composable
fun BadgeSample(
    navigateUp: (() -> Unit)? = null
) {
    Scaffold(topBar = {
        BadgeSampleTopBar(
            onBack = {
                navigateUp?.invoke()
            },
        )
    }) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp), verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {

            Text(text = "Badges", style = AppTheme.typography.h4)

            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(32.dp)) {
                Badge()

                Badge() {
                    Text(text = "9+", style = AppTheme.typography.label3)
                }

                Badge() {
                    Icon(Icons.Filled.CircleNotifications, contentDescription = "Warning")
                }
            }

            Spacer(modifier = Modifier.size(8.dp))
            Text(text = "Example with BadgeBox", style = AppTheme.typography.h4)

            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(32.dp)) {
                IconButton(
                    variant = IconButtonVariant.PrimaryGhost
                ) {
                    BadgedBox(badge = {
                        Badge()
                    }) {
                        Icon(Icons.Filled.Notifications, contentDescription = "Notifications")
                    }
                }

                IconButton(
                    variant = IconButtonVariant.PrimaryGhost
                ) {
                    BadgedBox(badge = {
                        Badge() {
                            Text(text = "12", style = AppTheme.typography.label3)
                        }
                    }) {
                        Icon(Icons.Filled.Notifications, contentDescription = "Notifications")
                    }
                }


                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {

                    BadgedBox(badge = {
                        Badge() {
                            Text(text = "9+", style = AppTheme.typography.label3)
                        }
                    }) {
                        Icon(Icons.Filled.Home, contentDescription = "Home")
                    }

                    Text(text = "Home", style = AppTheme.typography.label2)
                }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    BadgedBox(badge = {
                        Badge() {
                            Text(text = "9+", style = AppTheme.typography.label3)
                        }
                    }) {
                        Icon(Icons.Filled.Home, contentDescription = "Home")
                    }
                    Text(text = "Home", style = AppTheme.typography.label2)
                }

            }

            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                BadgedBox(badge = { Badge() }) {
                    Card(modifier = Modifier.size(120.dp)) {

                    }
                }

                BadgedBox(badge = {
                    Badge() {
                        Text(text = "9+", style = AppTheme.typography.label3)
                    }
                }) {
                    Card(modifier = Modifier.size(120.dp)) {

                    }
                }
            }

            BadgedBox(badge = { Badge() }) {
                Button(variant = ButtonVariant.Primary) {
                    Text(text = "Submit")
                }
            }
        }
    }
}

@Composable
fun BadgeSampleTopBar(
    onBack: () -> Unit = {}
) {
    TopBar(
        colors = TopBarDefaults.topBarColors(),
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                IconButton(
                    variant = IconButtonVariant.PrimaryGhost, onClick = onBack
                ) {
                    Icon(Icons.AutoMirrored.Outlined.ArrowBack, contentDescription = "Back")
                }

                Text(text = "Badge Sample", style = AppTheme.typography.h3)
            }

            IconButton(
                variant = IconButtonVariant.PrimaryGhost
            ) {
                BadgedBox(badge = {
                    Badge() {
                        Text(text = "9+", style = AppTheme.typography.label3)
                    }
                }) {
                    Icon(Icons.AutoMirrored.Filled.Chat, contentDescription = "Chat")
                }
            }

        }
    }
}
