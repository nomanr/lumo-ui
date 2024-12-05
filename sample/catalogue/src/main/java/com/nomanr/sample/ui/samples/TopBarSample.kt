package com.nomanr.sample.ui.samples

import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Hub
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.nomanr.sample.ui.AppTheme
import com.nomanr.sample.ui.LocalContentColor
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

@Composable
fun TopBarSample(
    padding: PaddingValues, triggerBackAction: Int, interceptNavigateUp: (intercept: Boolean) -> Unit
) {

    var currentScreen by remember {
        mutableStateOf(TopBarSamples.InitialState)
    }

    LaunchedEffect(currentScreen) {
        interceptNavigateUp(currentScreen != TopBarSamples.InitialState)
    }

    LaunchedEffect(triggerBackAction) {
        if (triggerBackAction > 0) {
            currentScreen = TopBarSamples.InitialState
        }
    }


    Box(modifier = Modifier.padding(padding)) {
        when (currentScreen) {
            TopBarSamples.ExampleWithInsets -> {
                WithInsetsSample()
            }

            TopBarSamples.ExampleWithoutInsets -> {
                WithoutInsetsSample()
            }

            TopBarSamples.ChangeColorOnScroll -> {
                ChangeColorOnScrollSample()
            }

            TopBarSamples.EnterAlwaysScrollBehavior -> {
                EnterAlwaysScrollBehaviorSample()
            }

            TopBarSamples.ExitUntilCollapsedScrollBehavior -> {
                ExitUntilCollapsedScrollBehaviorSample()
            }


            else -> {
                InitialStateSample {
                    currentScreen = it
                }
            }
        }
    }
}

@Composable
private fun InitialStateSample(onClick: (sample: TopBarSamples) -> Unit) {

    Spacer(modifier = Modifier.height(16.dp))

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 60.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            modifier = Modifier.fillMaxWidth(),
            text = "Example with insets",
            variant = ButtonVariant.PrimaryOutlined,
            onClick = {
                onClick(TopBarSamples.ExampleWithInsets)
            })

        Spacer(modifier = Modifier.height(16.dp))

        Button(modifier = Modifier.fillMaxWidth(),
            text = "Example without insets",
            variant = ButtonVariant.PrimaryOutlined,
            onClick = {
                onClick(TopBarSamples.ExampleWithoutInsets)
            })

        Spacer(modifier = Modifier.height(16.dp))

        Button(modifier = Modifier.fillMaxWidth(),
            text = "Change color on scroll",
            variant = ButtonVariant.PrimaryOutlined,
            onClick = {
                onClick(TopBarSamples.ChangeColorOnScroll)
            })

        Spacer(modifier = Modifier.height(16.dp))

        Button(modifier = Modifier.fillMaxWidth(),
            text = "Enter always scroll behavior",
            variant = ButtonVariant.PrimaryOutlined,
            onClick = {
                onClick(TopBarSamples.EnterAlwaysScrollBehavior)
            })

        Spacer(modifier = Modifier.height(16.dp))

        Button(modifier = Modifier.fillMaxWidth(),
            text = "Exit until collapsed scroll behavior",
            variant = ButtonVariant.PrimaryOutlined,
            onClick = {
                onClick(TopBarSamples.ExitUntilCollapsedScrollBehavior)
            })
    }
}


@Composable
private fun WithInsetsSample() {
    DeviceFrame {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
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
                        Text(
                            text = "Hello.", style = AppTheme.typography.h3, textAlign = TextAlign.Center
                        )

                        IconButton(variant = IconButtonVariant.Ghost) {
                            Icon(imageVector = Icons.Filled.MoreVert, contentDescription = "More options")
                        }
                    }
                }
            }
            LazyColumn(
                modifier = Modifier
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
                                        .padding(horizontal = 16.dp), contentAlignment = Alignment.Center
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
}

@Composable
private fun WithoutInsetsSample() {
    DeviceFrame {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
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
                        Icon(
                            modifier = Modifier.size(32.dp), imageVector = Icons.Filled.Hub, contentDescription = "More options"
                        )

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
            LazyColumn(
                modifier = Modifier
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
                                        .padding(horizontal = 16.dp), contentAlignment = Alignment.Center
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
}

@Composable
private fun ChangeColorOnScrollSample() {
    val scrollBehavior = TopBarDefaults.pinnedScrollBehavior()

    DeviceFrame {

        Scaffold(topBar = {
            TopBar(
                scrollBehavior = scrollBehavior, colors = TopBarColors(
                    containerColor = AppTheme.colors.background, scrolledContainerColor = AppTheme.colors.primary
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
                        Icon(
                            modifier = Modifier.size(32.dp), imageVector = Icons.Filled.Hub, contentDescription = "More options"
                        )

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
                                        .padding(horizontal = 16.dp), contentAlignment = Alignment.Center
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
}


@Composable
private fun EnterAlwaysScrollBehaviorSample() {
    val scrollBehavior = TopBarDefaults.enterAlwaysScrollBehavior()

    DeviceFrame {

        Scaffold(topBar = {
            TopBar(
                colors = TopBarColors(
                    containerColor = AppTheme.colors.primary, scrolledContainerColor = AppTheme.colors.primary
                ), scrollBehavior = scrollBehavior, windowInsets = WindowInsets.systemBarsForVisualComponents.only(
                    WindowInsetsSides.Horizontal
                )
            ) {
                Box(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    contentAlignment = Alignment.Center,

                    ) {
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
                                        .padding(horizontal = 16.dp), contentAlignment = Alignment.Center
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
}

@Composable
private fun ExitUntilCollapsedScrollBehaviorSample() {
    val scrollBehavior = TopBarDefaults.exitUntilCollapsedScrollBehavior()

    DeviceFrame {

        Scaffold(topBar = {
            TopBar(
                colors = TopBarColors(
                    containerColor = AppTheme.colors.primary, scrolledContainerColor = AppTheme.colors.primary
                ), scrollBehavior = scrollBehavior, windowInsets = WindowInsets.systemBarsForVisualComponents.only(
                    WindowInsetsSides.Horizontal
                )
            ) {
                Box(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    contentAlignment = Alignment.Center,

                    ) {
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
                                        .fillMaxSize(), contentAlignment = Alignment.Center
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
}

@Composable
private fun DeviceFrame(content: @Composable () -> Unit) {
    Box(
        modifier = Modifier
            .padding(16.dp)
            .clip(RoundedCornerShape(8.dp))
            .border(6.dp, AppTheme.colors.primary)
            .padding(top = 6.dp)
    ) {
        content()
    }
}

private enum class TopBarSamples {
    InitialState,
    ExampleWithInsets,
    ExampleWithoutInsets,
    ChangeColorOnScroll,
    EnterAlwaysScrollBehavior,
    ExitUntilCollapsedScrollBehavior
}