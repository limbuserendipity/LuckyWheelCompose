package net.limbuserendipity.luckywheelcompose.ui.screen

import android.annotation.SuppressLint
import android.graphics.ComposeShader
import android.graphics.RuntimeShader
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.*
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import net.limbuserendipity.luckywheelcompose.empty.Item
import net.limbuserendipity.luckywheelcompose.logic.LwGame
import net.limbuserendipity.luckywheelcompose.logic.LwState
import net.limbuserendipity.luckywheelcompose.logic.rememberLwState
import net.limbuserendipity.luckywheelcompose.ui.component.FlowInventory
import net.limbuserendipity.luckywheelcompose.ui.component.Hand
import net.limbuserendipity.luckywheelcompose.ui.component.Wheel
import net.limbuserendipity.luckywheelcompose.ui.local.LocalContentPadding
import net.limbuserendipity.luckywheelcompose.ui.theme.inventoryEmoji


@Composable
fun LuckyWheelScreen() {

    val lwState = rememberLwState()
    val lwGame = remember {
        LwGame(lwState)
    }

    if (lwState.showDialog.value) {
        ItemDialog(
            item = lwState.topStick.value.item,
            onDismissRequest = lwGame::onDismissRequest
        )
    }

    Scaffold(
        topBar = {
            ActionItem(
                emoji = inventoryEmoji,
                onClick = lwGame::showInventory
            )
        },
        bottomBar = {
            Hand(
                enabled = lwState.enabled.value,
                onValue = { value ->

                },
                onDragStopped = lwGame::onDragStopped,
                modifier = Modifier.fillMaxWidth()
            )
        }
    ) { paddingValues ->
        LuckyWheelContent(
            lwState = lwState,
            lwGame = lwGame,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        )
    }
}

@Composable
fun LuckyWheelContent(
    lwState: LwState,
    lwGame: LwGame,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {


        DropdownMenu(
            expanded = lwState.showInventory.value,
            onDismissRequest = lwGame::showInventory
        ) {
            DropdownMenuItem(onClick = {  }) {
                FlowInventory(inventory = lwState.inventory.value)
            }
        }

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
            onTopStick = lwGame::onTopStick,
            modifier = Modifier
                .padding(LocalContentPadding.current.medium)
                .fillMaxWidth()
        )

    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ActionItem(
    emoji: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    size: DpSize = DpSize(64.dp, 64.dp),
    paddingValues: PaddingValues = LocalContentPadding.current.medium,
    enabled: Boolean = true,
    shape: Shape = RectangleShape,
    color: Color = MaterialTheme.colors.background,
    contentColor: Color = contentColorFor(color),
    border: BorderStroke? = null,
    elevation: Dp = 1.dp,
) {
    Surface(
        onClick = onClick,
        enabled = enabled,
        shape = shape,
        color = color,
        contentColor = contentColor,
        border = border,
        elevation = elevation,
        modifier = modifier.size(size)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = modifier.padding(paddingValues)
        ) {
            Text(
                text = emoji,
                style = MaterialTheme.typography.h4
            )
        }
    }
}

@Composable
fun ItemDialog(
    item: Item,
    onDismissRequest: () -> Unit,
    properties: DialogProperties = DialogProperties(),
    scaleInitialValue: Float = 1f,
    scaleTargetValue: Float = 0.6f,
    animationSpec: InfiniteRepeatableSpec<Float> = infiniteRepeatable(
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
