package com.example.bill_compose_note.util

import android.util.Log

object ExcelFormatUtil {
    var addDateCategoryList:MutableList<String> = mutableListOf()
    fun initData():String {
        var filterData = ""
        var filterData2 = ""
        var filterData3:List<String> = listOf()
        var addDateList:MutableList<String> = mutableListOf()

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
                addDateList.add("$titleDate $data")
            }
        }

        filterType(addDateList)



        return filterData2

    }


    //分類帳款項目
    fun filterType(addDateList: MutableList<String>) {

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

        addDateList.forEach { data ->
            addDateCategoryList.add(data + " " + findCategoryByAccount(accountCategoryMap, data))
        }

        addDateCategoryList.forEach {data ->
            Log.v("Bill===看看結果","            ${data}")
        }


    }

    // 定義搜尋帳目對應類別的函數
    fun findCategoryByAccount(map: Map<String, List<String>>, account: String): String {

        // 遍歷 map，尋找包含該項目的類別
        for ((category, data) in map) {
            Log.v("Bill===檢查1.","            account：${account} ＝比對＝ ${data}")
            //TODO Bill 這個是OK的
//            data.forEach { aaa ->
//                if (account.contains(aaa)) {
//                    return category
//                }
//            }

            //TODO Bill 會失敗唷
//            if (data.contains(account)) {
//                return category
//            }

        }
        return "其他雜項支出" // 如果找不到該類別，返回 其他雜項支出
    }
}