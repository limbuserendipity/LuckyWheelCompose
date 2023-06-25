package net.limboserendipity.luckywheelcompose.ui.component

import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.unit.dp
import net.limboserendipity.luckywheelcompose.empty.Stick
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun Wheel(
    angle: Float,
    sticks: List<Stick>,
    onTopStick: (Stick) -> Unit,
    modifier: Modifier = Modifier,
) {
    BoxWithConstraints(
        modifier = modifier
            .fillMaxWidth()
            .border(
                width = 4.dp,
                color = Color.Black,
                shape = CircleShape
            )
            .graphicsLayer {
                rotationZ = 270f
            }
            .clip(CircleShape)
            .background(
                color = MaterialTheme.colors.surface,
                shape = CircleShape
            )
    ) {

        val stickSize = 360f / sticks.size
        val topStickIndex = ((360f - (angle % 360f)) / stickSize).toInt() % sticks.size
        onTopStick(sticks[topStickIndex])

        Canvas(
            modifier = Modifier
                .size(maxWidth)
                .background(color = MaterialTheme.colors.surface)
                .padding(16.dp)
        ) {

            drawWheelSticks(
                sticks = sticks,
                startAngle = angle,
                stickSize = stickSize
            )

            drawBorderSticks(
                sticks = sticks,
                startAngle = angle,
                stickSize = stickSize
            )

            drawItemSticks(
                sticks = sticks,
                startAngle = angle,
                stickSize = stickSize
            )

        }
    }

}

fun DrawScope.drawSticks(
    sticks: List<Stick>,
    startAngle: Float,
    stickSize: Float,
    stickCenter: Float = stickSize / 2f
) {
    val iconSize = size.width * 0.10f
    val iconCenter = iconSize / 2f
    val iconTopLeft = size.width * 0.33f
    sticks.forEachIndexed { index, stick ->
        drawArc(
            color = stick.color,
            startAngle = startAngle + (stickSize * index),
            sweepAngle = stickSize,
            useCenter = true
        )
        drawArc(
            color = Color.Black,
            startAngle = startAngle + (stickSize * index),
            sweepAngle = stickSize,
            useCenter = true,
            style = Stroke(
                width = 4f
            )
        )
        val angle =
            Math.toRadians((startAngle + stickSize * index + stickCenter).toDouble())
                .toFloat()
        val paint = Paint().apply {
            textSize = iconSize
        }
        rotate(
            degrees = startAngle + stickSize * (index + 2),
            pivot = Offset(
                x = size.center.x + iconTopLeft * cos(angle),
                y = size.center.y + iconTopLeft * sin(angle)
            )
        ) {
            drawContext.canvas.nativeCanvas.drawText(
                stick.item.emoji,
                size.center.x + iconTopLeft * cos(angle) - iconCenter,
                size.center.y + iconTopLeft * sin(angle) + (iconCenter / 2f),
                paint
            )
        }
    }
}

fun DrawScope.drawWheelSticks(
    sticks: List<Stick>,
    startAngle: Float,
    stickSize: Float,
) {
    sticks.forEachIndexed { index, stick ->
        drawArc(
            color = stick.color,
            startAngle = startAngle + (stickSize * index),
            sweepAngle = stickSize,
            useCenter = true
        )
    }
}

fun DrawScope.drawBorderSticks(
    sticks: List<Stick>,
    startAngle: Float,
    stickSize: Float,
) {
    sticks.forEachIndexed { index, stick ->
        drawArc(
            color = Color.Black,
            startAngle = startAngle + (stickSize * index),
            sweepAngle = stickSize,
            useCenter = true,
            style = Stroke(
                width = 4f
            )
        )
    }
}

fun DrawScope.drawItemSticks(
    sticks: List<Stick>,
    startAngle: Float,
    stickSize: Float,
    stickCenter: Float = stickSize / 2f
) {

    val iconSize = size.width * 0.10f
    val iconCenter = iconSize / 2f
    val iconTopLeft = size.width * 0.33f

    sticks.forEachIndexed { index, stick ->
        val angle =
            Math.toRadians((startAngle + stickSize * index + stickCenter).toDouble())
                .toFloat()
        val paint = Paint().apply {
            textSize = iconSize
        }
        rotate(
            degrees = startAngle + stickSize * (index + 2),
            pivot = Offset(
                x = size.center.x + iconTopLeft * cos(angle),
                y = size.center.y + iconTopLeft * sin(angle)
            )
        ) {
            drawContext.canvas.nativeCanvas.drawText(
                stick.item.emoji,
                size.center.x + iconTopLeft * cos(angle) - iconCenter,
                size.center.y + iconTopLeft * sin(angle) + (iconCenter / 2f),
                paint
            )
        }
    }
}

fun DrawScope.drawBorderWheel() {

}
