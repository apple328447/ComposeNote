package com.example.bill_compose_note

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout

class ConstraintLayout {
    /**
     * ConstraintLayout的例子
     * */
    //@Preview
    @Composable
    fun ConstraintLayoutExample() {
        ConstraintLayout(
            modifier = Modifier.fillMaxSize()
        ) {
            // 定義佈局中的元件
            val (redBox, greenBox, blueBox, grayBox) = createRefs()

            // 定義紅色方塊的佈局約束
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .background(Color.Red)
                    .constrainAs(redBox) {
                        top.linkTo(parent.top, margin = 16.dp)
                        start.linkTo(parent.start, margin = 16.dp)
                    }
            )

            // 定義綠色方塊的佈局約束
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .background(Color.Green)
                    .constrainAs(greenBox) {
                        top.linkTo(redBox.bottom, margin = 16.dp)
                        start.linkTo(parent.start, margin = 16.dp)
                    }
            )

            // 定義藍色方塊的佈局約束
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .background(Color.Blue)
                    .constrainAs(blueBox) {
                        top.linkTo(parent.top, margin = 16.dp)
                        start.linkTo(greenBox.end, margin = 16.dp)
                    }
            )


            //定義灰色
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .background(Color.LightGray)
                    .constrainAs(grayBox) {
                        top.linkTo(greenBox.bottom, margin = 16.dp)
                        start.linkTo(greenBox.end, margin = 16.dp)
                    }
            )

        }
    }

    /**
     * Compose 沒辦法Preview Drawable裡面<shape>的圖片  https://stackoverflow.com/questions/66399056/how-to-load-shape-drawable-in-compose-image
     * */
    @Preview
    @Composable
    fun GradientBackground() {
        Box(
            modifier = Modifier
                .size(200.dp)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color(0xFFCFA060), Color(0xFFEDC084)),
                        startY = 0f,
                        endY = 500f,

                        ),
                    shape = RoundedCornerShape(16.dp)
                )
        ) {
            // Your content here
        }
    }
}

//CardView很難用 直接用Box.RoundedCornerShape(50.dp))
@Preview
@Composable
fun CardExample() {
    Card(
        elevation = CardDefaults.cardElevation(), // 设置卡片的阴影大小
        shape = MaterialTheme.shapes.medium//RoundedCornerShape(50)就可自訂  // 设置卡片的形状，可以是圆角
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Title")
            Text(text = "Content")
        }
    }
}