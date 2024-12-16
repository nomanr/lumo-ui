package com.nomanr.sample.ui.sample.samples

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nomanr.sample.ui.AppTheme
import com.nomanr.sample.ui.components.Button
import com.nomanr.sample.ui.components.ButtonVariant
import com.nomanr.sample.ui.components.RadioButton
import com.nomanr.sample.ui.components.Scaffold
import com.nomanr.sample.ui.components.Text
import com.nomanr.sample.ui.components.snackbar.Snackbar
import com.nomanr.sample.ui.components.snackbar.SnackbarData
import com.nomanr.sample.ui.components.snackbar.SnackbarHost
import com.nomanr.sample.ui.components.snackbar.rememberSnackbarHost
import com.nomanr.sample.ui.sample.components.SampleScreenTopBar
import kotlinx.coroutines.launch

@Composable
fun SnackbarSample(navigateUp: (() -> Unit)?) {
    val snackbarHostState = rememberSnackbarHost()
    val scope = rememberCoroutineScope()
    var snackBarMessage by remember { mutableStateOf(SampleSnackbarMessage()) }
    var selectedType by remember { mutableStateOf(SnackbarType.Primary) }

    LaunchedEffect(snackBarMessage) {
        if (snackBarMessage.message == null) {
            return@LaunchedEffect
        }
        val newMessage = snackBarMessage.copy()
        snackBarMessage = SampleSnackbarMessage()
        scope.launch {
            snackbarHostState.showSnackbar(
                newMessage.message!!, newMessage.actionLabel, newMessage.withDismissAction
            )
        }
    }

    Scaffold(topBar = {
        SampleScreenTopBar(title = "Navigation Bar", onBack = {
            navigateUp?.invoke()
        })
    }, snackbarHost = {
        SnackbarHost(hostState = snackbarHostState, snackbar = { RenderSnackbar(selectedType, it) })
    }) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            Spacer(modifier = Modifier.padding(16.dp))

            Column(
                modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                RadioButton(selected = selectedType == SnackbarType.Primary, onClick = {
                    selectedType = SnackbarType.Primary
                }) {
                    Text("Primary")
                }

                RadioButton(selected = selectedType == SnackbarType.Error, onClick = {
                    selectedType = SnackbarType.Error
                }) {
                    Text("Error")
                }

                RadioButton(selected = selectedType == SnackbarType.Success, onClick = {
                    selectedType = SnackbarType.Success
                }) {
                    Text("Success")
                }

            }


            Button(modifier = Modifier.fillMaxWidth(), variant = ButtonVariant.PrimaryOutlined, onClick = {
                snackBarMessage = SampleSnackbarMessage("Something happened", withDismissAction = true)
            }) {
                Text("Basic Snackbar")
            }

            Button(modifier = Modifier.fillMaxWidth(), variant = ButtonVariant.PrimaryOutlined, onClick = {
                snackBarMessage = SampleSnackbarMessage("Something happened", withDismissAction = true)
            }) {
                Text("Snack with dismiss action")
            }

            Button(modifier = Modifier.fillMaxWidth(), variant = ButtonVariant.PrimaryOutlined, onClick = {
                snackBarMessage = SampleSnackbarMessage(
                    "Snackbar can also stretch to 2 lines if the text is too long. For example this one!"
                )
            }) {
                Text("Two lines")
            }

            Button(modifier = Modifier.fillMaxWidth(), variant = ButtonVariant.PrimaryOutlined, onClick = {
                snackBarMessage = SampleSnackbarMessage(
                    "Deleted 22 items!", actionLabel = "Undo"
                )
            }) {
                Text("With custom action")
            }

        }
    }
}

@Composable
private fun RenderSnackbar(type: SnackbarType, data: SnackbarData) {
    return when (type) {
        SnackbarType.Primary -> {
            Snackbar(data)
        }

        SnackbarType.Error -> {
            Snackbar(data, containerColor = AppTheme.colors.error)
        }

        SnackbarType.Success -> {
            Snackbar(data, containerColor = AppTheme.colors.success)
        }
    }
}


private enum class SnackbarType {
    Primary,
    Error,
    Success
}

private data class SampleSnackbarMessage(
    val message: String? = null, val actionLabel: String? = null, val withDismissAction: Boolean = false
)

