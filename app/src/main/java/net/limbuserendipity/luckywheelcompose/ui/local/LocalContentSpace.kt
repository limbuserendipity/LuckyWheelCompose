package net.limbuserendipity.luckywheelcompose.ui.local

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


val LocalContentSpace = compositionLocalOf { contentSpace() }

class ContentSpace(
    val small : Dp,
    val medium : Dp,
    val large : Dp
)

fun contentSpace(
    small : Dp = 16.dp,
    medium : Dp = 32.dp,
    large : Dp = 64.dp
) = ContentSpace(small, medium, large)