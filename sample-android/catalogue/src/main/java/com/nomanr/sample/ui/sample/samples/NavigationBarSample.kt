package com.nomanr.sample.ui.sample.samples

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.nomanr.sample.ui.AppTheme
import com.nomanr.sample.ui.components.Checkbox
import com.nomanr.sample.ui.components.HorizontalDivider
import com.nomanr.sample.ui.components.Icon
import com.nomanr.sample.ui.components.NavigationBar
import com.nomanr.sample.ui.components.NavigationBarItem
import com.nomanr.sample.ui.components.RadioButton
import com.nomanr.sample.ui.components.Scaffold
import com.nomanr.sample.ui.components.Text
import com.nomanr.sample.ui.components.card.Card
import com.nomanr.sample.ui.components.card.ElevatedCard
import com.nomanr.sample.ui.components.card.OutlinedCard
import com.nomanr.sample.ui.sample.components.SampleScreenTopBar
import com.nomanr.sample.ui.sample.components.Skeleton
import com.nomanr.sample.ui.utils.composableOrNull

@Composable
fun NavigationBarSample(navigateUp: (() -> Unit)?) {
    var selectedScreen by remember { mutableStateOf("Home") }
    var showLabels by remember { mutableStateOf(true) }
    var items by remember { mutableIntStateOf(3) }

    Scaffold(topBar = {
        SampleScreenTopBar(title = "Navigation Bar", onBack = {
            navigateUp?.invoke()
        })
    }, bottomBar = {
        NavigationBarShowcase(showLabels, items, selectedScreen) {
            selectedScreen = it
        }
    }) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
        ) {

            when (selectedScreen) {
                "Home" -> NavigationBarSampleHomeScreen(showLabels, onChangeShowLabel = {
                    showLabels = it
                }, items, onChangeItems = {
                    items = it
                })

                "Search" -> NavigationBarSampleSearchScreen()
                else -> NavigationBarSampleProfileScreen()
            }


        }
    }
}

@Composable
private fun NavigationBarShowcase(showLabels: Boolean, items: Int, selectedScreen: String, onSelect: (String) -> Unit) {
    val screens = mutableListOf(
        listOf("Home", Icons.Default.Home), listOf("Search", Icons.Default.Search), listOf("Profile", Icons.Default.Person),
    )

    if (items == 4) {
        screens.add( listOf("Settings", Icons.Default.Settings))
    } else if (items == 5) {
        screens.add( listOf("Settings", Icons.Default.Settings))
        screens.add(listOf("Chat", Icons.AutoMirrored.Default.Send))
    }

    Column {
        HorizontalDivider()
        NavigationBar {
            screens.forEach { screen ->
                val label = screen[0].toString()
                val icon = screen[1]

                NavigationBarItem(
                    selected = label == selectedScreen,
                    onClick = {
                        onSelect(label)
                    },
                    label = composableOrNull(showLabels) {
                        Text(text = label, style = AppTheme.typography.label2, maxLines = 1)
                    },
                    icon = {
                        Icon(icon as ImageVector, contentDescription = "nav bar item")
                    },
                )
            }
        }
    }
}


@Composable
private fun NavigationBarSampleHomeScreen(
    showLabel: Boolean, onChangeShowLabel: (Boolean) -> Unit, items: Int, onChangeItems: (Int) -> Unit
) {
    Column(modifier = Modifier.verticalScroll(rememberScrollState()), verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedCard {
            Column(
                modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Show labels", style = AppTheme.typography.label2)
                    Checkbox(checked = showLabel, onCheckedChange = {
                        onChangeShowLabel(it)
                    })
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Items")
                    Row {
                        RadioButton(selected = items == 3, onClick = {
                            onChangeItems(3)
                        }) {
                            Text("3", style = AppTheme.typography.label2)
                        }
                        RadioButton(selected = items == 4, onClick = {
                            onChangeItems(4)
                        }) {
                            Text("4", style = AppTheme.typography.label2)
                        }
                        RadioButton(selected = items == 5, onClick = {
                            onChangeItems(5)
                        }) {
                            Text("5", style = AppTheme.typography.label2)
                        }
                    }
                }
            }
        }
        SampleViewExample1()
        SampleViewExample3()
        SampleViewExample2()
        SampleViewExample4()
        Spacer(modifier = Modifier.height(16.dp))
    }

}

@Composable
private fun NavigationBarSampleSearchScreen() {
    Column(modifier = Modifier.verticalScroll(rememberScrollState()), verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Spacer(modifier = Modifier.height(16.dp))
        SampleViewExample1()
        SampleViewExample2()
        SampleViewExample3()
        SampleViewExample1()
        SampleViewExample3()
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
private fun NavigationBarSampleProfileScreen() {
    Column(modifier = Modifier.verticalScroll(rememberScrollState()), verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Spacer(modifier = Modifier.height(16.dp))
        SampleViewExample3()
        SampleViewExample4()
        SampleViewExample1()
        SampleViewExample2()
        SampleViewExample3()
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
private fun SampleViewExample1() {

    OutlinedCard(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Skeleton(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
            )
            Spacer(modifier = Modifier.height(12.dp))
            Skeleton(
                modifier = Modifier
                    .width(260.dp)
                    .height(24.dp)
            )
            Spacer(modifier = Modifier.height(12.dp))
            Skeleton(
                modifier = Modifier
                    .width(180.dp)
                    .height(24.dp)
            )
        }
    }
}

@Composable
private fun SampleViewExample2() {
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Card(
            modifier = Modifier.weight(0.48f)
        ) {
            Column(modifier = Modifier.padding(12.dp)) {
                Skeleton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                )
                Spacer(modifier = Modifier.height(12.dp))
                Skeleton(
                    modifier = Modifier
                        .width(260.dp)
                        .height(24.dp)
                )
                Spacer(modifier = Modifier.height(12.dp))
                Skeleton(
                    modifier = Modifier
                        .width(180.dp)
                        .height(24.dp)
                )

            }

        }

        ElevatedCard(
            modifier = Modifier.weight(0.48f)
        ) {
            Column(modifier = Modifier.padding(12.dp)) {
                Skeleton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                )
                Spacer(modifier = Modifier.height(12.dp))
                Skeleton(
                    modifier = Modifier
                        .width(260.dp)
                        .height(24.dp)
                )
                Spacer(modifier = Modifier.height(12.dp))
                Skeleton(
                    modifier = Modifier
                        .width(180.dp)
                        .height(24.dp)
                )
            }
        }
    }
}

@Composable
private fun SampleViewExample3() {
    OutlinedCard(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp)
                .defaultMinSize(minHeight = 80.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier.weight(0.75f)) {
                Text(text = "Hello.", style = AppTheme.typography.h4)
            }

            Box(modifier = Modifier.weight(0.25f)) {
                Skeleton(
                    modifier = Modifier
                        .width(56.dp)
                        .height(56.dp)
                )
            }
        }
    }

    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp)
                .defaultMinSize(minHeight = 80.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier.weight(0.75f)) {
                Text(text = "Hello.", style = AppTheme.typography.h4)
            }

            Box(modifier = Modifier.weight(0.25f)) {
                Skeleton(
                    modifier = Modifier
                        .width(56.dp)
                        .height(56.dp)
                )
            }
        }
    }

    ElevatedCard(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp)
                .defaultMinSize(minHeight = 80.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier.weight(0.75f)) {
                Text(text = "Hello.", style = AppTheme.typography.h4)
            }

            Box(modifier = Modifier.weight(0.25f)) {
                Skeleton(
                    modifier = Modifier
                        .width(56.dp)
                        .height(56.dp)
                )
            }
        }
    }

}

@Composable
private fun SampleViewExample4() {
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Card(modifier = Modifier.weight(0.33f), onClick = {}) {
            Column(
                modifier = Modifier.padding(12.dp), horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Skeleton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                )
                Spacer(modifier = Modifier.height(12.dp))

                Text(text = "Click me")
            }
        }

        ElevatedCard(modifier = Modifier.weight(0.33f), onClick = {}) {
            Column(
                modifier = Modifier.padding(12.dp), horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Skeleton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                )
                Spacer(modifier = Modifier.height(12.dp))

                Text(text = "Click me")
            }

        }

        OutlinedCard(modifier = Modifier.weight(0.33f), onClick = {}) {
            Column(
                modifier = Modifier.padding(12.dp), horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Skeleton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                )
                Spacer(modifier = Modifier.height(12.dp))

                Text(text = "Click me")
            }
        }
    }
}


