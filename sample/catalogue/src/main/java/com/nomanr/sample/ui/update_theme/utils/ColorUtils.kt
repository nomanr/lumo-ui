import androidx.compose.ui.graphics.Color
import com.nomanr.sample.ui.AppColors

internal fun getColorData(appColors: AppColors): Map<String, Pair<Color, Color>> {
    val lightColors = appColors.lightColors
    val darkColors = appColors.darkColors
    val colors = mutableMapOf<String, Pair<Color, Color>>()

    colors["Primary"] = Pair(lightColors.primary, darkColors.primary)
    colors["OnPrimary"] = Pair(lightColors.onPrimary, darkColors.onPrimary)
    colors["Secondary"] = Pair(lightColors.secondary, darkColors.secondary)
    colors["OnSecondary"] = Pair(lightColors.onSecondary, darkColors.onSecondary)
    colors["Tertiary"] = Pair(lightColors.tertiary, darkColors.tertiary)
    colors["OnTertiary"] = Pair(lightColors.onTertiary, darkColors.onTertiary)
    colors["Error"] = Pair(lightColors.error, darkColors.error)
    colors["OnError"] = Pair(lightColors.onError, darkColors.onError)
    colors["Success"] = Pair(lightColors.success, darkColors.success)
    colors["OnSuccess"] = Pair(lightColors.onSuccess, darkColors.onSuccess)
    colors["Disabled"] = Pair(lightColors.disabled, darkColors.disabled)
    colors["OnDisabled"] = Pair(lightColors.onDisabled, darkColors.onDisabled)
    colors["Surface"] = Pair(lightColors.surface, darkColors.surface)
    colors["OnSurface"] = Pair(lightColors.onSurface, darkColors.onSurface)
    colors["Background"] = Pair(lightColors.background, darkColors.background)
    colors["OnBackground"] = Pair(lightColors.onBackground, darkColors.onBackground)
    colors["Outline"] = Pair(lightColors.outline, darkColors.outline)
    colors["Text"] = Pair(lightColors.text, darkColors.text)
    colors["TextSecondary"] = Pair(lightColors.textSecondary, darkColors.textSecondary)
    colors["TextDisabled"] = Pair(lightColors.textDisabled, darkColors.textDisabled)
    colors["Scrim"] = Pair(lightColors.scrim, darkColors.scrim)
    colors["Elevation"] = Pair(lightColors.elevation, darkColors.elevation)

    return colors
}

internal fun getANewAppColorsCopy(title: String, lightColor: Color, darkColor: Color, appColors: AppColors): AppColors {
    return when (title) {
        "Primary" -> appColors.copy(
            lightColors = appColors.lightColors.copy(primary = lightColor),
            darkColors = appColors.darkColors.copy(primary = darkColor)
        )
        "OnPrimary" -> appColors.copy(
            lightColors = appColors.lightColors.copy(onPrimary = lightColor),
            darkColors = appColors.darkColors.copy(onPrimary = darkColor)
        )
        "Secondary" -> appColors.copy(
            lightColors = appColors.lightColors.copy(secondary = lightColor),
            darkColors = appColors.darkColors.copy(secondary = darkColor)
        )
        "OnSecondary" -> appColors.copy(
            lightColors = appColors.lightColors.copy(onSecondary = lightColor),
            darkColors = appColors.darkColors.copy(onSecondary = darkColor)
        )
        "Tertiary" -> appColors.copy(
            lightColors = appColors.lightColors.copy(tertiary = lightColor),
            darkColors = appColors.darkColors.copy(tertiary = darkColor)
        )
        "OnTertiary" -> appColors.copy(
            lightColors = appColors.lightColors.copy(onTertiary = lightColor),
            darkColors = appColors.darkColors.copy(onTertiary = darkColor)
        )
        "Error" -> appColors.copy(
            lightColors = appColors.lightColors.copy(error = lightColor),
            darkColors = appColors.darkColors.copy(error = darkColor)
        )
        "OnError" -> appColors.copy(
            lightColors = appColors.lightColors.copy(onError = lightColor),
            darkColors = appColors.darkColors.copy(onError = darkColor)
        )
        "Success" -> appColors.copy(
            lightColors = appColors.lightColors.copy(success = lightColor),
            darkColors = appColors.darkColors.copy(success = darkColor)
        )
        "OnSuccess" -> appColors.copy(
            lightColors = appColors.lightColors.copy(onSuccess = lightColor),
            darkColors = appColors.darkColors.copy(onSuccess = darkColor)
        )
        "Disabled" -> appColors.copy(
            lightColors = appColors.lightColors.copy(disabled = lightColor),
            darkColors = appColors.darkColors.copy(disabled = darkColor)
        )
        "OnDisabled" -> appColors.copy(
            lightColors = appColors.lightColors.copy(onDisabled = lightColor),
            darkColors = appColors.darkColors.copy(onDisabled = darkColor)
        )
        "Surface" -> appColors.copy(
            lightColors = appColors.lightColors.copy(surface = lightColor),
            darkColors = appColors.darkColors.copy(surface = darkColor)
        )
        "OnSurface" -> appColors.copy(
            lightColors = appColors.lightColors.copy(onSurface = lightColor),
            darkColors = appColors.darkColors.copy(onSurface = darkColor)
        )
        "Background" -> appColors.copy(
            lightColors = appColors.lightColors.copy(background = lightColor),
            darkColors = appColors.darkColors.copy(background = darkColor)
        )
        "OnBackground" -> appColors.copy(
            lightColors = appColors.lightColors.copy(onBackground = lightColor),
            darkColors = appColors.darkColors.copy(onBackground = darkColor)
        )
        "Outline" -> appColors.copy(
            lightColors = appColors.lightColors.copy(outline = lightColor),
            darkColors = appColors.darkColors.copy(outline = darkColor)
        )
        "Text" -> appColors.copy(
            lightColors = appColors.lightColors.copy(text = lightColor),
            darkColors = appColors.darkColors.copy(text = darkColor)
        )
        "TextSecondary" -> appColors.copy(
            lightColors = appColors.lightColors.copy(textSecondary = lightColor),
            darkColors = appColors.darkColors.copy(textSecondary = darkColor)
        )
        "TextDisabled" -> appColors.copy(
            lightColors = appColors.lightColors.copy(textDisabled = lightColor),
            darkColors = appColors.darkColors.copy(textDisabled = darkColor)
        )
        "Scrim" -> appColors.copy(
            lightColors = appColors.lightColors.copy(scrim = lightColor),
            darkColors = appColors.darkColors.copy(scrim = darkColor)
        )
        "Elevation" -> appColors.copy(
            lightColors = appColors.lightColors.copy(elevation = lightColor),
            darkColors = appColors.darkColors.copy(elevation = darkColor)
        )
        else -> appColors
    }
}

