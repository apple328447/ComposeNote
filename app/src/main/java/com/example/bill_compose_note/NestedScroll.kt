package com.example.bill_compose_note

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.rememberNestedScrollInteropConnection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * 實現NestedScrollView的效果
 * 第二層的RecyclerView一定要「設置高度」，不然會出現巢狀錯誤，除非Rv的方向跟最外面的ScrollView方向不同
 * 不可使用 Modifier.fillMaxHeight()、Modifier.fillMaxSize()
 * */

/**
 * LazyColumn使用「Modifier.nestedScroll」
 * */
//@Preview
@Composable
fun LazyColumnWithNestedScrollInteropEnabled() {
    LazyColumn(
        modifier = Modifier.nestedScroll(
            rememberNestedScrollInteropConnection()
        ),
        contentPadding = PaddingValues(top = 50.dp, bottom = 50.dp)//感覺也沒用
    ) {
        item {
            RowListView(listOf("111", "222", "333", "444"))
            Spacer(modifier = Modifier.height(16.dp)) //設置項目間的距離
        }
        item {
            ColumnListView(listOf("AAA", "BBB", "CCC"))
            Spacer(modifier = Modifier.height(16.dp))
        }
        item {
            Text("This is a Lazy Column")
        }
        items(40) { item ->
            Box(
                modifier = Modifier
                    .padding(16.dp)
                    .height(56.dp)
                    .fillMaxWidth()
                    .background(Color.Gray),
                contentAlignment = Alignment.Center
            ) {
                Text(item.toString())
            }
        }
    }
}

/**
 * Column使用「Modifier.verticalScroll」
 * */
@Preview
@Composable
fun ColumnWithVerticalScrollInteropEnabled() {
    Column(
        modifier = Modifier.verticalScroll(rememberScrollState()),
    ) {
        RowListView(listOf("111", "222", "333", "444", "555"))
        Spacer(modifier = Modifier.height(16.dp)) //設置項目間的距離
        ColumnListView(listOf("AAA", "BBB", "CCC", "DDD"))
        Spacer(modifier = Modifier.height(16.dp))
        Text("This is a Lazy Column")
        LazyColumn(modifier = Modifier.height(2000.dp)) { //這裡因為跟最外層的方向是一樣的，所以要設置高度
            items(40) { item ->
                Box(
                    modifier = Modifier
                        .padding(16.dp)
                        .height(56.dp)
                        .fillMaxWidth()
                        .background(Color.Gray),
                    contentAlignment = Alignment.Center
                ) {
                    Text(item.toString())
                }
            }
        }
    }
}


@Composable
fun ColumnListView(itemList: List<String>) {
    val height = 120 * itemList.size //自己算高度
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .height(height.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        items(itemList) { item ->
            Box(
                modifier = Modifier
                    .padding(bottom = 20.dp)
                    .size(100.dp)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(Color(0xFFCFA060), Color(0xFFEDC084)),
                            startY = 0f,
                            endY = 500f,
                        ),
                        shape = RoundedCornerShape(16.dp)
                    ),
                contentAlignment = Alignment.Center,
            ) {
                Text(item)
            }
        }
    }
}

@Composable
fun RowListView(itemList: List<String>) {
    val width = 120 * itemList.size //自己算高度
    LazyRow(
        modifier = Modifier
            .fillMaxSize(),//因為方向跟外層不一樣所以可以這樣用
//        .width(width.dp).wrapContentHeight(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        items(itemList) { item ->
            Box(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .size(100.dp)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(Color(0xFF148CEC), Color(0xFF00BCD4)),
                            startY = 0f,
                            endY = 500f,
                        ),
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center,
            ) {
                Text(item)
            }
        }
    }
}


/**
 * 使用LazyColumn包LazyColumn
 * https://youtu.be/1ANt65eoNhQ?t=898
 * */
@Composable
fun NestedLazyColumns() {
    LazyColumn {
        items(9) { outerIndex ->
            Text(text = "Outer Item $outerIndex")
            LazyColumn(modifier = Modifier.height(100.dp)) {
                items(3) { innerIndex ->
                    Text(text = "Inner Item $innerIndex of Outer Item $outerIndex")
                }
            }
        }
    }
}
