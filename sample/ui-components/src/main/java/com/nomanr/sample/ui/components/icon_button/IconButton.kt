package com.nomanr.sample.ui.components.icon_button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AcUnit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nomanr.sample.ui.AppTheme
import com.nomanr.sample.ui.components.Icon
import com.nomanr.sample.ui.components.Surface

@Composable
fun FilledIconButton(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    loading: Boolean = false,
    type: IconButtonType = IconButtonType.Square,
    onClick: () -> Unit = {},
    contentPadding: PaddingValues = IconButtonDefaults.ButtonPadding,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: (@Composable () -> Unit),
) {
    IconButtonComponent(
        modifier = modifier,
        enabled = enabled,
        loading = loading,
        style = IconButtonDefaults.primaryFilled(type.shape),
        onClick = onClick,
        contentPadding = contentPadding,
        interactionSource = interactionSource,
        content = content
    )
}

@Composable
fun ElevatedIconButton(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    loading: Boolean = false,
    type: IconButtonType = IconButtonType.Square,
    onClick: () -> Unit = {},
    contentPadding: PaddingValues = IconButtonDefaults.ButtonPadding,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: (@Composable () -> Unit),
) {
    IconButtonComponent(
        modifier = modifier,
        enabled = enabled,
        loading = loading,
        style = IconButtonDefaults.primaryElevated(type.shape),
        onClick = onClick,
        contentPadding = contentPadding,
        interactionSource = interactionSource,
        content = content
    )
}

@Composable
fun OutlinedIconButton(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    loading: Boolean = false,
    type: IconButtonType = IconButtonType.Square,
    onClick: () -> Unit = {},
    contentPadding: PaddingValues = IconButtonDefaults.ButtonPadding,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: (@Composable () -> Unit),
) {
    IconButtonComponent(
        modifier = modifier,
        enabled = enabled,
        loading = loading,
        style = IconButtonDefaults.primaryOutlined(type.shape),
        onClick = onClick,
        contentPadding = contentPadding,
        interactionSource = interactionSource,
        content = content
    )
}

@Composable
fun PrimaryGhostIconButton(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    loading: Boolean = false,
    type: IconButtonType = IconButtonType.Square,
    onClick: () -> Unit = {},
    contentPadding: PaddingValues = IconButtonDefaults.ButtonPadding,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: (@Composable () -> Unit),
) {
    IconButtonComponent(
        modifier = modifier,
        enabled = enabled,
        loading = loading,
        style = IconButtonDefaults.primaryGhost(type.shape),
        onClick = onClick,
        contentPadding = contentPadding,
        interactionSource = interactionSource,
        content = content
    )
}

@Composable
fun SecondaryFilledIconButton(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    loading: Boolean = false,
    type: IconButtonType = IconButtonType.Square,
    onClick: () -> Unit = {},
    contentPadding: PaddingValues = IconButtonDefaults.ButtonPadding,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: (@Composable () -> Unit),
) {
    IconButtonComponent(
        modifier = modifier,
        enabled = enabled,
        loading = loading,
        style = IconButtonDefaults.secondaryFilled(type.shape),
        onClick = onClick,
        contentPadding = contentPadding,
        interactionSource = interactionSource,
        content = content
    )
}

@Composable
fun SecondaryElevatedIconButton(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    loading: Boolean = false,
    type: IconButtonType = IconButtonType.Square,
    onClick: () -> Unit = {},
    contentPadding: PaddingValues = IconButtonDefaults.ButtonPadding,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: (@Composable () -> Unit),
) {
    IconButtonComponent(
        modifier = modifier,
        enabled = enabled,
        loading = loading,
        style = IconButtonDefaults.secondaryElevated(type.shape),
        onClick = onClick,
        contentPadding = contentPadding,
        interactionSource = interactionSource,
        content = content
    )
}

@Composable
fun SecondaryOutlinedIconButton(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    loading: Boolean = false,
    type: IconButtonType = IconButtonType.Square,
    onClick: () -> Unit = {},
    contentPadding: PaddingValues = IconButtonDefaults.ButtonPadding,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: (@Composable () -> Unit),
) {
    IconButtonComponent(
        modifier = modifier,
        enabled = enabled,
        loading = loading,
        style = IconButtonDefaults.secondaryOutlined(type.shape),
        onClick = onClick,
        contentPadding = contentPadding,
        interactionSource = interactionSource,
        content = content
    )
}

@Composable
fun SecondaryGhostIconButton(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    loading: Boolean = false,
    type: IconButtonType = IconButtonType.Square,
    onClick: () -> Unit = {},
    contentPadding: PaddingValues = IconButtonDefaults.ButtonPadding,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: (@Composable () -> Unit),
) {
    IconButtonComponent(
        modifier = modifier,
        enabled = enabled,
        loading = loading,
        style = IconButtonDefaults.secondaryGhost(type.shape),
        onClick = onClick,
        contentPadding = contentPadding,
        interactionSource = interactionSource,
        content = content
    )
}

@Composable
fun DestructiveFilledIconButton(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    loading: Boolean = false,
    type: IconButtonType = IconButtonType.Square,
    onClick: () -> Unit = {},
    contentPadding: PaddingValues = IconButtonDefaults.ButtonPadding,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: (@Composable () -> Unit),
) {
    IconButtonComponent(
        modifier = modifier,
        enabled = enabled,
        loading = loading,
        style = IconButtonDefaults.destructiveFilled(type.shape),
        onClick = onClick,
        contentPadding = contentPadding,
        interactionSource = interactionSource,
        content = content
    )
}

@Composable
fun DestructiveElevatedIconButton(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    loading: Boolean = false,
    type: IconButtonType = IconButtonType.Square,
    onClick: () -> Unit = {},
    contentPadding: PaddingValues = IconButtonDefaults.ButtonPadding,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: (@Composable () -> Unit),
) {

    IconButtonComponent(
        modifier = modifier,
        enabled = enabled,
        loading = loading,
        style = IconButtonDefaults.destructiveElevated(type.shape),
        onClick = onClick,
        contentPadding = contentPadding,
        interactionSource = interactionSource,
        content = content
    )
}

@Composable
fun DestructiveOutlinedIconButton(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    loading: Boolean = false,
    type: IconButtonType = IconButtonType.Square,
    onClick: () -> Unit = {},
    contentPadding: PaddingValues = IconButtonDefaults.ButtonPadding,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: (@Composable () -> Unit),
) {
    IconButtonComponent(
        modifier = modifier,
        enabled = enabled,
        loading = loading,
        style = IconButtonDefaults.destructiveOutlined(type.shape),
        onClick = onClick,
        contentPadding = contentPadding,
        interactionSource = interactionSource,
        content = content
    )
}

@Composable
fun DestructiveGhostIconButton(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    loading: Boolean = false,
    type: IconButtonType = IconButtonType.Square,
    onClick: () -> Unit = {},
    contentPadding: PaddingValues = IconButtonDefaults.ButtonPadding,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: (@Composable () -> Unit),
) {
    IconButtonComponent(
        modifier = modifier,
        enabled = enabled,
        loading = loading,
        style = IconButtonDefaults.destructiveGhost(type.shape),
        onClick = onClick,
        contentPadding = contentPadding,
        interactionSource = interactionSource,
        content = content
    )
}

@Composable
private fun IconButtonComponent(
    modifier: Modifier,
    enabled: Boolean = true,
    loading: Boolean = false,
    style: ButtonStyle,
    onClick: () -> Unit,
    contentPadding: PaddingValues = IconButtonDefaults.ButtonPadding,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: (@Composable () -> Unit),
) {

    val containerColor = style.colors.containerColor(enabled).value
    val contentColor = style.colors.contentColor(enabled).value
    val borderColor = style.colors.borderColor(enabled).value
    val borderStroke = if (borderColor != null) BorderStroke(
        IconButtonDefaults.OutlineHeight, borderColor
    ) else null

    val shadowElevation = style.elevation?.shadowElevation(enabled, interactionSource)?.value ?: 0.dp

    Surface(
        onClick = onClick,
        modifier = modifier
            .defaultMinSize(minWidth = IconButtonDefaults.ButtonSize, minHeight = IconButtonDefaults.ButtonSize)
            .semantics { role = Role.Button },
        enabled = enabled,
        shape = style.shape,
        color = containerColor,
        contentColor = contentColor,
        border = borderStroke,
        shadowElevation = shadowElevation,
        interactionSource = interactionSource
    ) {

        DefaultButtonContent(
            modifier = Modifier.padding(contentPadding),
            loading = loading,
            content = content,
        )
    }
}

@Composable
private fun DefaultButtonContent(
    modifier: Modifier = Modifier,
    loading: Boolean,
    content: (@Composable () -> Unit)
) {

    Box(
        modifier, contentAlignment = Alignment.Center
    ) {
        // TODO: Add loading indicator

        content()
    }
}

enum class IconButtonType(val shape: Shape) {
    Square(IconButtonDefaults.ButtonSquareShape), Circle(IconButtonDefaults.ButtonCircleShape),
}

@Composable
@Preview("Primary")
fun PrimaryButtonPreview() {
    AppTheme {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                FilledIconButton {
                    Icon(Icons.Filled.AcUnit, contentDescription = "Filled Icon Button")
                }

                FilledIconButton(enabled = false) {

                    Icon(Icons.Filled.AcUnit, contentDescription = "Filled Icon Button")
                }

                FilledIconButton(type = IconButtonType.Circle) {
                    Icon(Icons.Filled.AcUnit, contentDescription = "Filled Icon Button")
                }

                FilledIconButton(enabled = false, type = IconButtonType.Circle) {
                    Icon(Icons.Filled.AcUnit, contentDescription = "Filled Icon Button")
                }
            }
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                ElevatedIconButton {
                    Icon(Icons.Filled.AcUnit, contentDescription = "Filled Icon Button")
                }

                ElevatedIconButton(enabled = false) {
                    Icon(Icons.Filled.AcUnit, contentDescription = "Filled Icon Button")
                }

                ElevatedIconButton(type = IconButtonType.Circle) {
                    Icon(Icons.Filled.AcUnit, contentDescription = "Filled Icon Button")
                }
                ElevatedIconButton(enabled = false, type = IconButtonType.Circle) {
                    Icon(Icons.Filled.AcUnit, contentDescription = "Filled Icon Button")
                }
            }
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedIconButton {
                    Icon(Icons.Filled.AcUnit, contentDescription = "Filled Icon Button")
                }

                OutlinedIconButton(enabled = false) {
                    Icon(Icons.Filled.AcUnit, contentDescription = "Filled Icon Button")
                }

                OutlinedIconButton(type = IconButtonType.Circle) {
                    Icon(Icons.Filled.AcUnit, contentDescription = "Filled Icon Button")
                }

                OutlinedIconButton(enabled = false, type = IconButtonType.Circle) {
                    Icon(Icons.Filled.AcUnit, contentDescription = "Filled Icon Button")
                }
            }
        }
    }
}

@Composable
@Preview("Secondary")
fun SecondaryButtonPreview() {
    AppTheme {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                SecondaryFilledIconButton {
                    Icon(Icons.Filled.AcUnit, contentDescription = "Filled Icon Button")
                }

                SecondaryFilledIconButton(enabled = false) {
                    Icon(Icons.Filled.AcUnit, contentDescription = "Filled Icon Button")
                }

                SecondaryFilledIconButton(type = IconButtonType.Circle) {
                    Icon(Icons.Filled.AcUnit, contentDescription = "Filled Icon Button")
                }

                SecondaryFilledIconButton(enabled = false, type = IconButtonType.Circle) {
                    Icon(Icons.Filled.AcUnit, contentDescription = "Filled Icon Button")
                }
            }

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                SecondaryElevatedIconButton {
                    Icon(Icons.Filled.AcUnit, contentDescription = "Filled Icon Button")
                }

                SecondaryElevatedIconButton(enabled = false) {
                    Icon(Icons.Filled.AcUnit, contentDescription = "Filled Icon Button")
                }

                SecondaryElevatedIconButton(type = IconButtonType.Circle) {
                    Icon(Icons.Filled.AcUnit, contentDescription = "Filled Icon Button")
                }

                SecondaryElevatedIconButton(enabled = false, type = IconButtonType.Circle) {
                    Icon(Icons.Filled.AcUnit, contentDescription = "Filled Icon Button")
                }
            }

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                SecondaryOutlinedIconButton {
                    Icon(Icons.Filled.AcUnit, contentDescription = "Filled Icon Button")
                }

                SecondaryOutlinedIconButton(enabled = false) {
                    Icon(Icons.Filled.AcUnit, contentDescription = "Filled Icon Button")
                }

                SecondaryOutlinedIconButton(type = IconButtonType.Circle) {
                    Icon(Icons.Filled.AcUnit, contentDescription = "Filled Icon Button")
                }

                SecondaryOutlinedIconButton(enabled = false, type = IconButtonType.Circle) {
                    Icon(Icons.Filled.AcUnit, contentDescription = "Filled Icon Button")
                }
            }
        }
    }
}

@Composable
@Preview("Destructive")
fun DestructiveButtonPreview() {
    AppTheme {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                DestructiveFilledIconButton {
                    Icon(Icons.Filled.AcUnit, contentDescription = "Filled Icon Button")
                }

                DestructiveFilledIconButton(enabled = false) {
                    Icon(Icons.Filled.AcUnit, contentDescription = "Filled Icon Button")
                }

                DestructiveFilledIconButton(type = IconButtonType.Circle) {
                    Icon(Icons.Filled.AcUnit, contentDescription = "Filled Icon Button")
                }

                DestructiveFilledIconButton(enabled = false, type = IconButtonType.Circle) {
                    Icon(Icons.Filled.AcUnit, contentDescription = "Filled Icon Button")
                }
            }

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                DestructiveElevatedIconButton {
                    Icon(Icons.Filled.AcUnit, contentDescription = "Filled Icon Button")
                }

                DestructiveElevatedIconButton(enabled = false) {
                    Icon(Icons.Filled.AcUnit, contentDescription = "Filled Icon Button")
                }

                DestructiveElevatedIconButton(type = IconButtonType.Circle) {
                    Icon(Icons.Filled.AcUnit, contentDescription = "Filled Icon Button")
                }

                DestructiveElevatedIconButton(enabled = false, type = IconButtonType.Circle) {
                    Icon(Icons.Filled.AcUnit, contentDescription = "Filled Icon Button")
                }
            }

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                DestructiveOutlinedIconButton {
                    Icon(Icons.Filled.AcUnit, contentDescription = "Filled Icon Button")
                }

                DestructiveOutlinedIconButton(enabled = false) {
                    Icon(Icons.Filled.AcUnit, contentDescription = "Filled Icon Button")
                }

                DestructiveOutlinedIconButton(type = IconButtonType.Circle) {
                    Icon(Icons.Filled.AcUnit, contentDescription = "Filled Icon Button")
                }

                DestructiveOutlinedIconButton(enabled = false, type = IconButtonType.Circle) {
                    Icon(Icons.Filled.AcUnit, contentDescription = "Filled Icon Button")
                }
            }
        }
    }
}

@Composable
@Preview("Destructive")
fun GhostButtonPreview() {
    AppTheme {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                PrimaryGhostIconButton {
                    Icon(Icons.Filled.AcUnit, contentDescription = "Filled Icon Button")
                }

                SecondaryGhostIconButton(enabled = false) {
                    Icon(Icons.Filled.AcUnit, contentDescription = "Filled Icon Button")
                }

                DestructiveGhostIconButton(type = IconButtonType.Circle) {
                    Icon(Icons.Filled.AcUnit, contentDescription = "Filled Icon Button")
                }

                DestructiveGhostIconButton(enabled = false, type = IconButtonType.Circle) {
                    Icon(Icons.Filled.AcUnit, contentDescription = "Filled Icon Button")
                }
            }
        }
    }
}
