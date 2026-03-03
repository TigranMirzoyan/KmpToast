package com.tigranmirzoyan.popups.core

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.tigranmirzoyan.popups.models.PopUpMessage
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.abs
import kotlin.math.roundToInt


@Composable
internal fun PopUpItem(
    message: PopUpMessage,
    screenWidthPx: Int,
    onDismiss: () -> Unit,
    popupContent: @Composable (PopUpMessage) -> Unit
) {
    val transitionState = remember { MutableTransitionState(false) }
    val offsetX = remember { Animatable(0f) }
    val scope = rememberCoroutineScope()

    val density = LocalDensity.current
    val dismissThreshold = remember(screenWidthPx, density) {
        val proportional = screenWidthPx * 0.1f
        val maxThresholdPx = with(density) { 100.dp.toPx() }

        proportional.coerceAtMost(maxThresholdPx)
    }

    LaunchedEffect(Unit) {
        transitionState.targetState = true
        delay(message.duration.millis)
        transitionState.targetState = false
    }

    LaunchedEffect(transitionState.currentState, transitionState.isIdle) {
        if (!transitionState.targetState && !transitionState.currentState && transitionState.isIdle) {
            onDismiss()
        }
    }

    AnimatedVisibility(
        visibleState = transitionState,
        enter = slideInVertically(initialOffsetY = { it }) +
                fadeIn() +
                expandVertically(expandFrom = Alignment.Top),
        exit = slideOutVertically(targetOffsetY = { -it }) +
                fadeOut() +
                shrinkVertically(shrinkTowards = Alignment.Bottom)
    ) {
        Box(
            modifier = Modifier
                .offset { IntOffset(offsetX.value.roundToInt(), 0) }
                .pointerInput(Unit) {
                    detectHorizontalDragGestures(
                        onDragEnd = {
                            if (abs(offsetX.value) > dismissThreshold) {
                                scope.launch {
                                    val targetOffset = if (offsetX.value > 0) {
                                        screenWidthPx.toFloat()
                                    } else {
                                        -screenWidthPx.toFloat()
                                    }
                                    offsetX.animateTo(targetOffset)
                                    transitionState.targetState = false
                                }
                            } else {
                                scope.launch { offsetX.animateTo(0f) }
                            }
                        }
                    ) { change, dragAmount ->
                        change.consume()
                        val newOffset = offsetX.value + dragAmount
                        scope.launch { offsetX.snapTo(newOffset) }
                    }
                }
        ) {
            popupContent(message)
        }
    }
}