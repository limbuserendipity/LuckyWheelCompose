package net.limbuserendipity.luckywheelcompose.ui.theme

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

const val handEmoji = "\uD83D\uDCCD" //📍

const val bugEmoji = "\uD83D\uDC1E" // 🐞
const val brainEmoji = "\uD83E\uDDE0"  // 🧠
const val cloverEmoji = "\uD83C\uDF4B"  // 🍀
const val lemonEmoji = "\uD83C\uDF4B" // 🍋
const val moneyEmoji = "\uD83D\uDCB8" // 💸
const val deadEmoji = "\uD83D\uDC80" // 💀
const val diamondEmoji = "\uD83D\uDC8E" // 💎
const val crownEmoji = "\uD83D\uDC51" // 👑
const val starEmoji = "\uD83D\uDC51" // ✨

val inventoryEmoji = "\uD83D\uDCBC" //💼

fun getEmoji() = listOf(
    bugEmoji,
    brainEmoji,
    cloverEmoji,
    lemonEmoji,
    moneyEmoji,
    deadEmoji,
    diamondEmoji,
    crownEmoji,
    starEmoji,
)