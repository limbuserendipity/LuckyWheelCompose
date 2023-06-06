package net.limboserendipity.luckywheelcompose.ui.screen

import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import net.limboserendipity.luckywheelcompose.empty.Item
import net.limboserendipity.luckywheelcompose.logic.LwGame
import net.limboserendipity.luckywheelcompose.logic.rememberLwState
import net.limboserendipity.luckywheelcompose.ui.component.Hand
import net.limboserendipity.luckywheelcompose.ui.component.Wheel
import net.limboserendipity.luckywheelcompose.ui.local.LocalContentSpace

@Composable
fun LuckyWheelScreen() {

    val lwState = rememberLwState()
    val lwGame = remember{
        LwGame(lwState)
    }

    if(lwState.showDialog.value){
        ItemDialog(
            item = lwState.topStick.value.item,
            onDismissRequest = lwGame::onDismissRequest
        )
    }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {

        val animatedAngle = animateFloatAsState(
            targetValue = lwState.angle.value,
            animationSpec = tween(
                durationMillis = 3000
            ),
            finishedListener = lwGame::finishedListener
        )

        Wheel(
            angle = animatedAngle.value,
            sticks = lwState.sticks.value,
            onTopStick = lwGame::onTopStick
        )

        Spacer(modifier = Modifier.size(LocalContentSpace.current.large))

        Hand(
            enabled = lwState.enabled.value,
            onValue = { value ->

            },
            onDragStopped = lwGame::onDragStopped,
            modifier = Modifier.fillMaxWidth()
        )

    }

}

@Composable
fun ItemDialog(
    item: Item,
    onDismissRequest: () -> Unit,
    properties: DialogProperties = DialogProperties(),
    scaleInitialValue : Float = 1f,
    scaleTargetValue: Float = 0.6f,
    animationSpec : InfiniteRepeatableSpec<Float> = infiniteRepeatable(
        animation = tween(1000, easing = LinearEasing),
        repeatMode = RepeatMode.Reverse
    )
) {

    val infiniteTransition = rememberInfiniteTransition()

    val animatableScale = infiniteTransition.animateFloat(
        initialValue = scaleInitialValue,
        targetValue = scaleTargetValue,
        animationSpec = animationSpec
    )

    Dialog(
        onDismissRequest = onDismissRequest,
        properties = properties
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.scale(animatableScale.value)
        ) {
            Text(
                text = item.emoji,
                style = MaterialTheme.typography.h1
            )
        }
    }
}
