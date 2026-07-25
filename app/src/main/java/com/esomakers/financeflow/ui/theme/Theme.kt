package com.esomakers.financeflow.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val MonochromeDarkColorScheme = darkColorScheme(
    background = BlackBg,
    surface = SurfaceDark,
    surfaceVariant = BorderDark,
    onBackground = TextPrimary,
    onSurface = TextPrimary,
    onSurfaceVariant = TextMuted,
    primary = White,
    onPrimary = BlackBg,
    outline = BorderDark
)
@Composable
fun FinanceFlowTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = MonochromeDarkColorScheme,
        typography = Typography,
        content = content
    )
}