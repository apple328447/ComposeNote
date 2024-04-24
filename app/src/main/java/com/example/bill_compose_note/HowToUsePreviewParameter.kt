package com.example.bill_compose_note

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp


/**
 * 這個是@PreviewParameter的用法
 * */
data class GreetingPreviewData(val name: String)

class CheckInProgressPreviewParameterProvider : PreviewParameterProvider<List<GreetingPreviewData>> {
    override val values: Sequence<List<GreetingPreviewData>> = sequenceOf(
        listOf(
            GreetingPreviewData("1"),
            GreetingPreviewData("2"),
            GreetingPreviewData("3"),
            GreetingPreviewData("4"),
        )
    )
}
@Composable
fun Greeting(name: String) {
    Text(text = "Hello, $name!", modifier = Modifier.padding(16.dp), color = Color.White)
}

@Preview(showBackground = true, backgroundColor = 0x000000)
@Composable
fun PreviewGreeting(@PreviewParameter(CheckInProgressPreviewParameterProvider::class) dataList: List<GreetingPreviewData>) {
    LazyColumn{
        items(dataList){item ->
            Greeting(name = item.name)
        }
    }
}