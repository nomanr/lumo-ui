package com.nomanr.lumo.ui.components.topbar

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.DecayAnimationSpec
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.rememberSplineBasedDecay
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.LocalDensity
import org.jetbrains.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nomanr.lumo.ui.AppTheme
import com.nomanr.lumo.ui.LocalContentColor
import com.nomanr.lumo.ui.components.Surface
import com.nomanr.lumo.ui.components.Text
import com.nomanr.lumo.ui.components.topbar.TopBarDefaults.TopBarHeight
import com.nomanr.lumo.ui.contentColorFor
import com.nomanr.lumo.ui.foundation.systemBarsForVisualComponents

@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    scrollBehavior: TopBarScrollBehavior? = null,
    colors: TopBarColors = TopBarDefaults.topBarColors(),
    windowInsets: WindowInsets? = TopBarDefaults.windowInsets,
    content: @Composable () -> Unit
) {

    TopBarLayout(
        modifier = modifier, scrollBehavior = scrollBehavior, colors = colors, windowInsets = windowInsets
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(TopBarHeight),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            content()
        }
    }
}

@Composable
internal fun TopBarLayout(
    modifier: Modifier = Modifier,
    colors: TopBarColors = TopBarDefaults.topBarColors(),
    windowInsets: WindowInsets? = TopBarDefaults.windowInsets,
    scrollBehavior: TopBarScrollBehavior? = null,
    content: @Composable () -> Unit,
) {

    val height = remember { mutableIntStateOf(0) }

    val density = LocalDensity.current

    val windowInsetsPaddingValues = windowInsets?.asPaddingValues(density) ?: PaddingValues()

    val heightOffsetLimit = -height.intValue.toFloat()
    SideEffect {
        if (scrollBehavior?.state?.heightOffsetLimit != heightOffsetLimit) {
            scrollBehavior?.state?.heightOffsetLimit = heightOffsetLimit
        }
    }

    val colorTransitionFraction = scrollBehavior?.state?.overlappedFraction ?: 0f
    val fraction = if (colorTransitionFraction > 0) colorTransitionFraction else 0f
    val topBarContainerColor by animateColorAsState(
        targetValue = colors.containerColor(fraction),
        animationSpec = spring(stiffness = Spring.StiffnessMediumLow),
        label = "animate container color",
    )
    val topBarContentColor by animateColorAsState(
        targetValue = colors.contentColor(fraction),
        animationSpec = spring(stiffness = Spring.StiffnessMediumLow),
        label = "animate content color",
    )

    val topBarDragModifier = if (scrollBehavior != null && !scrollBehavior.isPinned) {
        Modifier.draggable(orientation = Orientation.Vertical, state = rememberDraggableState {
            scrollBehavior.state.heightOffset += it
        }, onDragStopped = { velocity ->
            settleBar(
                scrollBehavior.state, velocity, scrollBehavior.flingAnimationSpec, scrollBehavior.snapAnimationSpec
            )
        })
    } else {
        Modifier
    }

    // calculating based on scrolling behaviour
    val dynamicHeight = height.intValue + (scrollBehavior?.state?.heightOffset ?: 0).toInt()


    Surface(modifier = modifier.then(topBarDragModifier), color = topBarContainerColor) {
        CompositionLocalProvider(LocalContentColor provides topBarContentColor) {
            Layout(
                content = content, modifier = Modifier
                    .padding(windowInsetsPaddingValues)
                    .clipToBounds()
            ) { measurables, constraints ->
                val placeables = measurables.map { measurable ->
                    measurable.measure(constraints)
                }

                if (placeables.isEmpty() || placeables.size > 1) {
                    throw IllegalStateException("TopBar expects one child!")
                }

                if (height.intValue == 0) height.intValue = placeables.first().height

                layout(constraints.maxWidth, dynamicHeight) {
                    // Expects only one child, a layout with topbar content
                    placeables.first().place(0, dynamicHeight - height.intValue)
                }
            }
        }
    }
}

object TopBarDefaults {
    val TopBarHeight = 56.dp

    @Composable
    fun topBarColors(
        containerColor: Color = AppTheme.colors.background,
        scrolledContainerColor: Color = AppTheme.colors.background,
    ): TopBarColors = TopBarColors(
        containerColor,
        scrolledContainerColor,
    )

    val windowInsets: WindowInsets
        @Composable get() = WindowInsets.systemBarsForVisualComponents.only(
            WindowInsetsSides.Horizontal + WindowInsetsSides.Top
        )

    @Composable
    fun pinnedScrollBehavior(
        state: TopBarState = rememberTopBarState(), canScroll: () -> Boolean = { true }
    ): TopBarScrollBehavior = PinnedScrollBehavior(state = state, canScroll = canScroll)

    @Composable
    fun enterAlwaysScrollBehavior(
        state: TopBarState = rememberTopBarState(),
        canScroll: () -> Boolean = { true },
        snapAnimationSpec: AnimationSpec<Float>? = spring(stiffness = Spring.StiffnessMediumLow),
        flingAnimationSpec: DecayAnimationSpec<Float>? = rememberSplineBasedDecay()
    ): TopBarScrollBehavior = EnterAlwaysScrollBehavior(
        state = state, snapAnimationSpec = snapAnimationSpec, flingAnimationSpec = flingAnimationSpec, canScroll = canScroll
    )

    @Composable
    fun exitUntilCollapsedScrollBehavior(
        state: TopBarState = rememberTopBarState(),
        canScroll: () -> Boolean = { true },
        snapAnimationSpec: AnimationSpec<Float>? = spring(stiffness = Spring.StiffnessMediumLow),
        flingAnimationSpec: DecayAnimationSpec<Float>? = rememberSplineBasedDecay()
    ): TopBarScrollBehavior = ExitUntilCollapsedScrollBehavior(
        state = state, snapAnimationSpec = snapAnimationSpec, flingAnimationSpec = flingAnimationSpec, canScroll = canScroll
    )
}

@Composable
fun rememberTopBarState(
    initialHeightOffsetLimit: Float = -Float.MAX_VALUE, initialHeightOffset: Float = 0f, initialContentOffset: Float = 0f
): TopBarState {
    return rememberSaveable(saver = TopBarState.Saver) {
        TopBarState(
            initialHeightOffsetLimit, initialHeightOffset, initialContentOffset
        )
    }
}

@Stable
class TopBarState(
    initialHeightOffsetLimit: Float, initialHeightOffset: Float, initialContentOffset: Float
) {

    var heightOffsetLimit by mutableFloatStateOf(initialHeightOffsetLimit)

    var heightOffset: Float
        get() = _heightOffset.floatValue
        set(newOffset) {
            _heightOffset.floatValue = newOffset.coerceIn(
                minimumValue = heightOffsetLimit, maximumValue = 0f
            )
        }

    var contentOffset by mutableStateOf(initialContentOffset)

    val collapsedFraction: Float
        get() = if (heightOffsetLimit != 0f) {
            heightOffset / heightOffsetLimit
        } else {
            0f
        }

    val overlappedFraction: Float
        get() = if (heightOffsetLimit != 0f) {
            1 - ((heightOffsetLimit - contentOffset).coerceIn(
                minimumValue = heightOffsetLimit, maximumValue = 0f
            ) / heightOffsetLimit)
        } else {
            0f
        }

    companion object {
        /**
         * The default [Saver] implementation for [TopBarState].
         */
        val Saver: Saver<TopBarState, *> = listSaver(save = {
            listOf(
                it.heightOffsetLimit, it.heightOffset, it.contentOffset
            )
        }, restore = {
            TopBarState(
                initialHeightOffsetLimit = it[0], initialHeightOffset = it[1], initialContentOffset = it[2]
            )
        })
    }

    private var _heightOffset = mutableFloatStateOf(initialHeightOffset)
}

@Stable
data class TopBarColors(
    val containerColor: Color,
    val scrolledContainerColor: Color,
) {

    @Composable
    internal fun containerColor(colorTransitionFraction: Float): Color {
        return lerp(
            containerColor, scrolledContainerColor, FastOutLinearInEasing.transform(colorTransitionFraction)
        )
    }

    @Composable
    internal fun contentColor(colorTransitionFraction: Float): Color {
        return lerp(
            contentColorFor(color = containerColor),
            contentColorFor(color = scrolledContainerColor),
            FastOutLinearInEasing.transform(colorTransitionFraction)
        )
    }

}


@Composable
@Preview
fun TopBarSamples() {
    val icon = @Composable {
        Box(
            modifier = Modifier
                .size(24.dp)
                .background(Color.White, shape = RoundedCornerShape(6.dp))
        )
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        Text("Sample 1: Title Centered", style = AppTheme.typography.label1)

        Spacer(modifier = Modifier.width(16.dp))

        TopBar(
            colors = TopBarDefaults.topBarColors().copy(containerColor = Color.Black)
        ) {
            Box(
                modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Title", style = AppTheme.typography.h3, color = Color.White
                )
            }
        }
        Spacer(modifier = Modifier.height(32.dp))

        Text("Sample 2: Logo Left, Title Center, Logo Right", style = AppTheme.typography.label1)
        Spacer(modifier = Modifier.width(16.dp))

        TopBar(
            colors = TopBarDefaults.topBarColors().copy(containerColor = Color.Black)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp), verticalAlignment = Alignment.CenterVertically
            ) {

                icon()

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = "Title", style = AppTheme.typography.h3, color = Color.White
                )

                Spacer(modifier = Modifier.weight(1f))

                icon()
            }
        }
        Spacer(modifier = Modifier.height(32.dp))

        Text("Sample 3: Logo and Title Left", style = AppTheme.typography.label1)
        TopBar(
            colors = TopBarDefaults.topBarColors().copy(containerColor = Color.Black)
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 16.dp), verticalAlignment = Alignment.CenterVertically
            ) {

                icon()

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = "Title", style = AppTheme.typography.h3, color = Color.White
                )
            }
        }
    }
}



