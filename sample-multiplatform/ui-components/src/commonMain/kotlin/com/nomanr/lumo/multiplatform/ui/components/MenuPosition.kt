package com.nomanr.lumo.multiplatform.ui.components

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntRect
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.window.PopupPositionProvider

@Stable
internal object MenuPosition {
    @Stable
    fun interface Vertical {
        fun position(
            anchorBounds: IntRect,
            windowSize: IntSize,
            menuHeight: Int,
        ): Int
    }

    @Stable
    fun interface Horizontal {
        fun position(
            anchorBounds: IntRect,
            windowSize: IntSize,
            menuWidth: Int,
            layoutDirection: LayoutDirection,
        ): Int
    }

    fun startToAnchorStart(offset: Int = 0): Horizontal =
        AnchorAlignmentOffsetPosition.Horizontal(
            menuAlignment = Alignment.Start,
            anchorAlignment = Alignment.Start,
            offset = offset,
        )

    fun endToAnchorEnd(offset: Int = 0): Horizontal =
        AnchorAlignmentOffsetPosition.Horizontal(
            menuAlignment = Alignment.End,
            anchorAlignment = Alignment.End,
            offset = offset,
        )

    fun leftToWindowLeft(margin: Int = 0): Horizontal =
        WindowAlignmentMarginPosition.Horizontal(
            alignment = AbsoluteAlignment.Left,
            margin = margin,
        )

    fun rightToWindowRight(margin: Int = 0): Horizontal =
        WindowAlignmentMarginPosition.Horizontal(
            alignment = AbsoluteAlignment.Right,
            margin = margin,
        )

    fun topToAnchorBottom(offset: Int = 0): Vertical =
        AnchorAlignmentOffsetPosition.Vertical(
            menuAlignment = Alignment.Top,
            anchorAlignment = Alignment.Bottom,
            offset = offset,
        )

    fun bottomToAnchorTop(offset: Int = 0): Vertical =
        AnchorAlignmentOffsetPosition.Vertical(
            menuAlignment = Alignment.Bottom,
            anchorAlignment = Alignment.Top,
            offset = offset,
        )

    fun centerToAnchorTop(offset: Int = 0): Vertical =
        AnchorAlignmentOffsetPosition.Vertical(
            menuAlignment = Alignment.CenterVertically,
            anchorAlignment = Alignment.Top,
            offset = offset,
        )

    fun topToWindowTop(margin: Int = 0): Vertical =
        WindowAlignmentMarginPosition.Vertical(
            alignment = Alignment.Top,
            margin = margin,
        )

    fun bottomToWindowBottom(margin: Int = 0): Vertical =
        WindowAlignmentMarginPosition.Vertical(
            alignment = Alignment.Bottom,
            margin = margin,
        )
}

@Immutable
internal object AnchorAlignmentOffsetPosition {
    @Immutable
    data class Horizontal(
        private val menuAlignment: Alignment.Horizontal,
        private val anchorAlignment: Alignment.Horizontal,
        private val offset: Int,
    ) : MenuPosition.Horizontal {
        override fun position(
            anchorBounds: IntRect,
            windowSize: IntSize,
            menuWidth: Int,
            layoutDirection: LayoutDirection,
        ): Int {
            val anchorAlignmentOffset =
                anchorAlignment.align(
                    size = 0,
                    space = anchorBounds.width,
                    layoutDirection = layoutDirection,
                )
            val menuAlignmentOffset =
                -menuAlignment.align(
                    size = 0,
                    space = menuWidth,
                    layoutDirection,
                )
            val resolvedOffset = if (layoutDirection == LayoutDirection.Ltr) offset else -offset
            return anchorBounds.left + anchorAlignmentOffset + menuAlignmentOffset + resolvedOffset
        }
    }

    @Immutable
    data class Vertical(
        private val menuAlignment: Alignment.Vertical,
        private val anchorAlignment: Alignment.Vertical,
        private val offset: Int,
    ) : MenuPosition.Vertical {
        override fun position(
            anchorBounds: IntRect,
            windowSize: IntSize,
            menuHeight: Int,
        ): Int {
            val anchorAlignmentOffset =
                anchorAlignment.align(
                    size = 0,
                    space = anchorBounds.height,
                )
            val menuAlignmentOffset =
                -menuAlignment.align(
                    size = 0,
                    space = menuHeight,
                )
            return anchorBounds.top + anchorAlignmentOffset + menuAlignmentOffset + offset
        }
    }
}

@Immutable
internal object WindowAlignmentMarginPosition {
    @Immutable
    data class Horizontal(
        private val alignment: Alignment.Horizontal,
        private val margin: Int,
    ) : MenuPosition.Horizontal {
        override fun position(
            anchorBounds: IntRect,
            windowSize: IntSize,
            menuWidth: Int,
            layoutDirection: LayoutDirection,
        ): Int {
            if (menuWidth >= windowSize.width - 2 * margin) {
                return Alignment.CenterHorizontally.align(
                    size = menuWidth,
                    space = windowSize.width,
                    layoutDirection = layoutDirection,
                )
            }
            val x =
                alignment.align(
                    size = menuWidth,
                    space = windowSize.width,
                    layoutDirection = layoutDirection,
                )
            return x.coerceIn(margin, windowSize.width - margin - menuWidth)
        }
    }

    @Immutable
    data class Vertical(
        private val alignment: Alignment.Vertical,
        private val margin: Int,
    ) : MenuPosition.Vertical {
        override fun position(
            anchorBounds: IntRect,
            windowSize: IntSize,
            menuHeight: Int,
        ): Int {
            if (menuHeight >= windowSize.height - 2 * margin) {
                return Alignment.CenterVertically.align(
                    size = menuHeight,
                    space = windowSize.height,
                )
            }
            val y =
                alignment.align(
                    size = menuHeight,
                    space = windowSize.height,
                )
            return y.coerceIn(margin, windowSize.height - margin - menuHeight)
        }
    }
}

/** Calculates the position of a Material [DropdownMenu]. */
@Immutable
internal data class DropdownMenuPositionProvider(
    val contentOffset: DpOffset,
    val density: Density,
    val verticalMargin: Int = with(density) { MenuVerticalMargin.roundToPx() },
    val onPositionCalculated: (anchorBounds: IntRect, menuBounds: IntRect) -> Unit = { _, _ -> }
) : PopupPositionProvider {
    // Horizontal position
    private val startToAnchorStart: MenuPosition.Horizontal
    private val endToAnchorEnd: MenuPosition.Horizontal
    private val leftToWindowLeft: MenuPosition.Horizontal
    private val rightToWindowRight: MenuPosition.Horizontal
    // Vertical position
    private val topToAnchorBottom: MenuPosition.Vertical
    private val bottomToAnchorTop: MenuPosition.Vertical
    private val centerToAnchorTop: MenuPosition.Vertical
    private val topToWindowTop: MenuPosition.Vertical
    private val bottomToWindowBottom: MenuPosition.Vertical

    init {
        // Horizontal position
        val contentOffsetX = with(density) { contentOffset.x.roundToPx() }
        startToAnchorStart = MenuPosition.startToAnchorStart(offset = contentOffsetX)
        endToAnchorEnd = MenuPosition.endToAnchorEnd(offset = contentOffsetX)
        leftToWindowLeft = MenuPosition.leftToWindowLeft(margin = 0)
        rightToWindowRight = MenuPosition.rightToWindowRight(margin = 0)
        // Vertical position
        val contentOffsetY = with(density) { contentOffset.y.roundToPx() }
        topToAnchorBottom = MenuPosition.topToAnchorBottom(offset = contentOffsetY)
        bottomToAnchorTop = MenuPosition.bottomToAnchorTop(offset = contentOffsetY)
        centerToAnchorTop = MenuPosition.centerToAnchorTop(offset = contentOffsetY)
        topToWindowTop = MenuPosition.topToWindowTop(margin = verticalMargin)
        bottomToWindowBottom = MenuPosition.bottomToWindowBottom(margin = verticalMargin)
    }

    override fun calculatePosition(
        anchorBounds: IntRect,
        windowSize: IntSize,
        layoutDirection: LayoutDirection,
        popupContentSize: IntSize
    ): IntOffset {
        val xCandidates =
            listOf(
                startToAnchorStart,
                endToAnchorEnd,
                if (anchorBounds.center.x < windowSize.width / 2) {
                    leftToWindowLeft
                } else {
                    rightToWindowRight
                }
            )
        var x = 0
        for (index in xCandidates.indices) {
            val xCandidate =
                xCandidates[index].position(
                    anchorBounds = anchorBounds,
                    windowSize = windowSize,
                    menuWidth = popupContentSize.width,
                    layoutDirection = layoutDirection
                )
            if (
                index == xCandidates.lastIndex ||
                (xCandidate >= 0 && xCandidate + popupContentSize.width <= windowSize.width)
            ) {
                x = xCandidate
                break
            }
        }

        val yCandidates =
            listOf(
                topToAnchorBottom,
                bottomToAnchorTop,
                centerToAnchorTop,
                if (anchorBounds.center.y < windowSize.height / 2) {
                    topToWindowTop
                } else {
                    bottomToWindowBottom
                }
            )
        var y = 0
        for (index in yCandidates.indices) {
            val yCandidate =
                yCandidates[index].position(
                    anchorBounds = anchorBounds,
                    windowSize = windowSize,
                    menuHeight = popupContentSize.height
                )
            if (
                index == yCandidates.lastIndex ||
                (yCandidate >= verticalMargin &&
                        yCandidate + popupContentSize.height <= windowSize.height - verticalMargin)
            ) {
                y = yCandidate
                break
            }
        }

        val menuOffset = IntOffset(x, y)
        onPositionCalculated(
            /* anchorBounds = */ anchorBounds,
            /* menuBounds = */ IntRect(offset = menuOffset, size = popupContentSize)
        )
        return menuOffset
    }
}