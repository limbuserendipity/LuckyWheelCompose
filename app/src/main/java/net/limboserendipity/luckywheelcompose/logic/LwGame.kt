package net.limboserendipity.luckywheelcompose.logic

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import net.limboserendipity.luckywheelcompose.empty.Item
import net.limboserendipity.luckywheelcompose.empty.Stick
import net.limboserendipity.luckywheelcompose.ui.theme.getColors
import net.limboserendipity.luckywheelcompose.ui.theme.getEmoji
import kotlin.random.Random

class LwGame(
    val lwState: LwState
){

    init{
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

    fun finishedListener(value : Float){
        lwState.showDialog.value = !lwState.showDialog.value
    }

    fun onDragStopped(velocity : Float){
        lwState.angle.value = velocity
    }

    fun onDismissRequest(){
        lwState.showDialog.value = !lwState.showDialog.value
        lwState.enabled.value = true
        refreshWheel()
    }

    fun onTopStick(stick : Stick){
        lwState.topStick.value = stick
    }

}