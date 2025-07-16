package com.bureau.qrscanner.core.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40
)

/**
 * Customizable Bureau theme that accepts branding colors
 */
@Composable
fun BureauTheme(
    primaryColor: Color? = null,
    secondaryColor: Color? = null,
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme.copy(
            primary = primaryColor ?: DarkColorScheme.primary,
            secondary = secondaryColor ?: DarkColorScheme.secondary
        )
        else -> LightColorScheme.copy(
            primary = primaryColor ?: LightColorScheme.primary,
            secondary = secondaryColor ?: LightColorScheme.secondary
        )
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
} 