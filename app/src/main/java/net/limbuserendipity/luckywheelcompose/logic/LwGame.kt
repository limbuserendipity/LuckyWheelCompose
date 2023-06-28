package net.limbuserendipity.luckywheelcompose.logic

import net.limbuserendipity.luckywheelcompose.empty.Item
import net.limbuserendipity.luckywheelcompose.empty.Stick
import net.limbuserendipity.luckywheelcompose.ui.theme.deadEmoji
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
        lwState.enabled.value = true
    }

    fun onDragStopped(velocity: Float) {
        lwState.angle.value = velocity
        lwState.enabled.value = false
    }

    fun onDismissRequest() {
        lwState.showDialog.value = !lwState.showDialog.value
        lwState.enabled.value = true
        lwState.inventory.value.add(lwState.topStick.value.item)
        refreshWheel()
    }

    fun onTopStick(stick: Stick) {
        lwState.topStick.value = stick
        lwState.isWin.value = stick.item != Item(emoji = deadEmoji)
    }

    fun showInventory() {
        lwState.showInventory.value = !lwState.showInventory.value
    }

}