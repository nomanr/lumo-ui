package com.nomanr.sample.ui.sample.samples

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Chat
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.nomanr.sample.ui.AppTheme
import com.nomanr.sample.ui.LocalContentColor
import com.nomanr.sample.ui.components.Badge
import com.nomanr.sample.ui.components.BadgedBox
import com.nomanr.sample.ui.components.HorizontalDivider
import com.nomanr.sample.ui.components.Icon
import com.nomanr.sample.ui.components.IconButton
import com.nomanr.sample.ui.components.IconButtonVariant
import com.nomanr.sample.ui.components.Scaffold
import com.nomanr.sample.ui.components.Text
import com.nomanr.sample.ui.components.card.ElevatedCard
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
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {

            BadgeShowcase()

            BadgeExamples()

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

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun BadgeShowcase() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(text = "Badges", style = AppTheme.typography.h4)

        Spacer(modifier = Modifier)
        val colors = listOf(
            AppTheme.colors.primary,
            AppTheme.colors.secondary,
            AppTheme.colors.error,
            AppTheme.colors.success,
            AppTheme.colors.tertiary,
        )

        colors.forEach { color ->
            FlowRow(verticalArrangement = Arrangement.Center, horizontalArrangement = Arrangement.spacedBy(12.dp)) {

                Badge(containerColor = color) {
                    Text("BADGE", style = AppTheme.typography.label3)
                }

                Badge(containerColor = color) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(2.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(4.dp)
                                .background(LocalContentColor.current, shape = CircleShape)
                        )
                        Text("BADGE", style = AppTheme.typography.label3)
                    }
                }

                Badge(containerColor = color) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(2.dp)
                    ) {
                        Icon(
                            modifier = Modifier.size(12.dp),
                            imageVector = Icons.Filled.Notifications,
                            contentDescription = "Notifications"
                        )
                        Text("BADGE", style = AppTheme.typography.label3)
                    }
                }

                Badge(containerColor = color) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(2.dp)
                    ) {
                        Text("BADGE", style = AppTheme.typography.label3)
                        Icon(
                            modifier = Modifier.size(12.dp),
                            imageVector = Icons.Filled.Notifications,
                            contentDescription = "Notifications"
                        )
                    }
                }

                Badge(containerColor = color) {
                    Icon(
                        modifier = Modifier.size(12.dp),
                        imageVector = Icons.Filled.Notifications,
                        contentDescription = "Notifications"
                    )
                }


                Badge(containerColor = color) {
                    Text("2", style = AppTheme.typography.label3)
                }

                Badge(containerColor = color) {
                    Text("9+", style = AppTheme.typography.label3)
                }

                 Box (modifier = Modifier.size(18.dp), contentAlignment = Alignment.Center) {
                     Badge(containerColor = color)
                 }
            }

        }
    }
}


@Composable
internal fun BadgeExamples() {
    Spacer(modifier = Modifier.size(8.dp))

    Text(text = "Examples", style = AppTheme.typography.h4)

    NavigationBar()

    MoviesExample()
}


@Composable
internal fun NavigationBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(AppTheme.colors.primary)
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        CompositionLocalProvider(LocalContentColor provides AppTheme.colors.onPrimary) {
            IconButton {
                Icon(Icons.Default.Home, contentDescription = "Home")
            }

            Spacer(modifier = Modifier.size(8.dp))
            IconButton {
                BadgedBox(badge = {
                    Badge() {
                        Text(text = "9+", style = AppTheme.typography.label3)
                    }
                }) {
                    Icon(Icons.AutoMirrored.Filled.Chat, contentDescription = "Chat")
                }
            }
            Spacer(modifier = Modifier.size(8.dp))

            IconButton {
                Icon(Icons.Default.Person, contentDescription = "Profile")
            }
        }
    }


    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(AppTheme.colors.primary)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        CompositionLocalProvider(LocalContentColor provides AppTheme.colors.onPrimary) {
            Column(
                modifier = Modifier.wrapContentHeight(),
                verticalArrangement = Arrangement.spacedBy(4.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(Icons.Default.Home, contentDescription = "Home")
            }

            Spacer(modifier = Modifier.size(8.dp))
            Column(
                modifier = Modifier.wrapContentHeight(),
                verticalArrangement = Arrangement.spacedBy(4.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Icon(Icons.AutoMirrored.Filled.Chat, contentDescription = "Messages")
                Badge(containerColor = LocalContentColor.current)
            }
            Spacer(modifier = Modifier.size(8.dp))

            Column(
                modifier = Modifier.wrapContentHeight(),
                verticalArrangement = Arrangement.spacedBy(4.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(Icons.Default.Person, contentDescription = "Profile")
            }
        }
    }


    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(AppTheme.colors.primary)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        CompositionLocalProvider(LocalContentColor provides AppTheme.colors.onPrimary) {
            Column(
                modifier = Modifier.wrapContentHeight(),
                verticalArrangement = Arrangement.spacedBy(4.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(Icons.Default.Home, contentDescription = "Home")
                Text("Home", style = AppTheme.typography.label3)
            }

            Spacer(modifier = Modifier.size(8.dp))
            Column(
                modifier = Modifier.wrapContentHeight(),
                verticalArrangement = Arrangement.spacedBy(4.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                BadgedBox(badge = {
                    Badge() {
                        Text(text = "9+", style = AppTheme.typography.label3)
                    }
                }) {
                    Icon(Icons.AutoMirrored.Filled.Chat, contentDescription = "Messages")
                }
                Text("Messages", style = AppTheme.typography.label3)

            }
            Spacer(modifier = Modifier.size(8.dp))

            Column(
                modifier = Modifier.wrapContentHeight(),
                verticalArrangement = Arrangement.spacedBy(4.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(Icons.Default.Person, contentDescription = "Profile")
                Text("Profile", style = AppTheme.typography.label3)
            }
        }
    }
}


@Composable
internal fun MoviesExample() {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(top = 16.dp),
                contentAlignment = Alignment.CenterStart,
            ) {
                Text("Weekly Movies", style = AppTheme.typography.h4)
            }

            HorizontalDivider()

            val testNames = listOf(
                "The Matrix",
                "The Matrix Reloaded",
                "The Matrix Revolutions",
                "The Matrix Resurrections",
            )

            val testCast = listOf(
                "Keanu Reeves, Laurence Fishburne",
                "Keanu Reeves, Laurence Fishburne",
                "Keanu Reeves, Laurence Fishburne",
                "Keanu Reeves, Laurence Fishburne",
            )

            val testBadges = listOf(
                "New",
                "Popular",
                "Coming Soon",
                "Now Playing",
            )

            val testColors = listOf(
                AppTheme.colors.primary,
                AppTheme.colors.secondary,
                AppTheme.colors.tertiary,
                AppTheme.colors.success,
            )

            testNames.forEachIndexed { index, name ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .padding(bottom = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .background(AppTheme.colors.surface, shape = CircleShape)
                            .padding(8.dp),
                    )
                    Spacer(modifier = Modifier.size(8.dp))
                    Column(
                        modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(name, style = AppTheme.typography.h4)
                        Text(testCast[index], style = AppTheme.typography.body2)
                    }

                    Box(modifier = Modifier.padding(start = 16.dp)) {
                        Badge(
                            containerColor = testColors[index]
                        ) {
                            Text(testBadges[index], style = AppTheme.typography.label3)
                        }
                    }

                }

            }

            Spacer(modifier = Modifier)


        }
    }
}
