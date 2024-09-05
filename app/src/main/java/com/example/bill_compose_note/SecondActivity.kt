package com.example.bill_compose_note

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

class SecondActivity : ComponentActivity() {
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
            ColumnWithVerticalScrollInteropEnabled()//(OK)
//            NestedLazyColumns()
//            CreditCardSample()
//            TestCompositionLocalProvider()//測試取消overScroll effect效果
//            RemoveClickEffect()//取消點擊反饋動畫
//            TabView()//測試TabLayout
//            SimpleDialogExample() //測試彈窗
//            RemoveClickEffect()
        }
    }
}