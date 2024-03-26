package com.example.bill_compose_note

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

data class mItem(val name: String, val type: ItemType)

enum class ItemType {
    NORMAL, SPECIAL
}

val itemsList = listOf(
    mItem("Item 1", ItemType.NORMAL),
    mItem("Item 2", ItemType.NORMAL),
    mItem("Item 3", ItemType.SPECIAL),
    mItem("Item 4", ItemType.NORMAL),
    mItem("Item 5", ItemType.SPECIAL)
)


class mMainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ItemList(items = itemsList)
        }
    }
}

@Composable
fun ItemList(items: List<mItem>) {
    LazyColumn {
        items(items) { item ->
            when (item.type) {
                ItemType.NORMAL -> NormalListItem(item = item)
                ItemType.SPECIAL -> SpecialListItem(item = item)
            }
        }
    }
}

@Composable
fun NormalListItem(item: mItem) {
    Text(text = "Normal: ${item.name}")
}

@Composable
fun SpecialListItem(item: mItem) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Special: ${item.name}")
    }
}

@Preview
@Composable
fun PreviewItemList() {
    ItemList(items = itemsList)
}