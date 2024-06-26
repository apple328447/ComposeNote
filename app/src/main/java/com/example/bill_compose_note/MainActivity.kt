package com.example.bill_compose_note

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.bill_compose_note.ui.theme.Bill_Compose_NoteTheme

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
            TabView()//測試TabLayout
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

//@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Bill_Compose_NoteTheme {
        Greeting("Android")
    }
}