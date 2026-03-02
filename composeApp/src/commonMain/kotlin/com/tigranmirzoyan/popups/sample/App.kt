package com.tigranmirzoyan.popups.sample

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tigranmirzoyan.popups.PopUpDuration
import com.tigranmirzoyan.popups.PopUpHost
import com.tigranmirzoyan.popups.PopUpManager

@Composable
@Preview
fun App() {
    PopUpHost(modifier = Modifier.padding(16.dp)) { CustomPopUP() }
}

@Composable
fun CustomPopUP() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(16.dp)
        ) {
            TestContent()
        }
    }
}

@Composable
fun TestContent() {
    Text(
        text = "Pop-up Library Tester",
        fontSize = 20.sp,
        modifier = Modifier.padding(bottom = 16.dp)
    )

    Button(onClick = {
        PopUpManager.showMessage("Saved successfully!", PopUpDuration.SHORT)
    }) {
        Text("Show SHORT Message")
    }

    Button(onClick = {
        PopUpManager.showMessage("Item added to your cart.", PopUpDuration.MEDIUM)
    }) {
        Text("Show MID Message")
    }

    Button(onClick = {
        PopUpManager.showMessage(
            text = "Connection failed. Please check your internet and try again later.",
            duration = PopUpDuration.LONG
        )
    }) {
        Text("Show LONG Message")
    }

    Button(onClick = {
        PopUpManager.showMessage("Notification 1", PopUpDuration.LONG)
        PopUpManager.showMessage("Notification 2", PopUpDuration.LONG)
        PopUpManager.showMessage("Notification 3", PopUpDuration.LONG)
        PopUpManager.showMessage("Notification 4 (Queued)", PopUpDuration.LONG)
        PopUpManager.showMessage("Notification 5 (Queued)", PopUpDuration.LONG)
    }) {
        Text("Spam Pop-ups (Test Queue)")
    }
}