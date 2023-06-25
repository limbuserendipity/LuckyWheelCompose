package net.limbuserendipity.luckywheelcompose.logic

import androidx.compose.runtime.*
import net.limbuserendipity.luckywheelcompose.empty.Item
import net.limbuserendipity.luckywheelcompose.empty.Stick
import net.limbuserendipity.luckywheelcompose.ui.theme.bugEmoji
import net.limbuserendipity.luckywheelcompose.ui.theme.lwGreen

class LwState(
    val sticks: MutableState<List<Stick>>,
    val enabled: MutableState<Boolean>,
    val angle: MutableState<Float>,
    val showDialog : MutableState<Boolean>,
    val topStick: MutableState<Stick>,
    val showInventory : MutableState<Boolean>,
    val inventory: MutableState<Inventory<Item>>
)

@Composable
fun rememberLwState(
    sticks: List<Stick> = emptyList(),
    enabled: Boolean = true,
    angle: Float = 0f,
    showDialog : Boolean = false,
    topStick: Stick = Stick(Item(bugEmoji), lwGreen),
    showInventory : Boolean = false,
    inventory: Inventory<Item> = inventory(emptyList())
): LwState {

    val stateSticks = remember { mutableStateOf(sticks) }

    val stateEnabled = remember { mutableStateOf(enabled) }

    val stateAngle = remember { mutableStateOf(angle) }

    val stateShowDialog = remember { mutableStateOf(showDialog) }

    val stateTopStick = remember { mutableStateOf(topStick) }

    val stateShowInventory = remember { mutableStateOf(showInventory) }

    val stateInventory = remember { mutableStateOf(inventory) }

    return LwState(
        sticks = stateSticks,
        enabled = stateEnabled,
        angle = stateAngle,
        showDialog = stateShowDialog,
        topStick = stateTopStick,
        showInventory = stateShowInventory,
        inventory = stateInventory
    )
}