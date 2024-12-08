package com.nomanr.sample.ui.sample.samples

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nomanr.sample.ui.AppTheme
import com.nomanr.sample.ui.components.Button
import com.nomanr.sample.ui.components.ButtonVariant
import com.nomanr.sample.ui.components.Icon
import com.nomanr.sample.ui.components.IconButton
import com.nomanr.sample.ui.components.IconButtonVariant
import com.nomanr.sample.ui.components.Scaffold
import com.nomanr.sample.ui.components.Text
import com.nomanr.sample.ui.components.card.Card
import com.nomanr.sample.ui.components.topbar.TopBar
import com.nomanr.sample.ui.components.topbar.TopBarColors
import com.nomanr.sample.ui.components.topbar.TopBarDefaults
import com.nomanr.sample.ui.foundation.systemBarsForVisualComponents
import com.nomanr.sample.ui.sample.components.SampleScreenTopBar
import kotlinx.serialization.Serializable


@Composable
fun TopBarSample(navigateUp: () -> Unit) {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = TopbarNavRoute.InitialStateSample) {
        composable<TopbarNavRoute.InitialStateSample> {
            InitialStateSample(onClick = { route ->
                navController.navigate(route)
            }, navigateUp = navigateUp)
        }

        composable<TopbarNavRoute.WithInsetsSample> {
            WithInsetsSample {
                navController.navigateUp()
            }
        }

        composable<TopbarNavRoute.WithoutInsetsSample> {
            WithoutInsetsSample {
                navController.navigateUp()
            }
        }

        composable<TopbarNavRoute.ChangeColorOnScrollSample> {
            ChangeColorOnScrollSample {
                navController.navigateUp()
            }
        }

        composable<TopbarNavRoute.EnterAlwaysScrollBehaviorSample> {
            EnterAlwaysScrollBehaviorSample {
                navController.navigateUp()
            }
        }

        composable<TopbarNavRoute.ExitUntilCollapsedScrollBehaviorSample> {
            ExitUntilCollapsedScrollBehaviorSample {
                navController.navigateUp()
            }
        }
    }
}

@Composable
private fun InitialStateSample(onClick: (navRoute: TopbarNavRoute) -> Unit, navigateUp: () -> Unit) {

    Scaffold(topBar = {
        SampleScreenTopBar(title = "TopBar Sample", onBack = navigateUp)

    }) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 60.dp), verticalArrangement = Arrangement.Center
        ) {
            Button(modifier = Modifier.fillMaxWidth(),
                text = "Example with insets",
                variant = ButtonVariant.PrimaryOutlined,
                onClick = {
                    onClick(TopbarNavRoute.WithInsetsSample)
                })

            Spacer(modifier = Modifier.height(16.dp))

            Button(modifier = Modifier.fillMaxWidth(),
                text = "Example without insets",
                variant = ButtonVariant.PrimaryOutlined,
                onClick = {
                    onClick(TopbarNavRoute.WithoutInsetsSample)
                })

            Spacer(modifier = Modifier.height(16.dp))

            Button(modifier = Modifier.fillMaxWidth(),
                text = "Change color on scroll",
                variant = ButtonVariant.PrimaryOutlined,
                onClick = {
                    onClick(TopbarNavRoute.ChangeColorOnScrollSample)
                })

            Spacer(modifier = Modifier.height(16.dp))

            Button(modifier = Modifier.fillMaxWidth(),
                text = "Enter always scroll behavior",
                variant = ButtonVariant.PrimaryOutlined,
                onClick = {
                    onClick(TopbarNavRoute.EnterAlwaysScrollBehaviorSample)
                })

            Spacer(modifier = Modifier.height(16.dp))

            Button(modifier = Modifier.fillMaxWidth(),
                text = "Exit until collapsed scroll behavior",
                variant = ButtonVariant.PrimaryOutlined,
                onClick = {
                    onClick(TopbarNavRoute.ExitUntilCollapsedScrollBehaviorSample)
                })
        }
    }
}


@Composable
private fun WithInsetsSample(onNavigateUp: () -> Unit) {
    Scaffold(topBar = {
        TopBar(
            colors = TopBarColors(
                containerColor = AppTheme.colors.primary, scrolledContainerColor = AppTheme.colors.primary
            ),
        ) {
            Box(
                modifier = Modifier.padding(horizontal = 16.dp),
                contentAlignment = Alignment.Center,

                ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(12.dp), verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(variant = IconButtonVariant.Ghost, onClick = onNavigateUp) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "back",
                            )
                        }

                        Text(
                            text = "Hello.", style = AppTheme.typography.h3, textAlign = TextAlign.Center
                        )
                    }

                    IconButton(variant = IconButtonVariant.Ghost) {
                        Icon(imageVector = Icons.Filled.MoreVert, contentDescription = "More options")
                    }
                }
            }
        }
    }) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            item { Spacer(modifier = Modifier.height(16.dp)) }
            repeat(30) { index ->
                item {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp)
                    ) {
                        if (index == 0) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .defaultMinSize(minHeight = 60.dp)
                                    .padding(horizontal = 16.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(text = "With horizontal + top insets", maxLines = 1)
                            }
                        } else {
                            Spacer(modifier = Modifier.height(60.dp))
                        }
                    }
                }
            }
        }
    }

}

@Composable
private fun WithoutInsetsSample(onNavigateUp: () -> Unit) {

    val activity = LocalContext.current as ComponentActivity

    DisposableEffect(Unit) {
        WindowCompat.setDecorFitsSystemWindows(activity.window, true)

        onDispose {
            WindowCompat.setDecorFitsSystemWindows(activity.window, false)
        }
    }

    Scaffold(topBar = {
        TopBar(
            colors = TopBarColors(
                containerColor = AppTheme.colors.primary, scrolledContainerColor = AppTheme.colors.primary
            ), windowInsets = WindowInsets.systemBarsForVisualComponents.only(
                WindowInsetsSides.Horizontal
            )
        ) {
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(variant = IconButtonVariant.Ghost, onClick = onNavigateUp) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "back",
                        )
                    }

                    IconButton(variant = IconButtonVariant.Ghost) {
                        Icon(
                            imageVector = Icons.Filled.MoreVert,
                            contentDescription = "More options",
                        )
                    }
                }
                Text(
                    text = "Hello.", style = AppTheme.typography.h3, textAlign = TextAlign.Center
                )
            }
        }
    }) { padding ->

        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            item { Spacer(modifier = Modifier.height(16.dp)) }
            repeat(30) { index ->
                item {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp)
                    ) {
                        if (index == 0) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .defaultMinSize(minHeight = 60.dp)
                                    .padding(horizontal = 16.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(text = "With only horizontal insets", maxLines = 1)
                            }
                        } else {
                            Spacer(modifier = Modifier.height(60.dp))
                        }
                    }
                }
            }
        }
    }

}

@Composable
private fun ChangeColorOnScrollSample(onNavigateUp: () -> Unit) {
    val scrollBehavior = TopBarDefaults.pinnedScrollBehavior()

    Scaffold(topBar = {
        TopBar(
            scrollBehavior = scrollBehavior, colors = TopBarColors(
                containerColor = AppTheme.colors.background, scrolledContainerColor = AppTheme.colors.primary
            )
        ) {
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(variant = IconButtonVariant.Ghost, onClick = onNavigateUp) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "back",
                        )
                    }

                    IconButton(variant = IconButtonVariant.Ghost) {
                        Icon(
                            imageVector = Icons.Filled.MoreVert,
                            contentDescription = "More options",
                        )
                    }
                }
                Text(
                    text = "Hello.", style = AppTheme.typography.h3, textAlign = TextAlign.Center
                )
            }
        }
    }) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .nestedScroll(scrollBehavior.nestedScrollConnection)
                .padding(horizontal = 16.dp),
            contentPadding = padding,

            ) {
            item { Spacer(modifier = Modifier.height(16.dp)) }
            repeat(30) { index ->
                item {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp)
                    ) {
                        if (index == 0) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .defaultMinSize(minHeight = 60.dp)
                                    .padding(horizontal = 16.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(text = "Changes color with scroll", maxLines = 1)
                            }
                        } else {
                            Spacer(modifier = Modifier.height(60.dp))
                        }
                    }
                }
            }
        }
    }
}


@Composable
private fun EnterAlwaysScrollBehaviorSample(onNavigateUp: () -> Unit) {
    val scrollBehavior = TopBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(topBar = {
        TopBar(
            colors = TopBarColors(
                containerColor = AppTheme.colors.primary, scrolledContainerColor = AppTheme.colors.primary
            ), scrollBehavior = scrollBehavior
        ) {
            Row(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                IconButton(variant = IconButtonVariant.Ghost, onClick = onNavigateUp) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "back",
                    )
                }

                Text(
                    text = "topbar", style = AppTheme.typography.h3, textAlign = TextAlign.Center
                )
            }
        }
    }) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .nestedScroll(scrollBehavior.nestedScrollConnection)
                .padding(horizontal = 16.dp),
            contentPadding = padding,

            ) {
            item { Spacer(modifier = Modifier.height(16.dp)) }

            repeat(30) { index ->
                item {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp)
                    ) {
                        if (index == 0) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .defaultMinSize(minHeight = 60.dp)
                                    .padding(horizontal = 16.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(text = "Appears as soon as the scroll starts", maxLines = 1)
                            }
                        } else {
                            Spacer(modifier = Modifier.height(60.dp))
                        }
                    }
                }
            }
        }
    }

}

@Composable
private fun ExitUntilCollapsedScrollBehaviorSample(onNavigateUp: () -> Unit) {
    val scrollBehavior = TopBarDefaults.exitUntilCollapsedScrollBehavior()
    Scaffold(topBar = {
        TopBar(
            colors = TopBarColors(
                containerColor = AppTheme.colors.primary, scrolledContainerColor = AppTheme.colors.primary
            ), scrollBehavior = scrollBehavior
        ) {
            Row(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                IconButton(variant = IconButtonVariant.Ghost, onClick = onNavigateUp) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "back",
                    )
                }
                Text(
                    text = "topbar", style = AppTheme.typography.h3, textAlign = TextAlign.Center
                )
            }
        }
    }) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .nestedScroll(scrollBehavior.nestedScrollConnection)
                .padding(horizontal = 16.dp),
            contentPadding = padding,

            ) {
            item { Spacer(modifier = Modifier.height(16.dp)) }

            repeat(30) { index ->
                item {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp)
                    ) {
                        if (index == 0) {
                            Box(
                                modifier = Modifier
                                    .padding(horizontal = 16.dp)
                                    .defaultMinSize(minHeight = 60.dp)
                                    .fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(text = "Appears after scrolling finishes upward.")
                            }
                        } else {
                            Spacer(modifier = Modifier.height(60.dp))
                        }
                    }
                }
            }
        }

    }
}


internal sealed class TopbarNavRoute {
    @Serializable
    data object InitialStateSample : TopbarNavRoute()

    @Serializable
    data object WithInsetsSample : TopbarNavRoute()

    @Serializable
    data object WithoutInsetsSample : TopbarNavRoute()

    @Serializable
    data object ChangeColorOnScrollSample : TopbarNavRoute()

    @Serializable
    data object EnterAlwaysScrollBehaviorSample : TopbarNavRoute()

    @Serializable
    data object ExitUntilCollapsedScrollBehaviorSample : TopbarNavRoute()

}
