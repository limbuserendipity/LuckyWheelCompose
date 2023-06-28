package net.limbuserendipity.luckywheelcompose.ui.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import me.nikhilchaudhari.quarks.CreateParticles
import me.nikhilchaudhari.quarks.core.PI
import me.nikhilchaudhari.quarks.particle.*
import net.limbuserendipity.luckywheelcompose.ui.theme.*

@Composable
fun ConfettiParticle(
    x: Float,
    y: Float,
    modifier: Modifier = Modifier,
    velocity: Velocity = Velocity(xDirection = 1f, yDirection = 1f, randomize = true),
    force: Force = Force.Gravity(0.01f),
    acceleration: Acceleration = Acceleration(),
    particleSize: ParticleSize = ParticleSize.RandomSizes(10..30),
    particleColor: ParticleColor = ParticleColor.RandomColors(
        listOf(lwOrange, lwRed, lwBlue, lwBrown, lwAqua, lwYellow, lwGreen, lwPink, lwPurple)
    ),
    lifeTime: LifeTime = LifeTime(255f, 0.01f),
    emissionType: EmissionType = EmissionType.FlowEmission(
        maxParticlesCount = 300,
        emissionRate = 0.5f
    ),
    durationMillis: Int = 10 * 1000,
) {
    CreateParticles(
        x = x,
        y = y,
        velocity = velocity,
        force = force,
        acceleration = acceleration,
        particleSize = particleSize,
        particleColor = particleColor,
        lifeTime = lifeTime,
        emissionType = emissionType,
        durationMillis = durationMillis,
        modifier = modifier
    )
}

@Composable
fun BloodParticle(
    x: Float,
    y: Float,
    modifier: Modifier = Modifier,
    velocity: Velocity = Velocity(xDirection = 1f, yDirection = -15f, angle = PI, randomize = true),
    force: Force = Force.Gravity(0.2f),
    acceleration: Acceleration = Acceleration(0f, -4f),
    particleSize: ParticleSize = ParticleSize.RandomSizes(10..20),
    particleColor: ParticleColor = ParticleColor.RandomColors(
        listOf(lwHandRed, lwRed, Color.Black, Color.Red)
    ),
    lifeTime: LifeTime = LifeTime(255f, 1f),
    emissionType: EmissionType = EmissionType.FlowEmission(maxParticlesCount = 500),
    durationMillis: Int = 10 * 1000,
) {
    CreateParticles(
        x = x,
        y = y,
        velocity = velocity,
        force = force,
        acceleration = acceleration,
        particleSize = particleSize,
        particleColor = particleColor,
        lifeTime = lifeTime,
        emissionType = emissionType,
        durationMillis = durationMillis,
        modifier = modifier
    )
}