package com.nomanr.lumo.ui.components.snackbar

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.RecomposeScope
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.currentRecomposeScope
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.AccessibilityManager
import androidx.compose.ui.platform.LocalAccessibilityManager
import androidx.compose.ui.semantics.LiveRegionMode
import androidx.compose.ui.semantics.dismiss
import androidx.compose.ui.semantics.liveRegion
import androidx.compose.ui.semantics.paneTitle
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.util.fastFilterNotNull
import androidx.compose.ui.util.fastForEach
import androidx.compose.ui.util.fastMap
import androidx.compose.ui.util.fastMapTo
import kotlinx.coroutines.CancellableContinuation
import kotlinx.coroutines.delay
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlin.coroutines.resume

@Stable
interface SnackbarVisuals {
    val message: String
    val actionLabel: String?
    val withDismissAction: Boolean
    val duration: SnackbarDuration
}

@Stable
interface SnackbarData {
    val visuals: SnackbarVisuals

    fun performAction()

    fun dismiss()
}

enum class SnackbarResult {
    Dismissed,
    ActionPerformed,
}

enum class SnackbarDuration {
    Short,
    Long,
    Indefinite,
}

internal fun SnackbarDuration.toMillis(hasAction: Boolean, accessibilityManager: AccessibilityManager?): Long {
    val original =
        when (this) {
            SnackbarDuration.Indefinite -> Long.MAX_VALUE
            SnackbarDuration.Long -> 10000L
            SnackbarDuration.Short -> 4000L
        }
    return accessibilityManager?.calculateRecommendedTimeoutMillis(
        original,
        containsIcons = true,
        containsText = true,
        containsControls = hasAction,
    ) ?: original
}

@Stable
class SnackbarHostState {
    private val mutex = Mutex()
    var currentSnackbarData by mutableStateOf<SnackbarData?>(null)
        private set

    suspend fun showSnackbar(
        message: String,
        actionLabel: String? = null,
        withDismissAction: Boolean = false,
        duration: SnackbarDuration = if (actionLabel == null) SnackbarDuration.Short else SnackbarDuration.Indefinite,
    ): SnackbarResult =
        showSnackbar(
            SnackbarVisualsImpl(message, actionLabel, withDismissAction, duration),
        )

    suspend fun showSnackbar(visuals: SnackbarVisuals): SnackbarResult =
        mutex.withLock {
            try {
                return suspendCancellableCoroutine { continuation ->
                    currentSnackbarData = SnackbarDataImpl(visuals, continuation)
                }
            } finally {
                currentSnackbarData = null
            }
        }

    private class SnackbarVisualsImpl(
        override val message: String,
        override val actionLabel: String?,
        override val withDismissAction: Boolean,
        override val duration: SnackbarDuration,
    ) : SnackbarVisuals {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is SnackbarVisualsImpl) return false
            if (message != other.message) return false
            if (actionLabel != other.actionLabel) return false
            if (withDismissAction != other.withDismissAction) return false
            if (duration != other.duration) return false
            return true
        }

        override fun hashCode(): Int {
            var result = message.hashCode()
            result = 31 * result + (actionLabel?.hashCode() ?: 0)
            result = 31 * result + withDismissAction.hashCode()
            result = 31 * result + duration.hashCode()
            return result
        }
    }

    private class SnackbarDataImpl(
        override val visuals: SnackbarVisuals,
        private val continuation: CancellableContinuation<SnackbarResult>,
    ) : SnackbarData {
        override fun performAction() {
            if (continuation.isActive) continuation.resume(SnackbarResult.ActionPerformed)
        }

        override fun dismiss() {
            if (continuation.isActive) continuation.resume(SnackbarResult.Dismissed)
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is SnackbarDataImpl) return false
            if (visuals != other.visuals) return false
            if (continuation != other.continuation) return false
            return true
        }

        override fun hashCode(): Int {
            var result = visuals.hashCode()
            result = 31 * result + continuation.hashCode()
            return result
        }
    }
}

@Composable
fun SnackbarHost(
    hostState: SnackbarHostState,
    modifier: Modifier = Modifier,
    snackbar: @Composable (SnackbarData) -> Unit = { Snackbar(it) },
) {
    val currentSnackbarData = hostState.currentSnackbarData
    val accessibilityManager = LocalAccessibilityManager.current
    LaunchedEffect(currentSnackbarData) {
        if (currentSnackbarData != null) {
            val duration =
                currentSnackbarData.visuals.duration.toMillis(
                    currentSnackbarData.visuals.actionLabel != null,
                    accessibilityManager,
                )
            delay(duration)
            currentSnackbarData.dismiss()
        }
    }
    FadeInFadeOutWithScale(current = hostState.currentSnackbarData, modifier = modifier, content = snackbar)
}

@Composable
private fun FadeInFadeOutWithScale(
    current: SnackbarData?,
    modifier: Modifier = Modifier,
    content: @Composable (SnackbarData) -> Unit,
) {
    val state = remember { FadeInFadeOutState<SnackbarData?>() }
    if (current != state.current) {
        state.current = current
        val keys = state.items.fastMap { it.key }.toMutableList()
        if (!keys.contains(current)) keys.add(current)
        state.items.clear()
        keys.fastFilterNotNull().fastMapTo(state.items) { key ->
            FadeInFadeOutAnimationItem(key) { children ->
                val isVisible = key == current
                val opacity =
                    animatedOpacity(
                        animation = spring(stiffness = 400f),
                        visible = isVisible,
                        onAnimationFinish = {
                            if (key != state.current) {
                                state.items.removeAll { it.key == key }
                                state.scope?.invalidate()
                            }
                        },
                    )
                val scale = animatedScale(animation = spring(stiffness = 400f), visible = isVisible)
                Box(
                    Modifier.graphicsLayer(scaleX = scale.value, scaleY = scale.value, alpha = opacity.value)
                        .semantics {
                            liveRegion = LiveRegionMode.Polite
                            dismiss {
                                key.dismiss()
                                true
                            }
                            paneTitle = "SnackBar"
                        },
                ) {
                    children()
                }
            }
        }
    }
    Box(modifier) {
        state.scope = currentRecomposeScope
        state.items.fastForEach { (item, opacity) -> key(item) { opacity { content(item!!) } } }
    }
}

private class FadeInFadeOutState<T> {
    var current: Any? = Any()
    var items = mutableListOf<FadeInFadeOutAnimationItem<T>>()
    var scope: RecomposeScope? = null
}

private data class FadeInFadeOutAnimationItem<T>(
    val key: T,
    val transition: @Composable (content: @Composable () -> Unit) -> Unit,
)

@Composable
private fun animatedOpacity(
    animation: AnimationSpec<Float>,
    visible: Boolean,
    onAnimationFinish: () -> Unit = {},
): State<Float> {
    val alpha = remember { Animatable(if (!visible) 1f else 0f) }
    LaunchedEffect(visible) {
        alpha.animateTo(if (visible) 1f else 0f, animationSpec = animation)
        onAnimationFinish()
    }
    return alpha.asState()
}

@Composable
private fun animatedScale(animation: AnimationSpec<Float>, visible: Boolean): State<Float> {
    val scale = remember { Animatable(if (!visible) 1f else 0.8f) }
    LaunchedEffect(visible) {
        scale.animateTo(if (visible) 1f else 0.8f, animationSpec = animation)
    }
    return scale.asState()
}

@Composable
fun rememberSnackbarHost(): SnackbarHostState {
    val snackBarHost = remember { SnackbarHostState() }
    return snackBarHost
}
