package com.nomanr.lumo.multiplatform.sample.sample.samples

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.nomanr.lumo.multiplatform.ui.AppTheme
import com.nomanr.lumo.multiplatform.ui.components.Checkbox
import com.nomanr.lumo.multiplatform.ui.components.RadioButton
import com.nomanr.lumo.multiplatform.ui.components.Text
import com.nomanr.lumo.multiplatform.ui.components.otptextfield.OTPTextField
import com.nomanr.lumo.multiplatform.ui.components.otptextfield.OutlinedOTPTextField
import com.nomanr.lumo.multiplatform.ui.components.otptextfield.UnderlinedOTPTextField
import com.nomanr.lumo.multiplatform.ui.components.otptextfield.rememberOtpState

@Composable
fun OTPTextFieldSample() {
    Column(
        modifier =
            Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
                .padding(16.dp)
                .imePadding(),
    ) {
        OTPTextFieldInteractiveSample()

        OTPTextFieldExamples()

        Spacer(modifier = Modifier.height(50.dp))
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun OTPTextFieldInteractiveSample() {
    var textFieldType by remember { mutableStateOf("TextField") }
    var errorEnabled by remember { mutableStateOf(false) }
    var disabled by remember { mutableStateOf(false) }
    var readOnly by remember { mutableStateOf(false) }
    var otpLength by remember { mutableIntStateOf(6) }

    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        Text("OTPTextField Demo", style = AppTheme.typography.h4)

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
        ) {
            RenderOTPTextField(
                type = textFieldType,
                isError = errorEnabled,
                enabled = !disabled,
                readOnly = readOnly,
                length = otpLength,
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text("OTPTextField Type", style = AppTheme.typography.h4)

        FlowRow(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            RadioButton(selected = textFieldType == "TextField", onClick = { textFieldType = "TextField" }) {
                Text("Filled", style = AppTheme.typography.body2)
            }
            RadioButton(selected = textFieldType == "OutlinedTextField", onClick = { textFieldType = "OutlinedTextField" }) {
                Text("Outlined", style = AppTheme.typography.body2)
            }
            RadioButton(selected = textFieldType == "UnderlinedTextField", onClick = { textFieldType = "UnderlinedTextField" }) {
                Text("Underlined", style = AppTheme.typography.body2)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text("Toggle Features", style = AppTheme.typography.h4)
        Spacer(modifier = Modifier.height(16.dp))

        FlowRow(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            RadioButton(selected = otpLength == 4, onClick = { otpLength = 4 }) {
                Text("4 Length", style = AppTheme.typography.body2)
            }
            RadioButton(selected = otpLength == 6, onClick = { otpLength = 6 }) {
                Text("6 Length", style = AppTheme.typography.body2)
            }
            RadioButton(selected = otpLength == 8, onClick = { otpLength = 8 }) {
                Text("8 Length", style = AppTheme.typography.body2)
            }
        }

        FlowRow(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Checkbox(checked = errorEnabled, onCheckedChange = {
                    errorEnabled = it
                    disabled = false
                })
                Text(text = "Error State", style = AppTheme.typography.body2)
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Checkbox(checked = disabled, onCheckedChange = {
                    disabled = it
                    errorEnabled = false
                })
                Text(text = "Disabled", style = AppTheme.typography.body2)
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Checkbox(checked = readOnly, onCheckedChange = {
                    readOnly = it
                })
                Text(text = "Readonly", style = AppTheme.typography.body2)
            }
        }
    }
}

@Composable
private fun OTPTextFieldExamples() {
    Column(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(top = 24.dp),
    ) {
        Text(text = "Verify your number", style = AppTheme.typography.h2)
        Text(text = "Enter the code we've sent by text to +44-000-000-0000.", style = AppTheme.typography.label1)

        Spacer(modifier = Modifier.height(24.dp))
        Text(text = "Code", style = AppTheme.typography.label2)

        Row(
            modifier =
                Modifier
                    .fillMaxWidth(0.7f)
                    .padding(top = 4.dp),
        ) {
            OTPTextField(
                modifier = Modifier.fillMaxWidth(),
                autoFocus = false,
            )
        }

        Spacer(modifier = Modifier.height(50.dp))
        Text(text = "Enter the 4-digit code just sent to 44-000-000-0000.", style = AppTheme.typography.label1)

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier =
                Modifier
                    .fillMaxWidth(0.6f)
                    .padding(top = 4.dp),
        ) {
            OutlinedOTPTextField(
                state = rememberOtpState(4),
                modifier = Modifier.fillMaxWidth(),
                autoFocus = false,
            )
        }

        Spacer(modifier = Modifier.height(50.dp))
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(text = "Verify your number", style = AppTheme.typography.h1)

            Text(
                modifier = Modifier.padding(top = 8.dp, bottom = 16.dp),
                text = "All done! We've sent your code to (***)***-0000",
                style = AppTheme.typography.body3,
                textAlign = TextAlign.Center,
            )

            Text(
                text = "Enter your code below.",
                style = AppTheme.typography.body3,
                textAlign = TextAlign.Center,
            )

            Row(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp),
            ) {
                UnderlinedOTPTextField(
                    modifier = Modifier.fillMaxWidth(),
                    autoFocus = false,
                    textStyle = AppTheme.typography.h1,
                )
            }
        }
    }
}

@Composable
private fun RenderOTPTextField(
    modifier: Modifier = Modifier,
    type: String,
    isError: Boolean = false,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    length: Int = 6,
) {
    when (type) {
        "TextField" ->
            OTPTextField(
                state = rememberOtpState(length),
                modifier = modifier.fillMaxWidth(),
                isError = isError,
                enabled = enabled,
                readOnly = readOnly,
            )

        "OutlinedTextField" ->
            OutlinedOTPTextField(
                state = rememberOtpState(length),
                modifier = modifier.fillMaxWidth(),
                isError = isError,
                enabled = enabled,
                readOnly = readOnly,
            )

        "UnderlinedTextField" ->
            UnderlinedOTPTextField(
                state = rememberOtpState(length),
                modifier = modifier.fillMaxWidth(),
                isError = isError,
                enabled = enabled,
                readOnly = readOnly,
            )
    }
}
