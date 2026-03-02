package com.tigranmirzoyan.popups

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.tigranmirzoyan.popups.sample.App

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "KmpToast",
    ) {
        App()
    }
}