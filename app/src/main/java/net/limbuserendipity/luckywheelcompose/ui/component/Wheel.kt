package net.limbuserendipity.luckywheelcompose.ui.component

import android.graphics.Paint
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import net.limbuserendipity.luckywheelcompose.empty.Stick
import net.limbuserendipity.luckywheelcompose.ui.theme.lwHandRed
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
    ) {

        val stickSize = 360f / sticks.size
        val topStickIndex = ((360f - (angle % 360f)) / stickSize).toInt() % sticks.size
        onTopStick(sticks[topStickIndex])

        Canvas(
            modifier = Modifier
                .size(if (maxWidth < maxHeight) maxWidth else maxHeight)
        ) {
            rotate(
                degrees = 270f,
                pivot = center
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

            drawCenterCircle(
                color = lwHandRed,
                radius = maxWidth.value * 0.1f
            )

            val markerSize = Size(
                width = maxWidth.value * 0.3f,
                height = maxHeight.value * 0.2f
            )

            translate(
                left = center.y - markerSize.width / 2,
                top = 0f - markerSize.height * 0.30f
            ) {
                drawMarker(
                    color = lwHandRed,
                    size = markerSize
                )
            }

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
    strokeWidth: Float = 12f,
    strokeColorFun: (Stick) -> Color = { stick: Stick ->
        lerp(stick.color, Color.Black, 0.1f)
    }
) {

    val borderColors = sticks.map(strokeColorFun)

    borderColors.forEachIndexed { index, color ->
        drawArc(
            color = color,
            startAngle = startAngle + (stickSize * index),
            sweepAngle = stickSize,
            useCenter = true,
            style = Stroke(
                width = strokeWidth
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

fun DrawScope.drawCenterCircle(
    color: Color,
    radius: Float,
    borderWeight: Float = 8f,
    borderColor: Color = lerp(color, Color.Black, 0.1f)
) {
    drawCircle(
        color = color,
        radius = radius
    )

    drawCircle(
        color = borderColor,
        radius = radius,
        style = Stroke(borderWeight)
    )

}

fun DrawScope.drawMarker(
    color: Color,
    size: Size,
    borderWeight: Float = 8f,
    borderColor: Color = lerp(color, Color.Black, 0.1f)
) {
    val path: Path = Path()

    path.moveTo(0f, 0f)
    path.lineTo(size.width, 0f)
    path.lineTo(size.center.x, size.height)
    path.lineTo(0f, 0f)
    path.lineTo(size.width, 0f)

    drawPath(
        path = path,
        color = color
    )

    drawPath(
        path = path,
        color = borderColor,
        style = Stroke(
            width = borderWeight
        )
    )
}

fun DrawScope.drawBorderWheel() {

}
