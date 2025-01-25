package com.nomanr.lumo.ui.components.native

import android.content.res.Configuration
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.Transition
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.rememberTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BasicTooltipBox
import androidx.compose.foundation.BasicTooltipDefaults
import androidx.compose.foundation.BasicTooltipState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.MutatePriority
import androidx.compose.foundation.MutatorMutex
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.CacheDrawScope
import androidx.compose.ui.draw.DrawResult
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntRect
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.isSpecified
import androidx.compose.ui.window.PopupPositionProvider
import com.nomanr.lumo.ui.AppTheme
import com.nomanr.lumo.ui.components.Surface
import com.nomanr.lumo.ui.components.Text
import kotlinx.coroutines.CancellableContinuation
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withTimeout

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TooltipBox(
    modifier: Modifier = Modifier,
    positionProvider: PopupPositionProvider = rememberTooltipPositionProvider(),
    tooltip: @Composable TooltipScope.() -> Unit,
    state: TooltipState = rememberTooltipState(),
    focusable: Boolean = true,
    enableUserInput: Boolean = true,
    content: @Composable () -> Unit,
) {
    val transition = rememberTransition(state.transition, label = "tooltip transition")
    val anchorBounds: MutableState<LayoutCoordinates?> = remember { mutableStateOf(null) }
    val scope = remember { DefaultTooltipScope { anchorBounds.value } }

    val wrappedContent: @Composable () -> Unit = {
        Box(modifier = Modifier.onGloballyPositioned { anchorBounds.value = it }) {
            content()
        }
    }

    BasicTooltipBox(
        positionProvider = positionProvider,
        tooltip = {
            Box(Modifier.animateTooltip(transition)) {
                scope.tooltip()
            }
        },
        focusable = focusable,
        enableUserInput = enableUserInput,
        state = state,
        modifier = modifier,
        content = wrappedContent,
    )
}

@Composable
fun TooltipScope.Tooltip(
    modifier: Modifier = Modifier,
    caretSize: DpSize = TooltipDefaults.CaretSize,
    maxWidth: Dp = TooltipDefaults.MaxWidth,
    shape: Shape = TooltipDefaults.Shape,
    containerColor: Color = AppTheme.colors.surface,
    shadowElevation: Dp = TooltipDefaults.ShadowElevation,
    content: @Composable () -> Unit,
) {
    val drawCaretModifier =
        if (caretSize.isSpecified) {
            val density = LocalDensity.current
            val configuration = LocalConfiguration.current
            Modifier
                .drawCaret { anchorLayoutCoordinates ->
                    drawCaretWithPath(
                        density,
                        configuration,
                        containerColor,
                        caretSize,
                        anchorLayoutCoordinates,
                    )
                }
                .then(modifier)
        } else {
            modifier
        }

    Surface(
        modifier = drawCaretModifier,
        shape = shape,
        color = containerColor,
        shadowElevation = shadowElevation,
    ) {
        Box(
            modifier =
                Modifier
                    .sizeIn(
                        minWidth = TooltipDefaults.MinWidth,
                        maxWidth = maxWidth,
                        minHeight = TooltipDefaults.MinHeight,
                    )
                    .padding(TooltipDefaults.ContentPadding),
        ) {
            content()
        }
    }
}

sealed interface TooltipScope {
    fun Modifier.drawCaret(draw: CacheDrawScope.(LayoutCoordinates?) -> DrawResult): Modifier
}

internal class DefaultTooltipScope(val getAnchorBounds: () -> LayoutCoordinates?) : TooltipScope {
    override fun Modifier.drawCaret(
        draw: CacheDrawScope.(LayoutCoordinates?) -> DrawResult,
    ): Modifier = this.drawWithCache { draw(getAnchorBounds()) }
}

@OptIn(ExperimentalFoundationApi::class)
interface TooltipState : BasicTooltipState {
    val transition: MutableTransitionState<Boolean>
}

@Stable
private class TooltipStateImpl(
    initialIsVisible: Boolean,
    override val isPersistent: Boolean,
    private val mutatorMutex: MutatorMutex,
) : TooltipState {
    override val transition: MutableTransitionState<Boolean> = MutableTransitionState(initialIsVisible)
    private var job: CancellableContinuation<Unit>? = null

    override val isVisible: Boolean
        get() = transition.currentState || transition.targetState

    @OptIn(ExperimentalFoundationApi::class)
    override suspend fun show(mutatePriority: MutatePriority) {
        val cancellableShow: suspend () -> Unit = {
            suspendCancellableCoroutine { continuation ->
                transition.targetState = true
                job = continuation
            }
        }

        mutatorMutex.mutate(mutatePriority) {
            try {
                if (isPersistent) {
                    cancellableShow()
                } else {
                    withTimeout(BasicTooltipDefaults.TooltipDuration) { cancellableShow() }
                }
            } finally {
                if (mutatePriority != MutatePriority.PreventUserInput) {
                    dismiss()
                }
            }
        }
    }

    override fun dismiss() {
        transition.targetState = false
    }

    override fun onDispose() {
        job?.cancel()
    }
}

object TooltipDefaults {
    val CaretSize = DpSize(12.dp, 6.dp)
    val MaxWidth = 300.dp
    val ShadowElevation = 4.dp
    val SpacingBetweenTooltipAndAnchor = 4.dp
    val MinHeight = 24.dp
    val MinWidth = 40.dp
    val PlainTooltipVerticalPadding = 4.dp
    val PlainTooltipHorizontalPadding = 8.dp
    val ContentPadding = PaddingValues(PlainTooltipHorizontalPadding, PlainTooltipVerticalPadding)
    val Shape = RoundedCornerShape(4.dp)
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun rememberTooltipState(
    initialIsVisible: Boolean = false,
    isPersistent: Boolean = false,
    mutatorMutex: MutatorMutex = BasicTooltipDefaults.GlobalMutatorMutex,
): TooltipState =
    remember(isPersistent, mutatorMutex) {
        TooltipStateImpl(
            initialIsVisible = initialIsVisible,
            isPersistent = isPersistent,
            mutatorMutex = mutatorMutex,
        )
    }

@Composable
fun rememberTooltipPositionProvider(
    spacingBetweenTooltipAndAnchor: Dp = TooltipDefaults.SpacingBetweenTooltipAndAnchor,
): PopupPositionProvider {
    val tooltipAnchorSpacing =
        with(LocalDensity.current) {
            spacingBetweenTooltipAndAnchor.roundToPx()
        }
    return remember(tooltipAnchorSpacing) {
        object : PopupPositionProvider {
            override fun calculatePosition(
                anchorBounds: IntRect,
                windowSize: IntSize,
                layoutDirection: LayoutDirection,
                popupContentSize: IntSize,
            ): IntOffset {
                var x = anchorBounds.left + (anchorBounds.width - popupContentSize.width) / 2
                if (x < 0) {
                    x = anchorBounds.left
                } else if (x + popupContentSize.width > windowSize.width) {
                    x = anchorBounds.right - popupContentSize.width
                }

                var y = anchorBounds.top - popupContentSize.height - tooltipAnchorSpacing
                if (y < 0) y = anchorBounds.bottom + tooltipAnchorSpacing
                return IntOffset(x, y)
            }
        }
    }
}

internal fun Modifier.animateTooltip(transition: Transition<Boolean>): Modifier =
    composed(
        inspectorInfo =
            debugInspectorInfo {
                name = "animateTooltip"
                properties["transition"] = transition
            },
    ) {
        val inOutScaleAnimationSpec = tween<Float>(durationMillis = 100, easing = FastOutLinearInEasing)
        val inOutAlphaAnimationSpec = tween<Float>(durationMillis = 50, easing = FastOutSlowInEasing)

        val scale by transition.animateFloat(
            transitionSpec = { inOutScaleAnimationSpec },
            label = "tooltip transition: scaling",
        ) {
            if (it) 1f else 0.8f
        }

        val alpha by transition.animateFloat(
            transitionSpec = { inOutAlphaAnimationSpec },
            label = "tooltip transition: alpha",
        ) {
            if (it) 1f else 0f
        }

        this.graphicsLayer(scaleX = scale, scaleY = scale, alpha = alpha)
    }

private fun CacheDrawScope.drawCaretWithPath(
    density: Density,
    configuration: Configuration,
    containerColor: Color,
    caretSize: DpSize,
    anchorLayoutCoordinates: LayoutCoordinates?,
): DrawResult {
    val path = Path()

    if (anchorLayoutCoordinates != null) {
        val caretHeightPx: Int
        val caretWidthPx: Int
        val screenWidthPx: Int
        val tooltipAnchorSpacing: Int
        with(density) {
            caretHeightPx = caretSize.height.roundToPx()
            caretWidthPx = caretSize.width.roundToPx()
            screenWidthPx = configuration.screenWidthDp.dp.roundToPx()
            tooltipAnchorSpacing = TooltipDefaults.SpacingBetweenTooltipAndAnchor.roundToPx()
        }

        val anchorBounds = anchorLayoutCoordinates.boundsInWindow()
        val anchorLeft = anchorBounds.left
        val anchorRight = anchorBounds.right
        val anchorTop = anchorBounds.top
        val anchorMid = (anchorRight + anchorLeft) / 2
        val anchorWidth = anchorRight - anchorLeft
        val tooltipWidth = this.size.width
        val tooltipHeight = this.size.height
        val isCaretTop = anchorTop - tooltipHeight - tooltipAnchorSpacing < 0
        val caretY = if (isCaretTop) 0f else tooltipHeight

        var position: Offset =
            if (anchorLeft - tooltipWidth / 2 + anchorWidth / 2 <= 0) {
                Offset(anchorMid, caretY)
            } else if (anchorRight + tooltipWidth / 2 - anchorWidth / 2 >= screenWidthPx) {
                val anchorMidFromRightScreenEdge = screenWidthPx - anchorMid
                Offset(tooltipWidth - anchorMidFromRightScreenEdge, caretY)
            } else {
                Offset(tooltipWidth / 2, caretY)
            }
        if (anchorMid - tooltipWidth / 2 < 0) {
            position = Offset(anchorMid - anchorLeft, caretY)
        } else if (anchorMid + tooltipWidth / 2 > screenWidthPx) {
            position = Offset(anchorMid - (anchorRight - tooltipWidth), caretY)
        }

        if (isCaretTop) {
            path.moveTo(position.x, position.y)
            path.lineTo(position.x + caretWidthPx / 2, position.y)
            path.lineTo(position.x, position.y - caretHeightPx)
            path.lineTo(position.x - caretWidthPx / 2, position.y)
            path.close()
        } else {
            path.moveTo(position.x, position.y)
            path.lineTo(position.x + caretWidthPx / 2, position.y)
            path.lineTo(position.x, position.y + caretHeightPx)
            path.lineTo(position.x - caretWidthPx / 2, position.y)
            path.close()
        }
    }

    return onDrawWithContent {
        if (anchorLayoutCoordinates != null) {
            drawContent()
            drawPath(path = path, color = containerColor)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PlainTooltipWithCaret() {
    val tooltipState = rememberTooltipState(isPersistent = true)
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        TooltipBox(
            tooltip = {
                Tooltip {
                    Text(
                        text = "This is a tooltip",
                        textAlign = TextAlign.Center,
                    )
                }
            },
            state = tooltipState,
        ) {
            Box(
                modifier =
                    Modifier
                        .size(40.dp)
                        .background(Color.Blue),
            )
        }
    }
}
