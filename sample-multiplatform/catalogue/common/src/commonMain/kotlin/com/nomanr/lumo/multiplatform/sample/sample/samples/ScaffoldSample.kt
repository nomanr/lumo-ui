package com.nomanr.lumo.multiplatform.sample.sample.samples

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.nomanr.lumo.multiplatform.sample.sample.components.SampleScreenTopBar
import com.nomanr.lumo.multiplatform.sample.sample.components.Skeleton
import com.nomanr.lumo.multiplatform.ui.AppTheme
import com.nomanr.lumo.multiplatform.ui.components.HorizontalDivider
import com.nomanr.lumo.multiplatform.ui.components.Icon
import com.nomanr.lumo.multiplatform.ui.components.IconButton
import com.nomanr.lumo.multiplatform.ui.components.NavigationBar
import com.nomanr.lumo.multiplatform.ui.components.NavigationBarItem
import com.nomanr.lumo.multiplatform.ui.components.Scaffold
import com.nomanr.lumo.multiplatform.ui.components.Text
import com.nomanr.lumo.multiplatform.ui.components.snackbar.SnackbarHost
import com.nomanr.lumo.multiplatform.ui.components.snackbar.rememberSnackbarHost
import kotlinx.coroutines.launch

@Composable
fun ScaffoldSample(navigateUp: (() -> Unit)?) {
    var selectedScreen by remember { mutableStateOf("Home") }
    val snackbarHost = rememberSnackbarHost()

    var snackBarMessage by remember { mutableStateOf<String?>(null) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(snackBarMessage) {
        if (snackBarMessage == null) {
            return@LaunchedEffect
        }
        val newMessage = snackBarMessage!!
        snackBarMessage = null
        scope.launch {
            snackbarHost.showSnackbar(
                newMessage,
            )
        }
    }

    Scaffold(topBar = {
        SampleScreenTopBar(title = "Scaffold Sample",
            showBackButton = showBackButton(),
            onBack = { navigateUp?.invoke() })
    }, bottomBar = {
        ScaffoldSampleNavigationBar(selectedScreen = selectedScreen, onSelect = { selectedScreen = it })
    }, floatingActionButton = {
        IconButton(shape = CircleShape, onClick = {
            snackBarMessage = "Floating Action Button Clicked"
        }) {
            Icon(Icons.Default.Add, contentDescription = "Add")
        }
    }, snackbarHost = {
        SnackbarHost(hostState = snackbarHost)
    }) { padding ->

        Column(
            modifier =
                Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(padding)
                    .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Box(contentAlignment = Alignment.Center) {
                Skeleton(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .height(60.dp),
                )

                Text("Scaffold, scaffolding everything all together", style = AppTheme.typography.body2)
            }

            Box(contentAlignment = Alignment.Center) {
                Skeleton(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .height(60.dp),
                )

                Text("Accepts Topbar and Navigation bar", style = AppTheme.typography.body2)
            }

            Box(contentAlignment = Alignment.Center) {
                Skeleton(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .height(60.dp),
                )

                Text("Along with FAB and Snackbar!", style = AppTheme.typography.body2)
            }

            repeat(10) {
                Skeleton(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .height(60.dp),
                )
            }
        }
    }
}

@Composable
private fun ScaffoldSampleNavigationBar(selectedScreen: String, onSelect: (String) -> Unit) {
    val screens =
        mutableListOf(
            listOf("Home", Icons.Default.Home),
            listOf("Search", Icons.Default.Search),
            listOf("Profile", Icons.Default.Person),
        )

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
                    icon = {
                        Icon(icon as ImageVector, contentDescription = "nav bar item")
                    },
                )
            }
        }
    }
}
