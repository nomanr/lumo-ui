package com.nomanr.lumo.multiplatform.sample.sample.samples

import kotlinx.browser.window
import org.w3c.dom.url.URLSearchParams

actual fun showBackButton(): Boolean {
    val searchParams = URLSearchParams(window.location.search.toJsString())
    return !searchParams.has("noBackButton")
}
