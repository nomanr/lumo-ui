package com.nomanr.lumo.multiplatform.sample.navigation

import kotlinx.browser.window
import org.w3c.dom.url.URLSearchParams

actual fun getInitialComponentId(): String? {
    val searchParams = URLSearchParams(window.location.search)
    return searchParams.get("componentId")
}
