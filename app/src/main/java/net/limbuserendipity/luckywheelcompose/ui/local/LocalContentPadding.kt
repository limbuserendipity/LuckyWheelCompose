package net.limbuserendipity.luckywheelcompose.ui.local

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.dp

val LocalContentPadding = compositionLocalOf { contentPadding() }

class ContentPadding(
    val small : PaddingValues,
    val medium : PaddingValues,
    val large : PaddingValues
)

fun contentPadding(
    small : PaddingValues = PaddingValues(4.dp),
    medium : PaddingValues = PaddingValues(8.dp),
    large : PaddingValues = PaddingValues(16.dp),
) = ContentPadding(small, medium, large)