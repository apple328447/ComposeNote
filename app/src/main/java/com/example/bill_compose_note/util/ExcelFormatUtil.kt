package com.example.bill_compose_note.util

import android.util.Log
/**
 * 錯誤格式：
 * 1. 全聯：雜貨食品
 *   1420         ->全聯：雜貨食品1420
 *
 * 2. 直接貼照片
 *
 * 3. 2包水餃256 -> 水餃2包256
 *
 * 4. 衛生紙，水果，水餃，697元 -> 衛生紙+水果+水餃+697元  豬肉，排骨620  -> 豬肉+排骨620
 *
 * 5. wakamoto,840 -> wakamoto840
 *
 * 6. 水果50肥料100青菜90全聯490 -> 要加換行符號
 * */
object ExcelFormatUtil {
    var addDateCategoryList:MutableList<String> = mutableListOf()

    //帳目分類
    val accountCategoryMap:Map<String,List<String>> = mapOf(
        "家用品" to listOf("全聯","日用雜貨","日用品","沐浴乳","衛生紙","牙膏","雜貨"),
        "食材" to listOf("奶粉","麥片","米","菜","市場","蛋","肉","魚","醬","瓜","食品雜貨","水果","香蕉"),
        "貓咪" to listOf("賓賓","貓","喵喵","咪咪"),
        "貸款" to listOf("貸款","貸","款"),
        "醫療費" to listOf("急診","診","針","酸痛貼布","麻醉","身心","科","掛號","牙醫","醫","大腸鏡","天成醫院"),
        "電信費用" to listOf("第四台","電信","南桃園","網路費"),
        "車費" to listOf("車費","機車","加油","電瓶","車保養","汽車"),
        "保險" to listOf("保險","險")
    )

    fun initData():String {
        var filterText:List<String>
        var filterText2:MutableList<String> = mutableListOf()

        val textAndNumList:List<String>
        val dateTextAndNumList:MutableList<String> = mutableListOf()

        val originalData = "2023.08.01 星期二\n" +
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
                "19:46 徐銘昌 全聯：日用雜貨650\n" +
                "雞蛋+鹹蛋204\n" +
                "2023.08.18 星期五\n" +
                "10:07 皇太后 8月雜支2000、電費1936（軒）\n" +
                "2023.08.21 星期一\n" +
                "19:39 皇太后 你確認ㄧ下投保內容，沒需要修改就要繳費了\n" +
                "19:39 皇太后 圖片\n" +
                "2023.08.25 星期五\n" +
                "19:26 徐銘昌 全聯：日用雜貨水果1501\n" +
                "壽險8704\n"

        filterText =  originalData.replace("皇太后","").replace("徐銘昌","").replace("峰","")//消除使用者的名稱
            .replace(Regex("星期[一二三四五六日][^\n]*\n"), "")
            .replace(Regex("\\b\\d{2}:\\d{2}\\b\\s*"), "\n")//ex.14:49 -> "\n" 消除時間
            .replace("+","加")
            .replace("＋","加")
            .replace("、","\n")
            .replace("：","")
            .replace("元","")
            .replace("。","\n")
            .replace("  ","\n")
            .replace("，","\n")
            .replace(Regex("[!@#$%&*]"),"")
            .replaceMonth()
            .split("\n") // 用換行符號把資料型別轉成list
            .filter { it.isNotEmpty() }// 移除空資料 把只有換行符號的去除

        // 定義正則表達式，匹配「星期X」及其後面一個字
//        var filterData2 = ""
//        val regex = Regex("星期[一二三四五六日][^\n]*\n")
//        filterData2 = regex.replace(filterData,"") // .replace(Regex("星期[一二三四五六日][^\n]*\n"), "") 同一種寫法

        Log.v("Bill===>>>檢查 - 第一次篩選玩的字串","${filterText}")

        // 定義正則表達式，匹配「文字(中文) + 數字」//Regex("([a-zA-Z]+)(\\d+)") ->這個是英文
       // val regexTextNum = Regex("([\\u4E00-\\u9FFF\\u3400-\\u4DBF\\uF900-\\uFAFF]+\\d+)") // 這個是連在一起 中文數字 (先塞選出這種格式) 主要是處理：「豬肉240雞蛋114」這種偷懶的格式
        val regexTextNum = Regex("^(.*?)(\\d+)$")
        filterText.forEach {  text ->


            if (regexTextNum.matches(text)) {
                var aaaa: String = regexTextNum.replace(text){ matchResult ->
                    val description = matchResult.groupValues[1] // 取出描述部分
                    val amount = matchResult.groupValues[2] // 取出數字部分
                    "$amount $description" // 返回格式化後的結果
                }
                filterText2.add(aaaa)
                println("格式正確")
            } else {
                filterText2.add(text)
                println("格式不正確")
            }
        }

/*


        filterText = regexTextNum.replace(filterText) { matchResult ->
            "${matchResult.groupValues[1]}\n"
        }
        Log.v("Bill===>>>檢查 - 第二次篩選  (中文) + 數字  字串","${filterText}")

        val regex = Regex("[\\u4e00-\\u9fa5]+[0-9]+[\\u4e00-\\u9fa5]+[0-9]+")
        filterText = regex.replace(filterText) { matchResult ->

            Log.d("Bill===>>>檢查 - 中文數字中文數字 ","${matchResult.groupValues[0]} / ${matchResult.groupValues[1]} / ${matchResult.groupValues[2]}")
            "${matchResult.groupValues[1]}\n"

        }


        Log.d("Bill===>>>檢查查filterText2","${filterText}")
        val regexTextAndNum = Regex("([\\u4E00-\\u9FFF\\u3400-\\u4DBF\\uF900-\\uFAFF]+)(\\d+)") //這個是分段 中文「＋」數字 (才能把中間插入空白)
        textAndNumList = regexTextAndNum.replace(filterText) { matchResult ->
            "${matchResult.groupValues[1]} ${matchResult.groupValues[2]}"
        }//篩選成項目+金額
            .split("\n") // 用換行符號把資料型別轉成list
            .filter { it.isNotEmpty() }// 移除空資料 把只有換行符號的去除
*/


        filterText2.forEach {
            Log.v("Bill===>>>檢查查filterData3","${it}")
        }


        //TODo Bill 把時間加到前面
        val timeRegex = Regex("\\b\\d{1,4}.\\b\\d{1,2}.\\b\\d{2}")
        var titleDate = "項目添加時間"
        filterText2.forEach { data ->
            if(timeRegex.containsMatchIn(data)){
                titleDate =(timeRegex.find(data)?.value?:"沒找到資料").replace(".","/")
                Log.d("Bill===抓到的日期", (timeRegex.find(data)?.value?:"沒找到資料").replace(".","/") + "支出：")
            }else{
                Log.v("Bill===","            ${titleDate} ${data}")
                dateTextAndNumList.add("$titleDate $data")
            }
        }
        filterType(dateTextAndNumList)
        return "filterText2"
    }

    //分類帳款項目
    /**
     * \\b：匹配單詞邊界，確保時間是獨立的詞，防止錯誤匹配其他字符。  有加的話 \\bWord\\b  只有符合「Word」才會符合條件，如果是abcWord就會被判斷失敗
     * \\d{1,2}：匹配 1 或 2 位數字（表示小時）。
     * :：匹配時間中的冒號。
     * \\d{2}：匹配 2 位數字（表示分鐘）。
     * 這個正則表達式可以匹配任何「HH」格式的時間（例如 10:07, 8:30）。
     *
     *
     * */
    private fun filterType(addDateList: MutableList<String>) {
        Log.i("====比對資料====","======================開始比對資料======================")
        addDateList.forEach { data ->
            addDateCategoryList.add(data + " " + findCategoryByAccount(accountCategoryMap, data))
        }

        addDateCategoryList.forEach {data ->
            Log.v("Bill===看看結果","            ${data}")
        }




        // 這個是最後輸出的格式： ex.2023/08/15 豬肉 275 食材
        val regexDateTextNumCategory = Regex("\\b\\d{4}/\\d{1,2}/\\d{1,2}\\b \\b\\d+\\b \\b[\\u4E00-\\u9FFF\\u3400-\\u4DBF\\uF900-\\uFAFF]+\\b \\b[\\u4E00-\\u9FFF\\u3400-\\u4DBF\\uF900-\\uFAFF]+\\b")
        // 這個是最後輸出的格式： ex.2024/03/15 180 紅粄6個 其他雜項支出
        val regexDateTextNumCategoryWithUnit = Regex("\\b\\d{4}/\\d{1,2}/\\d{1,2}\\b \\b\\d+\\b \\b[\\u4E00-\\u9FFF\\u3400-\\u4DBF\\uF900-\\uFAFF]+\\d+[\\u4E00-\\u9FFF\\u3400-\\u4DBF\\uF900-\\uFAFF]+\\b \\b[\\u4E00-\\u9FFF\\u3400-\\u4DBF\\uF900-\\uFAFF]+\\b")

        val regex = Regex("\\d{4}/\\d{1,2}/\\d{1,2} [\\u4E00-\\u9FFF\\u3400-\\u4DBF\\uF900-\\uFAFF]+ \\d+ [\\u4E00-\\u9FFF\\u3400-\\u4DBF\\uF900-\\uFAFF]+")

        val correctFormatList = addDateCategoryList.filter { regexDateTextNumCategory.matches(it) || regexDateTextNumCategoryWithUnit.matches(it) }.toMutableList()
        val errorFormatList = addDateCategoryList.filterNot { regexDateTextNumCategory.matches(it) || regexDateTextNumCategoryWithUnit.matches(it)  }.toMutableList()
        Log.i("===輸出結果===","======================開始輸出結果======================")

        Log.d("===輸出結果===","正確的格式資料：")


        correctFormatList.forEach {
            Log.d("===輸出結果===","${it}")
        }
        Log.e("===輸出結果===","錯誤的格式資料：")
        errorFormatList.forEach {
            Log.e("===輸出結果===","${it}")
        }
    }

    // 定義搜尋帳目對應類別的函數
    private fun findCategoryByAccount(map: Map<String, List<String>>, account: String): String {
        // 遍歷 map，尋找包含該項目的類別
        for ((category, keyWordList) in map) {
            keyWordList.forEach { keyWord ->
                if (account.contains(keyWord)) {
                    Log.v("====比對資料====","帳目：${account.split(" ")[1]} \nKey Word：${keyWordList}")
                    Log.v("====比對資料====","分類：${category}")
                    return category
                }
            }
        }
        return "其他雜項支出" // 如果找不到該類別，返回 其他雜項支出
    }

    private fun String.replaceMonth(): String {
        // 建立數字到中文月份的映射表
        val monthMap = mapOf(
            "1" to "一", "2" to "二", "3" to "三", "4" to "四", "5" to "五",
            "6" to "六", "7" to "七", "8" to "八", "9" to "九", "10" to "十",
            "11" to "十一", "12" to "十二"
        )

        // 正則表達式匹配數字月份，假設月份格式是「X月」或「XX月」
        val regex = Regex("(\\d{1,2})月")

        // 使用 replace 方法進行替換，根據 monthMap 映射表
        return regex.replace(this) { matchResult ->
            val monthNumber = matchResult.groupValues[1]  // 取出數字部分
            val chineseMonth = monthMap[monthNumber] ?: monthNumber  // 查表替換
            "${chineseMonth}月"  // 返回中文月份格式
        }
    }
}