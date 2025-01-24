package com.nomanr.sample.ui.components.card

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.nomanr.sample.ui.AppTheme
import com.nomanr.sample.ui.LocalTypography
import com.nomanr.sample.ui.components.Surface
import com.nomanr.sample.ui.components.Text
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun Card(
    modifier: Modifier = Modifier,
    shape: Shape = CardDefaults.Shape,
    colors: CardColors = CardDefaults.cardColors(),
    elevation: CardElevation = CardDefaults.cardElevation(),
    border: BorderStroke? = null,
    content: @Composable ColumnScope.() -> Unit
) {
    Surface(
        modifier = modifier,
        shape = shape,
        color = colors.containerColor(enabled = true).value,
        contentColor = colors.contentColor(enabled = true).value,
        shadowElevation = elevation.shadowElevation(
            enabled = true,
            interactionSource = null
        ).value,
        border = border,
    ) {
        Column(content = content)
    }
}

@Composable
fun Card(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = CardDefaults.Shape,
    colors: CardColors = CardDefaults.cardColors(),
    elevation: CardElevation = CardDefaults.cardElevation(),
    border: BorderStroke? = null,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable ColumnScope.() -> Unit
) {
    Surface(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        shape = shape,
        color = colors.containerColor(enabled).value,
        contentColor = colors.contentColor(enabled).value,
        shadowElevation = elevation.shadowElevation(enabled, interactionSource).value,
        border = border,
        interactionSource = interactionSource,
    ) {
        Column(content = content)
    }
}

@Composable
fun ElevatedCard(
    modifier: Modifier = Modifier,
    shape: Shape = CardDefaults.ElevatedShape,
    colors: CardColors = CardDefaults.elevatedCardColors(),
    elevation: CardElevation = CardDefaults.elevatedCardElevation(),
    content: @Composable ColumnScope.() -> Unit
) = Card(
    modifier = modifier,
    shape = shape,
    border = null,
    elevation = elevation,
    colors = colors,
    content = content
)

@Composable
fun ElevatedCard(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = CardDefaults.ElevatedShape,
    colors: CardColors = CardDefaults.elevatedCardColors(),
    elevation: CardElevation = CardDefaults.elevatedCardElevation(),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable ColumnScope.() -> Unit
) = Card(
    onClick = onClick,
    modifier = modifier,
    enabled = enabled,
    shape = shape,
    colors = colors,
    elevation = elevation,
    border = null,
    interactionSource = interactionSource,
    content = content
)

@Composable
fun OutlinedCard(
    modifier: Modifier = Modifier,
    shape: Shape = CardDefaults.OutlinedShape,
    colors: CardColors = CardDefaults.outlinedCardColors(),
    elevation: CardElevation = CardDefaults.outlinedCardElevation(),
    border: BorderStroke = CardDefaults.outlinedCardBorder(),
    content: @Composable ColumnScope.() -> Unit
) = Card(
    modifier = modifier,
    shape = shape,
    border = border,
    elevation = elevation,
    colors = colors,
    content = content
)

@Composable
fun OutlinedCard(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = CardDefaults.OutlinedShape,
    colors: CardColors = CardDefaults.outlinedCardColors(),
    elevation: CardElevation = CardDefaults.outlinedCardElevation(),
    border: BorderStroke = CardDefaults.outlinedCardBorder(),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable ColumnScope.() -> Unit
) = Card(
    onClick = onClick,
    modifier = modifier,
    enabled = enabled,
    shape = shape,
    colors = colors,
    elevation = elevation,
    border = border,
    interactionSource = interactionSource,
    content = content
)

object CardDefaults {
    val Shape: Shape @Composable get() = RoundedCornerShape(12.0.dp)
    val ElevatedShape: Shape @Composable get() = Shape
    val OutlinedShape: Shape @Composable get() = Shape
    private val BorderWidth = 1.dp

    @Composable
    fun cardElevation(
        defaultElevation: Dp = 0.0.dp,
        pressedElevation: Dp = 0.0.dp,
        focusedElevation: Dp = 0.0.dp,
        hoveredElevation: Dp = 1.0.dp,
        draggedElevation: Dp = 3.0.dp,
        disabledElevation: Dp = 0.0.dp,
    ): CardElevation = CardElevation(
        defaultElevation = defaultElevation,
        pressedElevation = pressedElevation,
        focusedElevation = focusedElevation,
        hoveredElevation = hoveredElevation,
        draggedElevation = draggedElevation,
        disabledElevation = disabledElevation
    )

    @Composable
    fun elevatedCardElevation(
        defaultElevation: Dp = 2.0.dp,
        pressedElevation: Dp = 4.0.dp,
        focusedElevation: Dp = 4.0.dp,
        hoveredElevation: Dp = 4.0.dp,
        draggedElevation: Dp = 4.0.dp,
        disabledElevation: Dp = 0.0.dp,
    ): CardElevation = CardElevation(
        defaultElevation = defaultElevation,
        pressedElevation = pressedElevation,
        focusedElevation = focusedElevation,
        hoveredElevation = hoveredElevation,
        draggedElevation = draggedElevation,
        disabledElevation = disabledElevation
    )

    @Composable
    fun outlinedCardElevation(
        defaultElevation: Dp = 0.0.dp,
        pressedElevation: Dp = 0.0.dp,
        focusedElevation: Dp = 0.0.dp,
        hoveredElevation: Dp = 1.0.dp,
        draggedElevation: Dp = 3.0.dp,
        disabledElevation: Dp = 0.0.dp,
    ): CardElevation = CardElevation(
        defaultElevation = defaultElevation,
        pressedElevation = pressedElevation,
        focusedElevation = focusedElevation,
        hoveredElevation = hoveredElevation,
        draggedElevation = draggedElevation,
        disabledElevation = disabledElevation
    )

    @Composable
    fun cardColors(
        containerColor: Color = AppTheme.colors.surface,
        contentColor: Color = AppTheme.colors.onSurface,
        disabledContainerColor: Color =
            AppTheme.colors.disabled,
        disabledContentColor: Color = AppTheme.colors.onDisabled,
    ): CardColors = CardColors(
        containerColor = containerColor,
        contentColor = contentColor,
        disabledContainerColor = disabledContainerColor,
        disabledContentColor = disabledContentColor
    )

    @Composable
    fun elevatedCardColors(
        containerColor: Color = AppTheme.colors.background,
        contentColor: Color = AppTheme.colors.onBackground,
        disabledContainerColor: Color =
            AppTheme.colors.disabled,
        disabledContentColor: Color = AppTheme.colors.onDisabled,
    ): CardColors =
        CardColors(
            containerColor = containerColor,
            contentColor = contentColor,
            disabledContainerColor = disabledContainerColor,
            disabledContentColor = disabledContentColor
        )

    @Composable
    fun outlinedCardColors(
        containerColor: Color = AppTheme.colors.background,
        contentColor: Color = AppTheme.colors.onBackground,
        disabledContainerColor: Color =
            AppTheme.colors.disabled,
        disabledContentColor: Color = AppTheme.colors.onDisabled,
    ): CardColors =
        CardColors(
            containerColor = containerColor,
            contentColor = contentColor,
            disabledContainerColor = disabledContainerColor,
            disabledContentColor = disabledContentColor
        )

    @Composable
    fun outlinedCardBorder(enabled: Boolean = true): BorderStroke {
        val color = if (enabled) {
            AppTheme.colors.outline
        } else {
            AppTheme.colors.disabled
        }
        return remember(color) { BorderStroke(BorderWidth, color) }
    }
}

@Immutable
data class CardColors internal constructor(
    private val containerColor: Color,
    private val contentColor: Color,
    private val disabledContainerColor: Color,
    private val disabledContentColor: Color,
) {
    @Composable
    internal fun containerColor(enabled: Boolean): State<Color> {
        return rememberUpdatedState(if (enabled) containerColor else disabledContainerColor)
    }

    @Composable
    internal fun contentColor(enabled: Boolean): State<Color> {
        return rememberUpdatedState(if (enabled) contentColor else disabledContentColor)
    }

}


@Composable
@Preview
internal fun CardComponentSampleInTheme(){
    AppTheme {
        CardComponentSample()
    }
}

@Composable
@Preview
fun CardComponentSample() {
    val cardModifier = Modifier
        .fillMaxWidth()
        .height(120.dp)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Column {
            Text(text = "Default Card", style = LocalTypography.current.h3)
            Spacer(modifier = Modifier.height(8.dp))

            Card(
                modifier = cardModifier,
            ) {}
        }

        Column {
            Text(text = "Elevated Card with Action", style = LocalTypography.current.h3)
            ElevatedCard(
                modifier = cardModifier,
                onClick = { /* Handle click */ },
            ) {}
        }

        // Outlined Card
        Column {
            Text(text = "Custom Outlined Card", style = LocalTypography.current.h3)
            OutlinedCard(
                modifier = cardModifier,
            ) {}
        }

        Column {
            Text(text = "Disabled Card", style = LocalTypography.current.h3)
            Card(
                modifier = cardModifier,
                onClick = { },
                enabled = false,
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFBDBDBD),
                    contentColor = Color(0xFF9E9E9E),
                    disabledContainerColor = Color(0xFFEEEEEE),
                    disabledContentColor = Color(0xFFBDBDBD)
                )
            ) {}
        }

        Column {
            Text(text = "Custom Colored Card", style = LocalTypography.current.h3)
            Card(
                modifier = cardModifier,
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFECEFF1),
                    contentColor = Color(0xFF607D8B)
                )
            ) {}
        }

        Column {
            Text(text = "Outlined Card with Hover Elevation", style = LocalTypography.current.h3)
            OutlinedCard(
                modifier = cardModifier,
                onClick = { /* Handle click */ },
                elevation = CardDefaults.outlinedCardElevation(
                    defaultElevation = 0.dp,
                    hoveredElevation = 4.dp
                ),
                border = BorderStroke(1.dp, Color(0xFFBDBDBD)),
                colors = CardDefaults.outlinedCardColors(
                    containerColor = Color(0xFFE0E0E0),
                    contentColor = Color(0xFF616161)
                )
            ) {}
        }

        // Interactive Card
        Column {
            Text(text = "Interactive Card", style = LocalTypography.current.h3)
            Card(
                modifier = cardModifier,
                onClick = { /* Handle click */ },
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFECEFF1),
                    contentColor = Color(0xFF455A64)
                ),
                enabled = true
            ) {}
        }
    }
}