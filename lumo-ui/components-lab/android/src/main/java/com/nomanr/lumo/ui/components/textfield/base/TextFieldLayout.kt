package com.nomanr.lumo.ui.components.textfield.base

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.IntrinsicMeasurable
import androidx.compose.ui.layout.IntrinsicMeasureScope
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Measurable
import androidx.compose.ui.layout.MeasurePolicy
import androidx.compose.ui.layout.MeasureResult
import androidx.compose.ui.layout.MeasureScope
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.coerceAtLeast
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.offset
import androidx.compose.ui.util.fastFirst
import androidx.compose.ui.util.fastFirstOrNull
import kotlin.math.max
import kotlin.math.roundToInt

@Composable
internal fun TextFieldLayout(
    modifier: Modifier,
    textField: @Composable () -> Unit,
    label: @Composable (() -> Unit)?,
    placeholder: @Composable ((Modifier) -> Unit)?,
    leading: @Composable (() -> Unit)?,
    trailing: @Composable (() -> Unit)?,
    prefix: @Composable (() -> Unit)?,
    suffix: @Composable (() -> Unit)?,
    container: @Composable () -> Unit,
    supporting: @Composable (() -> Unit)?,
    paddingValues: PaddingValues,
    labelPaddingValues: PaddingValues,
    supportingPaddingValues: PaddingValues,
    leadingIconPaddingValues: PaddingValues,
    trailingIconPaddingValues: PaddingValues,

    ) {
    val measurePolicy = remember(paddingValues) {
        TextFieldMeasurePolicy(paddingValues)
    }
    val layoutDirection = LocalLayoutDirection.current

    Layout(
        modifier = modifier, content = {
            if (label != null) {
                Box(
                    Modifier
                        .layoutId(LabelId)
                        .padding(
                            labelPaddingValues
                        )
                        .wrapContentHeight()
                ) { label() }
            }
            container()

            if (leading != null) {
                Box(
                    modifier = Modifier
                        .layoutId(LeadingId)
                        .padding(leadingIconPaddingValues)
                        .then(IconDefaultSizeModifier),
                    contentAlignment = Alignment.Center
                ) {
                    leading()
                }
            }
            if (trailing != null) {
                Box(
                    modifier = Modifier
                        .layoutId(TrailingId)
                        .padding(trailingIconPaddingValues)
                        .then(IconDefaultSizeModifier),
                    contentAlignment = Alignment.Center
                ) {
                    trailing()
                }
            }

            val startTextFieldPadding = paddingValues.calculateStartPadding(layoutDirection)
            val endTextFieldPadding = paddingValues.calculateEndPadding(layoutDirection)
            val startLeadingIconPadding = leadingIconPaddingValues.calculateStartPadding(layoutDirection)
            val endTrailingIconPadding = trailingIconPaddingValues.calculateEndPadding(layoutDirection)

            val startPadding = if (leading != null) {
                (startTextFieldPadding - startLeadingIconPadding).coerceAtLeast(0.dp)
            } else {
                startTextFieldPadding
            }
            val endPadding = if (trailing != null) {
                (endTextFieldPadding - endTrailingIconPadding).coerceAtLeast(0.dp)
            } else {
                endTextFieldPadding
            }

            if (prefix != null) {
                Box(
                    Modifier
                        .layoutId(PrefixId)
                        .heightIn(min = MinTextLineHeight)
                        .wrapContentHeight()
                        .padding(start = startPadding, end = PrefixSuffixTextPadding)
                ) {
                    prefix()
                }
            }
            if (suffix != null) {
                Box(
                    Modifier
                        .layoutId(SuffixId)
                        .heightIn(min = MinTextLineHeight)
                        .wrapContentHeight()
                        .padding(start = PrefixSuffixTextPadding, end = endPadding)
                ) {
                    suffix()
                }
            }

            val textPadding = Modifier
                .heightIn(min = MinTextLineHeight)
                .wrapContentHeight()
                .padding(
                    start = if (prefix == null) startPadding else 0.dp,
                    end = if (suffix == null) endPadding else 0.dp,
                )

            if (placeholder != null) {
                placeholder(
                    Modifier
                        .layoutId(PlaceholderId)
                        .then(textPadding)
                )
            }
            Box(
                modifier = Modifier
                    .layoutId(TextFieldId)
                    .then(textPadding),
                propagateMinConstraints = true,
            ) {
                textField()
            }

            if (supporting != null) {
                Box(
                    Modifier
                        .layoutId(SupportingId)
                        .heightIn(min = MinSupportingTextLineHeight)
                        .wrapContentHeight()
                        .padding(supportingPaddingValues)
                ) { supporting() }
            }
        }, measurePolicy = measurePolicy
    )
}

private class TextFieldMeasurePolicy(
    private val paddingValues: PaddingValues
) : MeasurePolicy {
    override fun MeasureScope.measure(
        measurables: List<Measurable>, constraints: Constraints
    ): MeasureResult {
        val topPaddingValue = paddingValues.calculateTopPadding().roundToPx()
        val bottomPaddingValue = paddingValues.calculateBottomPadding().roundToPx()

        var occupiedSpaceHorizontally = 0
        var occupiedSpaceVertically = 0

        val looseConstraints = constraints.copy(minWidth = 0, minHeight = 0)

        // measure leading icon
        val leadingPlaceable = measurables.fastFirstOrNull { it.layoutId == LeadingId }?.measure(looseConstraints)
        occupiedSpaceHorizontally += widthOrZero(leadingPlaceable)
        occupiedSpaceVertically = max(occupiedSpaceVertically, heightOrZero(leadingPlaceable))

        // measure trailing icon
        val trailingPlaceable = measurables.fastFirstOrNull { it.layoutId == TrailingId }
            ?.measure(looseConstraints.offset(horizontal = -occupiedSpaceHorizontally))
        occupiedSpaceHorizontally += widthOrZero(trailingPlaceable)
        occupiedSpaceVertically = max(occupiedSpaceVertically, heightOrZero(trailingPlaceable))

        // measure prefix
        val prefixPlaceable = measurables.fastFirstOrNull { it.layoutId == PrefixId }
            ?.measure(looseConstraints.offset(horizontal = -occupiedSpaceHorizontally))
        occupiedSpaceHorizontally += widthOrZero(prefixPlaceable)
        occupiedSpaceVertically = max(occupiedSpaceVertically, heightOrZero(prefixPlaceable))

        // measure suffix
        val suffixPlaceable = measurables.fastFirstOrNull { it.layoutId == SuffixId }
            ?.measure(looseConstraints.offset(horizontal = -occupiedSpaceHorizontally))
        occupiedSpaceHorizontally += widthOrZero(suffixPlaceable)
        occupiedSpaceVertically = max(occupiedSpaceVertically, heightOrZero(suffixPlaceable))

        // measure label
        val labelConstraints = looseConstraints.offset(
            vertical = -bottomPaddingValue, horizontal = -occupiedSpaceHorizontally
        )
        val labelPlaceable = measurables.fastFirstOrNull { it.layoutId == LabelId }?.measure(labelConstraints)

        val supportingMeasurable = measurables.fastFirstOrNull { it.layoutId == SupportingId }
        val supportingIntrinsicHeight = supportingMeasurable?.minIntrinsicHeight(constraints.minWidth) ?: 0

        val effectiveTopOffset = topPaddingValue + heightOrZero(labelPlaceable)
        val textFieldConstraints = constraints.copy(minHeight = 0).offset(
            vertical = -effectiveTopOffset - bottomPaddingValue - supportingIntrinsicHeight,
            horizontal = -occupiedSpaceHorizontally
        )

        val textFieldPlaceable = measurables.fastFirst { it.layoutId == TextFieldId }.measure(textFieldConstraints)

        // measure placeholder
        val placeholderConstraints = textFieldConstraints.copy(minWidth = 0)
        val placeholderPlaceable = measurables.fastFirstOrNull { it.layoutId == PlaceholderId }?.measure(placeholderConstraints)

        occupiedSpaceVertically = max(
            occupiedSpaceVertically,
            max(heightOrZero(textFieldPlaceable), heightOrZero(placeholderPlaceable)) + effectiveTopOffset + bottomPaddingValue
        )
        val width = calculateWidth(
            leadingWidth = widthOrZero(leadingPlaceable),
            trailingWidth = widthOrZero(trailingPlaceable),
            prefixWidth = widthOrZero(prefixPlaceable),
            suffixWidth = widthOrZero(suffixPlaceable),
            textFieldWidth = textFieldPlaceable.width,
            labelWidth = widthOrZero(labelPlaceable),
            placeholderWidth = widthOrZero(placeholderPlaceable),
            constraints = constraints,
        )

        // measure supporting text
        val supportingConstraints = looseConstraints.offset(
            vertical = -occupiedSpaceVertically
        ).copy(minHeight = 0, maxWidth = width)
        val supportingPlaceable = supportingMeasurable?.measure(supportingConstraints)
        val labelHeight = heightOrZero(labelPlaceable)
        val supportingHeight = heightOrZero(supportingPlaceable)

        val totalHeight = calculateHeight(
            textFieldHeight = textFieldPlaceable.height,
            labelHeight = labelHeight,
            leadingHeight = heightOrZero(leadingPlaceable),
            trailingHeight = heightOrZero(trailingPlaceable),
            prefixHeight = heightOrZero(prefixPlaceable),
            suffixHeight = heightOrZero(suffixPlaceable),
            placeholderHeight = heightOrZero(placeholderPlaceable),
            supportingHeight = supportingHeight,
            constraints = constraints,
            density = density,
            paddingValues = paddingValues,
        )

        val containerHeight = totalHeight - supportingHeight - labelHeight

        val containerPlaceable = measurables.fastFirst { it.layoutId == ContainerId }.measure(
            Constraints(
                minWidth = if (width != Constraints.Infinity) width else 0,
                maxWidth = width,
                minHeight = if (containerHeight != Constraints.Infinity) containerHeight else 0,
                maxHeight = containerHeight
            )
        )

        return layout(width, totalHeight) {
            placePlaceables(
                width = width,
                totalHeight = totalHeight,
                textFieldPlaceable = textFieldPlaceable,
                labelPlaceable = labelPlaceable,
                placeholderPlaceable = placeholderPlaceable,
                leadingPlaceable = leadingPlaceable,
                trailingPlaceable = trailingPlaceable,
                prefixPlaceable = prefixPlaceable,
                suffixPlaceable = suffixPlaceable,
                containerPlaceable = containerPlaceable,
                supportingPlaceable = supportingPlaceable,
                textPosition = topPaddingValue + heightOrZero(labelPlaceable),
            )
        }
    }

    override fun IntrinsicMeasureScope.maxIntrinsicHeight(
        measurables: List<IntrinsicMeasurable>, width: Int
    ): Int {
        return intrinsicHeight(measurables, width) { intrinsicMeasurable, w ->
            intrinsicMeasurable.maxIntrinsicHeight(w)
        }
    }

    override fun IntrinsicMeasureScope.minIntrinsicHeight(
        measurables: List<IntrinsicMeasurable>, width: Int
    ): Int {
        return intrinsicHeight(measurables, width) { intrinsicMeasurable, w ->
            intrinsicMeasurable.minIntrinsicHeight(w)
        }
    }

    override fun IntrinsicMeasureScope.maxIntrinsicWidth(
        measurables: List<IntrinsicMeasurable>, height: Int
    ): Int {
        return intrinsicWidth(measurables, height) { intrinsicMeasurable, h ->
            intrinsicMeasurable.maxIntrinsicWidth(h)
        }
    }

    override fun IntrinsicMeasureScope.minIntrinsicWidth(
        measurables: List<IntrinsicMeasurable>, height: Int
    ): Int {
        return intrinsicWidth(measurables, height) { intrinsicMeasurable, h ->
            intrinsicMeasurable.minIntrinsicWidth(h)
        }
    }

    private fun intrinsicWidth(
        measurables: List<IntrinsicMeasurable>, height: Int, intrinsicMeasurer: (IntrinsicMeasurable, Int) -> Int
    ): Int {
        val textFieldWidth = intrinsicMeasurer(measurables.fastFirst { it.layoutId == TextFieldId }, height)
        val labelWidth = measurables.fastFirstOrNull { it.layoutId == LabelId }?.let {
            intrinsicMeasurer(it, height)
        } ?: 0
        val trailingWidth = measurables.fastFirstOrNull { it.layoutId == TrailingId }?.let {
            intrinsicMeasurer(it, height)
        } ?: 0
        val prefixWidth = measurables.fastFirstOrNull { it.layoutId == PrefixId }?.let {
            intrinsicMeasurer(it, height)
        } ?: 0
        val suffixWidth = measurables.fastFirstOrNull { it.layoutId == SuffixId }?.let {
            intrinsicMeasurer(it, height)
        } ?: 0
        val leadingWidth = measurables.fastFirstOrNull { it.layoutId == LeadingId }?.let {
            intrinsicMeasurer(it, height)
        } ?: 0
        val placeholderWidth = measurables.fastFirstOrNull { it.layoutId == PlaceholderId }?.let {
            intrinsicMeasurer(it, height)
        } ?: 0
        return calculateWidth(
            leadingWidth = leadingWidth,
            trailingWidth = trailingWidth,
            prefixWidth = prefixWidth,
            suffixWidth = suffixWidth,
            textFieldWidth = textFieldWidth,
            labelWidth = labelWidth,
            placeholderWidth = placeholderWidth,
            constraints = ZeroConstraints
        )
    }

    private fun IntrinsicMeasureScope.intrinsicHeight(
        measurables: List<IntrinsicMeasurable>, width: Int, intrinsicMeasurer: (IntrinsicMeasurable, Int) -> Int
    ): Int {
        var remainingWidth = width
        val leadingHeight = measurables.fastFirstOrNull { it.layoutId == LeadingId }?.let {
            remainingWidth = remainingWidth.substractConstraintSafely(
                it.maxIntrinsicWidth(Constraints.Infinity)
            )
            intrinsicMeasurer(it, width)
        } ?: 0
        val trailingHeight = measurables.fastFirstOrNull { it.layoutId == TrailingId }?.let {
            remainingWidth = remainingWidth.substractConstraintSafely(
                it.maxIntrinsicWidth(Constraints.Infinity)
            )
            intrinsicMeasurer(it, width)
        } ?: 0
        val labelHeight = measurables.fastFirstOrNull { it.layoutId == LabelId }?.let {
            intrinsicMeasurer(it, remainingWidth)
        } ?: 0

        val prefixHeight = measurables.fastFirstOrNull { it.layoutId == PrefixId }?.let {
            val height = intrinsicMeasurer(it, remainingWidth)
            remainingWidth = remainingWidth.substractConstraintSafely(
                it.maxIntrinsicWidth(Constraints.Infinity)
            )
            height
        } ?: 0
        val suffixHeight = measurables.fastFirstOrNull { it.layoutId == SuffixId }?.let {
            val height = intrinsicMeasurer(it, remainingWidth)
            remainingWidth = remainingWidth.substractConstraintSafely(
                it.maxIntrinsicWidth(Constraints.Infinity)
            )
            height
        } ?: 0

        val textFieldHeight = intrinsicMeasurer(measurables.fastFirst { it.layoutId == TextFieldId }, remainingWidth)
        val placeholderHeight = measurables.fastFirstOrNull { it.layoutId == PlaceholderId }?.let {
            intrinsicMeasurer(it, remainingWidth)
        } ?: 0

        val supportingHeight = measurables.fastFirstOrNull { it.layoutId == SupportingId }?.let {
            intrinsicMeasurer(it, width)
        } ?: 0

        return calculateHeight(
            textFieldHeight = textFieldHeight,
            labelHeight = labelHeight,
            leadingHeight = leadingHeight,
            trailingHeight = trailingHeight,
            prefixHeight = prefixHeight,
            suffixHeight = suffixHeight,
            placeholderHeight = placeholderHeight,
            supportingHeight = supportingHeight,
            constraints = ZeroConstraints,
            density = density,
            paddingValues = paddingValues
        )
    }
}

private fun Int.substractConstraintSafely(from: Int): Int {
    if (this == Constraints.Infinity) {
        return this
    }
    return this - from
}

private fun calculateWidth(
    leadingWidth: Int,
    trailingWidth: Int,
    prefixWidth: Int,
    suffixWidth: Int,
    textFieldWidth: Int,
    labelWidth: Int,
    placeholderWidth: Int,
    constraints: Constraints
): Int {
    val affixTotalWidth = prefixWidth + suffixWidth
    val middleSection = maxOf(
        textFieldWidth + affixTotalWidth,
        placeholderWidth + affixTotalWidth,
        // Prefix/suffix does not get applied to label
        labelWidth,
    )
    val wrappedWidth = leadingWidth + middleSection + trailingWidth
    return max(wrappedWidth, constraints.minWidth)
}

private fun calculateHeight(
    textFieldHeight: Int,
    labelHeight: Int,
    leadingHeight: Int,
    trailingHeight: Int,
    prefixHeight: Int,
    suffixHeight: Int,
    placeholderHeight: Int,
    supportingHeight: Int,
    constraints: Constraints,
    density: Float,
    paddingValues: PaddingValues
): Int {
    val verticalPadding = density * (paddingValues.calculateTopPadding() + paddingValues.calculateBottomPadding()).value

    val inputFieldHeight = maxOf(
        textFieldHeight,
        placeholderHeight,
        prefixHeight,
        suffixHeight,
    )

    val layoutHeight = verticalPadding + labelHeight + inputFieldHeight + supportingHeight

    return max(
        constraints.minHeight, maxOf(
            leadingHeight, trailingHeight, layoutHeight.roundToInt()
        )
    )
}

private fun Placeable.PlacementScope.placePlaceables(
    width: Int,
    totalHeight: Int,
    textFieldPlaceable: Placeable,
    labelPlaceable: Placeable?,
    placeholderPlaceable: Placeable?,
    leadingPlaceable: Placeable?,
    trailingPlaceable: Placeable?,
    prefixPlaceable: Placeable?,
    suffixPlaceable: Placeable?,
    containerPlaceable: Placeable,
    supportingPlaceable: Placeable?,
    textPosition: Int,
) {
    val containerHeight = totalHeight - heightOrZero(labelPlaceable) - heightOrZero(supportingPlaceable)
    val labelHeight = heightOrZero(labelPlaceable)

    containerPlaceable.place(0, heightOrZero(labelPlaceable))

    labelPlaceable?.placeRelative(0, 0)

    leadingPlaceable?.placeRelative(
        0, Alignment.CenterVertically.align(leadingPlaceable.height, containerHeight) + labelHeight
    )
    trailingPlaceable?.placeRelative(
        width - trailingPlaceable.width, Alignment.CenterVertically.align(trailingPlaceable.height, containerHeight) + labelHeight
    )

    prefixPlaceable?.placeRelative(widthOrZero(leadingPlaceable), textPosition)
    suffixPlaceable?.placeRelative(
        width - widthOrZero(trailingPlaceable) - suffixPlaceable.width,
        textPosition,
    )

    val textHorizontalPosition = widthOrZero(leadingPlaceable) + widthOrZero(prefixPlaceable)
    textFieldPlaceable.placeRelative(textHorizontalPosition, textPosition)
    placeholderPlaceable?.placeRelative(textHorizontalPosition, textPosition)

    supportingPlaceable?.placeRelative(0, totalHeight - heightOrZero(supportingPlaceable))
}
