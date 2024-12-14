package com.nomanr.sample.ui.sample.samples

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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import com.nomanr.sample.ui.AppTheme
import com.nomanr.sample.ui.components.Checkbox
import com.nomanr.sample.ui.components.Icon
import com.nomanr.sample.ui.components.RadioButton
import com.nomanr.sample.ui.components.Text
import com.nomanr.sample.ui.components.textfield.OutlinedTextField
import com.nomanr.sample.ui.components.textfield.TextField
import com.nomanr.sample.ui.components.textfield.UnderlinedTextField

@Composable
fun TextFieldSample() {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxWidth()
            .padding(16.dp)
            .imePadding()
    ) {

        InteractiveSample()

        Spacer(modifier = Modifier.height(32.dp))
        TextFieldExamples()
    }
}

@Composable
private fun TextFieldExamples() {
    var phone1 by remember { mutableStateOf("") }
    var phone2 by remember { mutableStateOf("") }
    var phone3 by remember { mutableStateOf("") }
    var phone4 by remember { mutableStateOf("") }
    var phone5 by remember { mutableStateOf("") }
    var phone6 by remember { mutableStateOf("") }
    var phone7 by remember { mutableStateOf("1234567890") }

    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.spacedBy(16.dp)) {

        Text(
            modifier = Modifier.padding(top = 16.dp), text = "Simple TextField", style = AppTheme.typography.h4
        )

        TextField(
            value = phone1,
            onValueChange = { if (it.isDigitsOnly()) phone1 = it },
            label = { Text("Phone") },
            placeholder = { Text("000-000-0000") },
            maxLines = 1,
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )

        Text(
            modifier = Modifier.padding(top = 16.dp), text = "TextField with a supporting text", style = AppTheme.typography.h4
        )


        OutlinedTextField(
            value = phone2,
            onValueChange = { if (it.isDigitsOnly()) phone2 = it },
            label = { Text("Phone") },
            placeholder = { Text("000-000-0000") },
            supportingText = { Text("Enter your phone number") },
            maxLines = 1,
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )

        Text(
            modifier = Modifier.padding(top = 16.dp), text = "And with a prefix", style = AppTheme.typography.h4
        )

        UnderlinedTextField(
            value = phone3,
            onValueChange = { if (it.isDigitsOnly()) phone3 = it },
            label = { Text("Phone") },
            prefix = { Text("+44") },
            placeholder = { Text("000-000-0000") },
            supportingText = { Text("Enter your phone number") },
            maxLines = 1,
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )

        Text(
            modifier = Modifier.padding(top = 16.dp), text = "And with a suffix", style = AppTheme.typography.h4
        )

        TextField(
            value = phone4,
            onValueChange = { if (it.isDigitsOnly()) phone4 = it },
            label = { Text("Phone") },
            prefix = { Text("+44") },
            suffix = { Text("${phone4.length}/10", style = AppTheme.typography.label3) },
            placeholder = { Text("000-000-0000") },
            supportingText = { Text("Enter your phone number") },
            maxLines = 1,
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )

        Text(
            modifier = Modifier.padding(top = 16.dp), text = "And with a leading icon", style = AppTheme.typography.h4
        )

        TextField(
            value = phone5,
            onValueChange = { if (it.isDigitsOnly()) phone5 = it },
            label = { Text("Phone") },
            prefix = { Text("+44") },
            suffix = { Text("${phone5.length}/10", style = AppTheme.typography.label3) },
            leadingIcon = {
                Icon(imageVector = Icons.Filled.Phone, contentDescription = "Phone Icon")
            },
            placeholder = { Text("000-000-0000") },
            supportingText = { Text("Enter your phone number") },
            maxLines = 1,
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )

        Text(
            modifier = Modifier.padding(top = 16.dp), text = "And with a trailing icon", style = AppTheme.typography.h4
        )

        TextField(
            value = phone6,
            onValueChange = { if (it.isDigitsOnly()) phone6 = it },
            label = { Text("Phone") },
            prefix = { Text("+44") },
            suffix = { Text("${phone6.length}/10", style = AppTheme.typography.label3) },
            leadingIcon = {
                Icon(imageVector = Icons.Filled.Phone, contentDescription = "Phone Icon")
            },
            trailingIcon = {
                if (phone6.length >= 10) {
                    Icon(imageVector = Icons.Outlined.Check, contentDescription = "Phone Icon", tint = Color.Green)
                } else {
                    Icon(imageVector = Icons.Outlined.Close, contentDescription = "Phone Icon", tint = Color.Red)
                }
            },
            placeholder = { Text("000-000-0000") },
            supportingText = { Text("Enter your phone number") },
            maxLines = 1,
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )

        Text(
            modifier = Modifier.padding(top = 16.dp), text = "And with an error", style = AppTheme.typography.h4
        )

        TextField(
            value = phone6,
            onValueChange = { if (it.isDigitsOnly()) phone6 = it },
            label = { Text("Phone") },
            prefix = { Text("+44") },
            suffix = { Text("${phone6.length}/10", style = AppTheme.typography.label3) },
            leadingIcon = {
                Icon(imageVector = Icons.Filled.Phone, contentDescription = "Phone Icon")
            },
            trailingIcon = {
                if (phone6.length >= 10) {
                    Icon(imageVector = Icons.Outlined.Check, contentDescription = "Phone Icon", tint = Color.Green)
                } else {
                    Icon(imageVector = Icons.Outlined.Close, contentDescription = "Phone Icon", tint = Color.Red)
                }
            },
            placeholder = { Text("000-000-0000") },
            supportingText = { Text("Enter your phone number") },
            isError = phone6.length < 10,
            maxLines = 1,
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )

        Text(
            modifier = Modifier.padding(top = 16.dp), text = "And read-only", style = AppTheme.typography.h4
        )

        TextField(
            value = phone7,
            onValueChange = { if (it.isDigitsOnly()) phone7 = it },
            label = { Text("Phone") },
            prefix = { Text("+44") },
            suffix = { Text("${phone7.length}/10", style = AppTheme.typography.label3) },
            leadingIcon = {
                Icon(imageVector = Icons.Filled.Phone, contentDescription = "Phone Icon")
            },
            trailingIcon = {
                if (phone7.length >= 10) {
                    Icon(imageVector = Icons.Outlined.Check, contentDescription = "Phone Icon", tint = Color.Green)
                } else {
                    Icon(imageVector = Icons.Outlined.Close, contentDescription = "Phone Icon", tint = Color.Red)
                }
            },
            placeholder = { Text("000-000-0000") },
            supportingText = { Text("Enter your phone number") },
            readOnly = true,
            maxLines = 1,
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )

        Text(
            modifier = Modifier.padding(top = 16.dp), text = "A date of birth example", style = AppTheme.typography.h4
        )


        var day by remember { mutableStateOf("") }
        var month by remember { mutableStateOf("") }
        var year by remember { mutableStateOf("") }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                modifier = Modifier.weight(1f),
                value = day,
                onValueChange = {
                    day = it
                },
                label = { Text("Day") },
                placeholder = { Text("DD") },
                supportingText = { Text("DD", style = AppTheme.typography.label2) },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
            )
            OutlinedTextField(
                modifier = Modifier.weight(1f),
                value = month,
                onValueChange = {
                    month = it
                },
                label = { Text("Month") },
                placeholder = { Text("MM") },
                supportingText = { Text("MM", style = AppTheme.typography.label2) },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
            )
            OutlinedTextField(
                modifier = Modifier.weight(1f),
                value = year,
                onValueChange = {
                    year = it
                },
                label = { Text("Year") },
                placeholder = { Text("YYYY") },
                supportingText = { Text("YYYY", style = AppTheme.typography.label2) },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
            )
        }


    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun InteractiveSample() {
    var textFieldType by remember { mutableStateOf("TextField") }
    var phone by remember { mutableStateOf("") }
    var supportingTextEnabled by remember { mutableStateOf(false) }
    var prefixEnabled by remember { mutableStateOf(false) }
    var suffixEnabled by remember { mutableStateOf(false) }
    var leadingIconEnabled by remember { mutableStateOf(false) }
    var trailingIconEnabled by remember { mutableStateOf(false) }
    var errorEnabled by remember { mutableStateOf(false) }
    var disabled by remember { mutableStateOf(false) }
    var readOnly by remember { mutableStateOf(false) }


    Column(
        modifier = Modifier.fillMaxSize()
    ) {


        Text("TextField Demo", style = AppTheme.typography.h4)

        Spacer(modifier = Modifier.height(16.dp))

        RenderTextField(
            label = "Phone",
            value = phone,
            onValueChange = { if (it.isDigitsOnly()) phone = it },
            type = textFieldType,
            supportingText = if (supportingTextEnabled) "Enter your phone number" else null,
            prefix = if (prefixEnabled) "+44" else null,
            suffix = if (suffixEnabled) "${phone.length}/10" else null,
            isError = errorEnabled,
            enabled = !disabled,
            readOnly = readOnly,
            leadingIcon = if (leadingIconEnabled) {
                { Icon(Icons.Filled.Phone, contentDescription = "Phone Icon") }
            } else null,
            trailingIcon = if (trailingIconEnabled) {
                if (phone.length >= 10) {
                    { Icon(Icons.Outlined.Check, contentDescription = "Phone Icon", tint = Color.Green) }
                } else {
                    { Icon(Icons.Outlined.Close, contentDescription = "Phone Icon", tint = Color.Red) }
                }
            } else null,
        )

        Spacer(modifier = Modifier.height(24.dp))


        Text("TextField Type", style = AppTheme.typography.h4)

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
            TextFieldToggleOptions("Supporting Text", supportingTextEnabled) { supportingTextEnabled = it }
            TextFieldToggleOptions("Prefix", prefixEnabled) { prefixEnabled = it }
            TextFieldToggleOptions("Suffix", suffixEnabled) { suffixEnabled = it }
            TextFieldToggleOptions("Leading Icon", leadingIconEnabled) { leadingIconEnabled = it }
            TextFieldToggleOptions("Trailing Icon", trailingIconEnabled) { trailingIconEnabled = it }
            TextFieldToggleOptions("Error State", errorEnabled) { errorEnabled = it }
            TextFieldToggleOptions("Disabled", disabled) { disabled = it }
            TextFieldToggleOptions("Readonly", readOnly) { readOnly = it }
        }
    }
}

@Composable
private fun RenderTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    type: String,
    label: String = "",
    supportingText: String? = null,
    prefix: String? = null,
    suffix: String? = null,
    isError: Boolean = false,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
) {
    when (type) {
        "TextField" -> TextField(value = value,
            onValueChange = onValueChange,
            maxLines = 1,
            modifier = modifier.fillMaxWidth(),
            label = if (label.isNotEmpty()) {
                { Text(label) }
            } else null,
            supportingText = supportingText?.let { { Text(it) } },
            prefix = prefix?.let { { Text(it) } },
            suffix = suffix?.let { { Text(it, style = AppTheme.typography.body2) } },
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            isError = isError,
            enabled = enabled,
            readOnly = readOnly,
            placeholder = { Text("Enter your phone number") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )

        "OutlinedTextField" -> OutlinedTextField(value = value,
            onValueChange = onValueChange,
            modifier = modifier.fillMaxWidth(),
            maxLines = 1,
            label = if (label.isNotEmpty()) {
                { Text(label) }
            } else null,
            supportingText = supportingText?.let { { Text(it) } },
            prefix = prefix?.let { { Text(it) } },
            suffix = suffix?.let { { Text(it, style = AppTheme.typography.body2) } },
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            isError = isError,
            enabled = enabled,
            readOnly = readOnly,
            placeholder = { Text("Enter your phone number") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )

        "UnderlinedTextField" -> UnderlinedTextField(value = value,
            onValueChange = onValueChange,
            modifier = modifier.fillMaxWidth(),
            maxLines = 1,
            label = if (label.isNotEmpty()) {
                { Text(label) }
            } else null,
            supportingText = supportingText?.let { { Text(it) } },
            prefix = prefix?.let { { Text(it) } },
            suffix = suffix?.let { { Text(it, style = AppTheme.typography.body2) } },
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            isError = isError,
            enabled = enabled,
            readOnly = readOnly,
            placeholder = { Text("Enter your phone number") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )
    }


}

@Composable
private fun TextFieldToggleOptions(label: String, state: Boolean, onStateChange: (Boolean) -> Unit) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(checked = state, onCheckedChange = onStateChange)
        Text(text = label, style = AppTheme.typography.body2)
    }
}