package net.limboserendipity.luckywheelcompose.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
)

val handEmoji = "\uD83D\uDCCD" //📍

val bugEmoji = "\uD83D\uDC1E" // 🐞
val brainEmoji = "\uD83E\uDDE0"  // 🧠
val cloverEmoji = "\uD83C\uDF4B"  // 🍀
val limonEmoji = "\uD83C\uDF4B" // 🍋
val moneyEmoji = "\uD83D\uDCB8" // 💸
val deadEmoji = "\uD83D\uDC80" // 💀
val diamondEmoji = "\uD83D\uDC8E" // 💎
val crownEmoji = "\uD83D\uDC51" // 👑
val starEmoji = "\uD83D\uDC51" // ✨


fun getEmoji() = listOf(
    bugEmoji,
    brainEmoji,
    cloverEmoji,
    limonEmoji,
    moneyEmoji,
    deadEmoji,
    diamondEmoji,
    crownEmoji,
    starEmoji,
)