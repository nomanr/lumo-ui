package com.nomanr.sample.ui.components.textfield.base

import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.takeOrElse

@Immutable
class TextFieldColors(
    val focusedTextColor: Color,
    val unfocusedTextColor: Color,
    val disabledTextColor: Color,
    val errorTextColor: Color,
    val focusedContainerColor: Color,
    val unfocusedContainerColor: Color,
    val disabledContainerColor: Color,
    val errorContainerColor: Color,
    val cursorColor: Color,
    val errorCursorColor: Color,
    val textSelectionColors: TextSelectionColors,
    val focusedOutlineColor: Color,
    val unfocusedOutlineColor: Color,
    val disabledOutlineColor: Color,
    val errorOutlineColor: Color,
    val focusedLeadingIconColor: Color,
    val unfocusedLeadingIconColor: Color,
    val disabledLeadingIconColor: Color,
    val errorLeadingIconColor: Color,
    val focusedTrailingIconColor: Color,
    val unfocusedTrailingIconColor: Color,
    val disabledTrailingIconColor: Color,
    val errorTrailingIconColor: Color,
    val focusedLabelColor: Color,
    val unfocusedLabelColor: Color,
    val disabledLabelColor: Color,
    val errorLabelColor: Color,
    val focusedPlaceholderColor: Color,
    val unfocusedPlaceholderColor: Color,
    val disabledPlaceholderColor: Color,
    val errorPlaceholderColor: Color,
    val focusedSupportingTextColor: Color,
    val unfocusedSupportingTextColor: Color,
    val disabledSupportingTextColor: Color,
    val errorSupportingTextColor: Color,
    val focusedPrefixColor: Color,
    val unfocusedPrefixColor: Color,
    val disabledPrefixColor: Color,
    val errorPrefixColor: Color,
    val focusedSuffixColor: Color,
    val unfocusedSuffixColor: Color,
    val disabledSuffixColor: Color,
    val errorSuffixColor: Color,
) {
    fun copy(
        focusedTextColor: Color = this.focusedTextColor,
        unfocusedTextColor: Color = this.unfocusedTextColor,
        disabledTextColor: Color = this.disabledTextColor,
        errorTextColor: Color = this.errorTextColor,
        focusedContainerColor: Color = this.focusedContainerColor,
        unfocusedContainerColor: Color = this.unfocusedContainerColor,
        disabledContainerColor: Color = this.disabledContainerColor,
        errorContainerColor: Color = this.errorContainerColor,
        cursorColor: Color = this.cursorColor,
        errorCursorColor: Color = this.errorCursorColor,
        textSelectionColors: TextSelectionColors? = this.textSelectionColors,
        focusedOutlineColor: Color = this.focusedOutlineColor,
        unfocusedOutlineColor: Color = this.unfocusedOutlineColor,
        disabledOutlineColor: Color = this.disabledOutlineColor,
        errorOutlineColor: Color = this.errorOutlineColor,
        focusedLeadingIconColor: Color = this.focusedLeadingIconColor,
        unfocusedLeadingIconColor: Color = this.unfocusedLeadingIconColor,
        disabledLeadingIconColor: Color = this.disabledLeadingIconColor,
        errorLeadingIconColor: Color = this.errorLeadingIconColor,
        focusedTrailingIconColor: Color = this.focusedTrailingIconColor,
        unfocusedTrailingIconColor: Color = this.unfocusedTrailingIconColor,
        disabledTrailingIconColor: Color = this.disabledTrailingIconColor,
        errorTrailingIconColor: Color = this.errorTrailingIconColor,
        focusedLabelColor: Color = this.focusedLabelColor,
        unfocusedLabelColor: Color = this.unfocusedLabelColor,
        disabledLabelColor: Color = this.disabledLabelColor,
        errorLabelColor: Color = this.errorLabelColor,
        focusedPlaceholderColor: Color = this.focusedPlaceholderColor,
        unfocusedPlaceholderColor: Color = this.unfocusedPlaceholderColor,
        disabledPlaceholderColor: Color = this.disabledPlaceholderColor,
        errorPlaceholderColor: Color = this.errorPlaceholderColor,
        focusedSupportingTextColor: Color = this.focusedSupportingTextColor,
        unfocusedSupportingTextColor: Color = this.unfocusedSupportingTextColor,
        disabledSupportingTextColor: Color = this.disabledSupportingTextColor,
        errorSupportingTextColor: Color = this.errorSupportingTextColor,
        focusedPrefixColor: Color = this.focusedPrefixColor,
        unfocusedPrefixColor: Color = this.unfocusedPrefixColor,
        disabledPrefixColor: Color = this.disabledPrefixColor,
        errorPrefixColor: Color = this.errorPrefixColor,
        focusedSuffixColor: Color = this.focusedSuffixColor,
        unfocusedSuffixColor: Color = this.unfocusedSuffixColor,
        disabledSuffixColor: Color = this.disabledSuffixColor,
        errorSuffixColor: Color = this.errorSuffixColor,
    ) = TextFieldColors(
        focusedTextColor = focusedTextColor.takeOrElse { this.focusedTextColor },
        unfocusedTextColor = unfocusedTextColor.takeOrElse { this.unfocusedTextColor },
        disabledTextColor = disabledTextColor.takeOrElse { this.disabledTextColor },
        errorTextColor = errorTextColor.takeOrElse { this.errorTextColor },
        focusedContainerColor = focusedContainerColor.takeOrElse { this.focusedContainerColor },
        unfocusedContainerColor = unfocusedContainerColor.takeOrElse { this.unfocusedContainerColor },
        disabledContainerColor = disabledContainerColor.takeOrElse { this.disabledContainerColor },
        errorContainerColor = errorContainerColor.takeOrElse { this.errorContainerColor },
        cursorColor = cursorColor.takeOrElse { this.cursorColor },
        errorCursorColor = errorCursorColor.takeOrElse { this.errorCursorColor },
        textSelectionColors = textSelectionColors.takeOrElse { this.textSelectionColors },
        focusedOutlineColor = focusedOutlineColor.takeOrElse { this.focusedOutlineColor },
        unfocusedOutlineColor = unfocusedOutlineColor.takeOrElse { this.unfocusedOutlineColor },
        disabledOutlineColor = disabledOutlineColor.takeOrElse { this.disabledOutlineColor },
        errorOutlineColor = errorOutlineColor.takeOrElse { this.errorOutlineColor },
        focusedLeadingIconColor = focusedLeadingIconColor.takeOrElse { this.focusedLeadingIconColor },
        unfocusedLeadingIconColor = unfocusedLeadingIconColor.takeOrElse { this.unfocusedLeadingIconColor },
        disabledLeadingIconColor = disabledLeadingIconColor.takeOrElse { this.disabledLeadingIconColor },
        errorLeadingIconColor = errorLeadingIconColor.takeOrElse { this.errorLeadingIconColor },
        focusedTrailingIconColor = focusedTrailingIconColor.takeOrElse { this.focusedTrailingIconColor },
        unfocusedTrailingIconColor = unfocusedTrailingIconColor.takeOrElse { this.unfocusedTrailingIconColor },
        disabledTrailingIconColor = disabledTrailingIconColor.takeOrElse { this.disabledTrailingIconColor },
        errorTrailingIconColor = errorTrailingIconColor.takeOrElse { this.errorTrailingIconColor },
        focusedLabelColor = focusedLabelColor.takeOrElse { this.focusedLabelColor },
        unfocusedLabelColor = unfocusedLabelColor.takeOrElse { this.unfocusedLabelColor },
        disabledLabelColor = disabledLabelColor.takeOrElse { this.disabledLabelColor },
        errorLabelColor = errorLabelColor.takeOrElse { this.errorLabelColor },
        focusedPlaceholderColor = focusedPlaceholderColor.takeOrElse { this.focusedPlaceholderColor },
        unfocusedPlaceholderColor = unfocusedPlaceholderColor.takeOrElse { this.unfocusedPlaceholderColor },
        disabledPlaceholderColor = disabledPlaceholderColor.takeOrElse { this.disabledPlaceholderColor },
        errorPlaceholderColor = errorPlaceholderColor.takeOrElse { this.errorPlaceholderColor },
        focusedSupportingTextColor = focusedSupportingTextColor.takeOrElse { this.focusedSupportingTextColor },
        unfocusedSupportingTextColor = unfocusedSupportingTextColor.takeOrElse { this.unfocusedSupportingTextColor },
        disabledSupportingTextColor = disabledSupportingTextColor.takeOrElse { this.disabledSupportingTextColor },
        errorSupportingTextColor = errorSupportingTextColor.takeOrElse { this.errorSupportingTextColor },
        focusedPrefixColor = focusedPrefixColor.takeOrElse { this.focusedPrefixColor },
        unfocusedPrefixColor = unfocusedPrefixColor.takeOrElse { this.unfocusedPrefixColor },
        disabledPrefixColor = disabledPrefixColor.takeOrElse { this.disabledPrefixColor },
        errorPrefixColor = errorPrefixColor.takeOrElse { this.errorPrefixColor },
        focusedSuffixColor = focusedSuffixColor.takeOrElse { this.focusedSuffixColor },
        unfocusedSuffixColor = unfocusedSuffixColor.takeOrElse { this.unfocusedSuffixColor },
        disabledSuffixColor = disabledSuffixColor.takeOrElse { this.disabledSuffixColor },
        errorSuffixColor = errorSuffixColor.takeOrElse { this.errorSuffixColor },
    )

    private fun TextSelectionColors?.takeOrElse(block: () -> TextSelectionColors): TextSelectionColors = this ?: block()

    @Composable
    internal fun leadingIconColor(
        enabled: Boolean,
        isError: Boolean,
        interactionSource: InteractionSource,
    ): State<Color> {
        val focused by interactionSource.collectIsFocusedAsState()

        return rememberUpdatedState(
            when {
                !enabled -> disabledLeadingIconColor
                isError -> errorLeadingIconColor
                focused -> focusedLeadingIconColor
                else -> unfocusedLeadingIconColor
            },
        )
    }

    @Composable
    internal fun trailingIconColor(
        enabled: Boolean,
        isError: Boolean,
        interactionSource: InteractionSource,
    ): State<Color> {
        val focused by interactionSource.collectIsFocusedAsState()

        return rememberUpdatedState(
            when {
                !enabled -> disabledTrailingIconColor
                isError -> errorTrailingIconColor
                focused -> focusedTrailingIconColor
                else -> unfocusedTrailingIconColor
            },
        )
    }

    @Composable
    internal fun labelColor(
        enabled: Boolean,
        isError: Boolean,
        interactionSource: InteractionSource,
    ): State<Color> {
        val focused by interactionSource.collectIsFocusedAsState()

        return rememberUpdatedState(
            when {
                !enabled -> disabledLabelColor
                isError -> errorLabelColor
                focused -> focusedLabelColor
                else -> unfocusedLabelColor
            },
        )
    }

    @Composable
    internal fun placeholderColor(
        enabled: Boolean,
        isError: Boolean,
        interactionSource: InteractionSource,
    ): State<Color> {
        val focused by interactionSource.collectIsFocusedAsState()

        return rememberUpdatedState(
            when {
                !enabled -> disabledPlaceholderColor
                isError -> errorPlaceholderColor
                focused -> focusedPlaceholderColor
                else -> unfocusedPlaceholderColor
            },
        )
    }

    @Composable
    internal fun supportingTextColor(
        enabled: Boolean,
        isError: Boolean,
        interactionSource: InteractionSource,
    ): State<Color> {
        val focused by interactionSource.collectIsFocusedAsState()

        return rememberUpdatedState(
            when {
                !enabled -> disabledSupportingTextColor
                isError -> errorSupportingTextColor
                focused -> focusedSupportingTextColor
                else -> unfocusedSupportingTextColor
            },
        )
    }

    @Composable
    internal fun prefixColor(
        enabled: Boolean,
        isError: Boolean,
        interactionSource: InteractionSource,
    ): State<Color> {
        val focused by interactionSource.collectIsFocusedAsState()

        return rememberUpdatedState(
            when {
                !enabled -> disabledPrefixColor
                isError -> errorPrefixColor
                focused -> focusedPrefixColor
                else -> unfocusedPrefixColor
            },
        )
    }

    @Composable
    internal fun suffixColor(
        enabled: Boolean,
        isError: Boolean,
        interactionSource: InteractionSource,
    ): State<Color> {
        val focused by interactionSource.collectIsFocusedAsState()

        return rememberUpdatedState(
            when {
                !enabled -> disabledSuffixColor
                isError -> errorSuffixColor
                focused -> focusedSuffixColor
                else -> unfocusedSuffixColor
            },
        )
    }

    @Composable
    internal fun containerColor(
        enabled: Boolean,
        isError: Boolean,
        interactionSource: InteractionSource,
    ): State<Color> {
        val focused by interactionSource.collectIsFocusedAsState()

        return rememberUpdatedState(
            when {
                !enabled -> disabledContainerColor
                isError -> errorContainerColor
                focused -> focusedContainerColor
                else -> unfocusedContainerColor
            },
        )
    }

    @Composable
    fun containerOutlineColor(
        enabled: Boolean,
        isError: Boolean,
        interactionSource: InteractionSource,
    ): State<Color> {
        val focused by interactionSource.collectIsFocusedAsState()
        return rememberUpdatedState(
            when {
                !enabled -> disabledOutlineColor
                isError -> errorOutlineColor
                focused -> focusedOutlineColor
                else -> unfocusedOutlineColor
            },
        )
    }

    @Composable
    internal fun textColor(
        enabled: Boolean,
        isError: Boolean,
        interactionSource: InteractionSource,
    ): State<Color> {
        val focused by interactionSource.collectIsFocusedAsState()

        return rememberUpdatedState(
            when {
                !enabled -> disabledTextColor
                isError -> errorTextColor
                focused -> focusedTextColor
                else -> unfocusedTextColor
            },
        )
    }

    @Composable
    internal fun cursorColor(isError: Boolean): State<Color> {
        return rememberUpdatedState(if (isError) errorCursorColor else cursorColor)
    }

    internal val selectionColors: TextSelectionColors
        @Composable get() = textSelectionColors
}
