package com.tigranmirzoyan.popups

import kotlin.random.Random

enum class PopUpDuration(val millis: Long) {
    SHORT(2000L),
    MEDIUM(4000L),
    LONG(7000L)
}

data class PopUpMessage(
    internal val id: Long = Random.nextLong(),
    val text: String,
    val duration: PopUpDuration = PopUpDuration.MEDIUM
)