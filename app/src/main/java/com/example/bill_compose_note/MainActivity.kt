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

        initData()
    }

    private fun initData() {
        var filterData = ""
        var filterData2 = ""
        var filterData3:List<String> = listOf()
       var aaa = "2023.08.01 星期二\n" +
               "22:25 徐銘昌 加油1048\n" +
               "全聯：日用雜貨900\n" +
               "市場：疏菜120\n" +
               "豬肉240\n" +
               "雞蛋120\n" +
               "\n" +
               "2023.08.04 星期五\n" +
               "14:49 皇太后 7月機車加油569、\n" +
               "14:49 皇太后 貓罐頭1100\n" +
               "14:49 皇太后 房屋火險1960\n" +
               "14:50 皇太后 機車保養560\n" +
               "2023.08.10 星期四\n" +
               "19:23 徐銘昌 全聯：日用雜貨900\n" +
               "雞蛋114\n" +
               "房貸12000\n" +
               "\n" +
               "19:52 皇太后 房貸3870\n" +
               "2023.08.15 星期二\n" +
               "11:08 徐銘昌 全聯：日用雜貨730\n" +
               "豬肉275\n" +
               "國保1186\n" +
               "2023.08.16 星期三\n" +
               "21:52 皇太后 電信費3280、貓罐1100、回診掛號200、加油95\n" +
               "2023.08.17 星期四\n" +
               "全聯：日用雜貨650\n" +
               "雞蛋+鹹蛋204\n" +
               "2023.08.18 星期五\n" +
               "10:07  8月雜支2000、電費1936（軒）"


        // 定義正則表達式，匹配「星期X」及其後面一個字
        val regex = Regex("星期[一二三四五六日][^\n]*\n")



        filterData =  aaa.replace("皇太后","").replace("徐銘昌","")
            .replace(Regex("\\b\\d{2}:\\d{2}\\b\\s*"), "\n")//消除時間
            .replace("、","\n")
            .replace("：","")
            .replace("元","")
            .replace(Regex("星期[一二三四五六日][^\n]*\n"), "")

        filterData2 = regex.replace(filterData,"") // .replace(Regex("星期[一二三四五六日][^\n]*\n"), "") 同一種寫法


        // 定義正則表達式，匹配「文字(中文) + 數字」
        val regex2 = Regex("([\\u4E00-\\u9FFF\\u3400-\\u4DBF\\uF900-\\uFAFF]+)(\\d+)")//Regex("([a-zA-Z]+)(\\d+)") ->這個是英文

        filterData3 = regex2.replace(filterData) { matchResult ->
            "${matchResult.groupValues[1]} ${matchResult.groupValues[2]}"
        }//篩選成項目+金額
            .split("\n") // 用換行符號轉成list
            .filter { it.isNotEmpty() }// 把只有換行符號的去除

        //TODo Bill 把時間加到前面
        var newTimeList = mutableListOf<String>()
        val timeRegex = Regex("\\b\\d{1,4}.\\b\\d{1,2}.\\b\\d{2}")
        var titleDate = "項目添加時間"
        filterData3.forEach { data ->
//            Log.v("Bill===讀取到的資料", data)
            if(timeRegex.containsMatchIn(data)){
                titleDate =(timeRegex.find(data)?.value?:"沒找到資料").replace(".","/")
                Log.d("Bill===抓到的日期", (timeRegex.find(data)?.value?:"沒找到資料").replace(".","/") + "支出：")
            }else{
                Log.v("Bill===","            ${titleDate} ${data}")
            }
        }


        setContent {
            Greeting2(filterData2)
        }
//        Log.v("Bill===","${filterData3}")

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