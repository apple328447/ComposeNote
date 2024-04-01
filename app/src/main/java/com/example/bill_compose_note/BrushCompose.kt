package com.example.bill_compose_note

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

/**
 * 文字漸變色：https://blog.csdn.net/qq_36072270/article/details/136851199 ->這寫法被淘汰了
 * */

//@Preview
@Composable
fun GradientTextPreview() {
    GradientText()
}

/**
 * 背景使用Brush漸層變色
 * */
@Composable
fun GradientText() {
    val gradientBrush = Brush.horizontalGradient(
        colors = listOf(Color.Red, Color.Yellow, Color.Green),
        startX = 10f,
        endX = 100f
    )

    Box(
        modifier = Modifier.fillMaxSize().background(brush = gradientBrush),
        contentAlignment = androidx.compose.ui.Alignment.Center
    ) {
        Text(
            text = "Gradient Text",
            fontSize = 24.sp,
            color = Color.Black, // 设置文本颜色为黑色
            textAlign = TextAlign.Center,
            modifier = Modifier.background(brush = gradientBrush)
        )
    }
}

/**
 * TextStyle有使用 「color」就不能用「brush」了，會被判斷成不同 function，一個是使用單色一個是使用複數顏色
 * */
@Preview
@Composable
fun BrushDemo() {
    Text(
        "Brush is awesome\nBrush is awesome\nBrush is awesome",
        style = TextStyle(
            brush = Brush.verticalGradient( //這是從上到下漸層
//                colors = listOf(Color(0xFFFFFFFF), Color(0xFFD7D7D7)),
                colors = listOf(Color(0xFFFFFFFF), Color(0xFF9C27B0)),
                tileMode = TileMode.Mirror
            ),
            fontSize = 30.sp
        )
    )
}