package com.tigranmirzoyan.popups.style

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.tigranmirzoyan.popups.models.PopUpType

interface PopUpStyle {
    fun backgroundColor(type: PopUpType): Color
    fun icon(type: PopUpType): ImageVector?
}