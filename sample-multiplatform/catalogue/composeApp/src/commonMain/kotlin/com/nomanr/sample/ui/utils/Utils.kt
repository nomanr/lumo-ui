package com.nomanr.sample.ui.utils

import androidx.compose.runtime.Composable

@Composable
fun composableOrNull(predicate: Boolean, composable: @Composable () -> Unit): @Composable (() -> Unit)? {
    if (!predicate) return null
    return composable
}
