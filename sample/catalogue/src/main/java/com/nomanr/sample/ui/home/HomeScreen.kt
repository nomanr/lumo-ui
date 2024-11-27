package com.nomanr.sample.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.nomanr.composeui.sample.R
import com.nomanr.sample.ui.AppTheme
import com.nomanr.sample.ui.components.Scaffold
import com.nomanr.sample.ui.components.Text
import com.nomanr.sample.ui.components.topbar.TopBar
import com.nomanr.sample.ui.components.topbar.TopBarDefaults

@Composable
fun HomeScreen() {
    Scaffold(topBar = {
        TopBar(
            colors = TopBarDefaults.topBarColors().copy(containerColor = AppTheme.colors.background.copy(alpha = 0.95f))
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Image(
                    modifier = Modifier.height(26.dp),
                    painter = painterResource(id = R.drawable.logo_with_name),
                    contentDescription = "Logo"
                )

            }

        }
    }) { padding ->
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(padding)
        ) {
            repeat(100) {
                Text(text = "Dummy Render Text $it")
            }
        }
    }
}