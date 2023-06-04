package net.limboserendipity.luckywheelcompose.ui.local

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

val LocalContentPadding = compositionLocalOf { contentPadding() }

class ContentPadding(
    val small : Dp,
    val medium : Dp,
    val large : Dp
)

fun contentPadding(
    small : Dp = 4.dp,
    medium : Dp = 8.dp,
    large : Dp = 16.dp
) = ContentSpace(small, medium, large)