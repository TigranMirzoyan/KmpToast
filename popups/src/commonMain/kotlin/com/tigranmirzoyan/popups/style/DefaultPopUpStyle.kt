package com.tigranmirzoyan.popups.style

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.tigranmirzoyan.popups.models.PopUpType

val LocalPopUpStyle = staticCompositionLocalOf<PopUpStyle> { DefaultPopUpStyle }

object DefaultPopUpStyle : PopUpStyle {
    override fun backgroundColor(type: PopUpType): Color = when (type) {
        PopUpType.Success -> Color(0xFF4CAF50)
        PopUpType.Warning -> Color(0xFFFF9800)
        PopUpType.Error -> Color(0xFFF44336)
        PopUpType.Info -> Color(0xFF2196F3)
        PopUpType.Default -> Color(0xFF222222)
    }

    override fun icon(type: PopUpType): ImageVector? = when (type) {
        PopUpType.Success -> Icons.Filled.CheckCircle
        PopUpType.Warning -> Icons.Filled.Warning
        PopUpType.Error -> Icons.Filled.Error
        PopUpType.Info -> Icons.Filled.Info
        PopUpType.Default -> null
    }
}