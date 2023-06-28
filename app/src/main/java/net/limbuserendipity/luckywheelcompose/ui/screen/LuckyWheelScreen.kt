package net.limbuserendipity.luckywheelcompose.ui.screen

import androidx.compose.animation.core.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.*
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import net.limbuserendipity.luckywheelcompose.empty.Item
import net.limbuserendipity.luckywheelcompose.logic.LwGame
import net.limbuserendipity.luckywheelcompose.logic.LwState
import net.limbuserendipity.luckywheelcompose.logic.rememberLwState
import net.limbuserendipity.luckywheelcompose.ui.component.*
import net.limbuserendipity.luckywheelcompose.ui.local.LocalContentPadding
import net.limbuserendipity.luckywheelcompose.ui.local.LocalContentSpace
import net.limbuserendipity.luckywheelcompose.ui.theme.inventoryEmoji
import net.limbuserendipity.luckywheelcompose.ui.theme.lwHandRed


@Composable
fun LuckyWheelScreen() {

    val lwState = rememberLwState()
    val lwGame = remember {
        LwGame(lwState)
    }

    if (lwState.showDialog.value) {
        ItemDialog(
            item = lwState.topStick.value.item,
            lwState.isWin.value,
            onDismissRequest = lwGame::onDismissRequest
        )
    }

    DropdownMenu(
        expanded = lwState.showInventory.value,
        onDismissRequest = lwGame::showInventory
    ) {
        DropdownMenuItem(onClick = {}) {
            FlowInventory(inventory = lwState.inventory.value)
        }
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
    BoxWithConstraints(
        contentAlignment = Alignment.Center,
        modifier = modifier
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
    isWin : Boolean,
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

    val animateScale = infiniteTransition.animateFloat(
        initialValue = scaleInitialValue,
        targetValue = scaleTargetValue,
        animationSpec = animationSpec
    )

    Dialog(
        onDismissRequest = onDismissRequest,
        properties = properties
    ) {
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(Unit) {
                    detectTapGestures {
                        onDismissRequest()
                    }
                }
        ) {
            if(isWin){
                DialogWinningContent(
                    item = item,
                    animateScale = animateScale
                )
            }else{
                DialogLosingContent(
                    item = item,
                    animateScale = animateScale
                )
            }
        }
    }
}
@Composable
fun BoxWithConstraintsScope.DialogWinningContent(
    item: Item,
    animateScale: State<Float>
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .align(Alignment.Center)
            .scale(animateScale.value)
    ) {
        Text(
            text = item.emoji,
            style = MaterialTheme.typography.h1
        )
    }

    with(LocalDensity.current){
        ConfettiParticle(
            x = maxWidth.toPx() / 2,
            y = 0f,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
fun BoxWithConstraintsScope.DialogLosingContent(
    item: Item,
    animateScale: State<Float>
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .align(Alignment.Center)
            .scale(animateScale.value)
    ) {
        Text(
            text = item.emoji,
            style = MaterialTheme.typography.h1
        )
        Spacer(modifier = Modifier.size(LocalContentSpace.current.medium))
        Text(
            text = "You lose",
            fontWeight = FontWeight.Bold,
            color = lwHandRed,
            style = MaterialTheme.typography.h2
        )
    }

    with(LocalDensity.current){
        BloodParticle(
            x = maxWidth.toPx() / 2,
            y = maxHeight.toPx(),
            modifier = Modifier.fillMaxSize()
        )
    }
}