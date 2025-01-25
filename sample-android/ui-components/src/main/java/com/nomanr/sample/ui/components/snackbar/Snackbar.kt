package com.nomanr.sample.ui.components.snackbar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastFirst
import androidx.compose.ui.util.fastFirstOrNull
import com.nomanr.sample.ui.AppTheme
import com.nomanr.sample.ui.LocalContentColor
import com.nomanr.sample.ui.LocalTextStyle
import com.nomanr.sample.ui.components.Icon
import com.nomanr.sample.ui.components.IconButton
import com.nomanr.sample.ui.components.IconButtonVariant
import com.nomanr.sample.ui.components.Surface
import com.nomanr.sample.ui.components.Text
import com.nomanr.sample.ui.components.snackbar.SnackbarDefaults.ContainerElevation
import com.nomanr.sample.ui.components.snackbar.SnackbarDefaults.ContainerMaxWidth
import com.nomanr.sample.ui.components.snackbar.SnackbarDefaults.HeightToFirstLine
import com.nomanr.sample.ui.components.snackbar.SnackbarDefaults.HorizontalSpacing
import com.nomanr.sample.ui.components.snackbar.SnackbarDefaults.HorizontalSpacingButtonSide
import com.nomanr.sample.ui.components.snackbar.SnackbarDefaults.SingleLineContainerHeight
import com.nomanr.sample.ui.components.snackbar.SnackbarDefaults.SnackbarVerticalPadding
import com.nomanr.sample.ui.components.snackbar.SnackbarDefaults.TextEndExtraSpacing
import com.nomanr.sample.ui.components.snackbar.SnackbarDefaults.TwoLinesContainerHeight
import kotlin.math.max
import kotlin.math.min

@Composable
fun Snackbar(
    snackbarData: SnackbarData,
    modifier: Modifier = Modifier,
    shape: Shape = SnackbarDefaults.shape,
    containerColor: Color = SnackbarDefaults.color,
    contentColor: Color = SnackbarDefaults.contentColor,
    actionColor: Color = SnackbarDefaults.actionColor,
    actionContentColor: Color = SnackbarDefaults.actionContentColor,
    dismissActionContentColor: Color = SnackbarDefaults.dismissActionContentColor,
) {
    val actionLabel = snackbarData.visuals.actionLabel
    val actionComposable: (@Composable () -> Unit)? = if (actionLabel != null) {
        @Composable {
            CompositionLocalProvider(LocalContentColor provides actionColor) {
                SnackbarDefaults.ActionButton(actionLabel) {
                    snackbarData.performAction()
                }
            }
        }
    } else {
        null
    }
    val dismissActionComposable: (@Composable () -> Unit)? = if (snackbarData.visuals.withDismissAction) {
        @Composable {
            IconButton(
                variant = IconButtonVariant.Ghost,
                modifier = Modifier.padding(end = 8.dp),
                onClick = { snackbarData.dismiss() }) {
                Icon(imageVector = Icons.Default.Close, contentDescription = "Close")
            }
        }
    } else {
        null
    }
    Snackbar(modifier = modifier.padding(12.dp),
        action = actionComposable,
        dismissAction = dismissActionComposable,
        shape = shape,
        containerColor = containerColor,
        contentColor = contentColor,
        actionContentColor = actionContentColor,
        dismissActionContentColor = dismissActionContentColor,
        content = { Text(snackbarData.visuals.message, style = AppTheme.typography.body2) })
}

@Composable
fun Snackbar(
    modifier: Modifier = Modifier,
    action: @Composable (() -> Unit)? = null,
    dismissAction: @Composable (() -> Unit)? = null,
    shape: Shape = SnackbarDefaults.shape,
    containerColor: Color = SnackbarDefaults.color,
    contentColor: Color = SnackbarDefaults.contentColor,
    actionContentColor: Color = SnackbarDefaults.actionContentColor,
    dismissActionContentColor: Color = SnackbarDefaults.dismissActionContentColor,
    content: @Composable () -> Unit
) {
    Surface(
        modifier = modifier,
        shape = shape,
        color = containerColor,
        contentColor = contentColor,
        shadowElevation = ContainerElevation
    ) {
        val textStyle = AppTheme.typography.body1
        val actionTextStyle = AppTheme.typography.h4
        CompositionLocalProvider(LocalTextStyle provides textStyle) {
            SnackbarLayout(
                text = content,
                action = action,
                dismissAction = dismissAction,
                actionTextStyle = actionTextStyle,
                actionTextColor = actionContentColor,
                dismissActionColor = dismissActionContentColor,
            )
        }
    }
}

@Composable
private fun SnackbarLayout(
    text: @Composable () -> Unit,
    action: @Composable (() -> Unit)?,
    dismissAction: @Composable (() -> Unit)?,
    actionTextStyle: TextStyle,
    actionTextColor: Color,
    dismissActionColor: Color
) {
    val textTag = "text"
    val actionTag = "action"
    val dismissActionTag = "dismissAction"

    Layout(
        {
            Box(
                Modifier
                    .layoutId(textTag)
                    .padding(vertical = SnackbarVerticalPadding)
            ) { text() }
            if (action != null) {
                Box(Modifier.layoutId(actionTag)) {
                    CompositionLocalProvider(
                        LocalContentColor provides actionTextColor, LocalTextStyle provides actionTextStyle, content = action
                    )
                }
            }
            if (dismissAction != null) {
                Box(Modifier.layoutId(dismissActionTag)) {
                    CompositionLocalProvider(
                        LocalContentColor provides dismissActionColor, content = dismissAction
                    )
                }
            }
        }, modifier = Modifier.padding(
            start = HorizontalSpacing, end = if (dismissAction == null) HorizontalSpacingButtonSide else 0.dp
        )
    ) { measurables, constraints ->
        val containerWidth = min(constraints.maxWidth, ContainerMaxWidth.roundToPx())
        val actionButtonPlaceable = measurables.fastFirstOrNull { it.layoutId == actionTag }?.measure(constraints)
        val dismissButtonPlaceable = measurables.fastFirstOrNull { it.layoutId == dismissActionTag }?.measure(constraints)
        val actionButtonWidth = actionButtonPlaceable?.width ?: 0
        val actionButtonHeight = actionButtonPlaceable?.height ?: 0
        val dismissButtonWidth = dismissButtonPlaceable?.width ?: 0
        val dismissButtonHeight = dismissButtonPlaceable?.height ?: 0
        val extraSpacingWidth = if (dismissButtonWidth == 0) TextEndExtraSpacing.roundToPx() else 0
        val textMaxWidth =
            (containerWidth - actionButtonWidth - dismissButtonWidth - extraSpacingWidth).coerceAtLeast(constraints.minWidth)
        val textPlaceable =
            measurables.fastFirst { it.layoutId == textTag }.measure(constraints.copy(minHeight = 0, maxWidth = textMaxWidth))

        val firstTextBaseline = textPlaceable[FirstBaseline]
        val lastTextBaseline = textPlaceable[LastBaseline]
        val hasText = firstTextBaseline != AlignmentLine.Unspecified && lastTextBaseline != AlignmentLine.Unspecified
        val isOneLine = firstTextBaseline == lastTextBaseline || !hasText
        val dismissButtonPlaceX = containerWidth - dismissButtonWidth
        val actionButtonPlaceX = dismissButtonPlaceX - actionButtonWidth

        val textPlaceY: Int
        val containerHeight: Int
        val actionButtonPlaceY: Int
        if (isOneLine) {
            val minContainerHeight = SingleLineContainerHeight.roundToPx()
            val contentHeight = max(actionButtonHeight, dismissButtonHeight)
            containerHeight = max(minContainerHeight, contentHeight)
            textPlaceY = (containerHeight - textPlaceable.height) / 2
            actionButtonPlaceY = if (actionButtonPlaceable != null) {
                actionButtonPlaceable[FirstBaseline].let {
                    if (it != AlignmentLine.Unspecified) {
                        textPlaceY + firstTextBaseline - it
                    } else {
                        0
                    }
                }
            } else {
                0
            }
        } else {
            val baselineOffset = HeightToFirstLine.roundToPx()
            textPlaceY = baselineOffset - firstTextBaseline
            val minContainerHeight = TwoLinesContainerHeight.roundToPx()
            val contentHeight = textPlaceY + textPlaceable.height
            containerHeight = max(minContainerHeight, contentHeight)
            actionButtonPlaceY = if (actionButtonPlaceable != null) {
                (containerHeight - actionButtonPlaceable.height) / 2
            } else {
                0
            }
        }
        val dismissButtonPlaceY = if (dismissButtonPlaceable != null) {
            (containerHeight - dismissButtonPlaceable.height) / 2
        } else {
            0
        }

        layout(containerWidth, containerHeight) {
            textPlaceable.placeRelative(0, textPlaceY)
            dismissButtonPlaceable?.placeRelative(dismissButtonPlaceX, dismissButtonPlaceY)
            actionButtonPlaceable?.placeRelative(actionButtonPlaceX, actionButtonPlaceY)
        }
    }
}

internal object SnackbarDefaults {
    val ContainerMaxWidth = 600.dp
    val SingleLineContainerHeight = 56.dp
    val TwoLinesContainerHeight = 68.dp
    val HeightToFirstLine = 30.dp
    val HorizontalSpacing = 16.dp
    val HorizontalSpacingButtonSide = 8.dp
    val SnackbarVerticalPadding = 6.dp
    val TextEndExtraSpacing = 8.dp
    val ContainerElevation = 8.dp
    private val ContainerShape = RoundedCornerShape(12.dp)

    val shape: Shape
        @Composable get() = ContainerShape

    val color: Color
        @Composable get() = AppTheme.colors.primary

    val contentColor: Color
        @Composable get() = AppTheme.colors.onPrimary

    val actionColor: Color
        @Composable get() = AppTheme.colors.onPrimary

    val actionContentColor: Color
        @Composable get() = AppTheme.colors.primary

    val dismissActionContentColor: Color
        @Composable get() = AppTheme.colors.onPrimary

    @Composable
    fun ActionButton(
        text: String, onClick: () -> Unit
    ) {
        Box(
            Modifier
                .defaultMinSize(44.dp)
                .clip(RoundedCornerShape(4.dp))
                .clickable(onClick = onClick)
                .padding(8.dp), contentAlignment = Alignment.Center
        ) {
            Text(text = text, style = AppTheme.typography.button)
        }
    }
}

