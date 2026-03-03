package com.tigranmirzoyan.popups

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tigranmirzoyan.popups.PopUpManager.MAX_VISIBLE_POPUPS
import com.tigranmirzoyan.popups.core.DefaultPopUp
import com.tigranmirzoyan.popups.core.PopUpItem
import com.tigranmirzoyan.popups.models.PopUpMessage
import com.tigranmirzoyan.popups.style.DefaultPopUpStyle
import com.tigranmirzoyan.popups.style.LocalPopUpStyle
import com.tigranmirzoyan.popups.style.PopUpStyle

@Composable
fun PopUpHost(
    modifier: Modifier = Modifier,
    maxVisibleMessages: Int = MAX_VISIBLE_POPUPS,
    style: PopUpStyle = DefaultPopUpStyle,
    popupContent: @Composable (PopUpMessage) -> Unit = { DefaultPopUp(it) },
    content: @Composable () -> Unit
) {
    val messages by PopUpManager.messages.collectAsStateWithLifecycle()
    val visibleMessages = messages.take(maxVisibleMessages)

    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        val screenWidthPx = constraints.maxWidth

        content()

        Column(
            modifier = modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .safeDrawingPadding(),
            verticalArrangement = Arrangement.spacedBy(PopUpDefaults.SpacingBetweenPopUps),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            CompositionLocalProvider(LocalPopUpStyle provides style) {
                visibleMessages.forEach { message ->
                    key(message.id) {
                        PopUpItem(
                            message = message,
                            screenWidthPx = screenWidthPx,
                            onDismiss = { PopUpManager.dismissMessage(message.id) },
                            popupContent = popupContent
                        )
                    }
                }
            }
        }
    }
}