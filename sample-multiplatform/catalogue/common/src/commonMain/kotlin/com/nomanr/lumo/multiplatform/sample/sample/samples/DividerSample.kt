package com.nomanr.lumo.multiplatform.sample.sample.samples

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nomanr.lumo.multiplatform.ui.AppTheme
import com.nomanr.lumo.multiplatform.ui.components.HorizontalDivider
import com.nomanr.lumo.multiplatform.ui.components.Text
import com.nomanr.lumo.multiplatform.ui.components.VerticalDivider

@Composable
fun DividerSample() {
    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .padding(16.dp),
    ) {
        Text(text = "Two type of dividers are available: \n- Horizontal\n- Vertical", style = AppTheme.typography.h4)

        Spacer(modifier = Modifier.height(24.dp))

        HorizontalDivider()
        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                modifier = Modifier.padding(16.dp),
                text = "Start",
            )
            VerticalDivider(
                modifier = Modifier.fillMaxHeight(),
            )
            Text(
                modifier = Modifier.padding(16.dp),
                text = "End",
            )
        }
        HorizontalDivider()

        Spacer(modifier = Modifier.height(24.dp))

        Text(text = "Different thickness", style = AppTheme.typography.label1)

        Spacer(modifier = Modifier.height(8.dp))

        HorizontalDivider(thickness = 4.dp)
        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                modifier = Modifier.padding(16.dp),
                text = "Start",
            )
            VerticalDivider(
                modifier = Modifier.fillMaxHeight(),
                thickness = 4.dp,
            )
            Text(
                modifier = Modifier.padding(16.dp),
                text = "End",
            )
        }
        HorizontalDivider(thickness = 4.dp)
    }
}
