package com.tigranmirzoyan.popups

import com.tigranmirzoyan.popups.models.PopUpDuration
import com.tigranmirzoyan.popups.models.PopUpMessage
import com.tigranmirzoyan.popups.models.PopUpType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

object PopUpManager {
    internal const val MAX_VISIBLE_POPUPS = 3
    private val _messages = MutableStateFlow<List<PopUpMessage>>(emptyList())
    internal val messages = _messages.asStateFlow()

    fun showMessage(
        text: String,
        duration: PopUpDuration = PopUpDuration.MEDIUM,
        type: PopUpType = PopUpType.Default
    ) {
        _messages.update { current ->
            current + PopUpMessage(text = text, duration = duration, type = type)
        }
    }

    internal fun dismissMessage(id: Long) {
        _messages.update { current ->
            current.filterNot { it.id == id }
        }
    }
}