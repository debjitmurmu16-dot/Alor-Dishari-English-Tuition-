package com.example.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

private val AlorDishariLightColorScheme = lightColorScheme(
    primary = DeepNavy,
    onPrimary = CrispWhite,
    primaryContainer = RoyalBlueSoft,
    onPrimaryContainer = DeepNavy,
    secondary = RoyalBlue,
    onSecondary = CrispWhite,
    secondaryContainer = SoftSurfaceVariant,
    onSecondaryContainer = DeepNavy,
    tertiary = AntiqueGold,
    onTertiary = MidnightNavy,
    tertiaryContainer = GoldLightTint,
    onTertiaryContainer = DeepNavy,
    background = IvoryBackground,
    onBackground = InkPrimary,
    surface = CrispWhite,
    onSurface = InkPrimary,
    surfaceVariant = SoftSurfaceVariant,
    onSurfaceVariant = InkSecondary,
    outline = SubtleOutline,
    outlineVariant = ColorTokens.LightBorder,
    error = ErrorRed,
    onError = CrispWhite,
    errorContainer = ErrorContainerLight,
    onErrorContainer = ErrorRed,
)

private val AlorDishariDarkColorScheme = darkColorScheme(
    primary = WarmGold,
    onPrimary = MidnightNavy,
    primaryContainer = NavyContainer,
    onPrimaryContainer = CrispWhite,
    secondary = RoyalBlueBright,
    onSecondary = MidnightNavy,
    secondaryContainer = RoyalBlueDarkContainer,
    onSecondaryContainer = RoyalBlueSoft,
    tertiary = AntiqueGold,
    onTertiary = MidnightNavy,
    tertiaryContainer = GoldDarkContainer,
    onTertiaryContainer = GoldLightTint,
    background = MidnightNavy,
    onBackground = CrispWhite,
    surface = DeepNavy,
    onSurface = CrispWhite,
    surfaceVariant = NavySurface,
    onSurfaceVariant = ColorTokens.DarkMutedText,
    outline = ColorTokens.DarkOutline,
    outlineVariant = NavyContainer,
    error = ColorTokens.DarkError,
    onError = MidnightNavy,
)

private object ColorTokens {
    val LightBorder = androidx.compose.ui.graphics.Color(0xFFE2E8F0)
    val DarkMutedText = androidx.compose.ui.graphics.Color(0xFFCBD5E1)
    val DarkOutline = androidx.compose.ui.graphics.Color(0xFF334E7B)
    val DarkError = androidx.compose.ui.graphics.Color(0xFFFCA5A5)
}

val AlorDishariShapes = Shapes(
    extraSmall = RoundedCornerShape(8.dp),
    small = RoundedCornerShape(12.dp),
    medium = RoundedCornerShape(16.dp),
    large = RoundedCornerShape(22.dp),
    extraLarge = RoundedCornerShape(28.dp),
)

@Composable
fun AlorDishariTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colorScheme = if (darkTheme) AlorDishariDarkColorScheme else AlorDishariLightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = AppTypography,
        shapes = AlorDishariShapes,
        content = content,
    )
}

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    AlorDishariTheme(darkTheme = darkTheme, content = content)
}
