package net.limboserendipity.luckywheelcompose.logic

import androidx.compose.runtime.*
import net.limboserendipity.luckywheelcompose.empty.Stick

class LwState(
    val sticks: MutableState<List<Stick>>,
    val enabled: MutableState<Boolean>,
    val angle: MutableState<Float>
)

@Composable
fun rememberLwState(
    sticks: List<Stick> = emptyList<Stick>(),
    enabled: Boolean = true,
    angle: Float = 0f
): LwState {

    val stateSticks = remember { mutableStateOf(sticks) }

    val stateEnabled = remember { mutableStateOf(enabled) }

    val stateAngle = remember { mutableStateOf(angle) }

    return LwState(
        sticks = stateSticks,
        enabled = stateEnabled,
        angle = stateAngle
    )
}