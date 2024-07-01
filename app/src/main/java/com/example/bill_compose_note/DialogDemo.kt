package com.example.bill_compose_note

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@Preview
@Composable
fun SimpleDialogExample() {
    var showDialog by remember { mutableStateOf(true) }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Button(onClick = { showDialog = true }) {
            Text("Show Dialog")
        }

        if (showDialog) {
            MyDialog {
                showDialog = false
            }
        }
    }
}

@Composable
fun MyDialog(onDismiss: () -> Unit) {
    Dialog(
        onDismissRequest = { onDismiss() }, //如果有Scope就需要加()來呼叫 lamda的寫法
//        onDismissRequest = onDismiss,//不用加()
        properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = true)
    ) {
        Surface(
            shape = MaterialTheme.shapes.medium,
            tonalElevation = 1.dp,
            shadowElevation = 1.dp //數值越高，外匡正方形越明顯
        ) {
            //放你要的內容
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("This is a custom dialog")
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = onDismiss) {
                    Text("Close")
                }
            }
        }
    }
}

@Preview
@Composable
fun MyDialogPreview() {
    Dialog(
        onDismissRequest = { },
        properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = true)
    ) {
        Surface(
            shape = MaterialTheme.shapes.medium,
            tonalElevation = 24.dp,
            shadowElevation = 50.dp
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .height(180.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("This is a custom dialog")
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = { }) {
                    Text("Close")
                }
            }
        }
    }
}