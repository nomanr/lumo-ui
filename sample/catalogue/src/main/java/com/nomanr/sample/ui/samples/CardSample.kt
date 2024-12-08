package com.nomanr.sample.ui.samples

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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nomanr.sample.ui.AppTheme
import com.nomanr.sample.ui.components.Text
import com.nomanr.sample.ui.components.card.Card
import com.nomanr.sample.ui.components.card.ElevatedCard
import com.nomanr.sample.ui.components.card.OutlinedCard
import com.nomanr.sample.ui.demo.components.Skeleton

@Composable
fun CardSample() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

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
                    Text(text = "Outlined Card", style = AppTheme.typography.h4)
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
                    Text(text = "Filled Card", style = AppTheme.typography.h4)
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
                    Text(text = "Elevated Card", style = AppTheme.typography.h4)
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



        Spacer(modifier = Modifier.height(24.dp))

    }

}

