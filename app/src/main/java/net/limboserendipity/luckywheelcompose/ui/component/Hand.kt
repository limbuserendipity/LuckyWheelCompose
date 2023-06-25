package net.limboserendipity.luckywheelcompose.ui.component

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import net.limboserendipity.luckywheelcompose.ui.local.LocalContentPadding
import net.limboserendipity.luckywheelcompose.ui.theme.handEmoji
import net.limboserendipity.luckywheelcompose.ui.theme.lwHandBlue
import net.limboserendipity.luckywheelcompose.ui.theme.lwHandRed
import kotlin.math.abs
import kotlin.math.roundToInt

@Composable
fun Hand(
    enabled: Boolean,
    onValue: (Float) -> Unit,
    onDragStopped: (Float) -> Unit,
    modifier: Modifier = Modifier,
    orientation: Orientation = Orientation.Horizontal,
    animationSpec: AnimationSpec<Float> = defaultAnimation,
    thumbSize: DpSize = DpSize(68.dp, 68.dp),
    thumb: @Composable BoxScope.(Modifier) -> Unit = { thumbModifier ->
        Thumb(
            modifier = thumbModifier
        ) {
            Text(
                text = handEmoji,
                style = MaterialTheme.typography.h4,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    },
) {

    var offset by remember { mutableStateOf(0f) }

    val animateOffset by animateFloatAsState(
        targetValue = offset,
        animationSpec = animationSpec
    )

    Surface(
        shape = MaterialTheme.shapes.medium,
        elevation = 1.dp,
        modifier = modifier.padding(LocalContentPadding.current.medium)
    ) {
        BoxWithConstraints(
            modifier = Modifier.padding(LocalContentPadding.current.medium)
        ) {
            val weight = with(LocalDensity.current) { maxWidth.toPx() }
            val size = with(LocalDensity.current) { thumbSize.width.toPx() }

            val state = rememberDraggableState(
                onDelta = { delta ->
                    offset = if (offset + delta > weight - size) weight - size
                    else offset + delta
                    onValue(offset)
                }
            )

            val thumbModifier = Modifier
                .offset { IntOffset(animateOffset.roundToInt(), 0) }
                .draggable(
                    state = state,
                    orientation = orientation,
                    enabled = enabled,
                    onDragStopped = { velocity ->
                        offset = 0f
                        onDragStopped(abs(velocity))
                    }
                )
                .size(thumbSize)

            thumb(thumbModifier)

        }
    }
}

@Composable
fun BoxScope.Thumb(
    modifier: Modifier = Modifier,
    color: Color = lwHandBlue,
    shape: Shape = MaterialTheme.shapes.medium ,
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = modifier
            .align(Alignment.CenterStart)
            .background(
                color = color,
                shape = shape
            )
    ) {
        content()
    }
}

private val defaultAnimation = spring<Float>()