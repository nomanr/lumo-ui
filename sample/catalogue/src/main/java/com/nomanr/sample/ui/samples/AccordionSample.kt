package com.nomanr.sample.ui.samples

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp
import com.nomanr.sample.ui.AppTheme
import com.nomanr.sample.ui.components.Accordion
import com.nomanr.sample.ui.components.HorizontalDivider
import com.nomanr.sample.ui.components.Icon
import com.nomanr.sample.ui.components.IconButton
import com.nomanr.sample.ui.components.IconButtonVariant
import com.nomanr.sample.ui.components.Text
import com.nomanr.sample.ui.components.card.ElevatedCard
import com.nomanr.sample.ui.components.rememberAccordionGroupState
import com.nomanr.sample.ui.components.rememberAccordionState
import com.nomanr.sample.ui.demo.components.Skeleton

@Composable
fun AccordionSample(padding: PaddingValues) {
    Column(
        modifier = Modifier
            .padding(padding)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {

        Spacer(modifier = Modifier.height(16.dp))

        ExpandClickingHeader()
        ExpandClickingArrow()
        AnimateArrowWithExpandCollapse()
        NoAnimation()
        OnlyOneShouldOpenAtATime()

        Spacer(modifier = Modifier.height(16.dp))

    }
}

@Composable
private fun ExpandClickingHeader() {
    ElevatedCard(modifier = Modifier.padding(0.dp)) {
        Accordion(headerContent = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier.weight(0.8f)
                ) {
                    Text(
                        text = "Click the header to expand and collapse", style = AppTheme.typography.body1
                    )
                }
                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .weight(0.2f), contentAlignment = Alignment.CenterEnd
                ) {
                    Icon(imageVector = Icons.Outlined.KeyboardArrowDown, contentDescription = "Expand")
                }
            }
        }, bodyContent = {
            HorizontalDivider()
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Skeleton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                )
                Skeleton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                )
            }
        })
    }
}


@Composable
private fun ExpandClickingArrow() {
    val state = rememberAccordionState(clickable = false)

    ElevatedCard(modifier = Modifier.padding(0.dp)) {
        Accordion(state = state, headerContent = {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier.weight(0.8f)
                ) {
                    Text(
                        text = "Click the dropdown button to expand and collapse", style = AppTheme.typography.body1
                    )
                }
                Box(modifier = Modifier.weight(0.2f), contentAlignment = Alignment.CenterEnd) {
                    IconButton(variant = IconButtonVariant.Ghost, onClick = {
                        state.toggle()
                    }) {
                        Icon(imageVector = Icons.Outlined.KeyboardArrowDown, contentDescription = "Expand")
                    }
                }
            }
        }, bodyContent = {
            HorizontalDivider()
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Skeleton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                )
                Skeleton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                )
            }
        })
    }
}

@Composable
private fun AnimateArrowWithExpandCollapse() {
    val state = rememberAccordionState(clickable = false)

    ElevatedCard(modifier = Modifier.padding(0.dp)) {
        Accordion(state = state, headerContent = {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier.weight(0.8f)
                ) {
                    Text(
                        text = "Animate the button with accordion expanding and collapsing", style = AppTheme.typography.body1
                    )
                }
                Box(modifier = Modifier.weight(0.2f), contentAlignment = Alignment.CenterEnd) {
                    IconButton(variant = IconButtonVariant.Ghost, onClick = {
                        state.toggle()
                    }) {
                        Icon(
                            modifier = Modifier.rotate(state.animationProgress * 180),
                            imageVector = Icons.Outlined.KeyboardArrowDown,
                            contentDescription = "Expand"
                        )
                    }
                }
            }
        }, bodyContent = {
            HorizontalDivider()
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Skeleton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                )
                Skeleton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                )
            }
        })
    }
}

@Composable
private fun NoAnimation() {
    ElevatedCard(modifier = Modifier.padding(0.dp)) {
        Accordion(animate = false, headerContent = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier.weight(0.8f)
                ) {
                    Text(
                        text = "No animation", style = AppTheme.typography.body1
                    )
                }
                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .weight(0.2f), contentAlignment = Alignment.CenterEnd
                ) {
                    Icon(

                        imageVector = Icons.Outlined.KeyboardArrowDown, contentDescription = "Expand"
                    )
                }
            }
        }, bodyContent = {
            HorizontalDivider()
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Skeleton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                )
                Skeleton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                )
            }
        })
    }
}

@Composable
private fun OnlyOneShouldOpenAtATime() {
    val accordionGroupState = rememberAccordionGroupState(count = accordionTexts.size)

    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {

        Text(text = "Only one should open at a time", style = AppTheme.typography.h4)

        Spacer(modifier = Modifier.height(16.dp))

        accordionTexts.forEachIndexed { index, text ->

            val state = accordionGroupState.getState(index)

            ElevatedCard(modifier = Modifier.padding(0.dp)) {
                Accordion(state = state, headerContent = {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier.weight(0.8f)
                        ) {
                            Text(
                                text = text.first, style = AppTheme.typography.body1
                            )
                        }
                        Box(
                            modifier = Modifier
                                .size(44.dp)
                                .weight(0.2f), contentAlignment = Alignment.CenterEnd
                        ) {

                            Icon(
                                modifier = Modifier.rotate(state.animationProgress * 180),
                                imageVector = Icons.Outlined.KeyboardArrowDown,
                                contentDescription = "Expand"
                            )
                        }
                    }
                }, bodyContent = {
                    HorizontalDivider()
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Text(
                            text = text.second, style = AppTheme.typography.body1
                        )
                    }
                })
            }
        }
    }
}


private val accordionTexts = listOf(
    "How to use animationProgress in Accordion?" to "The animationProgress property lets you synchronize custom animations with the Accordion's expand and collapse transitions. Use it to animate opacity, size, or other visuals dynamically based on the transition state.",

    "What can you customize in Accordion?" to "You can customize headers, body content, animations, clickable behavior, and enable or disable state transitions for the Accordion component.",

    "How does the Accordion handle state?" to "The Accordion uses an AccordionState object to manage its expanded state, animation progress, and clickable or enabled properties."
)