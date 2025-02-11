package com.nomanr.lumo.multiplatform.ui.configs

import androidx.compose.runtime.Composable
import platform.UIKit.UIApplication
import platform.UIKit.UIContentSizeCategoryExtraExtraExtraLarge
import platform.UIKit.UIContentSizeCategoryExtraExtraLarge
import platform.UIKit.UIContentSizeCategoryExtraLarge
import platform.UIKit.UIContentSizeCategoryExtraSmall
import platform.UIKit.UIContentSizeCategoryLarge
import platform.UIKit.UIContentSizeCategoryMedium
import platform.UIKit.UIContentSizeCategorySmall

@Composable
actual fun getSystemFontScale(): Float {
    val contentSizeCategory = UIApplication.sharedApplication.preferredContentSizeCategory
    return when (contentSizeCategory) {
        UIContentSizeCategoryExtraSmall -> 0.8f
        UIContentSizeCategorySmall -> 0.9f
        UIContentSizeCategoryMedium -> 1.0f
        UIContentSizeCategoryLarge -> 1.1f
        UIContentSizeCategoryExtraLarge -> 1.2f
        UIContentSizeCategoryExtraExtraLarge -> 1.3f
        UIContentSizeCategoryExtraExtraExtraLarge -> 1.4f
        else -> 1.0f
    }
}
