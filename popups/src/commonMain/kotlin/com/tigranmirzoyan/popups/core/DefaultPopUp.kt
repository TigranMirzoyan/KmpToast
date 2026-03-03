package com.tigranmirzoyan.popups.core

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.tigranmirzoyan.popups.PopUpDefaults
import com.tigranmirzoyan.popups.style.LocalPopUpStyle
import com.tigranmirzoyan.popups.models.PopUpMessage

@Composable
fun DefaultPopUp(message: PopUpMessage) {
    val style = LocalPopUpStyle.current
    val backgroundColor = style.backgroundColor(message.type)
    val icon = style.icon(message.type)

    Box(
        modifier = Modifier
            .sizeIn(maxWidth = PopUpDefaults.MaxWidth, minHeight = PopUpDefaults.MinHeight)
            .fillMaxWidth()
            .background(
                backgroundColor.copy(alpha = PopUpDefaults.BACKGROUND_ALPHA),
                RoundedCornerShape(PopUpDefaults.CornerRadius)
            ),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            modifier = Modifier.padding(PopUpDefaults.InnerPadding),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            if (icon != null) {
                Icon(
                    imageVector = icon,
                    contentDescription = message.type.name,
                    tint = Color.White
                )
                Spacer(modifier = Modifier.width(PopUpDefaults.IconSpacing))
            }

            Text(
                text = message.text,
                color = Color.White,
                fontSize = PopUpDefaults.TextFontSize,
                maxLines = PopUpDefaults.TEXT_MAX_LINES
            )
        }
    }
}