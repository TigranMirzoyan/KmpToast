package com.tigranmirzoyan.popups.models

import com.tigranmirzoyan.popups.models.PopUpType
import kotlin.random.Random

data class PopUpMessage(
    internal val id: Long = Random.nextLong(),
    val text: String,
    val duration: PopUpDuration = PopUpDuration.MEDIUM,
    val type: PopUpType = PopUpType.Default
)