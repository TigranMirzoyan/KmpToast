package com.tigranmirzoyan.popups.sample

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tigranmirzoyan.popups.models.PopUpDuration
import com.tigranmirzoyan.popups.PopUpHost
import com.tigranmirzoyan.popups.PopUpManager
import com.tigranmirzoyan.popups.models.PopUpType

@Composable
@Preview
fun App() {
    PopUpHost(modifier = Modifier.padding(24.dp)) { CustomPopUP() }
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
            modifier = Modifier
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
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
        modifier = Modifier.padding(bottom = 8.dp)
    )

    Text("Test Types", fontSize = 16.sp, color = Color.Gray)

    Button(
        onClick = { PopUpManager.showMessage("Saved successfully!", PopUpDuration.MEDIUM, PopUpType.Success) },
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50)),
        modifier = Modifier.fillMaxWidth()
    ) {
        Text("Show SUCCESS")
    }

    Button(
        onClick = { PopUpManager.showMessage("Disk space is running low.", PopUpDuration.MEDIUM, PopUpType.Warning) },
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF9800)),
        modifier = Modifier.fillMaxWidth()
    ) {
        Text("Show WARNING")
    }

    Button(
        onClick = { PopUpManager.showMessage("Connection failed. Try again.", PopUpDuration.MEDIUM, PopUpType.Error) },
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF44336)),
        modifier = Modifier.fillMaxWidth()
    ) {
        Text("Show ERROR")
    }

    Button(
        onClick = { PopUpManager.showMessage("New update available to download.", PopUpDuration.MEDIUM, PopUpType.Info) },
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2196F3)),
        modifier = Modifier.fillMaxWidth()
    ) {
        Text("Show INFO")
    }

    Button(
        onClick = { PopUpManager.showMessage("Just a regular message.", PopUpDuration.MEDIUM, PopUpType.Default) },
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF222222)),
        modifier = Modifier.fillMaxWidth()
    ) {
        Text("Show DEFAULT")
    }

    HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

    Text("Test Durations", fontSize = 16.sp, color = Color.Gray)

    Button(
        onClick = { PopUpManager.showMessage("This is quick!", PopUpDuration.SHORT, PopUpType.Info) },
        modifier = Modifier.fillMaxWidth()
    ) {
        Text("Show SHORT Message")
    }

    Button(
        onClick = { PopUpManager.showMessage("This will stay a bit longer.", PopUpDuration.LONG, PopUpType.Warning) },
        modifier = Modifier.fillMaxWidth()
    ) {
        Text("Show LONG Message")
    }

    HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

    Text("Test Queue & Limits", fontSize = 16.sp, color = Color.Gray)

    Button(
        onClick = {
            PopUpManager.showMessage("Notification 1", PopUpDuration.LONG, PopUpType.Default)
            PopUpManager.showMessage("Notification 2 (Success)", PopUpDuration.LONG, PopUpType.Success)
            PopUpManager.showMessage("Notification 3 (Error)", PopUpDuration.LONG, PopUpType.Error)
            PopUpManager.showMessage("Notification 4 (Queued)", PopUpDuration.LONG, PopUpType.Warning)
            PopUpManager.showMessage("Notification 5 (Queued)", PopUpDuration.LONG, PopUpType.Info)
        },
        modifier = Modifier.fillMaxWidth()
    ) {
        Text("Spam Pop-ups (Test Queue)")
    }
}