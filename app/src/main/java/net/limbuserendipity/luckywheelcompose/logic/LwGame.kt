package net.limbuserendipity.luckywheelcompose.logic

import net.limbuserendipity.luckywheelcompose.empty.Item
import net.limbuserendipity.luckywheelcompose.empty.Stick
import net.limbuserendipity.luckywheelcompose.ui.theme.getColors
import net.limbuserendipity.luckywheelcompose.ui.theme.getEmoji
import kotlin.random.Random

class LwGame(
    val lwState: LwState
) {

    init {
        refreshWheel()
    }

    fun refreshWheel() {
        val min = 2
        val sticks = mutableListOf<Stick>()
        val colors = getColors()
        val max = colors.size - min
        val range = Random.nextInt(min, max)

        repeat(range) { index ->
            val stick = Stick(
                item = Item(emoji = getEmoji().random()),
                color = colors[index]
            )
            sticks.add(stick)
        }

        lwState.sticks.value = sticks
    }

    fun finishedListener(value: Float) {
        lwState.showDialog.value = !lwState.showDialog.value
    }

    fun onDragStopped(velocity: Float) {
        lwState.angle.value = velocity
    }

    fun onDismissRequest() {
        lwState.showDialog.value = !lwState.showDialog.value
        lwState.enabled.value = true
        lwState.inventory.value.add(lwState.topStick.value.item)
        refreshWheel()
    }

    fun onTopStick(stick: Stick) {
        lwState.topStick.value = stick
    }

    fun showInventory() {
        lwState.showInventory.value = !lwState.showInventory.value
    }

}