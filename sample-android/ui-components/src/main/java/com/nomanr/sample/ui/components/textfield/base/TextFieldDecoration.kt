package com.nomanr.sample.ui.components.textfield.base

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.IntrinsicMeasurable
import androidx.compose.ui.layout.LayoutIdParentData
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.nomanr.sample.ui.AppTheme
import com.nomanr.sample.ui.LocalContentColor
import com.nomanr.sample.ui.foundation.ProvideContentColorTextStyle

@Composable
internal fun CommonDecorationBox(
    value: String,
    innerTextField: @Composable () -> Unit,
    visualTransformation: VisualTransformation,
    label: @Composable (() -> Unit)?,
    placeholder: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    prefix: @Composable (() -> Unit)? = null,
    suffix: @Composable (() -> Unit)? = null,
    supportingText: @Composable (() -> Unit)? = null,
    enabled: Boolean = true,
    isError: Boolean = false,
    interactionSource: InteractionSource,
    colors: TextFieldColors,
    contentPadding: PaddingValues,
    labelPadding: PaddingValues,
    supportingTextPadding: PaddingValues,
    leadingIconPadding: PaddingValues,
    trailingIconPadding: PaddingValues,
    container: @Composable () -> Unit,
) {
    val transformedText =
        remember(value, visualTransformation) {
            visualTransformation.filter(AnnotatedString(value))
        }.text.text

    val isFocused = interactionSource.collectIsFocusedAsState().value
    val inputState =
        when {
            isFocused -> InputPhase.Focused
            transformedText.isEmpty() -> InputPhase.UnfocusedEmpty
            else -> InputPhase.UnfocusedNotEmpty
        }

    TextFieldTransitionScope.Transition(
        inputState = inputState,
        showLabel = label != null,
    ) { placeholderAlphaProgress ->

        val labelColor = colors.labelColor(enabled = enabled, isError = isError, interactionSource = interactionSource).value
        val decoratedLabel: @Composable (() -> Unit)? =
            label?.let {
                @Composable {
                    Decoration(labelColor, AppTheme.typography.label1, it)
                }
            }

        val placeholderColor = colors.placeholderColor(enabled, isError, interactionSource).value
        val decoratedPlaceholder: @Composable ((Modifier) -> Unit)? =
            if (placeholder != null && transformedText.isEmpty() && placeholderAlphaProgress > 0f) {
                @Composable { modifier ->
                    Box(modifier.alpha(placeholderAlphaProgress)) {
                        Decoration(
                            contentColor = placeholderColor,
                            typography = AppTheme.typography.input,
                            content = placeholder,
                        )
                    }
                }
            } else {
                null
            }

        val prefixColor = colors.prefixColor(enabled, isError, interactionSource).value
        val decoratedPrefix: @Composable (() -> Unit)? =
            if (prefix != null) {
                @Composable {
                    Decoration(
                        contentColor = prefixColor,
                        typography = AppTheme.typography.input,
                        content = prefix,
                    )
                }
            } else {
                null
            }

        val suffixColor = colors.suffixColor(enabled, isError, interactionSource).value
        val decoratedSuffix: @Composable (() -> Unit)? =
            if (suffix != null) {
                @Composable {
                    Decoration(
                        contentColor = suffixColor,
                        typography = AppTheme.typography.input,
                        content = suffix,
                    )
                }
            } else {
                null
            }

        val leadingIconColor = colors.leadingIconColor(enabled, isError, interactionSource).value
        val decoratedLeading: @Composable (() -> Unit)? =
            leadingIcon?.let {
                @Composable {
                    Decoration(contentColor = leadingIconColor, content = it)
                }
            }

        val trailingIconColor = colors.trailingIconColor(enabled, isError, interactionSource).value
        val decoratedTrailing: @Composable (() -> Unit)? =
            trailingIcon?.let {
                @Composable {
                    Decoration(contentColor = trailingIconColor, content = it)
                }
            }

        val supportingTextColor = colors.supportingTextColor(enabled, isError, interactionSource).value
        val decoratedSupporting: @Composable (() -> Unit)? =
            supportingText?.let {
                @Composable {
                    Decoration(contentColor = supportingTextColor, typography = AppTheme.typography.body2, content = it)
                }
            }

        val containerWithId: @Composable () -> Unit = {
            Box(
                Modifier.layoutId(ContainerId),
                propagateMinConstraints = true,
            ) {
                container()
            }
        }

        TextFieldLayout(
            modifier = Modifier,
            textField = innerTextField,
            placeholder = decoratedPlaceholder,
            label = decoratedLabel,
            leading = decoratedLeading,
            trailing = decoratedTrailing,
            prefix = decoratedPrefix,
            suffix = decoratedSuffix,
            container = containerWithId,
            supporting = decoratedSupporting,
            paddingValues = contentPadding,
            labelPaddingValues = labelPadding,
            supportingPaddingValues = supportingTextPadding,
            leadingIconPaddingValues = leadingIconPadding,
            trailingIconPaddingValues = trailingIconPadding,
        )
    }
}

@Composable
internal fun Decoration(
    contentColor: Color,
    typography: TextStyle? = null,
    content: @Composable () -> Unit,
) {
    val contentWithColor: @Composable () -> Unit = @Composable {
        CompositionLocalProvider(
            LocalContentColor provides contentColor,
            content = content,
        )
    }

    if (typography != null) {
        ProvideContentColorTextStyle(contentColor, typography, content)
    } else {
        contentWithColor()
    }
}

private object TextFieldTransitionScope {
    @Composable
    fun Transition(
        inputState: InputPhase,
        showLabel: Boolean,
        content: @Composable (
            placeholderOpacity: Float,
        ) -> Unit,
    ) {
        val transition = updateTransition(inputState, label = "TextFieldInputState")

        val placeholderOpacity by transition.animateFloat(
            label = "PlaceholderOpacity",
            transitionSpec = {
                if (InputPhase.Focused isTransitioningTo InputPhase.UnfocusedEmpty) {
                    tween(
                        durationMillis = PlaceholderAnimationDelayOrDuration,
                        easing = LinearEasing,
                    )
                } else if (InputPhase.UnfocusedEmpty isTransitioningTo InputPhase.Focused ||
                    InputPhase.UnfocusedNotEmpty isTransitioningTo InputPhase.UnfocusedEmpty
                ) {
                    tween(
                        durationMillis = PlaceholderAnimationDuration,
                        delayMillis = PlaceholderAnimationDelayOrDuration,
                        easing = LinearEasing,
                    )
                } else {
                    spring()
                }
            },
        ) {
            when (it) {
                InputPhase.Focused -> 1f
                InputPhase.UnfocusedEmpty -> if (showLabel) 0f else 1f
                InputPhase.UnfocusedNotEmpty -> 0f
            }
        }

        content(
            placeholderOpacity,
        )
    }
}

@Composable
internal fun animateTextFieldBorderAsState(
    enabled: Boolean,
    isError: Boolean,
    interactionSource: InteractionSource,
    colors: TextFieldColors,
    borderThickness: Dp,
): State<BorderStroke> {
    val indicatorColor = colors.containerOutlineColor(enabled, isError, interactionSource)

    return rememberUpdatedState(
        BorderStroke(borderThickness, SolidColor(indicatorColor.value)),
    )
}

internal fun Modifier.containerOutline(
    enabled: Boolean,
    isError: Boolean,
    interactionSource: InteractionSource,
    colors: TextFieldColors,
    borderThickness: Dp,
    shape: Shape,
) = composed(
    inspectorInfo =
        debugInspectorInfo {
            name = "indicatorLine"
            properties["enabled"] = enabled
            properties["isError"] = isError
            properties["interactionSource"] = interactionSource
            properties["colors"] = colors
            properties["borderThickness"] = borderThickness
        },
) {
    val indicatorColor = colors.containerOutlineColor(enabled, isError, interactionSource)

    val stroke =
        rememberUpdatedState(
            BorderStroke(borderThickness, SolidColor(indicatorColor.value)),
        )
    this.then(Modifier.border(stroke.value, shape = shape))
}

internal fun Modifier.containerUnderline(
    enabled: Boolean,
    isError: Boolean,
    interactionSource: InteractionSource,
    colors: TextFieldColors,
    borderThickness: Dp,
) = composed {
    val indicatorColor = colors.containerOutlineColor(enabled, isError, interactionSource)

    this.then(
        Modifier.drawBehind {
            val strokeWidthPx = borderThickness.toPx()
            drawLine(
                color = indicatorColor.value,
                start = androidx.compose.ui.geometry.Offset(0f, size.height - strokeWidthPx / 2),
                end = androidx.compose.ui.geometry.Offset(size.width, size.height - strokeWidthPx / 2),
                strokeWidth = strokeWidthPx,
            )
        },
    )
}

private enum class InputPhase {
    Focused,
    UnfocusedEmpty,
    UnfocusedNotEmpty,
}

internal fun widthOrZero(placeable: Placeable?) = placeable?.width ?: 0

internal fun heightOrZero(placeable: Placeable?) = placeable?.height ?: 0

internal val IntrinsicMeasurable.layoutId: Any?
    get() = (parentData as? LayoutIdParentData)?.layoutId

internal const val TextFieldId = "TextField"
internal const val PlaceholderId = "Hint"
internal const val LabelId = "Label"
internal const val LeadingId = "Leading"
internal const val TrailingId = "Trailing"
internal const val PrefixId = "Prefix"
internal const val SuffixId = "Suffix"
internal const val SupportingId = "Supporting"
internal const val ContainerId = "Container"
internal val ZeroConstraints = Constraints(0, 0, 0, 0)

private const val PlaceholderAnimationDuration = 83
private const val PlaceholderAnimationDelayOrDuration = 67

internal val TextFieldMinHeight = 40.dp

internal val TextFieldHorizontalPadding = 16.dp
internal val TextFieldVerticalPadding = 10.dp
internal val HorizontalIconPadding = 12.dp
internal val LabelBottomPadding = 6.dp
internal val SupportingTopPadding = 4.dp
internal val PrefixSuffixTextPadding = 2.dp
internal val MinTextLineHeight = 24.dp
internal val MinSupportingTextLineHeight = 16.dp

internal val UnfocusedOutlineThickness = 2.dp
internal val FocusedOutlineThickness = 2.dp

internal val IconDefaultSizeModifier = Modifier.defaultMinSize(24.dp, 24.dp)
