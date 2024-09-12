package com.example.bill_compose_note

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.bill_compose_note.ui.theme.Bill_Compose_NoteTheme
import com.example.bill_compose_note.util.ExcelFormatUtil

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            Bill_Compose_NoteTheme {
//                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    Greeting("Android")
//                }
//            }

//            HazeApp() //模糊測試
//            LazyColumnWithNestedScrollInteropEnabled()//滑動效果（OK）
//            ColumnWithVerticalScrollInteropEnabled()//(OK)
//            NestedLazyColumns()
//            CreditCardSample()
//            TestCompositionLocalProvider()//測試取消overScroll effect效果
//            RemoveClickEffect()//取消點擊反饋動畫
//            TabView()//測試TabLayout

//            SimpleDialogExample() //測試彈窗

//            Greeting("")

        }

        setContent {
            Greeting2(ExcelFormatUtil.initData())
        }
    }
}

@Composable
fun Greeting2(name: String, modifier: Modifier = Modifier) {
    Text(
        text = name,
        modifier = modifier,
        fontSize = 24.sp,
        color = Color.Black, // 设置文本颜色为黑色
        textAlign = TextAlign.Start,
    )
}

//@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Bill_Compose_NoteTheme {
        Greeting("Android")
    }
}