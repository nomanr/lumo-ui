package com.nomanr.sample.ui.sample.samples

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nomanr.sample.ui.LocalTypography
import com.nomanr.sample.ui.components.Text

@Composable
fun TextSample() {
    val typography = LocalTypography.current

    Column(
        modifier =
            Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
                .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Text(
            text = "H1 Heading",
            style = typography.h1,
        )
        Text(
            text = "H2 Heading",
            style = typography.h2,
        )
        Text(
            text = "H3 Heading",
            style = typography.h3,
        )
        Text(
            text = "H4 Heading",
            style = typography.h4,
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "This is body1 text.",
            style = typography.body1,
        )
        Text(
            text = "This is body2 text.",
            style = typography.body2,
        )
        Text(
            text = "Body3 text for fine print.",
            style = typography.body3,
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Label1: Form Label",
            style = typography.label1,
        )
        Text(
            text = "Label2: Secondary Info",
            style = typography.label2,
        )
        Text(
            text = "Label3: Tiny Details",
            style = typography.label3,
        )

        Spacer(modifier = Modifier.height(16.dp))
    }
}
