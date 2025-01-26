package com.nomanr.lumo.ui.foundation

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.TweenSpec
import androidx.compose.foundation.interaction.DragInteraction
import androidx.compose.foundation.interaction.FocusInteraction
import androidx.compose.foundation.interaction.HoverInteraction
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.ui.unit.Dp

internal suspend fun Animatable<Dp, *>.animateElevation(
    target: Dp,
    from: Interaction? = null,
    to: Interaction? = null,
) {
    val spec =
        when {
            // Moving to a new state
            to != null -> ElevationDefaults.incomingAnimationSpecForInteraction(to)
            // Moving to default, from a previous state
            from != null -> ElevationDefaults.outgoingAnimationSpecForInteraction(from)
            // Loading the initial state, or moving back to the baseline state from a disabled /
            // unknown state, so just snap to the final value.
            else -> null
        }
    if (spec != null) animateTo(target, spec) else snapTo(target)
}

private object ElevationDefaults {
    fun incomingAnimationSpecForInteraction(interaction: Interaction): AnimationSpec<Dp>? {
        return when (interaction) {
            is PressInteraction.Press -> DefaultIncomingSpec
            is DragInteraction.Start -> DefaultIncomingSpec
            is HoverInteraction.Enter -> DefaultIncomingSpec
            is FocusInteraction.Focus -> DefaultIncomingSpec
            else -> null
        }
    }

    fun outgoingAnimationSpecForInteraction(interaction: Interaction): AnimationSpec<Dp>? {
        return when (interaction) {
            is PressInteraction.Press -> DefaultOutgoingSpec
            is DragInteraction.Start -> DefaultOutgoingSpec
            is HoverInteraction.Enter -> HoveredOutgoingSpec
            is FocusInteraction.Focus -> DefaultOutgoingSpec
            else -> null
        }
    }
}

private val OutgoingSpecEasing: Easing = CubicBezierEasing(0.40f, 0.00f, 0.60f, 1.00f)

private val DefaultIncomingSpec =
    TweenSpec<Dp>(
        durationMillis = 120,
        easing = FastOutSlowInEasing,
    )

private val DefaultOutgoingSpec =
    TweenSpec<Dp>(
        durationMillis = 150,
        easing = OutgoingSpecEasing,
    )

private val HoveredOutgoingSpec =
    TweenSpec<Dp>(
        durationMillis = 120,
        easing = OutgoingSpecEasing,
    )
