package com.nomanr.lumo.multiplatform.sample.sample.samples

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nomanr.lumo.multiplatform.ui.AppTheme
import com.nomanr.lumo.multiplatform.ui.LocalContentColor
import com.nomanr.lumo.multiplatform.ui.components.AlertDialog
import com.nomanr.lumo.multiplatform.ui.components.BasicAlertDialog
import com.nomanr.lumo.multiplatform.ui.components.Button
import com.nomanr.lumo.multiplatform.ui.components.ButtonVariant
import com.nomanr.lumo.multiplatform.ui.components.Icon
import com.nomanr.lumo.multiplatform.ui.components.Text
import com.nomanr.lumo.multiplatform.ui.components.textfield.TextField

@Composable
private fun rememberAlertDialogState() = remember { AlertDialogState() }

@Composable
fun AlertDialogSample() {
    val state = rememberAlertDialogState()

    Column(
        modifier =
        Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            modifier = Modifier.fillMaxWidth(),
            text = "Simple Dialog",
            variant = ButtonVariant.PrimaryOutlined,
            onClick = { state.showSimpleDialog = true },
        )

        Button(
            modifier = Modifier.fillMaxWidth(),
            text = "Single Button Dialog",
            variant = ButtonVariant.PrimaryOutlined,
            onClick = { state.showSingleButtonDialog = true },
        )

        Button(
            modifier = Modifier.fillMaxWidth(),
            text = "Long Content Dialog",
            variant = ButtonVariant.PrimaryOutlined,
            onClick = { state.showLongContentDialog = true },
        )

        Button(
            modifier = Modifier.fillMaxWidth(),
            text = "Input Dialog",
            variant = ButtonVariant.PrimaryOutlined,
            onClick = { state.showInputDialog = true },
        )

        Button(
            modifier = Modifier.fillMaxWidth(),
            text = "Custom Icon Dialog",
            variant = ButtonVariant.PrimaryOutlined,
            onClick = { state.showCustomIconDialog = true },
        )

        Button(
            modifier = Modifier.fillMaxWidth(),
            text = "Multiple Buttons Dialog",
            variant = ButtonVariant.PrimaryOutlined,
            onClick = { state.showMultipleButtonsDialog = true },
        )

        Button(
            modifier = Modifier.fillMaxWidth(),
            text = "Custom Content Dialog",
            variant = ButtonVariant.PrimaryOutlined,
            onClick = { state.showCustomContentDialog = true },
        )
    }

    AlertDialogSamples(state)
}

@Composable
private fun AlertDialogSamples(state: AlertDialogState) {
    if (state.showSimpleDialog) {
        AlertDialog(
            onDismissRequest = { state.showSimpleDialog = false },
            onConfirmClick = { state.showSimpleDialog = false },
            title = "Simple Dialog",
            text = "This is a simple dialog with default confirm and dismiss buttons.",
            confirmButtonText = "OK",
            dismissButtonText = "Cancel",
        )
    }

    if (state.showSingleButtonDialog) {
        AlertDialog(
            onDismissRequest = { state.showSingleButtonDialog = false },
            onConfirmClick = { state.showSingleButtonDialog = false },
            title = "Information",
            text = "This dialog only has a confirm button.",
            confirmButtonText = "Got it",
            dismissButtonText = null,
        )
    }

    if (state.showLongContentDialog) {
        AlertDialog(
            onDismissRequest = { state.showLongContentDialog = false },
            onConfirmClick = { state.showLongContentDialog = false },
            title = "Terms & Conditions",
            text =
            "This dialog displays longer content to ensure readability and proper layout for users with detailed content." +
                    " It can be used for displaying terms and conditions, privacy policies, or any other lengthy content.",
            confirmButtonText = "Accept",
            dismissButtonText = "Decline",
        )
    }

    if (state.showInputDialog) {
        var inputText by remember { mutableStateOf("") }

        BasicAlertDialog(onDismissRequest = { state.showInputDialog = false }) {
            Column(
                modifier =
                Modifier
                    .background(AppTheme.colors.success)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                Text("Enter your email", style = AppTheme.typography.h4)
                TextField(value = inputText, onValueChange = { inputText = it }, label = { Text("Email") }, leadingIcon = {
                    Icon(Icons.Default.Email, contentDescription = "Star Icon")
                }, placeholder = {
                    Text("abc@xyz.com")
                })

                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    text = "Submit",
                    variant = ButtonVariant.Primary,
                    onClick = { state.showInputDialog = false },
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        }
    }

    if (state.showCustomIconDialog) {
        AlertDialog(
            onDismissRequest = { state.showCustomIconDialog = false },
            onConfirmClick = { state.showCustomIconDialog = false },
            title = "Custom Icon",
            text = "This dialog features a custom icon.",
            confirmButtonText = "OK",
            dismissButtonText = "Cancel",
            icon = {
                Icon(
                    modifier = Modifier.size(50.dp),
                    imageVector = Icons.Default.Warning,
                    contentDescription = "Star Icon",
                )
            },
        )
    }

    if (state.showMultipleButtonsDialog) {
        AlertDialog(
            onDismissRequest = { state.showMultipleButtonsDialog = false },
            onConfirmClick = { state.showMultipleButtonsDialog = false },
            title = "Multiple Actions",
            text = "This dialog features multiple actions for more flexibility.",
            confirmButtonText = "Save",
            dismissButtonText = "Cancel",
        )
    }

    if (state.showCustomContentDialog) {
        BasicAlertDialog(onDismissRequest = { state.showCustomContentDialog = false }) {
            Column(
                modifier =
                Modifier
                    .background(AppTheme.colors.success)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                CompositionLocalProvider(
                    LocalContentColor provides AppTheme.colors.onPrimary,
                ) {
                    Text("Custom Content", style = AppTheme.typography.h4)

                    Text("This dialog allows for fully customizable content.")

                    Button(
                        text = "Close",
                        variant = ButtonVariant.Secondary,
                        onClick = { state.showCustomContentDialog = false },
                        modifier = Modifier.fillMaxWidth(),
                    )
                }
            }
        }
    }
}

private class AlertDialogState {
    var showSimpleDialog by mutableStateOf(false)
    var showSingleButtonDialog by mutableStateOf(false)
    var showLongContentDialog by mutableStateOf(false)
    var showInputDialog by mutableStateOf(false)
    var showCustomIconDialog by mutableStateOf(false)
    var showMultipleButtonsDialog by mutableStateOf(false)
    var showCustomContentDialog by mutableStateOf(false)
}
