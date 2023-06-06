package net.limboserendipity.luckywheelcompose.logic

import androidx.compose.runtime.*
import net.limboserendipity.luckywheelcompose.empty.Item
import net.limboserendipity.luckywheelcompose.empty.Stick
import net.limboserendipity.luckywheelcompose.ui.theme.bugEmoji
import net.limboserendipity.luckywheelcompose.ui.theme.getEmoji
import net.limboserendipity.luckywheelcompose.ui.theme.lwGreen

class LwState(
    val sticks: MutableState<List<Stick>>,
    val enabled: MutableState<Boolean>,
    val angle: MutableState<Float>,
    val showDialog : MutableState<Boolean>,
    val topStick: MutableState<Stick>
)

@Composable
fun rememberLwState(
    sticks: List<Stick> = emptyList<Stick>(),
    enabled: Boolean = true,
    angle: Float = 0f,
    showDialog : Boolean = false,
    topStick: Stick = Stick(Item(bugEmoji), lwGreen)
): LwState {

    val stateSticks = remember { mutableStateOf(sticks) }

    val stateEnabled = remember { mutableStateOf(enabled) }

    val stateAngle = remember { mutableStateOf(angle) }

    val stateShowDialog = remember { mutableStateOf(showDialog) }

    val stateTopStick = remember { mutableStateOf(topStick) }

    return LwState(
        sticks = stateSticks,
        enabled = stateEnabled,
        angle = stateAngle,
        showDialog = stateShowDialog,
        topStick = stateTopStick
    )
}